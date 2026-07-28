package com.fooddonation.repository;
import com.fooddonation.model.Donor;
import com.fooddonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByUser(User user);
    Optional<Donor> findByUserId(Long userId);
}
