package com.example.proj;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class RecipeDetails extends AppCompatActivity {
    TextView tvRecipeName, tvCalories, tvTime, tvOverview, tvIngredients, tvDirections;
    ImageView ivRecipeImage;
    ImageView ivBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getWindow().setNavigationBarContrastEnforced(false);
        }
        setContentView(R.layout.activity_recipe_content);

        ivRecipeImage = findViewById(R.id.ivRecipeImage);
        tvRecipeName = findViewById(R.id.tvRecipeName);
        tvCalories = findViewById(R.id.tvCalories);
        tvTime = findViewById(R.id.tvTime);
        tvOverview = findViewById(R.id.tvOverview);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvDirections = findViewById(R.id.tvDirections);
        ivBackIcon = findViewById(R.id.ivBackIcon);

        String name = getIntent().getStringExtra("name");
        int calories = getIntent().getIntExtra("calories", 0);
        int time = getIntent().getIntExtra("time", 0);
        String overview = getIntent().getStringExtra("overview");
        String ingredients = getIntent().getStringExtra("ingredients");
        String directions = getIntent().getStringExtra("directions");
        int imageResource = getIntent().getIntExtra("image", -1);

        tvRecipeName.setText(name);
        tvCalories.setText(String.format("%dkcal", calories));
        tvTime.setText(String.format("%dmin", time));
        tvOverview.setText(overview);
        tvIngredients.setText(ingredients);
        tvDirections.setText(directions);
        ivRecipeImage.setImageResource(imageResource);

        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });    }
}
