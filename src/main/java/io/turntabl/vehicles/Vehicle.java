package io.turntabl.vehicles;

import io.turntabl.owner.Person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Vehicle {

    private final String licencePlate;
    protected String parkingPermitNumber;
    private final VehicleType type;
    protected double monthlyBaseCharge;
    private final Set<Person> personSet;

    public Vehicle(String licencePlate, VehicleType type) {
        this.licencePlate = licencePlate;
        this.type = type;
        this.personSet = new HashSet<>();
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getParkingPermitNumber() {
        return parkingPermitNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setParkingPermitNumber(String parkingPermitNumber) {
        this.parkingPermitNumber = parkingPermitNumber;
    }

    public abstract double getTotalMonthlyCharge();

    public double getMonthlyBaseCharge() {
        return monthlyBaseCharge;
    }

    public Set<Person> getAllOwners() {
        return personSet;
    }

    public void setOwnersSet(Set<Person> owners) {
        personSet.addAll(owners);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return licencePlate.equals(vehicle.licencePlate) && type == vehicle.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(licencePlate, type);
    }

    @Override
    public String toString() {
        return "Vehicle{}";
    }
}
