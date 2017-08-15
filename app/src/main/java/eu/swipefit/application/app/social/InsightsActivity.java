package eu.swipefit.application.app.social;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import eu.swipefit.app.R;
import eu.swipefit.application.app.sharedPreferences.SharedPreferencesCounter;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/** ADD COMMENTS */
public class InsightsActivity extends Activity implements SwipeBackActivityBase{

    private SwipeBackActivityHelper swipeBackActivityHelper;
    private TextView sumOfLikes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insights_activity);
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        sumOfLikes = findViewById(R.id.sumOfLikes);
        sumOfLikes.setText(String.valueOf(SharedPreferencesCounter.getDefaults("counter", getApplicationContext())));
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
