package com.example.telcoapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.telcoapp.adapter.TelcoBannerAdapter;
import com.example.telcoapp.adapter.TelcoFeaturedAdapter;
import com.example.telcoapp.model.CartItem;
import com.example.telcoapp.model.Reward;
import com.example.telcoapp.recharge.PointTransferActivity;
import com.sixdee.cvm.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelcoActivity extends BaseActivity {

    public static ArrayList<Reward> rewardArrayList = new ArrayList<>();
    public static ArrayList<Reward> rewards = new ArrayList<>();
    public static int totalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        sdk.INSTANCE.initialiseSDK(this);
        sdk.INSTANCE.sendEvent("VIEW_PAGE", "Item Added to Cart productId 0 value 1 INR");
        Log.v("6DLOG", "VIEW_PAGE \t\tItem Added to Cart productId 0 value 1 INR");


//        String androidId = Settings.Secure.getString(
//                getContentResolver(),
//                Settings.Secure.ANDROID_ID
//        );
//        Log.e("TAG", "onCreate: "+androidId);
//        AdIdHelper.getAdId(this);
//        View t1 = findViewById(R.id.tile1);
//        View t2 = findViewById(R.id.tile2);
//        View t3 = findViewById(R.id.tile3);
//        View t4 = findViewById(R.id.tile4);
        View cta = findViewById(R.id.ctaContainer);
        View btnSearch = findViewById(R.id.btnSearchIcon);

        ImageView telco_cal = findViewById(R.id.telco_calendar);
        ImageView esim = findViewById(R.id.telco_esim);
        ImageView sim_card = findViewById(R.id.telco_sim_card);
        ImageView telco_offers = findViewById(R.id.telco_offers);
        ImageView cart = findViewById(R.id.cart);

        androidx.recyclerview.widget.RecyclerView featuredGrid = findViewById(R.id.featuredGrid);
        ViewPager bannerPager = findViewById(R.id.bannerPager);
        LinearLayout bannerDots = findViewById(R.id.bannerDots);

        View.OnClickListener openDetail = v -> {
            Intent i = new Intent(TelcoActivity.this, TelcoDetailActivity.class);
            startActivity(i);
        };

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelcoActivity.this, TelcoCartActivity.class));
            }
        });

