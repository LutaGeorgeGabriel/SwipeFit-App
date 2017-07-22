package eu.swipefit.app.app.swiping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;

import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import eu.swipefit.app.Product;
import eu.swipefit.app.R;
import eu.swipefit.app.networking.Networking;
import eu.swipefit.app.app.favorites.FavoritesContainer;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */
public class SwipingActivity extends Activity implements SwipeBackActivityBase{

    private SwipeBackActivityHelper swipeBackActivityHelper;
    private SwipeDeck swipeDeck;
    private ProductCard card;
    private List<ProductCard> cards = new ArrayList<>();
    public static Context context = null;
    private static List<Product> products = new ArrayList<>();

    private static int cardIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swiping_activity);
        context = getApplicationContext();
        Properties properties = new Properties();
        try {
            properties.load(this.getApplication().getAssets().open("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String URL = properties.getProperty("URL");

        ProductsAsyncTask productsAsyncTask = (ProductsAsyncTask) new ProductsAsyncTask(new ProductsAsyncTask.AsyncResponse() {
            @Override
            public void processFinish(List<Product> list) {
                products = list;
            }
        }).execute(URL);

        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        final SwipeDeck cardStack = findViewById(R.id.swipe_deck);

        for(int i = 0; i < products.size(); i++) {
            card = new ProductCard(swipeDeck, products.get(i),context);
            card.setIndex(i);
            cards.add(card);
        }

      /*  final List testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");
*/
        final SwipeDeckAdapter adapter = new SwipeDeckAdapter(cards,this);
        if(cardStack != null){
            cardStack.setAdapter(adapter);
        }

        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                // we can increment the card index only as long as it is smaller than the arrayList size
                if(cardIndex < cards.size()) {
                    cardIndex++;
                }
            }

            @Override
            public void cardSwipedRight(long stableId) {
                // we can increment the card index only as long as it is smaller than the arrayList size
                if(cardIndex < cards.size()) {
                    cardIndex++;
                }
            }
        });

        cardStack.setLeftImage(R.id.left_image);
        cardStack.setRightImage(R.id.right_image);


        final MultiChoicesCircleButton.Item item1 = new MultiChoicesCircleButton.Item("", getResources().getDrawable(R.drawable.up), 30);

        MultiChoicesCircleButton.Item item2 = new MultiChoicesCircleButton.Item("", getResources().getDrawable(R.drawable.ic_favorite), 70);

        MultiChoicesCircleButton.Item item3 = new MultiChoicesCircleButton.Item("", getResources().getDrawable(R.drawable.shop), 110);

        MultiChoicesCircleButton.Item item4= new MultiChoicesCircleButton.Item("", getResources().getDrawable(R.drawable.down), 150);


        List<MultiChoicesCircleButton.Item> buttonItems = new ArrayList<>();
        buttonItems.add(item1);
        buttonItems.add(item2);
        buttonItems.add(item3);
        buttonItems.add(item4);

        MultiChoicesCircleButton multiChoicesCircleButton = findViewById(R.id.multiChoicesCircleButton);
        multiChoicesCircleButton.setIcon(getResources().getDrawable(R.drawable.ic_menu));
        multiChoicesCircleButton.setBackgroundShadowColor(R.color.colorBackground);
        multiChoicesCircleButton.setButtonColor(R.color.colorSwipingMenuButton);

        multiChoicesCircleButton.setButtonItems(buttonItems);
        multiChoicesCircleButton.setOnSelectedItemListener(new MultiChoicesCircleButton.OnSelectedItemListener() {
            @Override
            public void onSelected(MultiChoicesCircleButton.Item item, int index) {
                switch (index) {
                    case 0:
                        // acts just like as a user natural swipe to left
                        cardStack.swipeTopCardLeft(1000);
                        break;
                    case 1:
                        // we can get the card index only as long as it is smaller than the arrayList size
                        if(cardIndex < cards.size()) {
                            FavoritesContainer.addFavroiteCard(cards.get(cardIndex));
                        }
                        break;
                    case 2 :
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(cards.get(cardIndex).getmProduct().getSiteURL()));
                        startActivity(intent);
                        break;
                    case 3:
                        // acts just like as a user natural swipe to right
                        cardStack.swipeTopCardRight(1000);

                }
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {

    }

    private static class ProductsAsyncTask extends AsyncTask<String,Void,List<Product>> {

        public interface AsyncResponse {
            void processFinish(List<Product> list);
        }

        public AsyncResponse delegate = null;

        public ProductsAsyncTask(AsyncResponse delegate){
            this.delegate = delegate;
        }

        @Override
        protected List<Product> doInBackground(String... strings) {
            List<Product> listOfProducts = Networking.fetchEarthquakeData(context ,strings[0]);
            return listOfProducts;
        }

        @Override
        protected void onPostExecute(List<Product> listOfproducts) {
            delegate.processFinish(listOfproducts);
        }
    }


}
