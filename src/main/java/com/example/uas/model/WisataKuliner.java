package com.example.uas.model;

import java.util.List;

public class WisataKuliner extends TempatWisata {
    private String makananKhas;

    public WisataKuliner(String makananKhas) {
        this.makananKhas = makananKhas;
    }
    public String getMakananKhas() {
        return makananKhas;
    }

    public void setMakananKhas(String makananKhas) {
        this.makananKhas = makananKhas;
    }
    @Override
    public String tampilkanInfo(){
        return "Makanan khas: " + makananKhas;
    }
}
