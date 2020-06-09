package com.example.eg23_project;

public class HelpRoomMessage {
    private String text;
    private MemberData memberData;
    private boolean belongsToCurrentUser;

    public HelpRoomMessage(String text, MemberData data, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

    public MemberData getMemberData() {
        return memberData;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
