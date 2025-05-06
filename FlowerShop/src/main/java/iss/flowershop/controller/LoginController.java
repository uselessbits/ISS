package iss.flowershop.controller;

import iss.flowershop.model.Role;
import iss.flowershop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccessHandler() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
                return "redirect:/admin";
            } else if (auth.getAuthorities().contains(new SimpleGrantedAuthority("CUSTOMER"))) {
                return "redirect:/client";
            }
        }

        // Default fallback
        return "redirect:/";
    }
}
