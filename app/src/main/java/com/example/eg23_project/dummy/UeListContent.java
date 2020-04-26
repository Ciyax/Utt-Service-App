package com.example.eg23_project.dummy;

import java.util.ArrayList;

public class UeListContent {

    private static ArrayList<Ue> ueListBrancheAutomne = new ArrayList<>();
    private static ArrayList<Ue> ueListFiliereAutomne = new ArrayList<>();

    static {
        ueListBrancheAutomne.add(new Ue("CS", "NF20", 6, "Modélisation et évaluation des systèmes complexes"));
        ueListBrancheAutomne.add(new Ue("TM", "IF09", 6, "Systèmes Documentaires"));
        ueListBrancheAutomne.add(new Ue("TM", "NF21", 6, "Conception de projet data pour l'innovation"));

        ueListFiliereAutomne.add(new Ue("CS", "IF10", 6, "Conception centrée usage des systèmes intéractifs"));
        ueListFiliereAutomne.add(new Ue("TM", "IF17", 6, "Architectures décisionnelles"));
        ueListFiliereAutomne.add(new Ue("TM", "IF26", 6, "Conception sécurisée d'applications : Web Mobile et Smartphone"));
    }

    public static void addUeBrancheAutomne(Ue ue) {
        ueListBrancheAutomne.add(ue);
    }
    public static void addUeFiliereAutomne(Ue ue) {
        ueListFiliereAutomne.add(ue);
    }

    public static ArrayList<Ue> getUeBrancheAutomneList() {
        ArrayList<Ue> res = new ArrayList<>();
        for (Ue ue : ueListBrancheAutomne) {
            res.add(ue);
        }
        return res;
    }
    public static ArrayList<Ue> getUeFiliereAutomneList() {
        ArrayList<Ue> res = new ArrayList<>();
        for (Ue ue : ueListFiliereAutomne) {
            res.add(ue);
        }
        return res;
    }
}
