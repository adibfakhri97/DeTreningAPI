package com.detrening.detrening.Authentication;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.detrening.detrening.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassword extends AppCompatActivity {
    private EditText emailReset;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        setTitle("Reset Password");

        progressDialog = new ProgressDialog(resetPassword.this);

        emailReset = (EditText) findViewById(R.id.emailReset);

        mAuth = FirebaseAuth.getInstance();

    }

    public void fungsiReset(View view) {
        progressDialog.setMessage("Mengirim email...");
        progressDialog.show();
        String email = emailReset.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Masukkan email anda!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(resetPassword.this, "Silakan cek email anda, ikuti langkah-langkah yang ada!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(resetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}
