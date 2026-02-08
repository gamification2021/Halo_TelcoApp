package com.example.telcoapp.model;

import android.graphics.drawable.Drawable;

import java.util.Objects;

public class Reward {

    int points;
    String program;
    String expiryDate;
    Drawable image;
    String type;
    boolean status;
    boolean isRecent;
    int position;
    int imageInt;
    String picture;
    String description;

    public Reward(String title, String description, String picture, boolean status, String type, String expiryDate, int points) {
        this.program = title;
        this.description = description;
        this.picture = picture;
        this.status = status;
        this.type = type;
        this.expiryDate = expiryDate;
        this.points = points;
    }

    public Reward(String title, String description, Drawable image, boolean status, String type, String expiryDate, int points) {
        this.program = title;
        this.description = description;
        this.image = image;
        this.status = status;
        this.type = type;
        this.expiryDate = expiryDate;
        this.points = points;
    }
    public Reward(String title, String description, int imageInt, boolean status, String type, String expiryDate, int points) {
        this.program = title;
        this.description = description;
        this.imageInt = imageInt;
        this.status = status;
        this.type = type;
        this.expiryDate = expiryDate;
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIcon() {
        return picture;
    }

    public void setIcon(String icon) {
        this.picture = icon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isRecent() {
        return isRecent;
    }

    public void setRecent(boolean recent) {
        isRecent = recent;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getExiryDate() {
        return expiryDate;
    }

    public void setExiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reward reward = (Reward) o;
        return points == reward.points && Objects.equals(program, reward.program) && Objects.equals(expiryDate, reward.expiryDate) && Objects.equals(type, reward.type) && Objects.equals(description, reward.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, program, expiryDate, type, description);
    }
}
