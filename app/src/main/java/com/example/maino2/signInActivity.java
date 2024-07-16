package com.example.maino2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signInActivity extends AppCompatActivity {

    EditText editTextEmailAddress, editTextPassword;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextEmailAddress = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
    }

    public void txtSignInForgotPasswordClicked(View v) {
        Intent intent = new Intent(signInActivity.this, ResetPasswordActivity.class);
        startActivity(intent);
    }

    public void txtSignInRegisterClicked(View v) {
        Intent intent = new Intent(signInActivity.this, signupActivity.class);
        startActivity(intent);
    }

    public void buttonSignInClicked(View v) {
        String userName = editTextEmailAddress.getText().toString().trim();
        String passWord = editTextPassword.getText().toString().trim();

        if (userName.isEmpty()) {
            editTextEmailAddress.setError("Email address is required");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            editTextEmailAddress.setError("Please enter a valid email address");
            editTextEmailAddress.requestFocus();
            return;
        }

        if (passWord.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (passWord.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters long");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userName, passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(signInActivity.this, "Sign in successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(signInActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear previous activities
                    startActivity(intent);
                } else {
                    Toast.makeText(signInActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
