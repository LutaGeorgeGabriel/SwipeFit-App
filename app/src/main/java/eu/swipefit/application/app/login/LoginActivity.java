package eu.swipefit.application.app.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import eu.swipefit.app.R;
import eu.swipefit.application.app.mainMenu.MainActivity;
import eu.swipefit.application.app.register.RegisterActivity;

/**
 * FILE DESCRIPTION
 */

/** ADD COMMENTS */
public class LoginActivity  extends Activity {
    private Button buttonSignIn;
    private EditText loginEditTextEmail;
    private EditText loginEditTextPassword;
    private TextView loginTextViewSignUp;
    private static FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        loginEditTextEmail = findViewById(R.id.loadingEditTextEmail);
        loginEditTextPassword = findViewById(R.id.loadingEditTextPassword);
        loginTextViewSignUp = findViewById(R.id.loginTextViewSignUp);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            // start MainActivity
            startActivity(new Intent(this,MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        loginTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }

    private void userLogin() {
        String email = loginEditTextEmail.getText().toString().trim();
        String password = loginEditTextPassword.getText().toString().trim();

        if(email.isEmpty()) {
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.isEmpty()) {
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()) {
                    // start MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        LoginActivity.firebaseAuth = firebaseAuth;
    }
}
