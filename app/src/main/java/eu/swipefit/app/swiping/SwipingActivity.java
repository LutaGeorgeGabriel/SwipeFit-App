package eu.swipefit.app.swiping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import eu.swipefit.app.R;
import io.saeid.fabloading.LoadingView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */
public class SwipingActivity extends Activity implements SwipeBackActivityBase{

    private SwipeBackActivityHelper swipeBackActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swiping_activity);

        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();

        LoadingView mLoadingView = (LoadingView) findViewById(R.id.loading_view);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.coat,LoadingView.FROM_BOTTOM);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.shirt,LoadingView.FROM_TOP);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.tshirt,LoadingView.FROM_LEFT);
        //mLoadingView.addAnimation(R.color.colorBackground,R.drawable.trousers,LoadingView.FROM_RIGHT);
        mLoadingView.startAnimation();
    }

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, SwipingActivity.class);
        intent.putExtra("title", title);
        return intent;
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
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }


}
