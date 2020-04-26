package com.example.eg23_project.dummy;

public class Ue {

    private String type;
    private String label;
    private int credit;
    private String description;

    public Ue(String type, String label, int credit, String description) {
        this.type = type;
        this.label = label;
        this.credit = credit;
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public String getLabel() {
        return this.label;
    }

    public int getCredit() {
        return this.credit;
    }

    public String getDescription() {
        return this.description;
    }
}
