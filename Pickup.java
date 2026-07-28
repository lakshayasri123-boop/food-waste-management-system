package com.fooddonation.model;
import jakarta.persistence.*;
@Entity
@Table(name = "pickups")
public class Pickup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pickup_id")
    private Long pickupId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "donation_id", referencedColumnName = "donation_id", nullable = false)
    private Donation donation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ngo_id", referencedColumnName = "ngo_id", nullable = false)
    private NGO ngo;
  @Enumerated(EnumType.STRING)
    @Column(name = "pickup_status", nullable = false)
    private PickupStatus pickupStatus = PickupStatus.SCHEDULED;
    public Pickup() {}
    public Pickup(Donation donation, NGO ngo, PickupStatus pickupStatus) {
        this.donation = donation;
        this.ngo = ngo;
        this.pickupStatus = pickupStatus;
    }
    public Long getPickupId() {
        return pickupId;
    }
    public void setPickupId(Long pickupId) {
        this.pickupId = pickupId;
    }
    public Donation getDonation() {
        return donation;
    }
   public void setDonation(Donation donation) {
        this.donation = donation;
    }
    public NGO getNgo() {
        return ngo;
    }
    public void setNgo(NGO ngo) {
        this.ngo = ngo;
    }
    public PickupStatus getPickupStatus() {
        return pickupStatus;
    }
    public void setPickupStatus(PickupStatus pickupStatus) {
        this.pickupStatus = pickupStatus;
    }
}


 
