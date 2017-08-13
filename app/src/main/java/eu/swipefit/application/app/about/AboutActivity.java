package eu.swipefit.application.app.about;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import eu.swipefit.app.R;
import io.saeid.fabloading.LoadingView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

/** ADD COMMENTS */
public class AboutActivity extends Activity implements SwipeBackActivityBase{

    private SwipeBackActivityHelper swipeBackActivityHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app_activity);

        /**
         * Instantiate swipeBackActivityHelper right after the creation of the activity
         * */

        swipeBackActivityHelper = new SwipeBackActivityHelper(this);

        /**
         * Enable the swipeBackActivityHelper after the content view has been set
         * */

        swipeBackActivityHelper.onActivityCreate();

        KonfettiView konfettiView00 = findViewById(R.id.viewKonfetti_00);
        KonfettiView konfettiView01 = findViewById(R.id.viewKonfetti_01);

        Size size = new Size(12,3);


        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                LoadingView mLoadingView = findViewById(R.id.loading_view);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.shorts,LoadingView.FROM_TOP);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.sneakers,LoadingView.FROM_BOTTOM);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.sneakers,LoadingView.FROM_RIGHT);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.tie,LoadingView.FROM_LEFT);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.top_hat,LoadingView.FROM_RIGHT);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.trousers,LoadingView.FROM_TOP);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.tshirt,LoadingView.FROM_BOTTOM);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.vest,LoadingView.FROM_LEFT);
                mLoadingView.addAnimation(R.color.colorBackground,R.drawable.hoodie,LoadingView.FROM_BOTTOM);
                mLoadingView.startAnimation();
            }
        });

        konfettiView00.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(0, konfettiView00.getWidth() +1000f, 50f, 50f)
                .stream(900, 5000L);

        konfettiView01.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(5000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(0, konfettiView01.getWidth() +1000f, 600f, 10f)
                .stream(900, 5000L);
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
