package org.example.online_qa_web_advance1.controller;

import org.example.online_qa_web_advance1.model.User;
import org.example.online_qa_web_advance1.service.CaptchaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/login", params = "type=captcha")
    public void captchaImage(HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session) throws ServletException, IOException {
        String captcha = captchaService.generateCaptcha();
        session.setAttribute("captcha", captcha);
        response.setContentType("image/jpeg");

        ImageIO.write(captchaService.generateCaptchaImage(captcha), "JPEG", response.getOutputStream());
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String captcha,
                        HttpSession session,
                        Model model) {
        String sessionCaptcha = (String) session.getAttribute("captcha");

        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(captcha)) {
            model.addAttribute("error", "验证码错误");
            return "login";
        }

        User user = new User(username);
        session.setAttribute("user", user);
        session.removeAttribute("captcha");

        return "redirect:/topics";
    }
}