package com.example.telcoapp;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

public class AdIdHelper {

    public static void getAdId(Context context) {
        new Thread(() -> {
            try {
                AdvertisingIdClient.Info adInfo =
                        AdvertisingIdClient.getAdvertisingIdInfo(context);

                if (adInfo == null) {
                    Log.d("GAID", "AdInfo is null");
                    return;
                }

                String adId = adInfo.getId();
                boolean limitAdTracking =
                        adInfo.isLimitAdTrackingEnabled();

                if (adId == null || adId.equals("00000000-0000-0000-0000-000000000000")) {
                    Log.d("GAID", "Advertising ID unavailable or deleted");
                    return;
                }

                Log.d("GAID", "Ad ID: " + adId);
                Log.d("GAID", "Limit Ad Tracking: " + limitAdTracking);

                // ✅ Use adId ONLY if limitAdTracking == false

            } catch (Exception e) {
                Log.e("GAID", "Failed to get Advertising ID", e);
            }
        }).start();
    }
}

