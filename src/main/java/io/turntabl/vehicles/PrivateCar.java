package io.turntabl.vehicles;

public class PrivateCar extends Vehicle {

    public PrivateCar(String licencePlate) {
        super(licencePlate, VehicleType.PRIVATE_CAR);
    }

    @Override
    public void setParkingPermitNumber(String parkingPermitNumber) {
        this.parkingPermitNumber = parkingPermitNumber;
    }

    @Override
    public float getTotalMonthlyCharge() {
        return monthlyBaseCharge;
    }

    @Override
    public String toString() {
        return "Private Car { parkingPermitNumber = " + parkingPermitNumber + '}';
    }
}
