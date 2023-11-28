package org.example;

import java.util.Date;
public class Employee {
    private int id;
    private String lastName, firstName, middleName, gender, address, position;
    private Date dob;

    public Employee(int id, String lastName, String firstName, String middleName, String gender, Date dob, String address, String position) {
        this.id=id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.position = position;
    }
    public int getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getPosition() {
        return position;
    }
}
