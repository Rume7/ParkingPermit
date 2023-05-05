package io.turntabl.towncouncil;

import io.turntabl.owner.Person;
import io.turntabl.services.PermitIssuerService;
import io.turntabl.services.VerificationService;
import io.turntabl.vehicles.MotorBike;
import io.turntabl.vehicles.PrivateCar;
import io.turntabl.vehicles.Truck;
import io.turntabl.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TownCouncilTest {

    private TownCouncil townCouncil;
    private Vehicle privateVehicle;

    @Mock
    private VerificationService verificationServiceMock;

    @Mock
    private PermitIssuerService permitIssuerServiceMock;

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

//    @Test
//    void issueVehiclePermit() {
//        Person person = new Person(false, "OWN1");
//        townCouncil.issueVehiclePermit(privateVehicle, person);
//        int count = townCouncil.getVehiclesWithPermitMapCount().get(privateVehicle.getType());
//        assertEquals(1, count);
//    }

    @Test
    void testFalseVehicleOwnerPermitRequester() {
        // Given
        TownCouncil council = new TownCouncil(verificationServiceMock, permitIssuerServiceMock);
        when(verificationServiceMock.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(false);

        // When
        Person person1 = new Person(true, "OWN12");
        Vehicle vehicle1 = new PrivateCar("PC001");
        String permit = council.issueVehiclePermit(vehicle1, person1);

        // Then
        assertEquals(permit, "You are not a vehicle owner");
    }

    @Test
    void testTruePrivateCarPermitRequester() {
        // Given
        TownCouncil council = new TownCouncil(verificationServiceMock, permitIssuerServiceMock);
        Person person1 = new Person(true, "OWN2");
        when(verificationServiceMock.verifyPerson(person1, privateVehicle)).thenReturn(true);
        when(permitIssuerServiceMock.issuePermit(privateVehicle)).thenReturn("Permit granted");

        // When
        String permit = council.issueVehiclePermit(privateVehicle, person1);

        // Then
        assertEquals(permit, "Permit granted");
    }

    @Test
    void testTrueTruckPermitRequester() {
        // Given
        TownCouncil council1 = new TownCouncil(verificationServiceMock, permitIssuerServiceMock);
        Person personGuy1 = new Person(true, "LOR001");
        Person personGuy2 = new Person(true, "LOR002");
        Vehicle lorry = new Truck("LORRY323", 200);
        lorry.setOwnersSet(Set.of (personGuy1, personGuy2));

        when(verificationServiceMock.verifyPerson(personGuy1, lorry)).thenReturn(true);

        // When
        String permit = council1.issueVehiclePermit(lorry, personGuy1);

        // Then
        assertEquals(permit, "TruckPermit001");
    }

    @Test
    void testTrueMotorbikePermitRequester() {
        // Given
        TownCouncil council1 = new TownCouncil(verificationServiceMock, permitIssuerServiceMock);
        Person personGuy3 = new Person(true, "BIK001");
        Person personGuy4 = new Person(true, "BIK002");
        Vehicle bike = new MotorBike("BIKE321", 840 );
        bike.setOwnersSet(Set.of (personGuy3, personGuy4));

        when(verificationServiceMock.verifyPerson(personGuy4, bike)).thenReturn(true);
        when(permitIssuerServiceMock.issuePermit(bike)).thenReturn("Bike permit granted");

        // When
        String permit = council1.issueVehiclePermit(bike, personGuy4);

        // Then
        assertEquals(permit, "Bike permit granted");
    }

    @Test
    void testPermitAlreadyGiven() {
        TownCouncil council1 = new TownCouncil(verificationServiceMock, permitIssuerServiceMock);
        Person personGuy5 = new Person(true, "PC212");
        Person personGuy6 = new Person(true, "PC225");
        Vehicle car = new PrivateCar("PC212");
        car.setOwnersSet(Set.of (personGuy5, personGuy6));
        car.setParkingPermitNumber("PC23240");

        when(verificationServiceMock.verifyPerson(personGuy5, car)).thenReturn(true);

        assertEquals(council1.issueVehiclePermit(car, personGuy5), "Permit already exit");
    }

    @Test
    void getVehiclesWithPermitMapCount() {

    }
}