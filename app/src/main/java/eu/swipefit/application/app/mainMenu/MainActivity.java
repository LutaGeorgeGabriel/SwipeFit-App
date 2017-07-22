package eu.swipefit.application.app.mainMenu;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import eu.swipefit.application.app.about.AboutActivity;
import eu.swipefit.application.app.social.SocialActivity;
import eu.swipefit.application.app.swiping.SwipingActivity;
import eu.swipefit.application.app.universalActivity.UniversalActivity;
import io.saeid.fabloading.LoadingView;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);


        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                LoadingView mLoadingView_00 = findViewById(R.id.loading_view_00);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.blazer,LoadingView.FROM_BOTTOM);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.coat,LoadingView.FROM_TOP);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.dress,LoadingView.FROM_LEFT);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.dress_2,LoadingView.FROM_RIGHT);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.hoodie,LoadingView.FROM_LEFT);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shirt,LoadingView.FROM_BOTTOM);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shirt_2,LoadingView.FROM_TOP);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shirt_3,LoadingView.FROM_RIGHT);
                mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shoes,LoadingView.FROM_TOP);
                mLoadingView_00.startAnimation();
                mLoadingView_00.addListener(new LoadingView.LoadingListener() {
                    @Override
                    public void onAnimationStart(int currentItemPosition) {

                    }

                    @Override
                    public void onAnimationRepeat(int nextItemPosition) {

                    }

                    @Override
                    public void onAnimationEnd(int nextItemPosition) {
                        /*LoadingView mLoadingView_00 = findViewById(R.id.loading_view_00);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.blazer,LoadingView.FROM_BOTTOM);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.coat,LoadingView.FROM_TOP);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.dress,LoadingView.FROM_LEFT);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.dress_2,LoadingView.FROM_RIGHT);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.hoodie,LoadingView.FROM_LEFT);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shirt,LoadingView.FROM_BOTTOM);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shirt_2,LoadingView.FROM_TOP);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shirt_3,LoadingView.FROM_RIGHT);
                        mLoadingView_00.addAnimation(R.color.colorBackground,R.drawable.shoes,LoadingView.FROM_TOP);
                        mLoadingView_00.startAnimation();*/
                    }
                });
            }
        });

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                LoadingView mLoadingView_01 = findViewById(R.id.loading_view_01);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.shorts,LoadingView.FROM_TOP);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.sneakers,LoadingView.FROM_BOTTOM);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.sneakers,LoadingView.FROM_RIGHT);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.tie,LoadingView.FROM_LEFT);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.top_hat,LoadingView.FROM_RIGHT);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.trousers,LoadingView.FROM_TOP);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.tshirt,LoadingView.FROM_BOTTOM);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.vest,LoadingView.FROM_LEFT);
                mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.hoodie,LoadingView.FROM_BOTTOM);
                mLoadingView_01.startAnimation();
                mLoadingView_01.addListener(new LoadingView.LoadingListener() {
                    @Override
                    public void onAnimationStart(int currentItemPosition) {

                    }

                    @Override
                    public void onAnimationRepeat(int nextItemPosition) {

                    }

                    @Override
                    public void onAnimationEnd(int nextItemPosition) {
                        /*LoadingView mLoadingView_01 = findViewById(R.id.loading_view_01);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.shorts,LoadingView.FROM_TOP);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.sneakers,LoadingView.FROM_BOTTOM);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.sneakers,LoadingView.FROM_LEFT);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.tie,LoadingView.FROM_RIGHT);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.top_hat,LoadingView.FROM_BOTTOM);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.trousers,LoadingView.FROM_LEFT);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.tshirt,LoadingView.FROM_TOP);
                        mLoadingView_01.addAnimation(R.color.colorBackground,R.drawable.vest,LoadingView.FROM_RIGHT);
                        mLoadingView_01.startAnimation();*/
                    }
                });
            }
        });


        /*//also you can add listener for getting callback (optional)
        mLoadingView_01.addListener(new LoadingView.LoadingListener() {
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
                tipView.setVisibility(View.VISIBLE);
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
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        if (intent != null) {
            startActivity(intent);
        }
    }

    public void onSwipeClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SwipingActivity.class);
        if(intent != null) {
            startActivity(intent);
        }
    }

    public void onFaceClick(View view) {
        KonfettiView konfettiView = (KonfettiView) findViewById(R.id.viewKonfetti);
        Size size = new Size(12,3);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(size)
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);
    }

    public void onSocialClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SocialActivity.class);
        startActivity(intent);
    }

    public void onFavoritesClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
        startActivity(intent);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
