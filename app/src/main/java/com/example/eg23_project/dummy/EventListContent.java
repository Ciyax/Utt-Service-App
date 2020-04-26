package com.example.eg23_project.dummy;

import java.util.ArrayList;

public class EventListContent {

    private static ArrayList<Event> eventList = new ArrayList<>();

    static {
        eventList.add(new Event("LO12", "8h - 10h", "A002"));
        eventList.add(new Event("EG23", "10h - 12h", "C002"));
        eventList.add(new Event("EG23", "14h - 17h", "P204"));
    }

    public static void addTask(Event event) {
        eventList.add(event);
    }

    public static ArrayList<Event> getEventListTask() {
        ArrayList<Event> res = new ArrayList<>();
        for (Event event : eventList) {
            res.add(event);
        }
        return res;
    }
}
