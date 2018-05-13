package com.city4age.mobile.city4age.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class JSONToFileHelper {

    static String fileName = "FinishedActivities.json";

    public static void saveData(Context context, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public static String getData(Context context) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            //check whether file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void saveMap(Context context, Map<Integer,Integer> inputMap){
        SharedPreferences pSharedPref = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        if (pSharedPref != null){
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<Integer, Integer> entry : inputMap.entrySet())
            {
                try {
                    jsonObject.put(String.valueOf(entry.getKey()), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("favorites_map").commit();
            editor.putString("favorites_map", jsonString);
            editor.commit();
        }
    }

    public static Map<Integer,Integer> loadMap(Context context){
        Map<Integer,Integer> outputMap = new HashMap<>();
        SharedPreferences pSharedPref = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
        try{
            if (pSharedPref != null){
                String jsonString = pSharedPref.getString("favorites_map", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    outputMap.put(Integer.parseInt(key), Integer.parseInt(jsonObject.getString(key)));
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return outputMap;
    }
}
