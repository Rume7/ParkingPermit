package io.turntabl.vehicles;

import io.turntabl.owner.Person;

import java.util.List;

public abstract class Vehicle {

    private String licencePlate;
    protected String parkingPermitNumber;
    private final VehicleType type;
    protected float monthlyBaseCharge;
    private List<Person> ownersList;

    public Vehicle(String licencePlate, VehicleType type) {
        this.licencePlate = licencePlate;
        this.type = type;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getParkingPermitNumber() {
        return parkingPermitNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setMonthlyBaseCharge(float monthlyBaseCharge) {
        this.monthlyBaseCharge = monthlyBaseCharge;
    }

    public void setParkingPermitNumber(String parkingPermitNumber) {
        this.parkingPermitNumber = parkingPermitNumber;
    }

    public abstract float getTotalMonthlyCharge();

    public float getMonthlyBaseCharge() {
        return monthlyBaseCharge;
    }

    public List<Person> getOwnersList() {
        return ownersList;
    }

    public void setOwnersList(List<Person> ownersList) {
        this.ownersList = ownersList;
    }

    @Override
    public String toString() {
        return "Vehicle{}";
    }
}
