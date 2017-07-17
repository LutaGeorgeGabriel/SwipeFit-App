package eu.swipefit.app.mainMenu;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mzule.fantasyslide.SideBar;
import com.github.mzule.fantasyslide.SimpleFantasyListener;
import com.github.mzule.fantasyslide.Transformer;
import eu.swipefit.app.R;
import eu.swipefit.app.swiping.SwipingActivity;
import eu.swipefit.app.UniversalActivity;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*LoadingView mLoadingView = (LoadingView) findViewById(R.id.loading_view);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.coat,LoadingView.FROM_BOTTOM);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.shirt,LoadingView.FROM_TOP);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.tshirt,LoadingView.FROM_LEFT);
        mLoadingView.addAnimation(R.color.colorBackground,R.drawable.trousers,LoadingView.FROM_RIGHT);
        mLoadingView.startAnimation();*/

        /*//also you can add listener for getting callback (optional)
        mLoadingView.addListener(new LoadingView.LoadingListener() {
            @Override public void onAnimationStart(int currentItemPosition) {
            }

            @Override public void onAnimationRepeat(int nextItemPosition) {
            }

            @Override public void onAnimationEnd(int nextItemPosition) {
            }
        });*/

    }

    private void setListener() {
        final TextView tipView = (TextView) findViewById(R.id.tipView);
        SideBar leftSideBar = (SideBar) findViewById(R.id.leftSideBar);
        leftSideBar.setFantasyListener(new SimpleFantasyListener() {
            @Override
            public boolean onHover(@Nullable View view, int index) {
                tipView.setVisibility(View.VISIBLE);
                if (view instanceof TextView) {
                    Toast.makeText(MainActivity.this,"HOVER", Toast.LENGTH_SHORT).show();
                    tipView.setText(String.format("%s at %d", ((TextView) view).getText().toString(), index));
                } else if (view != null && view.getId() == R.id.userInfo) {
                    tipView.setText(String.format("个人中心 at %d", index));
                } else {
                    tipView.setText(null);
                }
                return false;

            }

            @Override
            public boolean onSelect(View view, int index) {
                tipView.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"onSelect", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, String.format("%d selected", index), Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this,"OnCancel", Toast.LENGTH_SHORT).show();
                tipView.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setTransformer() {
        final float spacing = getResources().getDimensionPixelSize(R.dimen.spacing);
        SideBar rightSideBar = (SideBar) findViewById(R.id.rightSideBar);
        rightSideBar.setTransformer(new Transformer() {
            private View lastHoverView;

            @Override
            public void apply(ViewGroup sideBar, View itemView, float touchY, float slideOffset, boolean isLeft) {
                boolean hovered = itemView.isPressed();
                if (hovered && lastHoverView != itemView) {
                    animateIn(itemView);
                    animateOut(lastHoverView);
                    lastHoverView = itemView;
                }
            }

            private void animateOut(View view) {
                if (view == null) {
                    return;
                }
                ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", -spacing, 0);
                translationX.setDuration(200);
                translationX.start();
            }

            private void animateIn(View view) {
                ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", 0, -spacing);
                translationX.setDuration(200);
                translationX.start();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return true;
    }

    public void onClick(View view) {
        if (view instanceof TextView) {
            String title = ((TextView) view).getText().toString();
            if (title.startsWith("something")) {
                Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(UniversalActivity.newIntent(this, title));
            }
        } else if (view.getId() == R.id.userInfo) {
            startActivity(UniversalActivity.newIntent(this, "个人中心"));
            Intent intent = new Intent();

        }
    }

    public void onUserClick(View view) {
        View user = (TextView) findViewById(R.id.user);
        Intent intent = new Intent(getApplicationContext(),SwipingActivity.class);
        if (intent != null) {
            startActivity(intent);
        }
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
