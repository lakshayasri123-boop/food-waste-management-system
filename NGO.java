package com.fooddonation.model;
import jakarta.persistence.*;
@Entity
@Table(name = "ngos")
public class NGO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ngo_id")
    private Long ngoId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @Column(name = "ngo_name", nullable = false)
    private String ngoName;
   @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;
    public NGO() {}
    public NGO(User user, String ngoName, VerificationStatus verificationStatus) {
        this.user = user;
        this.ngoName = ngoName;
        this.verificationStatus = verificationStatus;
    }
    public Long getNgoId() {
        return ngoId;
    }
    public void setNgoId(Long ngoId) {
        this.ngoId = ngoId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getNgoName() {
        return ngoName;
    }
   public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }
    public VerificationStatus getVerificationStatus() {
        return verificationStatus;
    }
    public void setVerificationStatus(VerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}
