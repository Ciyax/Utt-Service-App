package com.example.eg23_project.dummy;

public class Contact {

    private String lastName;
    private String firstName;
    private String shortName;
    private String position;
    private String office;
    private String email;

    public Contact(String lastName, String firstName, String shortName, String position, String office, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.shortName = shortName;
        this.position = position;
        this.office = office;
        this.email = email;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return this.office;
    }

    public String getEmail() {
        return this.email;
    }
}
