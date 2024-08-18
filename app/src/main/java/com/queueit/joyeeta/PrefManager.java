package com.queueit.joyeeta;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private SharedPreferences sharedPreferences;
    public PrefManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences("user_not_first_time", 0);
    }
    public void saveLanguage(String language) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("language", language);
        editor.apply();
    }

    public String getLanguage() {
        return this.sharedPreferences.getString("language", "Bangla");
    }

}
