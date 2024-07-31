package org.simpleform.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.simpleform.Entity.User;
import org.simpleform.Repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/wel")
    public String getWelcomeMessage() {
        return "welcome to login app";
    }

//    @PostMapping("/login")
//    public String loginUser(@ModelAttribute User user, Model model) {
//        User existingUser = userRepository.findByUsername(user.getUsername());
//        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
//            return "welcome";
//        }
//        model.addAttribute("error", "Invalid username or password");
//        return "login";
//    }
@PostMapping("/login")
public String loginUser(String username, HttpSession session) {
    session.setAttribute("username", username);
    return "redirect:/welcome";
}

    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "welcome";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            model.addAttribute("error", "Username already taken");
            return "register";
        }
        System.out.println("Username: " + user.getUsername()); // Debugging
        System.out.println("Password: " + user.getPassword()); // Debugging

        userRepository.save(user);
        return "registration-success";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/register";
    }

}



