package com.example.seoultravel;

/**
 * Created by 성열 on 2016-06-12.
 */

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

public class RestNetwork {

    public static String readJSON(String request) {
        StringBuilder JSONdata = new StringBuilder();

        byte[] buffer = new byte[1024];

        try {
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    JSONdata.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return JSONdata.toString();
        }
    }
}