package io.turntabl.vehicles;

import java.util.Objects;

public class Truck extends Vehicle {

    private static final double BASE_CAPACITY_IN_KG = 150;
    private static final double COST_OF_EXCESS_CAPACITY_PER_20KG = 5;
    private final double truckCapacityInKg;

    public Truck(String licencePlate, float capacity) {
        super(licencePlate, VehicleType.TRUCK);
        this.monthlyBaseCharge = 30;
        this.truckCapacityInKg = capacity;
    }

    @Override
    public double getTotalMonthlyCharge() {
        if (truckCapacityInKg <= BASE_CAPACITY_IN_KG) {
            return getMonthlyBaseCharge();
        }
        double excessWeight = truckCapacityInKg - BASE_CAPACITY_IN_KG;
        double excessWeightCharge = Math.ceil(excessWeight / 20) * COST_OF_EXCESS_CAPACITY_PER_20KG;
        return getMonthlyBaseCharge() + excessWeightCharge;
    }

    public double getTruckCapacityInKg() {
        return truckCapacityInKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return Double.compare(truck.truckCapacityInKg, truckCapacityInKg) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), truckCapacityInKg);
    }

    @Override
    public String toString() {
        return "Truck { Capacity in Kg = " + truckCapacityInKg + " }";
    }
}
