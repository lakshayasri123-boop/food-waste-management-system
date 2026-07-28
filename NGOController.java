package com.fooddonation.controller;
import com.fooddonation.model.*;
import com.fooddonation.service.DonationService;
import com.fooddonation.service.NGOService;
import com.fooddonation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
@Controller
@RequestMapping("/ngo")
public class NGOController {
    private final NGOService ngoService;
    private final DonationService donationService;
  private final UserService userService;
    public NGOController(NGOService ngoService, DonationService donationService, UserService userService) {
        this.ngoService = ngoService;
        this.donationService = donationService;
        this.userService = userService;
    }
    @GetMapping("/dashboard")
    public String ngoDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.NGO) {
            return "redirect:/login";
        }
        NGO ngo = userService.findNGOByUser(loggedInUser)
                .orElseThrow(() -> new IllegalStateException("NGO profile not found for user."));
        List<Donation> availableDonations = donationService.findAvailableDonations();
        List<Pickup> myPickups = ngoService.getPickupsForNGO(ngo);
       model.addAttribute("user", loggedInUser);
        model.addAttribute("ngo", ngo);
        model.addAttribute("availableDonations", availableDonations);
        model.addAttribute("myPickups", myPickups);
        return "ngo-dashboard";
    }
    @PostMapping("/accept/{donationId}")
    public String acceptDonation(@PathVariable("donationId") Long donationId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.NGO) {
            return "redirect:/login";
        }
        NGO ngo = userService.findNGOByUser(loggedInUser)
                .orElseThrow(() -> new IllegalStateException("NGO profile not found."));
        if (ngo.getVerificationStatus() != VerificationStatus.VERIFIED) {
            redirectAttributes.addFlashAttribute("error", "Your NGO account is pending admin verification. You cannot accept donations yet.");
            return "redirect:/ngo/dashboard";
        }
        try {
            ngoService.acceptDonation(donationId, ngo);
            redirectAttributes.addFlashAttribute("success", "Donation accepted! Pickup scheduled.");
        } catch (Exception e) {
           redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/ngo/dashboard";
    }
    @PostMapping("/update-pickup/{pickupId}")
    public String updatePickupStatus(@PathVariable("pickupId") Long pickupId,
                                     @RequestParam("status") PickupStatus newStatus,
                                     HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.NGO) {
            return "redirect:/login";
        }
        try {
            ngoService.updatePickupStatus(pickupId, newStatus);
            redirectAttributes.addFlashAttribute("success", "Pickup status updated to " + newStatus);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/ngo/dashboard";
    }
}
