package com.example.telcoapp.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.telcoapp.api.RetrofitClient;
import com.example.telcoapp.model.EventData;
import com.example.telcoapp.model.EventRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventTracker {
    private static final String TAG = "EventTracker";
    private static EventTracker instance;
    private String deviceId;
    private String eventTrackingId;
    private String msisdn;

    private EventTracker() {
    }

    public static synchronized EventTracker getInstance() {
        if (instance == null) {
            instance = new EventTracker();
        }
        return instance;
    }

    public void initialize(Context context, String eventTrackingId, String msisdn) {
        this.deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        this.eventTrackingId = eventTrackingId;
        this.msisdn = msisdn;
        Log.d(TAG, "EventTracker initialized with deviceId: " + deviceId);
    }

    public void trackEvent(String eventName, String product, String description) {
//        if (deviceId == null || eventTrackingId == null || msisdn == null) {
//            Log.e(TAG, "EventTracker not initialized. Call initialize() first.");
//            return;
//        }

        String eventTime = String.valueOf(System.currentTimeMillis());

        EventData eventData = new EventData(eventName, eventTime, product, deviceId, description);
        EventRequest eventRequest = new EventRequest(eventTrackingId, deviceId, msisdn, eventData);

        Log.d(TAG, "========== EVENT TRACKING REQUEST ==========");
        Log.d(TAG, "Event Tracking ID: " + eventTrackingId);
        Log.d(TAG, "Device ID: " + deviceId);
        Log.d(TAG, "MSISDN: " + msisdn);
        Log.d(TAG, "Event Name: " + eventName);
        Log.d(TAG, "Event Time: " + eventTime);
        Log.d(TAG, "Product: " + product);
        Log.d(TAG, "Description: " + description);
        Log.d(TAG, "===========================================");

        sendEventToServer(eventRequest);
    }

    private void sendEventToServer(EventRequest eventRequest) {
        RetrofitClient.getInstance().getEventApiService().sendEvent(eventRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                Log.d(TAG, "========== EVENT TRACKING RESPONSE ==========");
                if (response.isSuccessful()) {
                    Log.d(TAG, "✓ Event sent successfully");
                    Log.d(TAG, "Response Code: " + response.code());
                    Log.d(TAG, "Event Name: " + eventRequest.getEvent().getEventName());
                    Log.d(TAG, "Message: SUCCESS");
                } else {
                    Log.e(TAG, "✗ Event send failed");
                    Log.e(TAG, "Response Code: " + response.code());
                    Log.e(TAG, "Response Message: " + response.message());
                    try {
                        if (response.errorBody() != null) {
                            Log.e(TAG, "Error Body: " + response.errorBody().string());
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Could not read error body", e);
                    }
                }
                Log.d(TAG, "============================================");
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e(TAG, "========== EVENT TRACKING FAILURE ==========");
                Log.e(TAG, "✗ Network/Connection Error");
                Log.e(TAG, "Error: " + t.getMessage());
                Log.e(TAG, "Event Name: " + eventRequest.getEvent().getEventName());
                Log.e(TAG, "===========================================");
                t.printStackTrace();
            }
        });
    }

    public void setEventTrackingId(String eventTrackingId) {
        this.eventTrackingId = eventTrackingId;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getEventTrackingId() {
        return eventTrackingId;
    }

    public String getMsisdn() {
        return msisdn;
    }
}
