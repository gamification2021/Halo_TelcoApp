package com.example.telcoapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.adapter.TelcoCartAdapter;
import com.example.telcoapp.model.CartItem;
import com.example.telcoapp.model.CartManager;
import com.example.telcoapp.utils.EventTracker;
import com.sixdee.cvm.sdk;

import java.util.List;

public class TelcoCartActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private TelcoCartAdapter adapter;
    private TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telco_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        sdk.INSTANCE.initialiseSDK(this);

        recyclerView = findViewById(R.id.recycler);
        totalText = findViewById(R.id.totalText);
        ImageView back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> finish());
        Button checkout = findViewById(R.id.checkoutBtn);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CartItem> items = CartManager.getInstance().getItems();
        adapter = new TelcoCartAdapter(items, () -> updateTotal());
        recyclerView.setAdapter(adapter);
        updateTotal();

        if (checkout != null) {
            checkout.setOnClickListener(v -> {
                List<CartItem> current = CartManager.getInstance().getItems();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < current.size(); i++) {
                    CartItem c = current.get(i);
                    sb.append(c.getTitle());
                    if (i < current.size() - 1) sb.append(", ");
                }
                try {
//                    sdk.INSTANCE.sendEvent("CHECKOUT", "Checked out " + sb.toString());
                    EventTracker.getInstance().trackEvent("CHECKOUT", "Cart Items", "Checked out " + sb.toString());
                    Log.v("6DLOG", "CHECKOUT \t\tChecked out " + sb.toString());
                } catch (Exception ignore) {}

                CartManager.getInstance().clear();
                adapter.notifyDataSetChanged();
                updateTotal();
                showSuccessDialog();
            });
        }
    }

    private void updateTotal() {
        double total = CartManager.getInstance().getTotal();
        totalText.setText(String.format("Rp %.2f", total));
    }

    private void showSuccessDialog() {
        Dialog d = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        d.setContentView(R.layout.dialog_order_success);
        ImageButton done = d.findViewById(R.id.btnSuccessDone);
        if (done != null) done.setOnClickListener(v -> d.dismiss());

        // Prepare entrance
        View root = d.findViewById(android.R.id.content);
        if (root != null) {
            root.setAlpha(0f);
            root.setTranslationY(24f);
        }
        // Hero zoom-in
        ImageView hero = d.findViewById(R.id.successHero);
        if (hero != null) {
            hero.setScaleX(0.85f);
            hero.setScaleY(0.85f);
            hero.setAlpha(0f);
        }
        // Texts & CTA
        TextView title1 = d.findViewById(R.id.titleLine1);
        TextView title2 = d.findViewById(R.id.titleLine2);
        TextView subtitle = d.findViewById(R.id.successSubtitle);
        if (title1 != null) { title1.setAlpha(0f); title1.setTranslationY(16f); }
        if (title2 != null) { title2.setAlpha(0f); title2.setTranslationY(20f); }
        if (subtitle != null) { subtitle.setAlpha(0f); subtitle.setTranslationY(24f); }
        if (done != null) { done.setScaleX(0.7f); done.setScaleY(0.7f); done.setAlpha(0f); }

        d.show();

        if (root != null) {
            AnimatorSet set = new AnimatorSet();
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.setDuration(280);
            set.playTogether(
                    ObjectAnimator.ofFloat(root, View.ALPHA, 0f, 1f),
                    ObjectAnimator.ofFloat(root, View.TRANSLATION_Y, 24f, 0f)
            );
            set.start();
        }

        if (hero != null) {
            AnimatorSet zoom = new AnimatorSet();
            zoom.setInterpolator(new OvershootInterpolator(1.0f));
            zoom.setStartDelay(180);
            zoom.setDuration(700);
            zoom.playTogether(
                    ObjectAnimator.ofFloat(hero, View.SCALE_X, 0.85f, 1f),
                    ObjectAnimator.ofFloat(hero, View.SCALE_Y, 0.85f, 1f),
                    ObjectAnimator.ofFloat(hero, View.ALPHA, 0f, 1f)
            );
            zoom.start();
        }

        if (title1 != null) {
            AnimatorSet s = new AnimatorSet();
            s.setInterpolator(new AccelerateDecelerateInterpolator());
            s.setStartDelay(260);
            s.setDuration(380);
            s.playTogether(
                    ObjectAnimator.ofFloat(title1, View.ALPHA, 0f, 1f),
                    ObjectAnimator.ofFloat(title1, View.TRANSLATION_Y, 16f, 0f)
            );
            s.start();
        }
        if (title2 != null) {
            AnimatorSet s = new AnimatorSet();
            s.setInterpolator(new AccelerateDecelerateInterpolator());
            s.setStartDelay(340);
            s.setDuration(380);
            s.playTogether(
                    ObjectAnimator.ofFloat(title2, View.ALPHA, 0f, 1f),
                    ObjectAnimator.ofFloat(title2, View.TRANSLATION_Y, 20f, 0f)
            );
            s.start();
        }
        if (subtitle != null) {
            AnimatorSet s = new AnimatorSet();
            s.setInterpolator(new AccelerateDecelerateInterpolator());
            s.setStartDelay(420);
            s.setDuration(320);
            s.playTogether(
                    ObjectAnimator.ofFloat(subtitle, View.ALPHA, 0f, 1f),
                    ObjectAnimator.ofFloat(subtitle, View.TRANSLATION_Y, 24f, 0f)
            );
            s.start();
        }
        if (done != null) {
            AnimatorSet s = new AnimatorSet();
            s.setInterpolator(new OvershootInterpolator(1.1f));
            s.setStartDelay(500);
            s.setDuration(380);
            s.playTogether(
                    ObjectAnimator.ofFloat(done, View.SCALE_X, 0.7f, 1f),
                    ObjectAnimator.ofFloat(done, View.SCALE_Y, 0.7f, 1f),
                    ObjectAnimator.ofFloat(done, View.ALPHA, 0f, 1f)
            );
            s.start();
        }
    }
}
