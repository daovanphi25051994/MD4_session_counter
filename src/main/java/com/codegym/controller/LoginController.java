package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class LoginController {

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("/login")
    public ModelAndView moveToLoginPage(@CookieValue(value = "email", defaultValue = "") String email) {
        Cookie emailCookie = new Cookie("email", email);
        ModelAndView modelAndView = new ModelAndView("login-form");
        modelAndView.addObject("emailCookie", emailCookie);
        return modelAndView;
    }

    @PostMapping("login")
    public ModelAndView login(@ModelAttribute("user") User user, @CookieValue(value = "email", defaultValue = "") String email, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("login-form");
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("123456")) {
            if (user.getEmail() != null) {
                email = user.getEmail();
                Cookie cookie = new Cookie("email", email);
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
                Cookie[] cookies = request.getCookies();
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("email")) {
                        modelAndView.addObject("emailCookie", ck);
                        return modelAndView;
                    }
                }
                cookie.setValue("");
                modelAndView.addObject("emailCookie", cookie);
                modelAndView.addObject("message", "Login success. Welcome  ");
            }
        } else {
            Cookie cookie = new Cookie("email", email);
            modelAndView.addObject("emailCookie", cookie);
            modelAndView.addObject("message", "Login failed. Try again.");
        }
        return modelAndView;


    }
}