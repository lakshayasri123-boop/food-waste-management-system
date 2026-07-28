package com.fooddonation.controller;
import com.fooddonation.model.Role;
import com.fooddonation.model.User;
import com.fooddonation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;
@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
   @GetMapping("/")
    public String index(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            return redirectUserByRole(loggedInUser);
        }
        return "login";
    }
    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        Optional<User> userOpt = userService.loginUser(email, password);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            session.setAttribute("user", user);
            return redirectUserByRole(user);
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email address or password.");
            return "redirect:/login";
        }
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            return redirectUserByRole(loggedInUser);
        }
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") User user,
                                  @RequestParam(value = "orgOrNgoName", required = false) String orgOrNgoName,
                                  RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user, orgOrNgoName);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please log in.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have been logged out successfully.");
        return "redirect:/login";
    }
    private String redirectUserByRole(User user) {
        if (user.getRole() == Role.DONOR) {
            return "redirect:/donor/dashboard";
        } else if (user.getRole() == Role.NGO) {
            return "redirect:/ngo/dashboard";
        } else if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/";
    }
}
