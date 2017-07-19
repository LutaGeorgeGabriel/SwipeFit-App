package eu.swipefit.app.swiping;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.daprlabs.aaron.swipedeck.SwipeDeck;

import java.util.List;

import annotations.Layout;
import annotations.View;
import eu.swipefit.app.Profile;
import eu.swipefit.app.R;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */

@Layout(R.layout.card_view)
public class CardView extends AppCompatActivity {

    private SwipeDeckAdapter swipeDeckAdapter;

    @View(R.id.offer_image)
    private ImageView profileImageView;

    private List<String> data;
    private SwipeDeck swipeDeck;
    public Profile mProfile;
    private Context context;

    public CardView(SwipeDeck swipeDeck, Profile mProfile, Context mContext) {
        this.swipeDeck = swipeDeck;
        this.mProfile = mProfile;
        this.context = mContext;
    }
}
