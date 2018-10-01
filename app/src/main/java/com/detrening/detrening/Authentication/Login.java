package com.detrening.detrening.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.detrening.detrening.Home.Beranda;
import com.detrening.detrening.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText emailLogin, passwordLogin;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog =  new ProgressDialog(Login.this);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(Login.this, Beranda.class));
            finish();
        }

        emailLogin = (EditText) findViewById(R.id.emailLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);

    }

    public void intentDaftar(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        this.finish();
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(Login.this, resetPassword.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void fungsiLogin(View view) {
        progressDialog.setMessage("Sign In ...");
        progressDialog.show();

        String email = emailLogin.getText().toString();
        final String password = passwordLogin.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Isi email anda!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Isi password anda!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                passwordLogin.setError(getString(R.string.minimum_password));
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        } else {
                            Intent intent = new Intent(Login.this, Beranda.class);
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

}
