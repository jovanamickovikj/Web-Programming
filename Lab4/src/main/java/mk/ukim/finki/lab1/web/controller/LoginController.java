package mk.ukim.finki.lab1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied.html";
    }
}