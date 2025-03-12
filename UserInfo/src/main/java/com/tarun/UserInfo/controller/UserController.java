package com.tarun.UserInfo.controller;

import com.tarun.UserInfo.model.User;
import com.tarun.UserInfo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/register")
    public String register(Model theModel){
        User user = new User();
        theModel.addAttribute("user",user);
        return "register-form";
    }

    @PostMapping("/processForm")
    public String processForm(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register-form";
        }
        //Check if the username already exists
        Optional<User> existingUser = userService.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage","Username already taken!");
            return "redirect:/register";
        }
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successMessage","Sign Up Successful");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String processLogin(Model model){
        model.addAttribute("user",new User());
        return "login-form";

    }

    @PostMapping("/processLogin")
    public String processLogin(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login-form";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Login Successful!");
        return "success"; // Redirect to home page after login
    }


}
