package com.credsure.controller;

import com.credsure.entity.User;
import com.credsure.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.regex.Pattern;

@Controller
public class AuthController {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/auth", "/login", "/register"})
    public String authPage(
            @RequestParam(required = false) String mode,
            HttpServletRequest request,
            Model model) {

        if (mode == null || mode.isBlank()) {
            mode = request.getRequestURI().endsWith("/register")
                    ? "register"
                    : "login";
        }

        model.addAttribute("mode",
                "register".equalsIgnoreCase(mode) ? "register" : "login");
        return "auth";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            RedirectAttributes attributes) {

        String cleanName = name == null ? "" : name.trim();
        String cleanEmail = email == null ? "" :
                email.trim().toLowerCase(Locale.ROOT);

        if (cleanName.length() < 2 || cleanName.length() > 100) {
            return registrationError(attributes,
                    "Name must contain 2 to 100 characters.",
                    cleanName, cleanEmail);
        }

        if (cleanEmail.length() > 254
                || !EMAIL_PATTERN.matcher(cleanEmail).matches()) {
            return registrationError(attributes,
                    "Enter a valid email address.",
                    cleanName, cleanEmail);
        }

        if (password == null
                || password.length() < 8
                || password.getBytes(StandardCharsets.UTF_8).length > 72) {
            return registrationError(attributes,
                    "Password must be at least 8 characters and no more than 72 UTF-8 bytes.",
                    cleanName, cleanEmail);
        }

        if (!password.equals(confirmPassword)) {
            return registrationError(attributes,
                    "Passwords do not match.",
                    cleanName, cleanEmail);
        }

        if (userRepository.existsByEmailIgnoreCase(cleanEmail)) {
            return registrationError(attributes,
                    "An account with this email already exists.",
                    cleanName, cleanEmail);
        }

        User user = new User();
        user.setName(cleanName);
        user.setEmail(cleanEmail);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");

        try {
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException exception) {
            return registrationError(attributes,
                    "This email may already be registered.",
                    cleanName, cleanEmail);
        }

        attributes.addFlashAttribute(
                "success", "Account created successfully. Please sign in.");
        return "redirect:/auth?mode=login";
    }

    private String registrationError(
            RedirectAttributes attributes,
            String message,
            String name,
            String email) {

        attributes.addFlashAttribute("error", message);
        attributes.addFlashAttribute("name", name);
        attributes.addFlashAttribute("email", email);
        return "redirect:/auth?mode=register";
    }
}
