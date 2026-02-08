package com.example.telcoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.R;
import com.example.telcoapp.TelcoCartActivity;
import com.example.telcoapp.TelcoDetailActivity;
import com.example.telcoapp.model.CartItem;
import com.example.telcoapp.model.CartManager;
import com.sixdee.cvm.sdk;

import java.util.List;

public class TelcoFeaturedAdapter extends RecyclerView.Adapter<TelcoFeaturedAdapter.VH> {
    private final Context context;
    private final List<CartItem> items;

    public TelcoFeaturedAdapter(Context context, List<CartItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_telco_featured, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        CartItem it = items.get(position);
        h.title.setText(it.getTitle());
        h.subtitle.setText(it.getSubtitle());
        h.price.setText(String.format("Rp %.2f", it.getPrice()));
        h.img.setImageResource(it.getImageRes());

        h.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TelcoDetailActivity.class);
            intent.putExtra("imgRes", it.getImageRes());
            intent.putExtra("title", it.getTitle());
            intent.putExtra("subtitle", it.getSubtitle());
            intent.putExtra("price", it.getPrice());
            intent.putExtra("description", it.getDescription());
            context.startActivity(intent);
        });

        h.btnAdd.setOnClickListener(v -> {
            CartManager.getInstance().addOrIncrement(new CartItem(
                    it.getId(), it.getTitle(), it.getSubtitle(), it.getPrice(), 1, it.getImageRes(), it.getDescription()
            ));
            try {
                // TODO: Uncomment this
                String msg = "Item Added to Cart " + it.getTitle() + " and " + String.format("Rp %.2f", it.getPrice());
                sdk.INSTANCE.sendEvent("ADD_CART", msg);
            } catch (Exception ignore) {}
            Intent i = new Intent(context, TelcoCartActivity.class);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView img; TextView title; TextView subtitle; TextView price; Button btnAdd;
        VH(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img);
            title = v.findViewById(R.id.title);
            subtitle = v.findViewById(R.id.subtitle);
            price = v.findViewById(R.id.price);
            btnAdd = v.findViewById(R.id.btnAdd);
        }
    }
}
