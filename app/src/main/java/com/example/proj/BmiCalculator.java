package com.example.proj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BmiCalculator extends AppCompatActivity {

    ImageView ivBackIcon;
    EditText etWeight, etHeight;
    Button btnCalculate;
    TextView tvBmiResult, tvCategory, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi_calculator);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
    }

    private void initialize() {
        ivBackIcon = findViewById(R.id.ivBackIcon);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);

        // go back
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double weight = Double.parseDouble(etWeight.getText().toString());
                    double height = Double.parseDouble(etHeight.getText().toString());

                    if (weight <= 0 || height <= 0) {
                        Toast.makeText(getApplicationContext(), "Please enter valid positive numbers", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    double bmi = calculateBMI(weight, height);
                    String[] bmiInfo = getBMICategoryAndDescription(bmi);
                    String category = bmiInfo[0];
                    String description = bmiInfo[1];
                    int categoryColor = Integer.parseInt(bmiInfo[2]);

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BmiCalculator.this);
                    View view1 = LayoutInflater.from(BmiCalculator.this).inflate(R.layout.activity_bmi_result, null);
                    bottomSheetDialog.setContentView(view1);

                    tvBmiResult = view1.findViewById(R.id.tvBmiResult);
                    tvCategory = view1.findViewById(R.id.tvCategory);
                    tvDescription = view1.findViewById(R.id.tvDescription);

                    tvBmiResult.setText(String.format("%.1f", bmi));
                    tvCategory.setText(String.format("(%s)", category));
                    tvCategory.setTextColor(categoryColor);  // Set the color of the category

                    tvDescription.setText(description);

                    bottomSheetDialog.show();

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // calculate BMI
    private double calculateBMI(double weight, double height) {
        double heightMeters = height / 100.0;
        return weight / (heightMeters * heightMeters);
    }

    // get category and desc based on bmi
    private String[] getBMICategoryAndDescription(double bmi) {
        String category = "";
        String description = "";
        int color = 0;

        if (bmi < 18.5) {
            category = "Underweight";
            description = "You are underwight for your height. It's important to aim to keep within " +
                    "your healthy weight range. Being in the healthy weight range will improve your " +
                    "body's ability to fight off infection or illness.";
            color = getResources().getColor(R.color.blue);
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            category = "Normal";
            description = "You are a healthy weight for your height. Aim to keep within the ideal weight " +
                    "range by eating a healthy, well-balanced diet and exercising regularly. " +
                    "Most adults should be active for 30 minutes on most days.";
            color = getResources().getColor(R.color.green);
        } else if (bmi >= 25 && bmi <= 29.9) {
            category = "Overweight";
            description = "Being overweight increases your risk of developing coronary heart disease, " +
                    "as well as other health conditions such as diabetes. Keeping to a healthy weight " +
                    "will help you control your blood pressure and cholesterol levels. You lose weight " +
                    "if the amount of energy coming into your body is less than what is being used up " +
                    "by your body. Aim to exercise more and eat a healthy balanced diet.";
            color = getResources().getColor(R.color.orange);
        } else {
            category = "Obese";
            description = "As your BMI increases, your risk of developing coronary heart disease, " +
                    "diabetes and some cancers increases. It is important that you take steps to " +
                    "reduce your weight. The good news is that even losing small amounts of weight " +
                    "can benefit your health. You lose weight if the amount of energy coming into your " +
                    "body is less than what is being used up by your body. Aim to exercise more and " +
                    "eat healthy balanced diet.";
            color = getResources().getColor(R.color.red);
        }

        // Return category, description, and color
        return new String[]{category, description, String.valueOf(color)};
    }

}
