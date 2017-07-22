package eu.swipefit.application.app.favorites;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import eu.swipefit.app.R;

/** ADD COMMENTS */
public class FavoritesActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list_activity);


        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),0,FavoritesContainer.getProducts());
        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(productAdapter);
    }
}
