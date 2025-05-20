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

import java.util.List;

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

        users.removeIf(it -> it.getId().equals(findUser.getId()) || it.getBestScore() == 0);

        UserScoreResponse response = new UserScoreResponse(
                findUser.getUsername(),
                findUser.getBestScore(),
                users.stream().map(it -> new UserScoreListResponse(it.getUsername(), it.getBestScore())).toList()
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
