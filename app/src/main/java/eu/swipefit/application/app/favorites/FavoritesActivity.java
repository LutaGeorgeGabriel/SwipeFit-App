package eu.swipefit.application.app.favorites;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bluehomestudio.progressimage.ProgressPicture;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import eu.swipefit.app.R;
import eu.swipefit.application.Product;
import eu.swipefit.application.app.swiping.SwipingActivity;
import eu.swipefit.application.networking.FetchData;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * ADD COMMENTS
 */
public class FavoritesActivity extends Activity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper swipeBackActivityHelper = null;
    public static String URL_GET_FAVORITES = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list_activity);
        ListView listView = findViewById(R.id.list);
        listView.setVisibility(View.INVISIBLE);
        // instantiate swipeBackActivityHelper right after the creation of the activity
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();

        Properties properties = new Properties();
        try {
            properties.load(this.getApplication().getAssets().open("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL_GET_FAVORITES = properties.getProperty("URL-GET-FAVORITES");

        // we check to see if there is internet connection

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            FavoritesAsyncTask favoritesAsyncTask = (FavoritesAsyncTask) new FavoritesAsyncTask(new FavoritesAsyncTask.AsyncResponse() {
                @Override
                public void processFinish(List<Product> list) {
                    if (list == null || list.size() == 0) {
                        setContentView(R.layout.no_favorite_items);
                    } else {
                        updateUi(list);
                    }
                }
            }).execute(URL_GET_FAVORITES);
        } else {
            //TODO
        }
    }

    private void updateUi(List<Product> list) {

        // enable the swipeBackActivityHelper after the content view has been set
        swipeBackActivityHelper.onActivityCreate();
        setContentView(R.layout.favorites_list_activity);
        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), 0, list);
        ListView listView = findViewById(R.id.list);
        listView.setVisibility(View.VISIBLE);
        listView.setAdapter(productAdapter);
        ProgressPicture progressPicture = findViewById(R.id.loading);
        progressPicture.setVisibility(View.GONE);
        //}
    }

    private static class FavoritesAsyncTask extends AsyncTask<String, Void, List<Product>> {

        public interface AsyncResponse {
            void processFinish(List<Product> list);
        }

        public FavoritesActivity.FavoritesAsyncTask.AsyncResponse delegate = null;

        public FavoritesAsyncTask(FavoritesActivity.FavoritesAsyncTask.AsyncResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        protected List<Product> doInBackground(String... strings) {
            List<Product> listOfFavorites = FetchData.fetchFavoritesData(strings[0]);
            return listOfFavorites;
        }

        @Override
        protected void onPostExecute(List<Product> listOfproducts) {
            delegate.processFinish(listOfproducts);
        }
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
}
