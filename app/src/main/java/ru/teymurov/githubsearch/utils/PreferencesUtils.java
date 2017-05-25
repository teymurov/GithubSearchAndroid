package ru.teymurov.githubsearch.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {
    private static final String PREF_NAME = "ru.teymurov.githubsearch";
    private static final String TOKEN = "token";

    private final SharedPreferences sharedPreferences;

    public PreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

    public void setToken(String value) {
        sharedPreferences.edit().putString(TOKEN, value).apply();
    }
}
