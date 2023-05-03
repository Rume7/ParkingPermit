package io.turntabl.towncouncil;

import io.turntabl.owner.Person;
import io.turntabl.services.PermitIssuerService;
import io.turntabl.services.VerificationService;
import io.turntabl.vehicles.Vehicle;
import io.turntabl.vehicles.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TownCouncil {

    private List<Person> registeredOwners;
    private final Map<VehicleType, Integer> vehiclesWithPermitMapCount;
    private static int count = 1;

    private VerificationService verificationService;
    private PermitIssuerService permitIssuerService;

    public TownCouncil() {
        this.vehiclesWithPermitMapCount = new HashMap<>();
    }

    public TownCouncil(VerificationService verificationService, PermitIssuerService permitIssuerService) {
        this.vehiclesWithPermitMapCount = new HashMap<>();
        this.verificationService = verificationService;
        this.permitIssuerService = permitIssuerService;
    }

    private static String generatePermitNumber() {
        String permitNumber = "VehiclePermit"+count;
        count++;
        return permitNumber;
    }

    // give permit to owners by issuing permit numbers.
    public void issueVehiclePermit(Vehicle vehicle) {
        Set<Person> owners = vehicle.getAllOwners();
        for (Person owner : owners) {
            if (owner.isRegistered()) {
                vehicle.setParkingPermitNumber(generatePermitNumber());
                setVehiclesWithPermitMapCount(vehicle);
                return ;
            }
        }
    }

    public boolean issueVehiclePermit1(Vehicle vehicle, Person permitRequestor) {
        vehicle.setParkingPermitNumber(generatePermitNumber());
        setVehiclesWithPermitMapCount(vehicle);
        return true;
    }

    public String issueTruckPermit(Vehicle vehicle, Person permitRequestor) {
        String permitNumber = generatePermitNumber();
        vehicle.setParkingPermitNumber(permitNumber);
        setVehiclesWithPermitMapCount(vehicle);
        return permitNumber;
    }

    public String issueVehiclePermit(Vehicle vehicle, Person permitRequestor) {
        if (!verificationService.verifyPerson(permitRequestor, vehicle)) {
            return "You are not a vehicle owner";
        } else {
            if (vehicle.getType() != VehicleType.TRUCK) {
                return permitIssuerService.issuePermit(vehicle);
            }
            return issueTruckPermit(vehicle, permitRequestor);
        }
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
