package com.example.telcoapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.adapter.TelcoSearchAdapter;
import com.example.telcoapp.utils.EventTracker;
import com.sixdee.cvm.sdk;

import java.util.ArrayList;
import java.util.List;

public class TelcoOffersActivity extends BaseActivity {

    public static final String EXTRA_SECTION = "section"; // esim | sim | simpati | offers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telco_offers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        RecyclerView recycler = findViewById(R.id.recycler);
        TextView title = findViewById(R.id.title);
        ImageView back = findViewById(R.id.backBtn);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        if (back != null) back.setOnClickListener(v -> finish());

        String section = getIntent().getStringExtra(EXTRA_SECTION);
        if (section == null) section = "offers";

        List<TelcoSearchAdapter.Item> items = new ArrayList<>();
        switch (section) {
            case "esim":
                title.setText("Recharge");
                items.add(new TelcoSearchAdapter.Item("Recharge Starter 1.5GB/day", "Rp 5", "Validity: 7 days", R.drawable.recharge_one));
                items.add(new TelcoSearchAdapter.Item("Recharge Power 2GB/day", "Rp 10", "Validity: 28 days", R.drawable.recharge_two));
                items.add(new TelcoSearchAdapter.Item("Recharge Global", "Rp 25", "Validity: 60 days", R.drawable.recharge_three));
                break;
            case "sim":
                title.setText("Buy Sim");
                items.add(new TelcoSearchAdapter.Item("SIM 1GB/day", "Rp 2", "Talktime: Rp 1", R.drawable.buy_sim_one));
                items.add(new TelcoSearchAdapter.Item("Halo+ Bundle", "Rp 20", "15GB + Calls", R.drawable.buy_sim_two));
                items.add(new TelcoSearchAdapter.Item("SIM 1.5GB/day", "Rp 10", "Calls + 2GB", R.drawable.buy_sim_three));
                break;
            case "simpati":
                title.setText("Buy Data Packs");
                items.add(new TelcoSearchAdapter.Item("Freedom Internet 5G 11GB / 3 Days", "Rp 1", "500MB + 50 mins", R.drawable.data_pack_one));
                items.add(new TelcoSearchAdapter.Item("Freedom Internet 5G 20GB / 7 Days", "Rp 6", "5GB + 200 mins", R.drawable.data_pack_two));
                items.add(new TelcoSearchAdapter.Item("Freedom Internet 5G 27GB / 30 Days", "Rp 18", "20GB + 1000 mins", R.drawable.data_pack_three));
                break;
            default:
                title.setText("Offers");
                items.add(new TelcoSearchAdapter.Item("3GB/30k Package {30 Days}", "Rp 30", "Extra 5GB on recharge", R.drawable.offers_one));
                items.add(new TelcoSearchAdapter.Item("Data Booster 3GB", "Rp 9", "10GB + Free SMS", R.drawable.offers_two));
                items.add(new TelcoSearchAdapter.Item("Student Offer", "Rp 9", "10GB + Free SMS", R.drawable.offers_three));
                items.add(new TelcoSearchAdapter.Item("Weekend Binge", "Rp 3", "Unlimited 12am-6am", R.drawable.offers_four));
                items.add(new TelcoSearchAdapter.Item("Night Data Pack", "Rp 6", "Unlimited 12am-6am", R.drawable.offers_five));
                break;
        }

