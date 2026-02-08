package com.example.telcoapp.utils.sharedPref;

import android.content.Context;


public class PrefHelper {

    public static void setUserLoggedIn(Context context, String email, String name) {
        PrefUtils preferences = PrefUtils.getInstance(context);
        preferences.setValue(PrefKeys.USER_EMAIL, email);
        preferences.setValue(PrefKeys.USER_NAME, name);
    }

    public static void setUserLoggedOut(Context context) {
        PrefUtils preferences = PrefUtils.getInstance(context);
        preferences.removeKey(PrefKeys.USER_EMAIL);
        preferences.removeKey(PrefKeys.USER_NAME);
    }
}
