package eu.swipefit.application.networking;
/**
 * FILE DESCRIPTION
 */

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

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
import java.util.HashMap;
import java.util.List;

import eu.swipefit.application.Product;
import eu.swipefit.application.app.favorites.FavoritesContainer;
import eu.swipefit.application.app.productsInfo.ProductsInformation;

/** ADD COMMENTS */
public class PushData {

    public static String URL_POST_BEHAVIOUR = null;
    public static String URL_POST_FAVORITES = null;

    public static void sendUserBehaviourToServer() {
        final String json = getJsonFromListOfProducts(ProductsInformation.getProductsInformation());
        final String type = "BEHAVIOUR";

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return getServerResponse(json,type);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("Message response",s);
            }
        }.execute();
    }

    public static void sendUserFavoritesToServer() {
        final String json = getJsonFromListOfFavorites(FavoritesContainer.getProducts());
        final String type = "FAVORITES";

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return getServerResponse(json,type);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("Message response",s);
            }
        }.execute();
    }

    public static String getServerResponse(String json,String type) {

        URL url = null;

        try {
            if(type.equals("BEHAVIOUR")) {
                url = new URL(URL_POST_BEHAVIOUR);
            } else if (type.equals("FAVORITES")) {
                url = new URL(URL_POST_FAVORITES);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection conn = null;

        try {

            assert url != null;
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

    /**
     * Preparing the data collected inside the app to be pushed to server
     *
     * In order to do this, I am converting the list of favorites to a JSON using
     * Gson
     *
     * */

    public static String getJsonFromListOfFavorites(List<Product> favorites) {
        Gson gson = new Gson();
        String jsonlistOfFavorites = gson.toJson(favorites);
        return jsonlistOfFavorites;
    }

    /**
     * Preparing the data collected inside the app to be pushed to server
     *
     * In order to do this, I am converting the user behaviour (which consists
     * in a HashMap where KEY = [ID OF THE PRODUCT] & VALUE = [USER BEHAVIOUR : 0 - DISLIKE , 1 - LIKE]
     *
     * I am returning a JSON in the form os a string from the HashMap by transforming it to a JSON object
     *
     * This is just another way of transforming data to JSON, apart from the previous one using Gson
     *
     * NOTE: jsonObject.toString(1) - return JSON as a String in a human readable form (preetyPrint)
     *       jsonObject.toString() - returns it without whitespaces
     *
     * */

    public static String getJsonFromListOfProducts(HashMap<String, String> productsInformation) {
        JSONObject jsonObject = new JSONObject(productsInformation);
        try {
            return jsonObject.toString(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