//        if (t1 != null) t1.setOnClickListener(openDetail);
//        if (t2 != null) t2.setOnClickListener(openDetail);
//        if (t3 != null) t3.setOnClickListener(openDetail);
//        if (t4 != null) t4.setOnClickListener(openDetail);

        View.OnClickListener openSearch = v -> {
            Intent i = new Intent(TelcoActivity.this, TelcoSearchActivity.class);
            startActivity(i);
        };
        if (cta != null) cta.setOnClickListener(openSearch);
        if (btnSearch != null) btnSearch.setOnClickListener(openSearch);

        // Setup Banner ViewPager with indicator (2 slides)
        if (bannerPager != null && bannerDots != null) {
            List<Integer> images = Arrays.asList(
                    R.drawable.iphone_image,
                    R.drawable.samsung_galaxy
            );
            List<String> caps = Arrays.asList(
                    "Here Comes The iPhone 17  \n Bundle with IM3",
                    "Galaxy 24 Ultra  \n Upgrade with IM3"
            );
            TelcoBannerAdapter pagerAdapter = new TelcoBannerAdapter(this, images, caps);
            bannerPager.setAdapter(pagerAdapter);

            final Runnable renderDots = new Runnable() {
                @Override
                public void run() {
                    bannerDots.removeAllViews();
                    int count = pagerAdapter.getCount();
                    int current = bannerPager.getCurrentItem();
                    for (int i = 0; i < count; i++) {
                        ImageView dot = new ImageView(TelcoActivity.this);
                        dot.setImageResource(i == current ? R.drawable.dot_active : R.drawable.dot_inactive);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        lp.setMargins(8, 0, 8, 0);
                        dot.setLayoutParams(lp);
                        bannerDots.addView(dot);
                    }
                }
            };

            renderDots.run();

            bannerPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    renderDots.run();
                }
            });
        }

        // Setup Featured Items RecyclerView
        if (featuredGrid != null) {
            androidx.recyclerview.widget.GridLayoutManager glm = new androidx.recyclerview.widget.GridLayoutManager(this, 2);
            featuredGrid.setLayoutManager(glm);

            List<CartItem> items = new java.util.ArrayList<>();
            items.add(new CartItem("Mac", "Mac Book Air", "★ 4.5", 3.14, 1, R.drawable.mac_book, "Lightweight • Fast • Long Battery Life\n" +
                    "\n" +
                    "This MacBook Air is in great working condition. Smooth performance, premium design, and perfect for daily use like work, study, browsing, video calls, and entertainment.\n" +
                    "\n" +
                    "✔ Key Highlights\n" +
                    "\n" +
                    "Ultra-thin & lightweight\n" +
                    "\n" +
                    "Fast performance\n" +
                    "\n" +
                    "Long-lasting battery\n" +
                    "\n" +
                    "Clean display\n" +
                    "\n" +
                    "Ideal for students & professionals\n" +
                    "\n" +
                    "✔ Condition\n" +
                    "\n" +
                    "No issues\n" +
                    "\n" +
                    "Well-maintained\n" +
                    "\n" +
                    "Fully functional\n" +
                    "\n" +
                    "A perfect Apple laptop at a great price.\n" +
                    "\n" +
                    "If you want, I can customize this based on your:\n" +
                    "▶ Year (2017/2019/2020 M1 etc.)\n" +
                    "▶ RAM & Storage (8GB / 256GB etc.)\n" +
                    "▶ Colour\n" +
                    "▶ Condition (like new / slightly used)\n" +
                    "▶ Included accessories (charger, box)"));
            items.add(new CartItem("iphone 17 Pro", "iPhone 17 Pro", "Classic Rust Orange • ★ 4.5", 3.14, 1, R.drawable.iphone_image_one, "Ultra Fast • Stunning Camera • Titanium Build\n" +
                    "\n" +
                    "This iPhone 17 Pro is in excellent condition with smooth performance and no issues. Comes with an amazing camera system, long battery life, and a premium titanium design.\n" +
                    "\n" +
                    "✔ Key Highlights\n" +
                    "\n" +
                    "Super Retina XDR Display\n" +
                    "\n" +
                    "Powerful A18 Pro Chip\n" +
                    "\n" +
                    "Pro-Grade Camera (Night Mode, 8K Video, Portraits)\n" +
                    "\n" +
                    "Long Battery Backup\n" +
                    "\n" +
                    "Titanium Body – Lightweight & Durable\n" +
                    "\n" +
                    "5G Support\n" +
                    "\n" +
                    "✔ Condition\n" +
                    "\n" +
                    "Excellent condition\n" +
                    "\n" +
                    "No scratches / No dents (if applicable)\n" +
                    "\n" +
                    "100% functional\n" +
                    "\n" +
                    "Well maintained\n" +
                    "\n" +
                    "✔ What You Get\n" +
                    "\n" +
                    "iPhone 17 Pro\n" +
                    "\n" +
                    "Original charger (if included)\n" +
                    "\n" +
                    "Box (if included)\n" +
                    "\n" +
                    "A perfect premium phone for photography, gaming, work, and daily use."));
            items.add(new CartItem("Samsung", "Samsung Galaxy 24 Ultra", "with S pen • ★ 4.4", 2.69, 1, R.drawable.samsung_galaxy, "200MP Camera • Super Fast • Premium Design\n" +
                    "\n" +
                    "This Samsung Galaxy S24 Ultra is in great condition and works perfectly. It delivers top-end performance, an amazing camera, and a stunning display — perfect for gaming, photography, work, and everyday use.\n" +
                    "\n" +
                    "✔ Key Highlights\n" +
                    "\n" +
                    "200MP Pro Camera (Super clear photos & 8K video)\n" +
                    "\n" +
                    "Snapdragon 8 Gen 3 Processor\n" +
                    "\n" +
                    "5000mAh Long Battery Life\n" +
                    "\n" +
                    "S-Pen Support\n" +
                    "\n" +
                    "120Hz Dynamic AMOLED 2X Display\n" +
                    "\n" +
                    "5G Ready\n" +
                    "\n" +
                    "✔ Condition\n" +
                    "\n" +
                    "Excellent condition\n" +
                    "\n" +
                    "No damages / No issues\n" +
                    "\n" +
                    "Smooth performance\n" +
                    "\n" +
                    "Well-maintained device\n" +
                    "\n" +
                    "✔ What’s Included\n" +
                    "\n" +
                    "Samsung Galaxy S24 Ultra\n" +
                    "\n" +
                    "Charger (if included)\n" +
                    "\n" +
                    "Box (if included)\n" +
                    "\n" +
                    "A perfect flagship smartphone with powerful features and premium build quality."));
            items.add(new CartItem("Google", "Google Pixel", "10 Pro XL • ★ 4.6", 3.29, 1, R.drawable.google_pixel, "AI-Powered Camera • Smooth Performance • Clean Android\n" +
                    "\n" +
                    "This Google Pixel 10 Pro XL is in great condition and works flawlessly. Known for its amazing AI camera, fast performance, and pure Android experience — perfect for photography lovers, students, and professionals.\n" +
                    "\n" +
                    "✔ Key Highlights\n" +
                    "\n" +
                    "Advanced AI Camera System (Stunning photos & 8K video)\n" +
                    "\n" +
                    "Google Tensor G4 Processor\n" +
                    "\n" +
                    "Large Smooth Display (120Hz OLED)\n" +
                    "\n" +
                    "All-day Battery with fast charging\n" +
                    "\n" +
                    "Latest Android with direct Google updates\n" +
                    "\n" +
                    "5G Support\n" +
                    "\n" +
                    "✔ Condition\n" +
                    "\n" +
                    "Excellent condition\n" +
                    "\n" +
                    "No scratches / No dents\n" +
                    "\n" +
                    "100% functional\n" +
                    "\n" +
                    "Well maintained\n" +
                    "\n" +
                    "✔ What’s Included\n" +
                    "\n" +
                    "Google Pixel 10 Pro XL\n" +
                    "\n" +
                    "Charger (if included)\n" +
                    "\n" +
                    "Box (if included)\n" +
                    "\n" +
                    "A powerful flagship phone with clean software, sharp camera output, and top performance."));

            TelcoFeaturedAdapter adapter = new TelcoFeaturedAdapter(this, items);
            featuredGrid.setAdapter(adapter);
        }

        // Quick actions -> TelcoOffersActivity
        if (esim != null) {
            esim.setOnClickListener(v -> {
                Intent i = new Intent(TelcoActivity.this, PointTransferActivity.class);
                i.putExtra(TelcoOffersActivity.EXTRA_SECTION, "esim");
                startActivity(i);
            });
        }
        if (sim_card != null) {
            sim_card.setOnClickListener(v -> {
                Intent i = new Intent(TelcoActivity.this, TelcoOffersActivity.class);
                i.putExtra(TelcoOffersActivity.EXTRA_SECTION, "sim");
                startActivity(i);
            });
        }
        if (telco_cal != null) {
            telco_cal.setOnClickListener(v -> {
                Intent i = new Intent(TelcoActivity.this, TelcoOffersActivity.class);
                i.putExtra(TelcoOffersActivity.EXTRA_SECTION, "simpati");
                startActivity(i);
            });
        }
        if (telco_offers != null) {
            telco_offers.setOnClickListener(v -> {
                Intent i = new Intent(TelcoActivity.this, TelcoOffersActivity.class);
                i.putExtra(TelcoOffersActivity.EXTRA_SECTION, "offers");
                startActivity(i);
            });
        }

        // Launch-time animation: reveal CTA from left to right (0% -> 100%)
        View ctaContainer = cta;
        View searchIcon = btnSearch;
        if (ctaContainer != null) {
            ctaContainer.setAlpha(0f);
            ctaContainer.post(() -> {
                int h = ctaContainer.getHeight();
                int w = ctaContainer.getWidth();
                // Start fully clipped (0% visible)
                ViewCompat.setClipBounds(ctaContainer, new Rect(0, 0, 0, h));
                // Alpha fade-in
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(ctaContainer, View.ALPHA, 0f, 1f);
                fadeIn.setDuration(200);
                // Reveal by expanding clip bounds from 0 to full width
                ValueAnimator reveal = ValueAnimator.ofInt(0, w);
                reveal.addUpdateListener(anim -> {
                    int cw = (int) anim.getAnimatedValue();
                    ViewCompat.setClipBounds(ctaContainer, new Rect(0, 0, cw, h));
                });
                reveal.setInterpolator(new AccelerateDecelerateInterpolator());
                reveal.setDuration(600);
                // Play together
                AnimatorSet set = new AnimatorSet();
                set.playTogether(fadeIn, reveal);
                set.start();
            });
        }

        // Animate rest of the content aligned with CTA timing
        float dp = getResources().getDisplayMetrics().density;
        View heading = findViewById(R.id.heading);
        View subheading = findViewById(R.id.subheading);
        View bannerSection = findViewById(R.id.bannerSection);
        View quickRow = findViewById(R.id.quickActionsRow);
        View featuredHeader = findViewById(R.id.featuredHeader);
