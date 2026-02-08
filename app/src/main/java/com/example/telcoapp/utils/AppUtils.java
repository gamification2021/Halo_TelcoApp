package com.example.telcoapp.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class AppUtils {
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    private AppUtils() {

    }

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = new Date().getTime();
        if (time > now || time <= 0) {
            return null;
        }

        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

//    public static RequestBody getPartFor(String stuff) {
//        return RequestBody.create(MediaType.parse("text/plain"), stuff);
//    }

    public static void share(String title, Context context) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");;
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Gamification");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, title);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public static String randomCodeGenerator(int lengthRequired){
        String code = "";
        int i = 0;
        while(i<lengthRequired){
            int randomNum = ThreadLocalRandom.current().nextInt(48,91);
            if(randomNum<58 || randomNum>64) {
                char alphabet = (char) randomNum;
                code+=alphabet;
                i++;
            }
        }
        return code;
    }

}
