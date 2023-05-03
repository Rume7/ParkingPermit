package io.turntabl.towncouncil;

import io.turntabl.owner.Person;
import io.turntabl.service.PermitIssueService;
import io.turntabl.service.VerificationService;
import io.turntabl.vehicles.Vehicle;
import io.turntabl.vehicles.VehicleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TownCouncil {

    private List<Person> registeredOwners;
    private Map<VehicleType, Integer> vehiclesWithPermitMapCount;
    private static int count = 1;

    private VerificationService verificationService;
    private PermitIssueService permitIssueService;

    public TownCouncil(VerificationService verificationService, PermitIssueService permitIssueService) {
        this.vehiclesWithPermitMapCount = new HashMap<>();
        this.permitIssueService = permitIssueService;
        this.verificationService = verificationService;
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

    public boolean issueVehiclePermit(Vehicle vehicle, Person permitRequestor) {
        boolean isPersonVerified = verificationService.verifyPerson(permitRequestor, vehicle);
        if (!isPersonVerified) {
            System.out.println("Person not an owner");
            return false;
        }

        String generatedPermit;
        if (vehicle.getType() != VehicleType.TRUCK) {
            generatedPermit = permitIssueService.issuePermit(vehicle);
        } else {
            generatedPermit = issueTruckPermit(vehicle);
        }

        if (generatedPermit == null || generatedPermit.isEmpty()) {
            System.out.println("Vehicle permit not generated");
            return false;
        }

        vehicle.setParkingPermitNumber(generatedPermit);
        setVehiclesWithPermitMapCount(vehicle);
        return true;
    }

    private String issueTruckPermit(Vehicle vehicle) {
        String permitNumber = generatePermitNumber();
        return permitNumber;
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
