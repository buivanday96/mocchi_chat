package com.example.appchatdemo.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class SharePrefUtils {
    private final SharedPreferences preferences;
    private final String DefPreferenceName = "wwf_app_reference";
    private SharedPreferences.Editor editor;

    public SharePrefUtils(Context context) {
        this.preferences = context.getSharedPreferences(DefPreferenceName,
                Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public void putString(String key, String value) {
        if (editor != null) {
            editor.putString(key, value);
        }
        if (value == null) {
            preferences.edit().remove(key).commit();
        } else {
            preferences.edit().putString(key, value).commit();
        }
    }

    public String getString(String key) {
        if (preferences.contains(key)) {
            return preferences.getString(key, "");
        }
        return null;
    }

    public void putInt(String key, int value) {
        if (editor != null) {
            editor.putInt(key, value);
        }
        preferences.edit().putInt(key, value).commit();
    }

    public int getInt(String key) {
        if (preferences.contains(key)) {
            return preferences.getInt(key, -1);
        }
        return -1;
    }

    public void putLong(String key, long value) {
        if (editor != null) {
            editor.putLong(key, value);
        }
        preferences.edit().putLong(key, value).commit();
    }

    public long getLong(String key) {
        if (preferences.contains(key)) {
            return preferences.getLong(key, -1);
        }
        return -1;
    }

    public void putFloat(String key, float value) {
        if (editor != null) {
            editor.putFloat(key, value);
        }
        preferences.edit().putFloat(key, value).commit();
    }

    public float getFloat(String key) {
        if (preferences.contains(key)) {
            return preferences.getFloat(key, -1f);
        }
        return -1f;
    }

    public void putBoolean(String key, boolean value) {
        if (editor != null) {
            editor.putBoolean(key, value);
        }
        preferences.edit().putBoolean(key, value).commit();
    }

    public boolean getBoolean(String key) {
        if (preferences.contains(key)) {
            return preferences.getBoolean(key, false);
        }
        return false;
    }

    public <T> void putObject(String key, T data, boolean commit, Gson gson) {

        if (editor != null) {
            if (commit) {
                editor.putString(key, gson.toJson(data)).commit();
            } else {
                editor.putString(key, gson.toJson(data)).apply();
            }
        }

        if (commit) {
            preferences.edit().putString(key, gson.toJson(data)).commit();
        } else {
            preferences.edit().putString(key, gson.toJson(data)).apply();
        }
    }

    public <T> T getObject(String key, Class<T> anonymousClass, Gson gson) {
        return gson.fromJson(preferences.getString(key, ""), anonymousClass);
    }

    public <T> T getObject(String key, Type type, Gson gson) {
        return gson.fromJson(preferences.getString(key, ""), type);
    }


    public void removeByKey(String key) {
        if (key != null && key != "" && preferences.contains(key)) {
            preferences.edit().remove(key).commit();
        }
    }

    public boolean containsKey(String key) {
        return preferences.contains(key);
    }

    public void clear() {
        preferences.edit().clear().commit();
    }

}
