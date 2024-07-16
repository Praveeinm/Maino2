package com.example.maino2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText editText;
    Button ResetButton;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reset_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText=findViewById(R.id.editText);
        ResetButton=findViewById(R.id.resetpassword);
        mAuth = FirebaseAuth.getInstance();
        progressBar =(ProgressBar) findViewById(R.id.progressbar);

    }

    public void resetPassWordButtonClicked(View v){

        resetPassword();

    }

    private void resetPassword() {

        String txtEmail = editText.getText().toString().trim();
        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches()){
            editText.setError("enter correct email");
            editText.requestFocus();

        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(txtEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ResetPasswordActivity.this,"Reset Link Send To Mail",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ResetPasswordActivity.this, signInActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this,"Failed To Reset Password",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }
}