package com.despereaux.springauth.controller;

import com.despereaux.springauth.dto.LoginRequestDto;
import com.despereaux.springauth.dto.SignupRequestDto;
import com.despereaux.springauth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto) { // @model Attribute 생략 가능
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res) { // @model Attribute 생략 가능
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error"; // ?error 추가
        }

        return "redirect:/";
    }
}
