package com.fooddonation.repository;
import com.fooddonation.model.Donation;
import com.fooddonation.model.DonationStatus;
import com.fooddonation.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonor(Donor donor);
    List<Donation> findByStatus(DonationStatus status);
    List<Donation> findByStatusAndExpiryTimeBefore(DonationStatus status, LocalDateTime now);
    List<Donation> findByDonorDonorIdOrderByDonationIdDesc(Long donorId);
}
