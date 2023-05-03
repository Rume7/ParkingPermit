package io.turntabl.vehicles;

public class MotorBike extends Vehicle {

    private int capacity;
    private MotorBikeType bikeType;

    public MotorBike(String licencePlate, VehicleType type) {
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
        return 0;
    }

    @Override
    public String toString() {
        return "MotorBike{ capacity = " + capacity + '}';
    }
}
