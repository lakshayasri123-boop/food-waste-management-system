package com.fooddonation.service;
import com.fooddonation.model.*;
import com.fooddonation.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final NGORepository ngoRepository;
    private final DonorRepository donorRepository;
    private final DonationRepository donationRepository;
    private final PickupRepository pickupRepository;
    public AdminService(UserRepository userRepository, NGORepository ngoRepository, DonorRepository donorRepository, DonationRepository donationRepository, PickupRepository pickupRepository) {
        this.userRepository = userRepository;
        this.ngoRepository = ngoRepository;
        this.donorRepository = donorRepository;
        this.donationRepository = donationRepository;
        this.pickupRepository = pickupRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
  public List<NGO> getAllNGOs() {
        return ngoRepository.findAll();
    }
    public List<NGO> getPendingNGOs() {
        return ngoRepository.findByVerificationStatus(VerificationStatus.PENDING);
    }
  @Transactional
    public NGO verifyNGO(Long ngoId, VerificationStatus status) {
        NGO ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new IllegalArgumentException("NGO not found"));
        ngo.setVerificationStatus(status);
        return ngoRepository.save(ngo);
    }
    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user.getRole() == Role.DONOR) {
            donorRepository.findByUser(user).ifPresent(donorRepository::delete);
        } else if (user.getRole() == Role.NGO) {
            ngoRepository.findByUser(user).ifPresent(ngoRepository::delete);
        }
        userRepository.delete(user);
    }
    public Map<String, Object> generatePlatformReport() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalDonors", donorRepository.count());
        stats.put("totalNGOs", ngoRepository.count());
        stats.put("totalDonations", donationRepository.count());
        stats.put("availableDonations", donationRepository.findByStatus(DonationStatus.AVAILABLE).size());
        stats.put("deliveredDonations", donationRepository.findByStatus(DonationStatus.DELIVERED).size());
        stats.put("expiredDonations", donationRepository.findByStatus(DonationStatus.EXPIRED).size());
        stats.put("totalPickups", pickupRepository.count());
        return stats;
    }
}
