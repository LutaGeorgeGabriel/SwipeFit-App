package eu.swipefit.application.app.favorites;
/**
 * FILE DESCRIPTION
 */

import java.util.ArrayList;

import eu.swipefit.application.app.swiping.ProductCard;
import eu.swipefit.app.Product;

/** ADD COMMENTS */
public class FavoritesContainer {

    private static ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void addFavroiteCard(Product product) {
        products.add(product);
    }
}
