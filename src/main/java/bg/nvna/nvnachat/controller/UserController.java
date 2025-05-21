package bg.nvna.nvnachat.controller;

import bg.nvna.nvnachat.dto.user.UserSaveScoreRequest;
import bg.nvna.nvnachat.dto.user.UserScoreListResponse;
import bg.nvna.nvnachat.dto.user.UserScoreResponse;
import bg.nvna.nvnachat.model.User;
import bg.nvna.nvnachat.service.SessionService;
import bg.nvna.nvnachat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public UserController(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @PostMapping("/scores")
    public ResponseEntity<UserScoreResponse> scores(@RequestHeader("Authorization") String token) {
        User findUser = sessionService.findSessionById(token).getUser();
        List<User> users = userService.findAll();

        List<UserScoreListResponse> scores = users.stream()
                .map(it -> new UserScoreListResponse(it.getId(), it.getUsername(), it.getBestScore()))
                .filter(it -> it.getScore() > 0)
                .collect(Collectors.toCollection(ArrayList::new)); // mutable list

        AtomicInteger i = new AtomicInteger(1);
        scores = scores.stream()
                .map(it -> new UserScoreListResponse(it.getId(), i.getAndIncrement() + ". " + it.getUsername(), it.getScore()))
                .collect(Collectors.toCollection(ArrayList::new)); // mutable list again

        UserScoreListResponse user = scores.stream()
                .filter(it -> it.getId().equals(findUser.getId()))
                .findFirst()
                .orElse(null);

        if (user != null) {
            scores.removeIf(it -> it.getId().equals(user.getId()));
        }

        UserScoreResponse response = new UserScoreResponse(
                user.getUsername(),
                user.getScore(),
                scores
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/scores/save")
    public ResponseEntity<String> saveScore(@RequestBody UserSaveScoreRequest request, @RequestHeader("Authorization") String token) {
        User findUser = sessionService.findSessionById(token).getUser();
        if (request.getScore() > findUser.getBestScore()) {
            findUser.setBestScore(request.getScore());
        }
        userService.update(findUser);

        return ResponseEntity.ok("Score saved successfully");
    }
}
