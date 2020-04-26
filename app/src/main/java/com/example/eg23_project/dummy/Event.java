package com.example.eg23_project.dummy;

public class Event {

    private String label;
    private String dateHour;
    private String room;

    public Event(String label, String dateHour, String room) {
        this.label = label;
        this.dateHour = dateHour;
        this.room = room;
    }

    public String getlabel() {
        return this.label;
    }

    public String getDateHour() {
        return this.dateHour;
    }

    public String getRoom() {
        return this.room;
    }
}
