package com.example.uas.model;

import java.util.List;

/**
 * Model class for tourist destination (Wisata).
 */
public class TempatWisata {
    protected int id;
    protected String name;
    protected String category;
    protected String location;
    protected String Description;
    protected List<String> imageUrls; // Image URLS for gallery

    public TempatWisata() {}

    public TempatWisata(int id, String name, String category, String location,
                  String Description, List<String> imageUrls) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.Description = Description;
        this.imageUrls = imageUrls;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return Description; }
    public void setDescription(String Description) { this.Description = Description; }

    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, category, location);
    }
    public String tampilkanInfo(){
        return null;
    }
}

