package com.example.telcoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telcoapp.R;

import java.util.ArrayList;
import java.util.List;

public class TelcoSearchAdapter extends RecyclerView.Adapter<TelcoSearchAdapter.VH> {

    public static class Item {
        public final String title;
        public final String priceText;
        public final String amountText;
        public final int imageRes;
        public Item(String title, String priceText, String amountText, int imageRes) {
            this.title = title;
            this.priceText = priceText;
            this.amountText = amountText;
            this.imageRes = imageRes;
        }
    }

    private final List<Item> all;
    private final List<Item> data;
    public interface OnItemClick { void onClick(Item item); }
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick l) { this.onItemClick = l; }
    public Context context;

    public TelcoSearchAdapter(List<Item> items, Context context) {
        this.all = new ArrayList<>(items);
        this.data = new ArrayList<>(items);
        this.context = context;
    }

    public void filter(String query) {
        data.clear();
        if (query == null || query.trim().isEmpty()) {
            data.addAll(all);
        } else {
            String q = query.toLowerCase();
            for (Item it : all) {
                if (it.title != null && it.title.toLowerCase().contains(q)) {
                    data.add(it);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_telco_search, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Item it = data.get(position);
        h.title.setText(it.title);
        h.price.setText(it.priceText);
        h.amount.setText(it.amountText);
        Glide.with(context).load(it.imageRes).into(h.image);
//        h.image.setImageResource(it.imageRes);
        h.itemView.setOnClickListener(v -> { if (onItemClick != null) onItemClick.onClick(it); });
        // Info button can be wired later if needed
    }

    @Override
    public int getItemCount() { return data.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image; TextView title; TextView price; TextView amount; ImageButton info;
        VH(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.itemImage);
            title = v.findViewById(R.id.itemTitle);
            price = v.findViewById(R.id.itemPrice);
            amount = v.findViewById(R.id.itemAmount);
        }
    }
}
