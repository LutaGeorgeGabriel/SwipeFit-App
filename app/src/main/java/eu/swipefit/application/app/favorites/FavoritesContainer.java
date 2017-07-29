package eu.swipefit.application.app.favorites;
/**
 * FILE DESCRIPTION
 */

import java.util.ArrayList;
import java.util.List;

import eu.swipefit.application.Product;


/** ADD COMMENTS */
public class FavoritesContainer {

    private static List<Product> products = new ArrayList<>();

    public static List<Product> getProducts() {
        return products;
    }

    public static void addFavroiteCard(Product product) {
        // for every new item in the favorites collection
        for (Product prod: products) {
            // if the new foavorite item has the same id as an existing one from the list
            if(product.getID().equals(prod.getID()))
                // do not add that product as it is already there
                return;
        }
        products.add(product);
    }

    public static void setProducts(List<Product> products) {
        FavoritesContainer.products = products;
    }
}
