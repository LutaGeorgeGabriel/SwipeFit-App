package eu.swipefit.application.networking;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import eu.swipefit.application.Product;
import eu.swipefit.application.app.favorites.FavoritesContainer;
import eu.swipefit.application.app.productsInfo.ProductsInformation;

public class PushData {

    /**
     * Fields that contain the endpoint to which the app connects to in orded to push the data
     * */

    public static String URL_POST_BEHAVIOUR = null;
    public static String URL_POST_FAVORITES = null;

    public static void sendUserBehaviourToServer() {
        final String json = getJsonFromListOfProducts(ProductsInformation.getProductsInformation());
        final String type = "BEHAVIOUR";

        new AsyncTask<Void, Void, String>() {

            /**
             * As network request are not allowed on Main thread also known as UI thread,
             * I instantiate a new AsyncTask that has three generic parameters
             *
             * <INPUT TYPE, PROGRESS TYPE, RETURN TYPE>
             *
             * */

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

            /**
             * As network request are not allowed on Main thread also known as UI thread,
             * I instantiate a new AsyncTask that has three generic parameters
             *
             * <INPUT TYPE, PROGRESS TYPE, RETURN TYPE>
             *
             * */

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

    /**
     * This method is used for two different requests:
     *
     * I set up a type in sendUserFavoritesToServer and sendUserBehaviourToServer methods
     *
     * If the type is BEHAVIOUR, then I ask the server to accept my POST request with the
     * data which is user behaviour
     *
     *
     * If the type is FAVORITES, then I ask the server to accept my POST request with the
     * data which is user favorites
     *
     * */

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

            /**
             * We assert the URL to be sure that is a valid one (not null)
             * */
            assert url != null;
            conn = (HttpURLConnection) url.openConnection();
            /**
             * We set the read timeout to be 10 seconds
             * */
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");

            /**
             * Here I am specifying that the connection should accept INPUT to send data as well as
             * OUTPUT to get the response from the server
             * */
            conn.setDoInput(true);
            conn.setDoOutput(true);

            /**
             * Here I am specifying the the header just denotes what the content is encoded in
             *
             * Content-type: application/json; charset=utf-8 designates the content to be in JSON format,
             * encoded in the UTF-8 character encoding. Designating the encoding is somewhat redundant for
             * JSON, since the default (only?) encoding for JSON is UTF-8. So in this case the receiving
             * server apparently is happy knowing that it's dealing with JSON and assumes that the encoding
             * is UTF-8 by default, that's why it works with or without the header.
             * */
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            /**
             * Here is where I actually POST the data to the server
             * */
            writer.write(json);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * This portion of code gets the response of the server which will represent the return data
         * of the method
         * */

        try {
            InputStream inputStream;
            // get stream
            if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            /**
             * Here we parse the stream sent by the server from inputStream to String
             * */

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }

            /**
             * We construct the response here
             * */

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
