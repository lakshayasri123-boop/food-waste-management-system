package com.fooddonation.repository;
import com.fooddonation.model.NGO;
import com.fooddonation.model.User;
import com.fooddonation.model.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface NGORepository extends JpaRepository<NGO, Long> {
    Optional<NGO> findByUser(User user);
    Optional<NGO> findByUserId(Long userId);
    List<NGO> findByVerificationStatus(VerificationStatus status);
}
