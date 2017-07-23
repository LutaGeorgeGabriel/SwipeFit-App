package eu.swipefit.application.app.swiping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.bluehomestudio.progressimage.ProgressPicture;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import eu.swipefit.application.Product;
import eu.swipefit.app.R;
import eu.swipefit.application.app.favorites.FavoritesContainer;
import eu.swipefit.application.networking.Networking;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */
public class SwipingActivity extends Activity implements SwipeBackActivityBase{

    private SwipeBackActivityHelper swipeBackActivityHelper = null;
    private SwipeDeck swipeDeck = null;
    private ProductCard card = null;
    private List<ProductCard> cards = new ArrayList<>();
    public static Context context = null;
    private static List<Product> products = new ArrayList<>();

    private static int cardIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swiping_activity);
        MultiChoicesCircleButton multiChoicesCircleButton = findViewById(R.id.multiChoicesCircleButton);
        multiChoicesCircleButton.setVisibility(View.GONE);



        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();

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
                updateUi();
                ProgressPicture progressPicture = findViewById(R.id.loading_indicator);
                progressPicture.setVisibility(View.GONE);


            }
        }).execute(URL);

    }

    // this method is to ensure that the index of the cards is always correct according to the UI
    // and also that when we come back to this menu, it will begin from 0
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cardIndex = 0;
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

    public void updateUi() {
        setContentView(R.layout.swiping_activity);
        final SwipeDeck cardStack = findViewById(R.id.swipe_deck);

        if(products != null) {
            for (int i = 0; i < products.size(); i++) {
                card = new ProductCard(swipeDeck, products.get(i), context);
                card.setIndex(i);
                cards.add(card);
            }
        }


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
                        FavoritesContainer.addFavroiteCard(cards.get(cardIndex).getmProduct());
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
}
