package com.example.labtwo.model;

public class Meme {
    private int ID;
    private String name;
    private String bottomText;
    private String image;
    private String topText;
    private String rank;

    public Meme(int ID, String bottomText,  String name, String image, String topText, String rank) {
        this.ID = ID;
        this.bottomText = bottomText;
        this.image = image;
        this.topText = topText;
        this.name=name;
        this.rank=rank;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
