package io.turntabl.services;

import io.turntabl.owner.Person;
import io.turntabl.vehicles.Vehicle;

public interface VerificationService {

    boolean verifyPerson(Person p, Vehicle v) ;


}
