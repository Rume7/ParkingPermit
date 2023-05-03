package io.turntabl.vehicles;

import java.util.Objects;

public class MotorBike extends Vehicle {

    private final int capacity;
    private final MotorBikeType bikeType;

    public MotorBike(String licencePlate, int capacity) {
        super(licencePlate, VehicleType.MOTORBIKE);
        if (capacity <= 850) {
            bikeType = MotorBikeType.SCOOTER;
        } else {
            bikeType = MotorBikeType.LARGE;
        }
        this.capacity = capacity;
    }

    @Override
    public double getTotalMonthlyCharge() {
        return super.getMonthlyBaseCharge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MotorBike motorBike = (MotorBike) o;
        return capacity == motorBike.capacity && bikeType == motorBike.bikeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity, bikeType);
    }

    @Override
    public String toString() {
        return "MotorBike{ capacity = " + capacity + '}';
    }
}
