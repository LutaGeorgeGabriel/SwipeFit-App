package eu.swipefit.application.app.favorites;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import eu.swipefit.app.R;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/** ADD COMMENTS */
public class FavoritesActivity extends Activity implements SwipeBackActivityBase{

    private SwipeBackActivityHelper swipeBackActivityHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // instantiate swipeBackActivityHelper right after the creation of the activity
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        if(FavoritesContainer.getProducts().size() == 0 || FavoritesContainer.getProducts() == null) {
            setContentView(R.layout.no_favorite_items);
            // enable the swipeBackActivityHelper after the content view has been set
            swipeBackActivityHelper.onActivityCreate();
        }
        else {
            setContentView(R.layout.favorites_list_activity);
            // enable the swipeBackActivityHelper after the content view has been set
            swipeBackActivityHelper.onActivityCreate();
            ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(),0,FavoritesContainer.getProducts());
            ListView listView = findViewById(R.id.listView);

            listView.setAdapter(productAdapter);
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
