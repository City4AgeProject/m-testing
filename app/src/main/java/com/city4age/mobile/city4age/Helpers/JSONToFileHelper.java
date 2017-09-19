package com.city4age.mobile.city4age.Helpers;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */

public class JSONToFileHelper {
    static String fileName = "FinishedActivities.json";

    public static void saveData(Context context, String mJsonResponse) {
        try {
            String folderPathExternal = Environment.getExternalStorageDirectory().getPath();
            String folderPathInternal = context.getFilesDir().getPath();
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
}
