package com.example.uas.model;

import java.util.List;

public class WisataBudaya extends TempatWisata {
    private String budayaTerkait;

    public WisataBudaya(String budayaTerkait) {
        this.budayaTerkait = budayaTerkait;
    }
    public String getBudayaTerkait() {
        return budayaTerkait;
    }

    public void setBudayaTerkait(String budayaTerkait) {
        this.budayaTerkait = budayaTerkait;
    }
    @Override
    public String tampilkanInfo(){
        return "Jenis Budaya: " + budayaTerkait;
    }
}
