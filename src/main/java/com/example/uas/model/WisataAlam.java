package com.example.uas.model;

import java.util.List;

public class WisataAlam extends TempatWisata {
    private String jenisAlam;

    public WisataAlam(String jenisAlam) {
        this.jenisAlam = jenisAlam;
    }
    public String getJenisAlam() {
        return jenisAlam;
    }

    public void setJenisAlam(String jenisAlam) {
        this.jenisAlam = jenisAlam;
    }

    @Override
    public String tampilkanInfo(){
        return "Jenis Alam: " + jenisAlam;
    }
}
