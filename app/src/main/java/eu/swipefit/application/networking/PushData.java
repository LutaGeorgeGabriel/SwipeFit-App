package eu.swipefit.application.networking;
/**
 * FILE DESCRIPTION
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import eu.swipefit.application.app.productsInfo.ProductsInformation;

/** ADD COMMENTS */
public class PushData {
    public static String formatJson() {
        JSONObject jsonObject = new JSONObject(ProductsInformation.getProductsInformation());
        try {
            return jsonObject.toString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*JSONObject root = new JSONObject();
        try {
            root.put("Android","Data from Android device");
            return root.toString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";*/
        return null;
    }

    public static void sendDataToServer() {
        final String json = formatJson();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return getServerResponse(json);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("Message response",s);
            }
        }.execute();
    }

    public static String getServerResponse(String json) {

        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://192.168.0.101:8080/test");
           conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(json);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream inputStream;
            // get stream
            if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }
            // parse stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
