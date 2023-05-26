package com.example.androidprojectprep25may.helper;

import static com.example.androidprojectprep25may.helper.Constants.PREF_USER_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;


public class SaveSharedPreference
{


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
}
