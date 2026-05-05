package booking;

import vehicles.LuxuryVehicle;
import members.Member;

public class Booking {

    private LuxuryVehicle vehicle;
    private Member customer;
    private int rentalHours;
    private String deliveryAddress;
    private boolean isReturned;

    public Booking(Member customer, LuxuryVehicle vehicle, int rentalHours, String deliveryAddress) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalHours = rentalHours;
        this.deliveryAddress = deliveryAddress;
        this.isReturned = false;

        if (!vehicle.isAvailable()) {
            throw new IllegalStateException("Vehicle is not available!");
        }

        if (!vehicle.canBeRentedBy(customer)) {
            throw new IllegalStateException("Customer membership is not high enough for this vehicle!");
        }

        

        vehicle.setAvailable(false);
    }

    public void returnVehicle() {
        if (isReturned) {
            throw new IllegalStateException("Vehicle already returned!");
        }
        isReturned = true;
        vehicle.setAvailable(true);
    }

    public double getFinalCost() {
        double base = vehicle.calculateRentalCost(rentalHours);
        double discount = customer.getDiscount(base);
        return base - discount;
    }

    private boolean canMemberRentVehicle(Member customer, LuxuryVehicle vehicle) {
    String memberLevel = customer.getMembershipLevel();
    String requiredLevel = vehicle.getMembershipRequired();

    if (requiredLevel.equals("STANDARD")) {
        return true;
    }

    if (requiredLevel.equals("GOLD")) {
        return memberLevel.equals("GOLD") || memberLevel.equals("PLATINUM");
    }

    if (requiredLevel.equals("PLATINUM")) {
        return memberLevel.equals("PLATINUM");
    }

    return false;
}

    public LuxuryVehicle getVehicle() { return vehicle; }
    public Member getCustomer() { return customer; }
    public int getRentalHours() { return rentalHours; }
    public String getDeliveryAddress() { return deliveryAddress; }
}
