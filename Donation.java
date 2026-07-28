       package com.fooddonation.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long donationId;
    @Column(name = "food_name", nullable = false)
    private String foodName;
    @Column(name = "food_type", nullable = false)
    private String foodType;
    @Column(nullable = false)
   private String quantity;
    @Column(name = "expiry_time", nullable = false)
    private LocalDateTime expiryTime;
    @Column(name = "pickup_address", nullable = false)
    private String pickupAddress;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationStatus status = DonationStatus.AVAILABLE;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "donor_id", referencedColumnName = "donor_id", nullable = false)
    private Donor donor;
    public Donation() {}
    public Donation(String foodName, String foodType, String quantity, LocalDateTime expiryTime, String pickupAddress, DonationStatus status, Donor donor) {
      this.foodName = foodName;
        this.foodType = foodType;
        this.quantity = quantity;
        this.expiryTime = expiryTime;
        this.pickupAddress = pickupAddress;
        this.status = status;
        this.donor = donor;
    }
    public Long getDonationId() {
        return donationId;
    }
    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getFoodType() {
        return foodType;
    }
    public void setFoodType(String foodType) {
      this.foodType = foodType;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }
    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
    public String getPickupAddress() {
        return pickupAddress;
    }
    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }
    public DonationStatus getStatus() {
        return status;
    }
    public void setStatus(DonationStatus status) {
        this.status = status;
    }
    public Donor getDonor() {
        return donor;
    }
    public void setDonor(Donor donor) {
        this.donor = donor;
    }
}
