package com.example.uas.model;

public class TempatWisata {
    private String nama;
    private String kategori;
    private String lokasi;
    private String deskripsi;
    private String gambar;

    public TempatWisata(String nama, String kategori, String lokasi, String deskripsi, String gambar) {
        this.nama = nama;
        this.kategori = kategori;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    // Getter & Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    // Lanjutkan untuk semua atribut
}
