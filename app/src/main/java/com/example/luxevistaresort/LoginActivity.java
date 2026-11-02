package com.example.luxevistaresort;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText txtUser, txtpass;
    Button btnLogin;
    TextView lblDisplay;
    DBOperations db;

    private int loginAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;
    private static final long LOCKOUT_TIME_MS = 30000;
    private DBOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUser = findViewById(R.id.txtLoginEmail);
        txtpass = findViewById(R.id.txtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        lblDisplay = findViewById(R.id.textViewAttempts);
        db = new DBOperations(this);
    }

    public void ResgisterBtn(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void adminLogin(View view) {
        Intent intent = new Intent(this, AdminLoginActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        String user = txtUser.getText().toString();
        String pass = txtpass.getText().toString();

        User user1 = new User();
        user1.setUsername(user);
        user1.setPassword(pass);

        if (db.userlogin(user1)) {
            Intent intent = new Intent(this, HomeActivity.class);
            loginAttempts = 0;
            intent.putExtra("email", user.toUpperCase());
            startActivity(intent);
        } else {
            loginAttempts++;
            if (loginAttempts > MAX_ATTEMPTS) {
                Toast.makeText(this, "Too many attempts. Try again in 30 seconds!", Toast.LENGTH_SHORT).show();
                lblDisplay.setText(String.format("Attempts : %d", loginAttempts));
                btnLogin.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnLogin.setEnabled(true);
                        loginAttempts = 0;
                    }
                }, LOCKOUT_TIME_MS);
            } else {
                Toast.makeText(this, "Login Faild. Attempts: " + loginAttempts + " of " + MAX_ATTEMPTS, Toast.LENGTH_LONG).show();
                lblDisplay.setText(String.format("Attempts : %d", loginAttempts));
            }
        }
    }
}