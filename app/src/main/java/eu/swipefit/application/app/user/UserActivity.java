package eu.swipefit.application.app.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import eu.swipefit.app.R;
import eu.swipefit.application.app.login.LoginActivity;
import eu.swipefit.application.app.sharedPreferences.SharedPreferencesCounter;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */
public class UserActivity extends Activity {

    private TextView userEmail;
    private TextView sumOfLikes;
    private Button logout;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        relativeLayout = findViewById(R.id.profilePic);
        relativeLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        userEmail = findViewById(R.id.userEmail);
        sumOfLikes = findViewById(R.id.sumOfLikes);
        logout = findViewById(R.id.logout);

        final FirebaseAuth firebaseAuth = LoginActivity.getFirebaseAuth();
        userEmail.setText(firebaseAuth.getCurrentUser().getEmail());

        sumOfLikes.setText(String.valueOf(SharedPreferencesCounter.getDefaults("counter", getApplicationContext())));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(UserActivity.this,LoginActivity.class));
            }
        });
    }
}
