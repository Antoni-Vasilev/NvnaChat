package bg.nvna.nvnachat.service.impl;

import bg.nvna.nvnachat.model.Message;
import bg.nvna.nvnachat.repository.MessageRepository;
import bg.nvna.nvnachat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static bg.nvna.nvnachat.Extensions.convertCurrentDateToUTC;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> getOldMessages(Date currentDate) {
        return messageRepository.findMessagesByCreatedAtBefore(convertCurrentDateToUTC(currentDate));
    }

    @Override
    public List<Message> getNewMessages(Date currentDate) {
        return messageRepository.findMessagesByCreatedAtAfter(convertCurrentDateToUTC(currentDate));
    }
}
