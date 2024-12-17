package com.example.proj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {

    CardView cvBmiCalc, cvCalorieCalc, cvWorkoutPlans, cvNutritionTips, cvWorkoutTips;
    TextView tvName;
    CircleImageView civImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        initialize();
    }

    private void initialize() {
        cvBmiCalc = findViewById(R.id.cvBmiCalc);
        cvCalorieCalc = findViewById(R.id.cvCalorieCalc);
        cvWorkoutPlans = findViewById(R.id.cvWorkoutPlans);
        cvNutritionTips = findViewById(R.id.cvNutritionTips);
        cvWorkoutTips = findViewById(R.id.cvWorkoutTips);

        tvName = findViewById(R.id.tvName);
        civImg = findViewById(R.id.civImg);

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String firstName = prefs.getString("firstName", "");
        String lastName = prefs.getString("lastName", "");
        String encodedImage = prefs.getString("photo", "");

        tvName.setText(firstName + " " + lastName + "!");

        if (!encodedImage.isEmpty()) {
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            civImg.setImageBitmap(decodedByte);
        }

        cvBmiCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, BmiCalculator.class);
                startActivity(intent);
            }
        });

        cvCalorieCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, CalorieCalculator.class);
                startActivity(intent);
            }
        });
    }


}