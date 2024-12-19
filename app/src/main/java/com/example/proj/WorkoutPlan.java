package com.example.proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan extends AppCompatActivity {
    ImageView ivBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_plan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // all workouts
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<WorkoutPlanModel> workoutPlans = new ArrayList<>();
        workoutPlans.add(new WorkoutPlanModel("Full Body", 30, "Push-ups, Squats", "A quick full-body workout.", "Push-ups, Squats, Plank, Push-ups, Squats, Plank, Push-ups, Squats, Plank, Push-ups, Squats, Plank", R.drawable.tarragon));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));
        workoutPlans.add(new WorkoutPlanModel("Cardio Blast", 20, "Jumping Jacks, Running", "An intense cardio session.", "Jumping Jacks, Running, Burpees", R.drawable.img_gym_onboarding));

        WorkoutAdapter adapter = new WorkoutAdapter(workoutPlans);
        recyclerView.setAdapter(adapter);

        // featured workouts
        RecyclerView featuredRecyclerView = findViewById(R.id.rvFeaturedWorkouts);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<WorkoutPlanModel> featuredWorkouts = new ArrayList<>();
        featuredWorkouts.add(new WorkoutPlanModel("HIIT Power", 15, "Burpees, Jumping Jacks", "A quick high-intensity workout.", "Burpees, Jumping Jacks, Plank", R.drawable.tarragon));
        featuredWorkouts.add(new WorkoutPlanModel("Yoga Flow", 25, "Downward Dog, Warrior Pose", "A relaxing yoga session.", "Downward Dog, Warrior Pose, Child's Pose", R.drawable.img_gym_onboarding));

        FeaturedWorkoutAdapter featuredAdapter = new FeaturedWorkoutAdapter(featuredWorkouts);
        featuredRecyclerView.setAdapter(featuredAdapter);

        goBack();
    }

    private void goBack() {
        ivBackIcon = findViewById(R.id.ivBackIcon);
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
        private final List<WorkoutPlanModel> workoutPlans;

        public WorkoutAdapter(List<WorkoutPlanModel> workoutPlans) {
            this.workoutPlans = workoutPlans;
        }

        @NonNull
        @Override
        public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_plan, parent, false);
            return new WorkoutViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
            WorkoutPlanModel plan = workoutPlans.get(position);
            holder.tvWorkoutName.setText(plan.getName());
            holder.tvMinutes.setText(String.format("%d minutes", plan.getMinutes()));
            holder.ivWorkoutImage.setImageResource(plan.getImage());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(WorkoutPlan.this, WorkoutPlanDetails.class);
                intent.putExtra("name", plan.getName());
                intent.putExtra("minutes", plan.getMinutes());
                intent.putExtra("movements", plan.getMovements());
                intent.putExtra("description", plan.getDescription());
                intent.putExtra("exercises", plan.getExercises());
                intent.putExtra("image", plan.getImage());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return workoutPlans.size();
        }

        public class WorkoutViewHolder extends RecyclerView.ViewHolder {
            TextView tvWorkoutName, tvMinutes;
            ImageView ivWorkoutImage;

            public WorkoutViewHolder(@NonNull View itemView) {
                super(itemView);
                tvWorkoutName = itemView.findViewById(R.id.tvWorkoutName);
                tvMinutes = itemView.findViewById(R.id.tvMinutes);
                ivWorkoutImage = itemView.findViewById(R.id.ivWorkoutImage);

            }
        }
    }

    private class FeaturedWorkoutAdapter extends RecyclerView.Adapter<FeaturedWorkoutAdapter.FeaturedWorkoutViewHolder> {
        private final List<WorkoutPlanModel> featuredWorkoutPlans;

        public FeaturedWorkoutAdapter(List<WorkoutPlanModel> featuredWorkoutPlans) {
            this.featuredWorkoutPlans = featuredWorkoutPlans;
        }

        @NonNull
        @Override
        public FeaturedWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_plan_featured, parent, false);
            return new FeaturedWorkoutViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FeaturedWorkoutViewHolder holder, int position) {
            WorkoutPlanModel plan = featuredWorkoutPlans.get(position);
            holder.tvFeaturedWorkoutName.setText(plan.getName());
            holder.tvFeaturedWorkoutMinutes.setText(String.format("%d minutes", plan.getMinutes()));
            holder.ivWorkoutImage.setImageResource(plan.getImage());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(WorkoutPlan.this, WorkoutPlanDetails.class);
                intent.putExtra("name", plan.getName());
                intent.putExtra("minutes", plan.getMinutes());
                intent.putExtra("movements", plan.getMovements());
                intent.putExtra("description", plan.getDescription());
                intent.putExtra("exercises", plan.getExercises());
                intent.putExtra("image", plan.getImage());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return featuredWorkoutPlans.size();
        }

        public class FeaturedWorkoutViewHolder extends RecyclerView.ViewHolder {
            TextView tvFeaturedWorkoutName, tvFeaturedWorkoutMinutes;
            ImageView ivWorkoutImage;

            public FeaturedWorkoutViewHolder(@NonNull View itemView) {
                super(itemView);
                tvFeaturedWorkoutName = itemView.findViewById(R.id.tvFeaturedWorkoutName);
                tvFeaturedWorkoutMinutes = itemView.findViewById(R.id.tvFeaturedWorkoutMinutes);
                ivWorkoutImage = itemView.findViewById(R.id.ivWorkoutImage);
            }
        }
    }

    public static class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
        private final List<String> exercises;

        public ExerciseAdapter(List<String> exercises) {
            this.exercises = exercises;
        }

        @NonNull
        @Override
        public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_card, parent, false);
            return new ExerciseViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
            holder.tvExerciseName.setText(exercises.get(position));

        }

        @Override
        public int getItemCount() {
            return exercises.size();
        }

        public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
            TextView tvExerciseName;

            public ExerciseViewHolder(@NonNull View itemView) {
                super(itemView);
                tvExerciseName = itemView.findViewById(R.id.tvExerciseName);
            }
        }
    }
}