//        View featuredGrid = findViewById(R.id.featuredGrid);

        long baseDelay = 150; // starts shortly after CTA begins
        if (heading != null) {
            heading.setAlpha(0f);
            heading.setTranslationY(14f * dp);
            heading.post(() -> {
                ObjectAnimator a = ObjectAnimator.ofFloat(heading, View.ALPHA, 0f, 1f);
                ObjectAnimator t = ObjectAnimator.ofFloat(heading, View.TRANSLATION_Y, 14f * dp, 0f);
                AnimatorSet s = new AnimatorSet();
                s.setInterpolator(new AccelerateDecelerateInterpolator());
                s.setStartDelay(baseDelay);
                s.setDuration(350);
                s.playTogether(a, t);
                s.start();
            });
        }

        if (subheading != null) {
            subheading.setAlpha(0f);
            subheading.setTranslationY(16f * dp);
            subheading.post(() -> {
                ObjectAnimator a = ObjectAnimator.ofFloat(subheading, View.ALPHA, 0f, 1f);
                ObjectAnimator t = ObjectAnimator.ofFloat(subheading, View.TRANSLATION_Y, 16f * dp, 0f);
                AnimatorSet s = new AnimatorSet();
                s.setInterpolator(new AccelerateDecelerateInterpolator());
                s.setStartDelay(baseDelay + 120);
                s.setDuration(350);
                s.playTogether(a, t);
                s.start();
            });
        }

        if (bannerSection != null) {
            bannerSection.setAlpha(0f);
            bannerSection.setScaleX(0.97f);
            bannerSection.setScaleY(0.97f);
            bannerSection.post(() -> {
                ObjectAnimator a = ObjectAnimator.ofFloat(bannerSection, View.ALPHA, 0f, 1f);
                ObjectAnimator sx = ObjectAnimator.ofFloat(bannerSection, View.SCALE_X, 0.97f, 1.0f);
                ObjectAnimator sy = ObjectAnimator.ofFloat(bannerSection, View.SCALE_Y, 0.97f, 1.0f);
                AnimatorSet s = new AnimatorSet();
                s.setInterpolator(new AccelerateDecelerateInterpolator());
                s.setStartDelay(baseDelay + 220);
                s.setDuration(380);
                s.playTogether(a, sx, sy);
                s.start();
            });
        }

        if (quickRow != null) {
            quickRow.setAlpha(0f);
            quickRow.setTranslationY(18f * dp);
            quickRow.post(() -> {
                ObjectAnimator a = ObjectAnimator.ofFloat(quickRow, View.ALPHA, 0f, 1f);
                ObjectAnimator t = ObjectAnimator.ofFloat(quickRow, View.TRANSLATION_Y, 18f * dp, 0f);
                AnimatorSet s = new AnimatorSet();
                s.setInterpolator(new AccelerateDecelerateInterpolator());
                s.setStartDelay(baseDelay + 320);
                s.setDuration(360);
                s.playTogether(a, t);
                s.start();
            });
        }

        if (featuredHeader != null) {
            featuredHeader.setAlpha(0f);
            featuredHeader.setTranslationY(14f * dp);
            featuredHeader.post(() -> {
                ObjectAnimator a = ObjectAnimator.ofFloat(featuredHeader, View.ALPHA, 0f, 1f);
                ObjectAnimator t = ObjectAnimator.ofFloat(featuredHeader, View.TRANSLATION_Y, 14f * dp, 0f);
                AnimatorSet s = new AnimatorSet();
                s.setInterpolator(new AccelerateDecelerateInterpolator());
                s.setStartDelay(baseDelay + 420);
                s.setDuration(320);
                s.playTogether(a, t);
                s.start();
            });
        }

        if (featuredGrid != null) {
            featuredGrid.post(() -> {
                int count = (featuredGrid instanceof android.view.ViewGroup)
                        ? ((android.view.ViewGroup) featuredGrid).getChildCount() : 0;
                for (int i = 0; i < count; i++) {
                    View child = ((android.view.ViewGroup) featuredGrid).getChildAt(i);
                    if (child == null) continue;
                    child.setAlpha(0f);
                    child.setTranslationY(20f * dp);
                    ObjectAnimator a = ObjectAnimator.ofFloat(child, View.ALPHA, 0f, 1f);
                    ObjectAnimator t = ObjectAnimator.ofFloat(child, View.TRANSLATION_Y, 20f * dp, 0f);
                    AnimatorSet s = new AnimatorSet();
                    s.setInterpolator(new AccelerateDecelerateInterpolator());
                    s.setStartDelay(baseDelay + 480 + (i * 80L));
                    s.setDuration(320);
                    s.playTogether(a, t);
                    s.start();
                }
            });
        }

        Glide.with(this).load(R.drawable.calendar_telco).into(telco_cal);
        Glide.with(this).load(R.drawable.esim).into(esim);
        Glide.with(this).load(R.drawable.sim_card).into(sim_card);
        Glide.with(this).load(R.drawable.offers).into(telco_offers);
    }
}
