package Network;
import android.content.ContentValues;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RequestHttpURLConnection {

    public String request(String _url, ContentValues _params){

        HttpURLConnection urlConnection = null;
        StringBuffer stringBuffer = new StringBuffer();

        if(_params == null)
            stringBuffer.append("");
        else{
            boolean isAnd = false;
            String key;
            String value;

            for(Map.Entry<String, Object> parameter : _params.valueSet()){
                key = parameter.getKey();
                value = parameter.getValue().toString();

                if(isAnd)
                    stringBuffer.append("&");

                stringBuffer.append(key).append("=").append(value);

                if(!isAnd)
                    if(_params.size() >= 2)
                        isAnd = true;
            }
        }

        try{
            URL url = new URL(_url);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setRequestProperty("Context_Type","application/x-www-form-urlencoded;charset=UTF-8");

            String strParams = stringBuffer.toString();
            OutputStream os = urlConnection.getOutputStream();
            os.write(strParams.getBytes("UTF-8"));
            os.flush();
            os.close();

            if(urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String line;
            String page = "";

            while ((line = reader.readLine()) != null){
                page += line;
            } return page;
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return null;
    }
}
