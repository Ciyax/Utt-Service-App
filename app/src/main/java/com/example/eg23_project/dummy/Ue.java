package com.example.eg23_project.dummy;

public class Ue {

    private String type;
    private String libelle;
    private int credits;
    private String UE_id;
    private String description;

    public Ue(String type, String UE_id, int credits, String libelle, String description) {
        this.type = type;
        this.UE_id = UE_id;
        this.credits = credits;
        this.libelle = libelle;
        this.description = description;
    }

    public String getType() {
        return this.type;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public int getCredits() {
        return this.credits;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUE_id() {
        return UE_id;
    }

    @Override
    public String toString() {
        return UE_id + " - " + libelle + " : " + credits + " crédits, " + " type : " + type;
    }
}
