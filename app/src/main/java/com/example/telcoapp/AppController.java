package com.example.telcoapp;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.telcoapp.utils.sharedPref.PrefKeys;
import com.example.telcoapp.utils.sharedPref.PrefUtils;

import java.util.HashMap;
import java.util.Map;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class AppController extends Application implements ComponentCallbacks2 {

    private static AppController mInstance;
    com.example.telcoapp.utils.sharedPref.PrefUtils prefUtils;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        prefUtils = PrefUtils.getInstance(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Livvic-Medium.ttf")
                                .setFontAttrId(io.github.inflationx.calligraphy3.R.attr.fontPath)
                                .build()))
                .build());

//        FirebaseApp.initializeApp(this);
//        setupPresenceTracking();
    }
//
//    private void setupPresenceTracking() {
//        Log.d("TAG", "setupPresenceTracking:  "+prefUtils.getStringValue(PrefKeys.MSISDN,"testUser"));
//        String userId =  prefUtils.getStringValue(PrefKeys.MSISDN,"testUser");
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference statusRef = database.getReference("users_status/" + userId);
//
//        Map<String, Object> onlineStatus = new HashMap<>();
//        onlineStatus.put("online", true);
//        onlineStatus.put("last_active", ServerValue.TIMESTAMP);
//
//        Map<String, Object> offlineStatus = new HashMap<>();
//        offlineStatus.put("online", false);
//        offlineStatus.put("last_active", ServerValue.TIMESTAMP);
//
//        DatabaseReference connectedRef = database.getReference(".info/connected");
//
//        connectedRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                Boolean connected = snapshot.getValue(Boolean.class);
//                if (connected != null && connected) {
//                    statusRef.onDisconnect().setValue(offlineStatus);
//                    statusRef.setValue(onlineStatus);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

