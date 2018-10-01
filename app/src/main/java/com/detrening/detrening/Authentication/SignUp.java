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

public class SignUp extends AppCompatActivity {
    private EditText emailDaftar, passwordDaftar, repasswordDaftar;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(SignUp.this);

        mAuth = FirebaseAuth.getInstance();

        emailDaftar = (EditText) findViewById(R.id.emailDaftar);
        passwordDaftar = (EditText) findViewById(R.id.passwordDaftar);
        repasswordDaftar = (EditText) findViewById(R.id.repasswordDaftar);

    }

    public void intentLogin(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        this.finish();
    }

    public void fungsiDaftar(View view) {
        progressDialog.setMessage("Mendaftar...");
        progressDialog.show();

        String email = emailDaftar.getText().toString().trim();
        String password = passwordDaftar.getText().toString().trim();
        String rePassword = repasswordDaftar.getText().toString().trim();

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
        if (TextUtils.isEmpty(rePassword)){
            Toast.makeText(getApplicationContext(), "Isi password anda!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        if (!rePassword.equals(password)){
            Toast.makeText(getApplicationContext(), "Password dan Re-password harus sama", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUp.this, "Register Berhasil!", Toast.LENGTH_SHORT).show();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Login Gagal, periksa jaringan anda",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            progressDialog.dismiss();
                            startActivity(new Intent(SignUp.this, Beranda.class));
                            finish();
                        }
                    }
                });
    }
}
