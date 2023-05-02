package io.turntabl.towncouncil;

import io.turntabl.owner.Person;
import io.turntabl.vehicles.Vehicle;
import io.turntabl.vehicles.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TownCouncil {

    private List<Person> registeredOwners;
    private Map<VehicleType, Integer> vehiclesWithPermitMapCount;
    private static int count = 1;

    public TownCouncil() {
        this.vehiclesWithPermitMapCount = new HashMap<>();
    }

    private static String generatePermitNumber() {
        String permitNumber = "VehiclePermit"+count;
        count++;
        return permitNumber;
    }

    // give permit to owners by issuing permit numbers.
    public void issueVehiclePermit(Vehicle vehicle) {
        List<Person> owners = vehicle.getOwnersList();
        for (Person owner : owners) {
            if (owner.isRegistered()) {
                vehicle.setParkingPermitNumber(generatePermitNumber());
                setVehiclesWithPermitMapCount(vehicle);
                return ;
            }
        }
    }

    public boolean issueVehiclePermit(Vehicle vehicle, Person permitRequestor) {
        if (permitRequestor.isRegistered()) {
            return false;
        }
        List<Person> ownersList = vehicle.getOwnersList();
        if (!ownersList.contains(permitRequestor)) {
            return false;
        }
        vehicle.setParkingPermitNumber(generatePermitNumber());
        setVehiclesWithPermitMapCount(vehicle);
        return true;
    }

    /** Display the number of vehicles types. */
    private void setVehiclesWithPermitMapCount(Vehicle vehicle) {
        VehicleType type = vehicle.getType();
        if (vehiclesWithPermitMapCount.containsKey(type)) {
            int frequencyOfType = vehiclesWithPermitMapCount.get(type);
            vehiclesWithPermitMapCount.put(type, frequencyOfType+1);
        } else {
            vehiclesWithPermitMapCount.put(type, 1);
        }
    }

    public Map<VehicleType, Integer> getVehiclesWithPermitMapCount() {
        return vehiclesWithPermitMapCount;
    }

    @Override
    public String toString() {
        return "TownCouncil {} ";
    }
}
