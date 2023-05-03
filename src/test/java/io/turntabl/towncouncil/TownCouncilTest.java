package io.turntabl.towncouncil;

import io.turntabl.owner.Person;
import io.turntabl.vehicles.PrivateCar;
import io.turntabl.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TownCouncilTest {

    private TownCouncil townCouncil;
    private Vehicle privateVehicle;

    @BeforeEach
    void setUp() {
        townCouncil = new TownCouncil();
        privateVehicle = new PrivateCar("AA2323");
        Set<Person> privateCarOwners = Set.of(
                new Person(true, "OWN1"),
                new Person(true, "OWN2"),
                new Person(true, "OWN3")
                );
        privateVehicle.setOwnersSet(privateCarOwners);
    }

    @Test
    void issueVehiclePermit() {
//        Person person = new Person(false, "OWN1");
//        townCouncil.issueVehiclePermit(privateVehicle, person);
//        int count = townCouncil.getVehiclesWithPermitMapCount().get(privateVehicle.getType());
//        assertEquals(1, count);
    }

    @Test
    void getVehiclesWithPermitMapCount() {

    }
}