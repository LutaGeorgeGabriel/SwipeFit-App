package eu.swipefit.application.app.productsInfo;
/**
 * FILE DESCRIPTION
 */

import java.util.Arrays;

/** ADD COMMENTS */
public class ProductsInformation {

    // variable that stores the sate of the products (likes and dislikes)
    private static double[] productInformation = null;


    public static void initiateInfoArray(int size) {
        productInformation = new double[size];
        Arrays.fill(productInformation, 0.5);
    }

    public static double[] getProductInformation() {
        return productInformation;
    }

    // if the user likes the item then we set metadata to 1
    public static void like(int index) {
        productInformation[index] = 1;
    }

    // if the user doesn't like the item then we set metadata to 0
    public static void dislike(int index) {
        productInformation[index] = 0;
    }


}
