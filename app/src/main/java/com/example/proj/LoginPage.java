package com.example.proj;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginPage extends AppCompatActivity {
    TextView tvRegister;
    EditText etUsername, etPassword;
    Context c = this;
    Button btnLogin;
    String user1, user2, user3, pass1, pass2, pass3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
    }

    private void initialize() {
        tvRegister = findViewById(R.id.tvRegister);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        pass1 = "12345";
        user2 = "Joan";
        pass2 = "567890";
        user3 = "Dani";
        pass3 = "ASDFGH";

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Redirecting")
                        .setMessage("You will now be redirected to the registration page")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(c, RegisterPage.class);
                                startActivity(i);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    showAlert("All fields are required", "Login Error");
                    return;
                }

                SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                String savedUsername = prefs.getString("username", "");
                String savedPassword = prefs.getString("password", "");

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    Intent intent = new Intent(c, Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                // default users
                else if ((username.equals(user1) && password.equals(pass1)) ||
                        (username.equals(user2) && password.equals(pass2)) ||
                        (username.equals(user3) && password.equals(pass3))) {
                    Toast.makeText(c, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    showAlert("Username or Password is incorrect.", "Login Error");
                }

                etUsername.setText("");
                etPassword.setText("");
            }
        });


    }

    private void showAlert(String message, String title) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}