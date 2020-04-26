package com.example.eg23_project.dummy;

import java.util.ArrayList;

public class ConversationListContent {

    private static ArrayList<Conversation> conversationList = new ArrayList<>();

    static {
        conversationList.add(new Conversation("PU","Parrain UTT", "Ok, merci du conseil !", "11/04", true, true));
        conversationList.add(new Conversation("E1","Etudiant 1",  "Entendu, je t'envoie un mail ;)", "03/04", false, false));
        conversationList.add(new Conversation("E2","Etudiant 2",  "On mange où à 12h ?", "31/03", false, false));
    }

    public static void addContact(Conversation conversation) {
        conversationList.add(conversation);
    }

    public static ArrayList<Conversation> getConversationList() {
        ArrayList<Conversation> res = new ArrayList<>();
        for (Conversation conversation : conversationList) {
            res.add(conversation);
        }
        return res;
    }
}
