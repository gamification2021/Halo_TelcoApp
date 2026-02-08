package com.example.telcoapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telcoapp.utils.sharedPref.PrefKeys;
import com.example.telcoapp.utils.sharedPref.PrefUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class BaseActivity extends AppCompatActivity {

    PrefUtils prefUtils;

//    LocalizationActivityDelegate localizationActivityDelegate = new LocalizationActivityDelegate(this);

    private @Nullable String getUserId() {
        // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user = prefUtils.getStringValue(PrefKeys.MSISDN,"testUser");
        if (user == null) return null;
        return user;
    }

//    private @Nullable DatabaseReference getStatusRef() {
//        String uid = getUserId();
//        if (uid == null) return null;
//        return FirebaseDatabase.getInstance().getReference("users_status/" + uid);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        localizationActivityDelegate.addOnLocaleChangedListener(this);
//        localizationActivityDelegate.onCreate();
        prefUtils = PrefUtils.getInstance(getApplicationContext());

        super.onCreate(savedInstanceState);
    }




    @Override
    public void onResume() {
        super.onResume();
//        DatabaseReference ref = getStatusRef();
//        if (ref == null) return;

        Map<String, Object> data = new HashMap<>();
        data.put("online", true);
//        data.put("last_active", ServerValue.TIMESTAMP);

//        ref.updateChildren(data);

    }

    @Override
    protected void onPause() {
        super.onPause();
//        DatabaseReference ref = getStatusRef();
//        if (ref == null) return;

        Map<String, Object> data = new HashMap<>();
        data.put("online", false);
//        data.put("last_active", ServerValue.TIMESTAMP);

//        ref.updateChildren(data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap((newBase)));
    }

//    @Override
//    public void onAfterLocaleChanged() {
//
//    }

//    @Override
//    public void onBeforeLocaleChanged() {
//
//    }
}
