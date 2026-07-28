package com.fooddonation.service;
import com.fooddonation.model.*;
import com.fooddonation.repository.DonationRepository;
import com.fooddonation.repository.PickupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class NGOService {
    private final DonationRepository donationRepository;
    private final PickupRepository pickupRepository;
    public NGOService(DonationRepository donationRepository, PickupRepository pickupRepository) {
        this.donationRepository = donationRepository;
        this.pickupRepository = pickupRepository;
    }
    @Transactional
    public Pickup acceptDonation(Long donationId, NGO ngo) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("Donation not found"));
        if (donation.getStatus() != DonationStatus.AVAILABLE) {
            throw new IllegalStateException("Donation is not available for claim.");
        }
        donation.setStatus(DonationStatus.ACCEPTED);
        donationRepository.save(donation);
       Pickup pickup = new Pickup(donation, ngo, PickupStatus.SCHEDULED);
        return pickupRepository.save(pickup);
    }
    @Transactional
    public Pickup updatePickupStatus(Long pickupId, PickupStatus newStatus) {
        Pickup pickup = pickupRepository.findById(pickupId)
                .orElseThrow(() -> new IllegalArgumentException("Pickup record not found"));
        pickup.setPickupStatus(newStatus);
        if (newStatus == PickupStatus.DELIVERED) {
            Donation donation = pickup.getDonation();
            donation.setStatus(DonationStatus.DELIVERED);
            donationRepository.save(donation);
        }
        return pickupRepository.save(pickup);
    }
    public List<Pickup> getPickupsForNGO(NGO ngo) {
        return pickupRepository.findByNgoNgoIdOrderByPickupIdDesc(ngo.getNgoId());
    }
    public Optional<Pickup> getPickupByDonationId(Long donationId) {
        return pickupRepository.findByDonationDonationId(donationId);
    }
}
