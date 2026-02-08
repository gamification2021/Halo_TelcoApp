package com.example.telcoapp.model;

public class CartItem {
    private final String id;
    private final String title;
    private final String subtitle;
    private final double price;
    private int quantity;
    private final int imageRes; // drawable resource id
    private final String description;

    public CartItem(String id, String title, String subtitle, double price, int quantity, int imageRes, String description) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.quantity = quantity;
        this.imageRes = imageRes;
        this.description = description;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSubtitle() { return subtitle; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getImageRes() { return imageRes; }
    public String getDescription() { return description; }


}
