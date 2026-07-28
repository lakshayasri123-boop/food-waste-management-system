package com.fooddonation.repository;
import com.fooddonation.model.NGO;
import com.fooddonation.model.Pickup;
import com.fooddonation.model.PickupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface PickupRepository extends JpaRepository<Pickup, Long> {
    List<Pickup> findByNgo(NGO ngo);
    Optional<Pickup> findByDonationDonationId(Long donationId);
    List<Pickup> findByNgoNgoIdOrderByPickupIdDesc(Long ngoId);
    List<Pickup> findByPickupStatus(PickupStatus status);
}
