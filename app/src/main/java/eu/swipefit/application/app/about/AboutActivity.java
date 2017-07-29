package eu.swipefit.application.app.about;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import eu.swipefit.app.R;
import eu.swipefit.application.networking.PushData;

/** ADD COMMENTS */
public class AboutActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app_activity);
        /*KonfettiView view = (KonfettiView) findViewById(R.id.viewKonfetti);
        Size size = new Size(12,3);
        view.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(size)
                .setPosition(-50f, view.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);*/
    }

    public void onButtonClick(View view) {
        Button button = findViewById(R.id.POST_BUTTON);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PushData.sendUserBehaviourToServer();
            }
        });
    }
}
