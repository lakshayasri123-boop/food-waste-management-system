package com.fooddonation.controller;
import com.fooddonation.model.Donation;
import com.fooddonation.model.Donor;
import com.fooddonation.model.Role;
import com.fooddonation.model.User;
import com.fooddonation.service.DonationService;
import com.fooddonation.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequestMapping("/donor")
  public class DonationController {
    private final DonationService donationService;
    private final UserService userService;
    public DonationController(DonationService donationService, UserService userService) {
        this.donationService = donationService;
        this.userService = userService;
    }
    @GetMapping("/dashboard")
    public String donorDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.DONOR) {
            return "redirect:/login";
        }
        Donor donor = userService.findDonorByUser(loggedInUser)
                .orElseThrow(() -> new IllegalStateException("Donor profile not found for user."));
        List<Donation> donations 
              @PostMapping("/edit/{id}")
    public String updateDonation(@PathVariable("id") Long id,
                                 @RequestParam("foodName") String foodName,
                                 @RequestParam("foodType") String foodType,
                                 @RequestParam("quantity") String quantity,
                                 @RequestParam("expiryTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime expiryTime,
                                 @RequestParam("pickupAddress") String pickupAddress,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.DONOR) {
            return "redirect:/login";
        }
        Donation donation = new Donation();
        donation.setDonationId(id);
        donation.setFoodName(foodName);
        donation.setFoodType(foodType);
        donation.setQuantity(quantity);
        donation.setExpiryTime(expiryTime);
        donation.setPickupAddress(pickupAddress);
        donationService.updateDonation(donation);
        redirectAttributes.addFlashAttribute("success", "Donation updated successfully!");
        return "redirect:/donor/dashboard";
    }
    @PostMapping("/delete/{id}")
    public String deleteDonation(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null || loggedInUser.getRole() != Role.DONOR) {
            return "redirect:/login";
        }
        donationService.deleteDonation(id);
        redirectAttributes.addFlashAttribute("success", "Donation deleted successfully!");
        return "redirect:/donor/dashboard";
    }
}
