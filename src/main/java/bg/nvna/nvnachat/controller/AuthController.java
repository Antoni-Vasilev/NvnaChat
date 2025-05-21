package bg.nvna.nvnachat.controller;

import bg.nvna.nvnachat.dto.auth.AuthLoginRequest;
import bg.nvna.nvnachat.dto.auth.AuthLoginResponse;
import bg.nvna.nvnachat.dto.auth.AuthRegisterRequest;
import bg.nvna.nvnachat.dto.auth.AuthRegisterResponse;
import bg.nvna.nvnachat.exception.DuplicateRecordException;
import bg.nvna.nvnachat.exception.NotFoundException;
import bg.nvna.nvnachat.exception.UnauthorizedException;
import bg.nvna.nvnachat.model.User;
import bg.nvna.nvnachat.service.SessionService;
import bg.nvna.nvnachat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;
    private final SessionService sessionService;

    @Autowired
    public AuthController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthRegisterResponse> register(@RequestBody AuthRegisterRequest request) {
        // Проверявал дали имейла съществува
        if (userService.isEmailExist(request.getEmail())) {
            throw new DuplicateRecordException("Email already exist");
        }

        // Запазвам и връшам информацията на потребителя
        User registeredUser = userService.register(request);
        return ResponseEntity.ok(new AuthRegisterResponse(
                registeredUser.getEmail(),
                "User registered successfully"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {
        // Проверявам дали потребителя съществува
        if (!userService.isEmailExist(request.getEmail())) {
            throw new NotFoundException("Email not exist");
        }

        // Проверявам дали паролата въведена от потребителя е правилна
        User findUser = userService.findByEmail(request.getEmail());
        if (request.getPassword().equals(findUser.getPassword())) {
            return ResponseEntity.ok(new AuthLoginResponse(
                    findUser.getEmail(),
                    sessionService.createSession(findUser).getId(),
                    "Login successfully"
            ));
        } else {
            throw new UnauthorizedException("Invalid password");
        }
    }
}
