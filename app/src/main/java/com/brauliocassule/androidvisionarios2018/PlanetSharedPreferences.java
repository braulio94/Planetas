package com.brauliocassule.androidvisionarios2018;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PlanetSharedPreferences {

    public static void savePlanetList(List<Planet> planets, Context context, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(planets);
        editor.putString(key, json);
        editor.apply();
    }

    public static List<Planet> loadPlanetList(Context context, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(key, null);
        Type type = new TypeToken<List<Planet>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
