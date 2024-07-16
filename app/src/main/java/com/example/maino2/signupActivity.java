package com.example.maino2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupActivity extends AppCompatActivity {

    EditText editTextUserName, editTextPassword, editTextPhoneNumber, editTextEmail;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextUserName = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        editTextPhoneNumber = findViewById(R.id.phonenumber);
        editTextEmail = findViewById(R.id.mail);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Password regex pattern: at least 8 characters, contains uppercase, lowercase, numbers, and special characters
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public void SignupClicked(View v) {
        String txtUserName = editTextUserName.getText().toString().trim();
        String txtPassword = editTextPassword.getText().toString().trim();
        String txtPhoneNumber = editTextPhoneNumber.getText().toString().trim();
        String txtMail = editTextEmail.getText().toString().trim();

        // Validate each field individually and show appropriate error messages
        if (txtUserName.isEmpty()) {
            editTextUserName.setError("Please enter username");
            editTextUserName.requestFocus();
            return;
        }

        if (!isValidPassword(txtPassword)) {
            editTextPassword.setError("Password must be at least 8 characters, contain uppercase and lowercase letters, numbers, and special characters.");
            editTextPassword.requestFocus();
            return;
        }

        if (txtPhoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Please enter phone number");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if (txtMail.isEmpty()) {
            editTextEmail.setError("Please enter email address");
            editTextEmail.requestFocus();
            return;
        }

        // If all fields are valid, proceed with Firebase authentication and user creation
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(txtMail, txtPassword)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Firebase user creation successful, now save additional user data
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            String userId = firebaseUser.getUid();
                            user user = new user(txtUserName, txtPassword, txtPhoneNumber, txtMail);
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userId);
                            reference.setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(signupActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                                            // Clear fields after successful registration
                                            clearFields();
                                        } else {
                                            Toast.makeText(signupActivity.this, "Failed to save user data", Toast.LENGTH_LONG).show();
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    });
                        }
                    } else {
                        // Firebase user creation failed
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(signupActivity.this, "User with this email already exists", Toast.LENGTH_LONG).show();
                        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(signupActivity.this, "Invalid email format", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(signupActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void clearFields() {
        editTextUserName.setText("");
        editTextPassword.setText("");
        editTextPhoneNumber.setText("");
        editTextEmail.setText("");
    }
}
