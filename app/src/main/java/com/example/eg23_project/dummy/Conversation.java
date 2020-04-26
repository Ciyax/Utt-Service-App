package com.example.eg23_project.dummy;

public class Conversation {

    private String shortName;
    private String fullName;
    private String lastMessage;
    private String lastMessageDate;
    private boolean isLastMessageSenderUser;
    private boolean isOnline;

    public Conversation(String shortName, String fullName, String lastMessage, String lastMessageDate, boolean isLastMessageSenderUser, boolean isOnline) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
        this.isLastMessageSenderUser = isLastMessageSenderUser;
        this.isOnline = isOnline;
    }

    public String getshortName() {
        return this.shortName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getLastMessage() {
        return this.lastMessage;
    }

    public String getLastMessageDate() {
        return this.lastMessageDate;
    }

    public boolean getIsLastMessageSenderUser() {
        return this.isLastMessageSenderUser;
    }

    public boolean getIsOnline() {
        return this.isOnline;
    }
}
