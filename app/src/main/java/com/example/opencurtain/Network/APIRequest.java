package com.example.opencurtain.Network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class APIRequest {
    private URL url;
    private String method;
    private String token;

    private static class APIRequestHolder {
        public static final APIRequest instance = new APIRequest();
    }
    public static APIRequest getInstance() {
        return APIRequestHolder.instance;
    }

    private String cookieString;
    private boolean isConnectionPersist;

    public APIRequest() {
        cookieString = "";
        isConnectionPersist = false;
    }

//    private String uri = "http://opencurtain.run.goorm.io/";

//    public APIRequest(URL url, String method){
//        this.url = url;
//        this.method = method;
//        this.token = null;
//    }

//    public APIRequest(API api, Method method) throws MalformedURLException {
//        this.url = new URL(api.getEndPoint());
//        this.method = method.getMethod();
//        this.token = null;
//    }

    public void setUrl(URL url){this.url= url; }
    public void setToken(String token){this.token = token;}

    class AsyncRequestCall extends AsyncTask<JSONObject, Void, JSONObject>{
        private RequestHandler handler;
        private int code = -1;
        protected AsyncRequestCall(RequestHandler handle){
            handler = handle;
        }

        @Override
        protected JSONObject doInBackground(JSONObject... objects){
            JSONObject result = null;
            try{
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
//                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( comatible) ");
//                connection.setRequestProperty("connection", "keep-alive");
                connection.setRequestProperty("Accept","application/json");
                connection.setUseCaches(false);
                connection.setDefaultUseCaches(false);
                if(token != null){
                    connection.setRequestProperty("X-App-Token",token);
                }

                Log.d("cookie status", cookieString);
                Log.d("session status", String.valueOf(isConnectionPersist));
                if(isConnectionPersist) {
                    connection.setRequestProperty("Cookie", cookieString);
                }

                if(objects != null && objects[0] != null){
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type","application/json");
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(objects[0].toString().getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                    outputStream.close();
                }

                connection.connect();

                code = connection.getResponseCode();
                Map<String, List<String>> header = connection.getHeaderFields();
                if(header.containsKey("Set-Cookie")) {
                    List<String> cookie = header.get("Set-Cookie");
                    for(int i = 0; i < cookie.size(); i++) {
                        cookieString += cookie.get(i);
                    }
                    isConnectionPersist = true;
                    Log.d("cookieeeeee", cookieString);
                }
                if(code == HttpURLConnection.HTTP_OK){
                    result = new JSONObject();
                    result.put("HTTP_CODE", code);
                    String line = null;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                    StringBuilder builder = new StringBuilder();
                    while((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    result.put("BODY", builder.toString());
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e){
                code = 200;
                e.printStackTrace();
            }
            return result;
        }



        @Override
        protected void onPostExecute(JSONObject jsonObject){
            super.onPostExecute(jsonObject);
            if(jsonObject != null){
                handler.onRequestOK(jsonObject);
            } else{
                handler.onRequestErr(code);
            }
        }
    }

    class AsyncArrayRequestCall extends AsyncTask<JSONObject, Void, JSONObject>{
        private RequestHandler handler;
        private int code = -1;
        protected AsyncArrayRequestCall(RequestHandler handle){
            handler = handle;
        }

        @Override
        protected JSONObject doInBackground(JSONObject... objects){
            JSONObject result = null;
            try{
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod(method);
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( comatible) ");
                connection.setRequestProperty("Accept","application/json");
                connection.setUseCaches(false);
                connection.setDefaultUseCaches(false);
                if(token != null){
                    connection.setRequestProperty("X-App-Token",token);
                }

                if(objects != null && objects[0] != null){
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type","application/json");
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(objects[0].toString().getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                    outputStream.close();
                }

                connection.connect();

                code = connection.getResponseCode();
                Map<String, List<String>> header = connection.getHeaderFields();
                if(header.containsKey("Set-Cookie")) {
                    List<String> cookie = header.get("Set-Cookie");
                    for(int i = 0; i < cookie.size(); i++) {
                        cookieString += cookie.get(i);
                    }
                    isConnectionPersist = true;
                }

                code = connection.getResponseCode();
                if(code == HttpURLConnection.HTTP_OK){
                    result = new JSONObject();
                    result.put("HTTP_CODE", code);
                    String line = null;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                    StringBuilder builder = new StringBuilder();
                    while((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    JSONObject obj = new JSONObject(builder.toString());
                    result.put("BODY_JSON", obj);
                }
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e){
                code = 200;
                e.printStackTrace();
            }
            return result;
        }



        @Override
        protected void onPostExecute(JSONObject jsonObject){
            super.onPostExecute(jsonObject);
            if(jsonObject != null){
                handler.onRequestOK(jsonObject);
            } else{
                handler.onRequestErr(code);
            }
        }
    }

//    public void execute(API api, Method method, RequestHandler handler) throws MalformedURLException {
//        execute(api.getEndPoint(), method, handler, null);
//    }

    public void execute(String string, Method method, RequestHandler handler) throws MalformedURLException {
        execute(string, method, handler, null);
    }
    public void execute(String string, Method method, RequestHandler handler, JSONObject object) throws MalformedURLException {
        this.url = new URL(string);
        this.method = method.getMethod();
        this.token = null;
        AsyncRequestCall asyncRequestCall = new AsyncRequestCall(handler);
        asyncRequestCall.execute(object);
    }

    public void executeArray(RequestHandler handler, JSONObject object){
        AsyncArrayRequestCall asyncArrayRequestCall = new AsyncArrayRequestCall(handler);
        asyncArrayRequestCall.execute(object);
    }
}
