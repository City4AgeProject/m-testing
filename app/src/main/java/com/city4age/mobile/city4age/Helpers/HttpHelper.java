package com.city4age.mobile.city4age.Helpers;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class HttpHelper {

    public static JSONObject getJSONFromUrl(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection;
        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setDoOutput(true);
        urlConnection.setConnectTimeout(5000);
        urlConnection.connect();

        int responseCode = urlConnection.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();

        urlConnection.disconnect();

        return new JSONObject(jsonString);
    }

    public static JSONObject postJSONToUrl(String urlString, JSONObject values) throws IOException, JSONException {
        HttpURLConnection urlConnection;
        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout( 5000 /*milliseconds*/ );
        urlConnection.setConnectTimeout( 5000 /* milliseconds */ );
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Connection", "Keep-Alive");
        urlConnection.setRequestProperty("Cache-Control", "no-cache");
        urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setFixedLengthStreamingMode(values.toString().getBytes().length);
        urlConnection.setRequestProperty("User-Agent","C4AMobile/1.0");
        urlConnection.setUseCaches(false);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);

        // Send POST output.
        DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
        writer.writeBytes(values.toString());
        writer.flush();

        int responseCode = urlConnection.getResponseCode();

        String response = "";
        if (responseCode == 200) {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line=br.readLine()) != null) {
                response+=line;
            }
            br.close();
        } else {
            response = "{}";
        }

        writer.close();
        urlConnection.disconnect();

        return new JSONObject(response);
    }
}
