package com.example.telcoapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telcoapp.adapter.TelcoSearchAdapter;

public class TelcoSearchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telco_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });

        RecyclerView recycler = findViewById(R.id.recyclerSearch);
        EditText et = findViewById(R.id.etSearch);
        TextView tvInitial = findViewById(R.id.tvInitial);
        TextView tvEmpty = findViewById(R.id.tvEmpty);
        TextView header = findViewById(R.id.resultHeader);

        if (recycler != null) {
            recycler.setLayoutManager(new LinearLayoutManager(this));
            java.util.List<TelcoSearchAdapter.Item> items = new java.util.ArrayList<>();
            items.add(new TelcoSearchAdapter.Item("MH Thamrin", "Sim Price: Rp 10", "Activation - 24 hrs", R.drawable.location));
            items.add(new TelcoSearchAdapter.Item("Jenderal Sudirman", "Sim Price: Rp 130", "Activation - 4 hrs", R.drawable.location));
            items.add(new TelcoSearchAdapter.Item("Letjen S. Parman", "Sim Price: Rp 98", "Activation - 2 hrs", R.drawable.location));
            items.add(new TelcoSearchAdapter.Item("Yos Sudarso", "Sim Price: Rp 98", "Activation - 30 mins", R.drawable.location));
            items.add(new TelcoSearchAdapter.Item("DI Panjaitan", "Sim Price: Rp 98", "Activation - 2 days", R.drawable.location));
            TelcoSearchAdapter adapter = new TelcoSearchAdapter(items, getApplicationContext());
            recycler.setAdapter(adapter);

            // Initial state
            if (header != null) header.setVisibility(View.GONE);
            if (recycler != null) recycler.setVisibility(View.GONE);
            if (tvInitial != null) tvInitial.setVisibility(View.VISIBLE);
            if (tvEmpty != null) tvEmpty.setVisibility(View.GONE);

            final boolean[] hasTyped = {false};
            if (et != null) {
                et.addTextChangedListener(new TextWatcher() {
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
                    @Override public void afterTextChanged(Editable s) {
                        String q = s == null ? "" : s.toString();
                        if (!hasTyped[0] && q.length() > 0) {
                            hasTyped[0] = true;
                        }
                        adapter.filter(q);
                        int countNow = adapter.getItemCount();
                        if (q.isEmpty()) {
                            if (!hasTyped[0]) {
                                // User hasn't started yet
                                if (tvInitial != null) tvInitial.setVisibility(View.VISIBLE);
                                if (tvEmpty != null) tvEmpty.setVisibility(View.GONE);
                            } else {
                                // Cleared input after typing -> show try again text
                                if (tvInitial != null) tvInitial.setVisibility(View.GONE);
                                if (tvEmpty != null) tvEmpty.setText("Search is empty. Try again.");
                                if (tvEmpty != null) tvEmpty.setVisibility(View.VISIBLE);
                            }
                            if (header != null) header.setVisibility(View.GONE);
                            if (recycler != null) recycler.setVisibility(View.GONE);
                        } else {
                            if (countNow > 0) {
                                if (header != null) header.setVisibility(View.VISIBLE);
                                if (recycler != null) recycler.setVisibility(View.VISIBLE);
                                if (tvInitial != null) tvInitial.setVisibility(View.GONE);
                                if (tvEmpty != null) tvEmpty.setVisibility(View.GONE);
                            } else {
                                if (header != null) header.setVisibility(View.GONE);
                                if (recycler != null) recycler.setVisibility(View.GONE);
                                if (tvInitial != null) tvInitial.setVisibility(View.GONE);
                                if (tvEmpty != null) tvEmpty.setText("No results. Try again.");
                                if (tvEmpty != null) tvEmpty.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        }
    }
}
