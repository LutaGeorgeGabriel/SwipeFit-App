package eu.swipefit.app.app.favorites;
/**
 * FILE DESCRIPTION
 */

import java.util.ArrayList;

import eu.swipefit.app.app.swiping.ProductCard;

/** ADD COMMENTS */
public class FavoritesContainer {

    private static ArrayList<ProductCard> cards = new ArrayList<>();

    public static ArrayList<ProductCard> getCards() {
        return cards;
    }

    public static void addFavroiteCard(ProductCard card) {
        cards.add(card);
    }
}
