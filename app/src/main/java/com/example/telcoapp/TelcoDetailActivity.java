package com.example.telcoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.telcoapp.model.CartItem;
import com.example.telcoapp.model.CartManager;
import com.example.telcoapp.utils.EventTracker;
import com.sixdee.cvm.sdk;

public class TelcoDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telco_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        ImageView hero = findViewById(R.id.hero);
        TextView title = findViewById(R.id.title);
        TextView price = findViewById(R.id.price);
        TextView descriptionTxt = findViewById(R.id.descriptionTxt);
        ImageButton addCartBtn = findViewById(R.id.addCartBtn);

        int imgRes = getIntent().getIntExtra("imgRes", 0);
        String t = getIntent().getStringExtra("title");
        String subtitle = getIntent().getStringExtra("subtitle");
        String d = getIntent().getStringExtra("description");
        double p = getIntent().getDoubleExtra("price", -1);

        if (imgRes != 0 && hero != null) hero.setImageResource(imgRes);
        if (t != null && title != null) title.setText(t);
        if (d != null && descriptionTxt != null) descriptionTxt.setText(d);
        if (p >= 0 && price != null) price.setText(String.format("Rp %.2f", p));

        if (addCartBtn != null) {
            addCartBtn.setOnClickListener(v -> {
                CartManager.getInstance().addOrIncrement(new CartItem(
                        t != null ? t : "Unknown",
                        t != null ? t : "Unknown",
                        subtitle != null ? subtitle : "",
                        p,
                        1,
                        imgRes,
                        d != null ? d : ""
                ));
                try {
                    // TODO: Uncomment this
                    String msg = "Item Added to Cart " + t + " and " + String.format("Rp %.2f", p);
//                    sdk.INSTANCE.sendEvent("ADD_CART", msg);
                    EventTracker.getInstance().trackEvent("ADD_CART", t, msg);
                    Log.v("6DLOG", "ADD_CART \t\t"+msg);
                } catch (Exception ignore) {}
                Intent i = new Intent(TelcoDetailActivity.this, TelcoCartActivity.class);
                startActivity(i);
            });
        }
    }
}
