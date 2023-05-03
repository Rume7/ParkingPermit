package io.turntabl.towncouncil;

import io.turntabl.owner.Person;
import io.turntabl.service.PermitIssueService;
import io.turntabl.service.VerificationService;
import io.turntabl.vehicles.PrivateCar;
import io.turntabl.vehicles.Truck;
import io.turntabl.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TownCouncilTest {

    private TownCouncil townCouncil;
    private Vehicle privateVehicle;
    @Mock
    private VerificationService verificationService;

    @Mock
    private PermitIssueService permitIssueService;

    @BeforeEach
    void setUp() {
        townCouncil = new TownCouncil(verificationService, permitIssueService);
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
        Person person = new Person(false, "OWN1");
        when(verificationService.verifyPerson(person, privateVehicle)).thenReturn(true);
        when(permitIssueService.issuePermit(privateVehicle)).thenReturn("PER324234");
        townCouncil.issueVehiclePermit(privateVehicle, person);
        int count = townCouncil.getVehiclesWithPermitMapCount().get(privateVehicle.getType());
        assertEquals(1, count);
    }

    @Test
    void getVehiclesWithPermitMapCount() {
    }


//    When Verification Service is True
    @Test
    void testFalseVehicleOwner() {
        //Given
        Person person1 = new Person(true, "OWN2");
        when(verificationService.verifyPerson(person1, privateVehicle)).thenReturn(false);
//        when(permitIssueService.issuePermit(privateVehicle)).thenReturn("PER324234");

        //When
        boolean isIssuedVehiclePermit = townCouncil.issueVehiclePermit(privateVehicle, person1);

        //Then
        assertFalse(isIssuedVehiclePermit);
    }

    @Test
    void testTrueVehicleOwner() {
        //Given
        Person person1 = new Person(true, "OWN12312");
        when(verificationService.verifyPerson(person1, privateVehicle)).thenReturn(true);
        when(permitIssueService.issuePermit(privateVehicle)).thenReturn("PER324234");

        //When
        boolean isIssuedVehiclePermit = townCouncil.issueVehiclePermit(privateVehicle, person1);

        //Then
        assertTrue(isIssuedVehiclePermit);
    }

//    When Permit Issuer Service returns permit number
    @Test
    void testWhenIssuePermitReturnsPermitNumber() {
        //Given
        Person person1 = new Person(true, "OWN12312");
        when(verificationService.verifyPerson(person1, privateVehicle)).thenReturn(true);
        when(permitIssueService.issuePermit(privateVehicle)).thenReturn("PER324234");

        //When
        boolean isIssuedVehiclePermit = townCouncil.issueVehiclePermit(privateVehicle, person1);

        //Then
        assertTrue(isIssuedVehiclePermit);
        assertEquals("PER324234", privateVehicle.getParkingPermitNumber());
    }

//    Test whether Vehicle of type Truck calls issuePermit
    @Test
    void testIssueTruckPermit() {
        Person person1 = new Person(true, "OWN12312");
        Vehicle truck = new Truck("AA2323", 150);
        truck.setOwnersSet(Set.of(
                new Person(true, "OWN12312"),
                new Person(true, "OWN2"),
                new Person(true, "OWN3")
        ));

        when(verificationService.verifyPerson(person1, truck)).thenReturn(true);


        townCouncil.issueVehiclePermit(truck, person1);


        verify(permitIssueService, times(0)).issuePermit(truck);
        verify(verificationService, times(1)).verifyPerson(person1, truck);
    }

//    When Permit Issuer Service returns null empty string
//    When Permit Issuer Service returns empty string

}