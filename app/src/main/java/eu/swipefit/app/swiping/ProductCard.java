package eu.swipefit.app.swiping;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daprlabs.aaron.swipedeck.SwipeDeck;

import java.util.List;

import annotations.Layout;
import annotations.View;
import eu.swipefit.app.Product;
import eu.swipefit.app.R;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */

@Layout(R.layout.card_view)
public class ProductCard extends AppCompatActivity {

    private SwipeDeckAdapter swipeDeckAdapter;

    @View(R.id.offer_image)
    private ImageView profileImageView;

    private List<String> data;
    private SwipeDeck swipeDeck;
    public Product mProduct;
    private Context context;


    private long index;

    public ProductCard(SwipeDeck swipeDeck, Product mProduct, Context mContext) {
        this.swipeDeck = swipeDeck;
        this.mProduct = mProduct;
        this.context = mContext;
        this.index = 0L;
    }

    public SwipeDeckAdapter getSwipeDeckAdapter() {
        return swipeDeckAdapter;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public void setSwipeDeckAdapter(SwipeDeckAdapter swipeDeckAdapter) {
        this.swipeDeckAdapter = swipeDeckAdapter;
    }

    public ImageView getProfileImageView() {
        return profileImageView;
    }

    public void setProfileImageView(ImageView profileImageView) {
        this.profileImageView = profileImageView;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public SwipeDeck getSwipeDeck() {
        return swipeDeck;
    }

    public void setSwipeDeck(SwipeDeck swipeDeck) {
        this.swipeDeck = swipeDeck;
    }

    public Product getmProduct() {
        return mProduct;
    }

    public void setmProduct(Product mProduct) {
        this.mProduct = mProduct;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
