package bg.nvna.nvnachat.controller;

import bg.nvna.nvnachat.dto.message.MessageGetRequest;
import bg.nvna.nvnachat.dto.message.MessageGetResponse;
import bg.nvna.nvnachat.dto.message.MessageSendRequest;
import bg.nvna.nvnachat.model.Message;
import bg.nvna.nvnachat.service.MessageService;
import bg.nvna.nvnachat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MessageController {

    private final SessionService sessionService;
    private final MessageService messageService;

    @Autowired
    public MessageController(SessionService sessionService, MessageService messageService) {
        this.sessionService = sessionService;
        this.messageService = messageService;
    }

    @PostMapping()
    public ResponseEntity<String> sendMessage(
            @RequestBody MessageSendRequest request,
            @RequestHeader("Authorization") String token
    ) {
        messageService.sendMessage(new Message(null, request.getMessage(), new Date(), sessionService.findSessionById(token).getUser()));
        return ResponseEntity.ok("Message sent successfully");
    }

    @PostMapping("/get")
    public ResponseEntity<List<MessageGetResponse>> getMessages(@RequestBody MessageGetRequest request, @RequestHeader("Authorization") String token) {
        List<Message> messages;
        if (request.getCurrentDate() == null) {
            messages = messageService.getOldMessages(new Date());
        } else {
            if (request.isGetOldMessages()) messages = messageService.getOldMessages(request.getCurrentDate());
            else messages = messageService.getNewMessages(request.getCurrentDate());
        }

        String myUsername = sessionService.findSessionById(token).getUser().getUsername();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        List<MessageGetResponse> responses = new ArrayList<>();
        messages.forEach(it -> responses.add(new MessageGetResponse(it.getSender().getUsername(), it.getMessage(), simpleDateFormat.format(it.getCreatedAt()), it.getSender().getUsername().equals(myUsername))));
        return ResponseEntity.ok(responses);
    }
}
