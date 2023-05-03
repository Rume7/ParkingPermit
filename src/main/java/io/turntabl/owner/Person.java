package io.turntabl.owner;

import java.util.Objects;

public class Person {

    private boolean isRegistered;
    private String licenceNumber;

    public Person(boolean isRegistered, String licenceNumber) {
        this.isRegistered = isRegistered;
        this.licenceNumber = licenceNumber;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person owner = (Person) o;
        return licenceNumber.equals(owner.licenceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenceNumber);
    }

    @Override
    public String toString() {
        return "Owner{ licenceNumber = " + licenceNumber + " }";
    }
}
