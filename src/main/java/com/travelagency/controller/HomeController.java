package com.travelagency.controller;

import com.travelagency.entity.User;
import com.travelagency.entity.Role;
import com.travelagency.entity.Gender;
import com.travelagency.service.UserService;
import com.travelagency.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private VilleService villeService;
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("villes", villeService.findAll());
        model.addAttribute("genders", Gender.values());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam String role) {
        if (userService.existsByUsername(user.getUsername())) {
            return "redirect:/register?error=username";
        }
        
        if (userService.existsByEmail(user.getEmail())) {
            return "redirect:/register?error=email";
        }
        
        Role userRole = role.equals("ADMIN") ? Role.ADMIN : Role.CLIENT;
        user.setRoles(Set.of(userRole));
        
        userService.save(user);
        return "redirect:/login?success";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/client/dashboard";
        }
    }
}
