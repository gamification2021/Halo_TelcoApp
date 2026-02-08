package com.example.telcoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.R;
import com.example.telcoapp.model.CartItem;

import java.util.List;

public class TelcoCartAdapter extends RecyclerView.Adapter<TelcoCartAdapter.VH> {

    public interface OnChanged {
        void onChanged();
    }

    private final List<CartItem> data;
    private final OnChanged onChanged;

    public TelcoCartAdapter(List<CartItem> data, OnChanged onChanged) {
        this.data = data;
        this.onChanged = onChanged;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_telco_cart, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        CartItem it = data.get(position);
        h.title.setText(it.getTitle());
        h.subtitle.setText(it.getSubtitle());
        h.price.setText(String.format("Rp %.2f", it.getPrice()));
        h.qty.setText(String.format("%02d", it.getQuantity()));
        h.image.setImageResource(it.getImageRes());

        h.plus.setOnClickListener(v -> {
            it.setQuantity(it.getQuantity() + 1);
            notifyItemChanged(h.getAdapterPosition());
            if (onChanged != null) onChanged.onChanged();
        });
        h.minus.setOnClickListener(v -> {
            int q = Math.max(0, it.getQuantity() - 1);
            it.setQuantity(q);
            if (q == 0) {
                int idx = h.getAdapterPosition();
                data.remove(idx);
                notifyItemRemoved(idx);
            } else {
                notifyItemChanged(h.getAdapterPosition());
            }
            if (onChanged != null) onChanged.onChanged();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image; TextView title; TextView subtitle; TextView price; TextView qty; ImageButton plus; ImageButton minus;
        VH(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.itemImage);
            title = v.findViewById(R.id.itemTitle);
            subtitle = v.findViewById(R.id.itemSubtitle);
            price = v.findViewById(R.id.itemPrice);
            qty = v.findViewById(R.id.itemQty);
            plus = v.findViewById(R.id.btnPlus);
            minus = v.findViewById(R.id.btnMinus);
        }
    }
}
