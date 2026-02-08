package com.example.telcoapp.model;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final List<CartItem> items = new ArrayList<>();

    private CartManager() {}

    public static synchronized CartManager getInstance() {
        if (instance == null) instance = new CartManager();
        return instance;
    }

    public synchronized void addOrIncrement(CartItem item) {
        for (CartItem it : items) {
            if (it.getId().equals(item.getId())) {
                it.setQuantity(it.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public synchronized List<CartItem> getItems() {
        return items; // return live list for in-place updates
    }

    public synchronized void removeIfZero() {
        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i).getQuantity() <= 0) items.remove(i);
        }
    }

    public synchronized double getTotal() {
        double total = 0;
        for (CartItem it : items) {
            total += it.getPrice() * it.getQuantity();
        }
        return total;
    }

    public synchronized void clear() { items.clear(); }
}
