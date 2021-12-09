package com.example.pickafood;

public class Restaurant {

    private String name;
    private String address;
    private String description;
    private String photoLink;

    public Restaurant(String name, String address,String description,String photoLink){
        this.name=name;
        this.address=address;
        this.description=description;
        this.photoLink=photoLink;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoLink(){
        return photoLink;
    }
}