        TelcoSearchAdapter adapter = new TelcoSearchAdapter(items, getApplicationContext());
        recycler.setAdapter(adapter);
        adapter.setOnItemClick(item -> showOrderDialog(item));
    }

    private void showOrderDialog(TelcoSearchAdapter.Item item) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_order_confirmation, null, false);
        ImageView hero = view.findViewById(R.id.heroCart);
        ImageView itemImage = view.findViewById(R.id.itemImage);
        TextView itemTitle = view.findViewById(R.id.itemTitle);
        TextView itemVariant = view.findViewById(R.id.itemVariant);
        TextView itemPrice = view.findViewById(R.id.itemPrice);
        TextView itemQty = view.findViewById(R.id.itemQty);
        TextView totalAmount = view.findViewById(R.id.totalAmount);
        TextView deliveryMethod = view.findViewById(R.id.deliveryMethod);
        TextView orderDate = view.findViewById(R.id.orderDate);
        TextView orderNumber = view.findViewById(R.id.orderNumber);
        TextView shippingAddress = view.findViewById(R.id.shippingAddress);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);

        // Populate data
        if (itemImage != null) itemImage.setImageResource(item.imageRes != 0 ? item.imageRes : R.drawable.bg_telco_mock_image);
        if (itemTitle != null) itemTitle.setText(item.title);
        if (itemVariant != null) itemVariant.setText("Black");
        if (itemPrice != null) itemPrice.setText(item.priceText);
        if (itemQty != null) itemQty.setText("Qty: 1");
        if (totalAmount != null) totalAmount.setText(item.priceText);
        if (deliveryMethod != null) deliveryMethod.setText("Express Shipping");
        if (orderDate != null) orderDate.setText("May 3, 2025");
        if (orderNumber != null) orderNumber.setText("#ORD-57321");
        if (shippingAddress != null) shippingAddress.setText("No. 28, Grogol, Jakarta Barat");

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnConfirm.setOnClickListener(v -> {
//            sdk.INSTANCE.sendEvent("CHECKOUT", "Checked out " + item.title);
            EventTracker.getInstance().trackEvent("CHECKOUT", item.title, "Checked out " + item.title);
            Log.v("6DLOG", "CHECKOUT \t\tChecked out " + item.title);
            dialog.dismiss();
            showSuccessDialog();
        });

        dialog.show();
    }

    private void showSuccessDialog() {
        Dialog d = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        d.setContentView(R.layout.dialog_order_success);
        ImageButton done = d.findViewById(R.id.btnSuccessDone);
        if (done != null) done.setOnClickListener(v -> d.dismiss());

        // Prepare animations
        // Fade/slide in the whole dialog content
        View root = d.findViewById(android.R.id.content);
        if (root != null) {
            root.setAlpha(0f);
            root.setTranslationY(24f);
        }
        // Zoom in the hero image with a nice overshoot
        ImageView hero = d.findViewById(R.id.successHero);
        if (hero != null) {
            hero.setScaleX(0.85f);
            hero.setScaleY(0.85f);
            hero.setAlpha(0f);
        }

        // Prepare for text and CTA animations
        TextView title1 = d.findViewById(R.id.titleLine1);
        TextView title2 = d.findViewById(R.id.titleLine2);
        TextView subtitle = d.findViewById(R.id.successSubtitle);
        if (title1 != null) { title1.setAlpha(0f); title1.setTranslationY(16f); }
        if (title2 != null) { title2.setAlpha(0f); title2.setTranslationY(20f); }
        if (subtitle != null) { subtitle.setAlpha(0f); subtitle.setTranslationY(24f); }
        if (done != null) { done.setScaleX(0.7f); done.setScaleY(0.7f); done.setAlpha(0f); }

        d.show();

        // Run animations after the dialog is shown so views are laid out
        if (root != null) {
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(root, View.ALPHA, 0f, 1f);
            ObjectAnimator slideUp = ObjectAnimator.ofFloat(root, View.TRANSLATION_Y, 24f, 0f);
            AnimatorSet set = new AnimatorSet();
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.setDuration(280);
            set.playTogether(fadeIn, slideUp);
            set.start();
        }

        if (hero != null) {
            ObjectAnimator sx = ObjectAnimator.ofFloat(hero, View.SCALE_X, 0.85f, 1f);
            ObjectAnimator sy = ObjectAnimator.ofFloat(hero, View.SCALE_Y, 0.85f, 1f);
            ObjectAnimator a = ObjectAnimator.ofFloat(hero, View.ALPHA, 0f, 1f);
            AnimatorSet zoom = new AnimatorSet();
            zoom.setInterpolator(new OvershootInterpolator(1.0f));
            zoom.setStartDelay(180);
            zoom.setDuration(700);
            zoom.playTogether(sx, sy, a);
            zoom.start();
        }

        // Animate titles
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
