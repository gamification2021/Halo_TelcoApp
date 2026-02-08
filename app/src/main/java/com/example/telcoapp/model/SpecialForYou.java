package com.example.telcoapp.model;

public class SpecialForYou {
    private String title;
    private String mainOffer;
    private String subOffer;
    private String amount;
    private String image;
    private String mainTagText;
    private int mainTagColor;
    private String secondaryTagText;
    private int secondaryTagColor;

    public SpecialForYou(String title, String mainOffer, String subOffer, String amount, String image, String mainTagText, int mainTagColor) {
        this.title = title;
        this.mainOffer = mainOffer;
        this.subOffer = subOffer;
        this.amount = amount;
        this.image = image;
        this.mainTagText = mainTagText;
        this.mainTagColor = mainTagColor;
    }

    public SpecialForYou(String title, String mainOffer, String subOffer, String amount, String image, String mainTagText, int mainTagColor, String secondaryTagText, int secondaryTagColor) {
        this.title = title;
        this.mainOffer = mainOffer;
        this.subOffer = subOffer;
        this.amount = amount;
        this.image = image;
        this.mainTagText = mainTagText;
        this.mainTagColor = mainTagColor;
        this.secondaryTagText = secondaryTagText;
        this.secondaryTagColor = secondaryTagColor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainOffer() {
        return mainOffer;
    }

    public void setMainOffer(String mainOffer) {
        this.mainOffer = mainOffer;
    }

    public String getSubOffer() {
        return subOffer;
    }

    public void setSubOffer(String subOffer) {
        this.subOffer = subOffer;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMainTagText() {
        return mainTagText;
    }

    public void setMainTagText(String mainTagText) {
        this.mainTagText = mainTagText;
    }

    public int getMainTagColor() {
        return mainTagColor;
    }

    public void setMainTagColor(int mainTagColor) {
        this.mainTagColor = mainTagColor;
    }

    public String getSecondaryTagText() {
        return secondaryTagText;
    }

    public void setSecondaryTagText(String secondaryTagText) {
        this.secondaryTagText = secondaryTagText;
    }

    public int getSecondaryTagColor() {
        return secondaryTagColor;
    }

    public void setSecondaryTagColor(int secondaryTagColor) {
        this.secondaryTagColor = secondaryTagColor;
    }
}
