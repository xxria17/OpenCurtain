package Network;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class Request {
    private URL url;
    private String method;
    private String token;

    public Request(URL url, String method){
        this.url = url;
        this.method = method;
        this.token = null;
    }

    public void setUrl(URL url){this.url= url; }
    public void setToken(String token){this.token = token;}

    class AsyncRequestCall extends AsyncTask<JSONObject, Void, JSONObject>{
        private RequestHandler handler;
        private int code = -1;

        protected AsyncRequestCall(RequestHandler handler){
            this.handler = handler;
        }

        @Override
        protected JSONObject doInBackground(JSONObject... objects){
            JSONObject result = null;
            try{
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( comatible) ");
                connection.setRequestProperty("Accept","*/*");

                if(token != null){
                    connection.setRequestProperty("X-App-Token",token);
                }

                if(objects != null && objects[0] != null){
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content=Type","application/json");
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(objects[0].toString().getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                    outputStream.close();
                }

                connection.connect();

                code = connection.getResponseCode();
                if(code == HttpURLConnection.HTTP_OK){
                    String line = null;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                    StringBuilder builder = new StringBuilder();
                    while((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    result = new JSONObject(builder.toString());
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

    public void execute(RequestHandler handler){
        execute(handler, null);
    }

    public void execute(RequestHandler handler, JSONObject object){
        AsyncRequestCall asyncRequestCall = new AsyncRequestCall(handler);
        asyncRequestCall.execute(object);
    }
}
