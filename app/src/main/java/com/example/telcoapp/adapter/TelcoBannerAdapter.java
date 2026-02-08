package com.example.telcoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.telcoapp.R;

import java.util.List;

public class TelcoBannerAdapter extends PagerAdapter {

    private final Context context;
    private final List<Integer> imageRes;
    private final List<String> captions;

    public TelcoBannerAdapter(Context context, List<Integer> imageRes, List<String> captions) {
        this.context = context;
        this.imageRes = imageRes;
        this.captions = captions;
    }

    @Override
    public int getCount() {
        return imageRes == null ? 0 : imageRes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_telco_banner, container, false);
        ImageView img = v.findViewById(R.id.bannerImage);
        TextView caption = v.findViewById(R.id.bannerCaption);
        img.setImageResource(imageRes.get(position));
        if (captions != null && position < captions.size()) {
            caption.setText(captions.get(position));
        } else {
            caption.setText("");
        }
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
