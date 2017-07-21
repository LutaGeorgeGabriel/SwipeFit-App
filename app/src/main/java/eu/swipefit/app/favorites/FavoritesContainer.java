package eu.swipefit.app.favorites;
/**
 * FILE DESCRIPTION
 */

import java.util.ArrayList;

import eu.swipefit.app.Product;
import eu.swipefit.app.swiping.ProductCard;

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
