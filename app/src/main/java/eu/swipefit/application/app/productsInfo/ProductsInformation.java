package eu.swipefit.application.app.productsInfo;
/**
 * FILE DESCRIPTION
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/** ADD COMMENTS */
public class ProductsInformation {

    // variable that stores the sate of the products (likes and dislikes)
    //private static double[] productInformation = null;

    public static HashMap<String, String> getProductsInformation() {
        Iterator it = productsInformation.entrySet().iterator();
        /*while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            if(pair.getValue().toString().equals("0") || pair.getValue().toString().equals("1")) {
                // do nothing
            }
            else {
                pair.setValue()
            }
            it.remove(); // avoids a ConcurrentModificationException
        }*/
        return productsInformation;
    }

    private static HashMap<String,String> productsInformation = new HashMap<>();

    // this variable should be reinitialized after POST method
    private static ArrayList<String> cardsIds = new ArrayList<>();

    public static void initializeMap(int size) {
        for (int index = 1; index <= size; index++) {
            productsInformation.put(String.valueOf(index),"0.5");
        }
    }


    /*public static void initiateInfoArray(int size) {
        productInformation = new double[size];
        Arrays.fill(productInformation, 0.5);
    }*/

    /*public static double[] getProductInformation() {
        return productInformation;
    }*/

    // if the user likes the item then we set metadata to 1
    public static void like(int index) {
        //productInformation[index] = 1;
        productsInformation.put(cardsIds.get(index),"1");
    }

    // if the user doesn't like the item then we set metadata to 0
    public static void dislike(int index) {
        //productInformation[index] = 0;
        productsInformation.put(cardsIds.get(index),"0");
    }

    public static void setCardId(String id) {
        cardsIds.add(id);
    }


}
