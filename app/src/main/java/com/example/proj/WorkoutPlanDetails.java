package com.example.proj;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class WorkoutPlanDetails extends AppCompatActivity {
    TextView tvWorkoutName, tvMinutes, tvDescription;
    ImageView ivTopImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_plan_content);

        String workoutName = getIntent().getStringExtra("name");
        String exercisesString = getIntent().getStringExtra("exercises");
        int minutes = getIntent().getIntExtra("minutes", 0);
        String description = getIntent().getStringExtra("description");
        int imageResource = getIntent().getIntExtra("image", -1);

        tvWorkoutName = findViewById(R.id.tvWorkoutName);
        tvMinutes = findViewById(R.id.tvDetailMinutes);
        tvDescription = findViewById(R.id.tvDescription);
        ivTopImage = findViewById(R.id.ivTopImage);

        tvWorkoutName.setText(workoutName);
        tvMinutes.setText(String.format("%dmin", minutes));
        tvDescription.setText(description);
        ivTopImage.setImageResource(imageResource);

        List<String> exercises = Arrays.asList(exercisesString.split(", "));

        RecyclerView rvExercises = findViewById(R.id.rvExercises);
        rvExercises.setLayoutManager(new LinearLayoutManager(this));
        WorkoutPlan.ExerciseAdapter adapter = new WorkoutPlan.ExerciseAdapter(exercises);
        rvExercises.setAdapter(adapter);

        ImageView ivBackIcon = findViewById(R.id.ivBackIcon);
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
