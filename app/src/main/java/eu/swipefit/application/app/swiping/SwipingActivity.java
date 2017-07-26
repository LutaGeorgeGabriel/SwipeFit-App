package eu.swipefit.application.app.swiping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluehomestudio.progressimage.ProgressPicture;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.gjiazhe.multichoicescirclebutton.MultiChoicesCircleButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.dmoral.toasty.Toasty;
import eu.swipefit.application.Product;
import eu.swipefit.app.R;
import eu.swipefit.application.app.favorites.FavoritesContainer;
import eu.swipefit.application.app.productsInfo.ProductsInformation;
import eu.swipefit.application.networking.FetchData;
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



        // when waiting for the data to be fetched, the menu button and the other views should not be displayed
        MultiChoicesCircleButton multiChoicesCircleButton = findViewById(R.id.multiChoicesCircleButton);
        multiChoicesCircleButton.setVisibility(View.GONE);

        // instantiate swipeBackActivityHelper right after the creation of the activity
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        // enable the swipeBackActivityHelper after the content view has been set
        swipeBackActivityHelper.onActivityCreate();

        context = getApplicationContext();
        Properties properties = new Properties();
        try {
            properties.load(this.getApplication().getAssets().open("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String URL = properties.getProperty("URL");


        // we check to see if there is internet connection

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // if it works
            ProductsAsyncTask productsAsyncTask = (ProductsAsyncTask) new ProductsAsyncTask(new ProductsAsyncTask.AsyncResponse() {
                @Override
                public void processFinish(List<Product> list) {
                    products = list;
                    // check if the data from the server is null
                    if(list == null || list.size() == 0) {
                        ProgressPicture progressPicture = findViewById(R.id.loading_indicator);
                        progressPicture.setVisibility(View.GONE);
                        ImageView imageView = findViewById(R.id.no_connection_image);
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.unable_fetch));
                        TextView textView = findViewById(R.id.no_connection_text);
                        textView.setText("Unable to fetch data.");
                    }
                    else {
                        updateUi();
                        ProgressPicture progressPicture = findViewById(R.id.loading_indicator);
                        progressPicture.setVisibility(View.GONE);
                    }

                }
            }).execute(URL);
        }
        else {
            ProgressPicture progressPicture = findViewById(R.id.loading_indicator);
            progressPicture.setVisibility(View.GONE);
            ImageView imageView = findViewById(R.id.no_connection_image);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.no_connection));
            TextView textView = findViewById(R.id.no_connection_text);
            textView.setText("No internet connection.");
        }

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
            List<Product> listOfProducts = FetchData.fetchEarthquakeData(context ,strings[0]);
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

        // condition to see if the products is null, to prevent the call of the .size() method on a null object
        if(products != null) {
            for (int i = 0; i < 5; i++) {
                //if(ProductsInformation.getProductsInformation().get(i) == 0.5 ) {
                    card = new ProductCard(swipeDeck, products.get(i), context);
                    card.setIndex(i);
                    cards.add(card);
            //}
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
                ProductsInformation.like(cardIndex-1);
                // when we reach to the end of the products list
                if(cardIndex == cards.size()) {

                    MultiChoicesCircleButton multiChoicesCircleButton = findViewById(R.id.multiChoicesCircleButton);
                    multiChoicesCircleButton.setVisibility(View.GONE);

                    Toast toast = Toasty.normal(getApplicationContext(),"End of product list", Toast.LENGTH_LONG,getResources().getDrawable(R.drawable.empty));
                    toast.setGravity(1,0,0);
                    toast.show();

                    //TODO call new method
                }
            }

            @Override
            public void cardSwipedRight(long stableId) {
                // we can increment the card index only as long as it is smaller than the arrayList size
                if(cardIndex < cards.size()) {
                    cardIndex++;
                }
                ProductsInformation.dislike(cardIndex-1);
                // when we reach to the end of the products list
                if(cardIndex == cards.size()) {

                    MultiChoicesCircleButton multiChoicesCircleButton = findViewById(R.id.multiChoicesCircleButton);
                    multiChoicesCircleButton.setVisibility(View.GONE);

                    Toast toast = Toasty.normal(getApplicationContext(),"End of product list", Toast.LENGTH_LONG,getResources().getDrawable(R.drawable.empty));
                    toast.setGravity(1,0,0);
                    toast.show();

                    //TODO call new method
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
                        // add current card to favorites container
                        FavoritesContainer.addFavroiteCard(cards.get(cardIndex).getmProduct());
                        Toasty.normal(getApplicationContext(),"Product added to favorites", Toast.LENGTH_LONG,getResources().getDrawable(R.drawable.hanger)).show();
                        break;
                    case 2 :
                        // create new intent that starts the default broswer
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        // redirects browser to retailer URL
                        intent.setData(Uri.parse(cards.get(cardIndex).getmProduct().getSite()));
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
