package com.fooddonation.service;
import com.fooddonation.model.Donation;
import com.fooddonation.model.DonationStatus;
import com.fooddonation.model.Donor;
import com.fooddonation.repository.DonationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class DonationService {
    private final DonationRepository donationRepository;
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }
    public Donation createDonation(Donation donation) {
        donation.setStatus(DonationStatus.AVAILABLE);
        return donationRepository.save(donation);
    }
          existing.setPickupAddress(donationDetails.getPickupAddress());
        
        return donationRepository.save(existing);
    }
    public void deleteDonation(Long donationId) {
        donationRepository.deleteById(donationId);
    }
    public Optional<Donation> findById(Long donationId) {
        return donationRepository.findById(donationId);
    }
    public List<Donation> findDonationsByDonor(Donor donor) {
        return donationRepository.findByDonorDonorIdOrderByDonationIdDesc(donor.getDonorId());
    }
    public List<Donation> findAvailableDonations() {
        return donationRepository.findByStatus(DonationStatus.AVAILABLE);
    }
    public List<Donation> findAllDonations() {
        return donationRepository.findAll();
    }
    /**
     * Automated Expiry Monitoring
     * Runs every 1 minute to transition past-expiry AVAILABLE donations to EXPIRED status
     */
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void monitorAndExpireDonations() {
        LocalDateTime now = LocalDateTime.now();
        List<Donation> expiredList = donationRepository.findByStatusAndExpiryTimeBefore(DonationStatus.AVAILABLE, now);
        for (Donation donation : expiredList) {
          donation.setStatus(DonationStatus.EXPIRED);
            donationRepository.save(donation);
        }
    }
}

          
          
