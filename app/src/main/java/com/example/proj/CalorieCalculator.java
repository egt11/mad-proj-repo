package com.example.proj;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CalorieCalculator extends AppCompatActivity {
    ImageView ivBackIcon;
    EditText etAge, etHeight, etWeight;
    Spinner spActivityLevel;
    Button btnCalculate;
    TextView tvResult, headerActivity, headerCalories, activityText, caloriesText;
    CardView cvMale, cvFemale;
    String selectedGender = "";
    TableLayout tlCalorieInfo;
    TableRow headerRow, row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calorie_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
    }

    private void initialize() {
        etAge = findViewById(R.id.etAge);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        spActivityLevel = findViewById(R.id.spActivityLevel);
        btnCalculate = findViewById(R.id.btnCalculate);
        cvMale = findViewById(R.id.cvMale);
        cvFemale = findViewById(R.id.cvFemale);
        btnCalculate = findViewById(R.id.btnCalculate);
        ivBackIcon = findViewById(R.id.ivBackIcon);

        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cvMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = "Male";
                cvMale.setSelected(true);
                cvFemale.setSelected(false);
            }
        });

        cvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = "Female";
                cvFemale.setSelected(true);
                cvMale.setSelected(false);
            }
        });

        final double[] activityMultipliers = {1.2, 1.375, 1.55, 1.725, 1.9};
        final String[] activityLevels = {
                "Sedentary",
                "Lightly active",
                "Moderately active",
                "Very active",
                "Extra active"
        };

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    int age = Integer.parseInt(etAge.getText().toString());
                    double height = Double.parseDouble(etHeight.getText().toString());
                    double weight = Double.parseDouble(etWeight.getText().toString());
                    int activityLevel = spActivityLevel.getSelectedItemPosition();

                    if (selectedGender.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double bmr;
                    if (selectedGender.equals("Male")) {
                        bmr = 10 * weight + 6.25 * height - 5 * age + 5;
                    } else {
                        bmr = 10 * weight + 6.25 * height - 5 * age - 161;
                    }
                    double dailyCalories = bmr * activityMultipliers[activityLevel];

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CalorieCalculator.this);
                    View view1 = LayoutInflater.from(CalorieCalculator.this).inflate(R.layout.activity_calorie_result, null);
                    bottomSheetDialog.setContentView(view1);

                    tvResult = view1.findViewById(R.id.tvResult);
                    tvResult.setText(String.format(" %.2f", dailyCalories));
                    tlCalorieInfo = view1.findViewById(R.id.tlCalorieInfo);
                    tlCalorieInfo.removeAllViews(); // clean the table

                    headerRow = new TableRow(CalorieCalculator.this);
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

                    headerActivity = new TextView(CalorieCalculator.this);
                    headerActivity.setText(R.string.activity_level);
                    headerActivity.setTypeface(headerActivity.getTypeface(), Typeface.BOLD);
                    headerActivity.setPadding(16, 8, 16, 8);
                    headerActivity.setLayoutParams(params);
                    headerRow.addView(headerActivity);

                    headerCalories = new TextView(CalorieCalculator.this);
                    headerCalories.setText(R.string.calories);
                    headerCalories.setTypeface(headerCalories.getTypeface(), Typeface.BOLD);
                    headerCalories.setPadding(16, 8, 16, 8);
                    headerCalories.setLayoutParams(params);
                    headerRow.addView(headerCalories);

                    tlCalorieInfo.addView(headerRow);

                    for (int i = 0; i < activityLevels.length; i++) {
                        row = new TableRow(CalorieCalculator.this);

                        activityText = new TextView(CalorieCalculator.this);
                        activityText.setText(activityLevels[i]);
                        activityText.setPadding(16, 8, 16, 8);
                        activityText.setLayoutParams(params);
                        row.addView(activityText);

                        double activityCalories = bmr * activityMultipliers[i];
                        caloriesText = new TextView(CalorieCalculator.this);
                        caloriesText.setText(String.format("%.2f calories per day", activityCalories));
                        caloriesText.setPadding(16, 8, 16, 8);
                        caloriesText.setLayoutParams(params);
                        row.addView(caloriesText);

                        tlCalorieInfo.addView(row);
                    }

                    bottomSheetDialog.show();

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please fill out all fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
