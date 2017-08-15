package eu.swipefit.application.app.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.dragankrstic.autotypetextview.AutoTypeTextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import eu.swipefit.app.R;
import eu.swipefit.application.app.about.AboutActivity;
import eu.swipefit.application.app.login.LoginActivity;
import eu.swipefit.application.app.mainMenu.MainActivity;
import info.hoang8f.widget.FButton;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */
public class UserActivity extends Activity {

    private static FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {
            setContentView(R.layout.user_activity_logged_in);
            AutoTypeTextView autoTypeTextView = findViewById(R.id.userEmail);
            autoTypeTextView.setTextAutoTyping(auth.getCurrentUser().getEmail());
            FButton logout = findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.signOut();
                    finish();
                    startActivity(new Intent(UserActivity.this, MainActivity.class));
                }
            });
        } else {
            setContentView(R.layout.user_activity_logged_out);
            LinearLayout login = findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(UserActivity.this, LoginActivity.class));
                }
            });
        }

    }

    public static FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        // your code.
        startActivity(new Intent(this, MainActivity.class));
    }
}
