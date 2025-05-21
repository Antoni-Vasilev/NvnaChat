package bg.nvna.nvnachat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/tetris")
    public String tetris() {
        return "tetris";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/scoreboard")
    public String scoreboard() {
        return "scoreboard";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/tetris";
    }
}
