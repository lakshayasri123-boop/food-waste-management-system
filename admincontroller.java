
package com.fooddonation.controller;
import com.fooddonation.model.Role;
import com.fooddonation.model.User;
import com.fooddonation.model.VerificationStatus;
import com.fooddonation.service.AdminService;
import com.fooddonation.service.DonationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final DonationService donationService;
    public AdminController(AdminService adminService, DonationService donationService) {
        this.adminService = adminService;
        this.donationService = donationService;
    }
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("user", loggedInUser);
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("ngos", adminService.getAllNGOs());
        model.addAttribute("pendingNgos", adminService.getPendingNGOs());
        model.addAttribute("reports", adminService.generatePlatformReport());
        return "admin-dashboard";
    }
    @PostMapping("/verify-ngo/{ngoId}")
    public String verifyNGO(@PathVariable("ngoId") Long ngoId,
                            @RequestParam("status") VerificationStatus status,
                            HttpSession session,
