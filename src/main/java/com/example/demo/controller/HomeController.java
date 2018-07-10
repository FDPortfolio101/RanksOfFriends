package com.example.demo.controller;
import com.example.demo.model.AppUser;
import com.example.demo.model.AppUserRepository;
import com.example.demo.model.UserRole;
import com.example.demo.model.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    AppUserRepository users;

    @Autowired
    UserRoleRepository roles;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/login") public String showLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String regiter(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("user") AppUser user, BindingResult result, Model model) {

        AppUser currentUser = ((AppUser)result.getModel().get("user"));

        if(users.existsByUsername(currentUser.getUsername())){
            model.addAttribute("usernameErr", users.existsByUsername(currentUser.getUsername()));
            return "register";
        }

        UserRole role = new UserRole("USER");
        roles.save(role);

        user.addRole(role);
        users.save(user);
        return "redirect:/";
    }
}
