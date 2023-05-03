package io.turntabl.service;

import io.turntabl.owner.Person;
import io.turntabl.vehicles.Vehicle;

public interface VerificationService {
    boolean verifyPerson(Person person, Vehicle vehicle);
}
