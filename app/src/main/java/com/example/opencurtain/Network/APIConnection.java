package com.example.opencurtain.Network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIConnection {

    public static String connect(URL url) throws MalformedURLException, IOException{
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if(conn.getResponseCode() != conn.HTTP_OK){
            Log.i("통신 결과", conn.getResponseCode() + "에러");
            return null;
        }

        InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
        BufferedReader reader = new BufferedReader(tmp);
        StringBuffer buffer = new StringBuffer();

        String str;
        while ((str = reader.readLine()) != null){
            buffer.append(str);
        }

        reader.close();
        return buffer.toString();
    }
}
