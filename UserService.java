package com.fooddonation.service;
import com.fooddonation.model.*;
import com.fooddonation.repository.DonorRepository;
import com.fooddonation.repository.NGORepository;
import com.fooddonation.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final DonorRepository donorRepository;
    private final NGORepository ngoRepository;
    public UserService(UserRepository userRepository, DonorRepository donorRepository, NGORepository ngoRepository) {
        this.userRepository = userRepository;
        this.donorRepository = donorRepository;
        this.ngoRepository = ngoRepository;
    }
  @Transactional
    public User registerUser(User user, String orgOrNgoName) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email address is already registered.");
        }
        User savedUser = userRepository.save(user);
        if (user.getRole() == Role.DONOR) {
            Donor donor = new Donor(savedUser, orgOrNgoName != null && !orgOrNgoName.isBlank() ? orgOrNgoName : savedUser.getName());
            donorRepository.save(donor);
        } else if (user.getRole() == Role.NGO) {
            NGO ngo = new NGO(savedUser, orgOrNgoName != null && !orgOrNgoName.isBlank() ? orgOrNgoName : savedUser.getName(), VerificationStatus.PENDING);
            ngoRepository.save(ngo);
        }
        return savedUser;
    }
    public Optional<User> loginUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password));
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<Donor> findDonorByUser(User user) {
        return donorRepository.findByUser(user);
    }
    public Optional<NGO> findNGOByUser(User user) {
        return ngoRepository.findByUser(user);
    }
}

