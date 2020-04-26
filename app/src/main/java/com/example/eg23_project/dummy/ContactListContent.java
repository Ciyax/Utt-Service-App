package com.example.eg23_project.dummy;

import java.util.ArrayList;

public class ContactListContent {

    private static ArrayList<Contact> contactList = new ArrayList<>();

    static {
        contactList.add(new Contact("BERTHER","Sandrine", "BH", "Assistante de programme", "T19", "sandrine.berthier@utt.fr"));
        contactList.add(new Contact("DUTEURTRE","Laurence",  "LD", "Assistante de programme", "T20", "laurence.duteurtre@utt.fr"));
        contactList.add(new Contact("MAURER","Thomas",  "TM", "Responsable TC", "T208", "thomas.maurer@utt.fr"));
        contactList.add(new Contact("VIAL","Alexandre",  "AV", "Responsable TC", "T227","alexandre.vial@utt.fr"));
    }

    public static void addContact(Contact contact) {
        contactList.add(contact);
    }

    public static ArrayList<Contact> getContactList() {
        ArrayList<Contact> res = new ArrayList<>();
        for (Contact contact : contactList) {
            res.add(contact);
        }
        return res;
    }
}
