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
        workoutPlans.add(new WorkoutPlanModel("Lower Body Guided Foam Roll", 15, "Foam rolling feels great and can temporarily relieve soreness. This routine is a quick way to foam roll all the major muscles of the legs.",
                "Foam Rolling Glutes, Foam Rolling Adductors, Calf Foam Rolling, Hamstring Foam Rolling, Quad Foam Rolling", R.drawable.workout_foam_roll));
        workoutPlans.add(new WorkoutPlanModel("Post Ride Lower Body Stretch", 8, "Legs feeling absolutely cooked after you last ride? Get your recovery started with this complete lower body stretch routine. Loosen up now, feel better tomorrow!",
                "Standing Quad Stretch, Crossover Hamstring Stretch, Upper Calf Stretch, Hip Flexor Stretch Kneeling, Butterfly Stretch, Figure 4 Glute Stretch", R.drawable.workout_post_ride));
        workoutPlans.add(new WorkoutPlanModel("Post Run Stretch Roll", 5, "Only have a few minutes post run to squeeze in some mobility? This is the perfect routine for you. Roll and stretch your key running muscles in this quick session!",
                "Calf Foam Rolling, Foam Rolling Glutes, Upper Calf Stretch, Hip Flexor Stretch Kneeling, Standing Quad Stretch, Quad Foam Rolling", R.drawable.workout_post_run));
        workoutPlans.add(new WorkoutPlanModel("Fast Foot Mobility", 5, "This is a great, quick way to loosen or warm up the muscles of your feet and ankles. Don't underestimate these short routines. They help make your next workout strong from the ground up.",
                "Toe Yoga, Toe Spreading, Toe Raise and Clench, Arch Raise", R.drawable.workout_fast_foot));
        workoutPlans.add(new WorkoutPlanModel("Glute and Core Strength", 20, "This is a fun way to work the muscle that keep us stable. The glutes align the lower leg during workouts and the core aligns the torso. Build you stability and strength with this fun and fast pace program.",
                "Lateral Toe Taps, Shifting Forward Plank, Single Leg Bridge, Side Planks with Rotations, Supine Scissor Kicks", R.drawable.workout_glute_core));
        workoutPlans.add(new WorkoutPlanModel("Plank Circuit", 10, "Love planks? We've got all our fun variations here in one place. Maximize your core stability with this fun routine!",
                "Plank Kick Outs, Side Planks with Rotations, Forward Plank Slides", R.drawable.workout_plank));
        workoutPlans.add(new WorkoutPlanModel("Light Stretching and Body Weight Mobility", 7, "Mobilize your major muscles then finish with a few light exercises to move your body through its whole range of motion. This is a great one to make part of your training habits.",
                "Standing Quad Stretch, Crossover Hamstring Stretch, Lower Calf Stretch Hip Flexor Stretch Kneeling, Bird Dogs, Seated Soleus Raises, Bodyweight Squat", R.drawable.workout_stretching));
        workoutPlans.add(new WorkoutPlanModel("Hip Strength Routine", 20, "Our hip muscles support structures all the way through our kinetic chain. This routine has just 1 set of each exercise. You'll breeze through it while building some serious hip strength.",
                "Side Leg Raises, 3 Way Side Plank, Lateral Band Walks Around, Single Leg Squats to Chair, Forward Step Down, Standing Clamshell, Side Plank with Hip Abduction", R.drawable.workout_hip));
        workoutPlans.add(new WorkoutPlanModel("Intro to Plyometrics", 9, "Hopping and jumping routines can help improve our explosiveness and efficiency while strengthening our bones. This routine is perfect for an athlete who's new to the world of plyos.",
                "Box Jumps - down, Double Leg Hops Laterally Over, 3 Direction Hops, Alternating Single Leg Hops, Out-Ins Off Step", R.drawable.workout_plyos));
        workoutPlans.add(new WorkoutPlanModel("Intermediate Plyometrics", 12, "Hopping and jumping routines can help improve our explosiveness and efficiency while strengthening our bones. Once your body is used to Intro to Plyos routine, give this more advanced version a try.",
                "Soccer Taps, Out-Out-Ins-Ins with Band, Out-Ins Off Step Banded, Single Leg Crossover Hops, Single Leg Forward Hops, ", R.drawable.workout_intermediate_plyo));
        workoutPlans.add(new WorkoutPlanModel("Advanced Glute Strength", 15, "If you do a lot of climbing on the bike or uphills when you run, strong hip extensors are especially important. This routine will help you strengthen your glutes to power all your adventures.",
                "Hip Thrust Weighted, Side Plank with Hip Abduction, Single Leg Squats to Chair, Standing Clamshell, Squats with Mini Band", R.drawable.workout_advance_glute));


        WorkoutAdapter adapter = new WorkoutAdapter(workoutPlans);
        recyclerView.setAdapter(adapter);

        // featured workouts
        RecyclerView featuredRecyclerView = findViewById(R.id.rvFeaturedWorkouts);
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<WorkoutPlanModel> featuredWorkouts = new ArrayList<>();
        featuredWorkouts.add(new WorkoutPlanModel("Plank Circuit", 10, "Love planks? We've got all our fun variations here in one place, Maximize your core stability with this fun routine!",
                "Plank Kick Outs, Side Planks with Rotations, Forward Plank Slides", R.drawable.workout_plank));
        featuredWorkouts.add(new WorkoutPlanModel("Glute and Core Strength", 20, "This is a fun way to wrok the muscle that keep us stable. The glutes align the lower leg during workouts and the core aligns the torso. Build you stability and strength with this fun and fast pace program.",
                "Lateral Toe Taps, Shifting Forward Plank, Single Leg Bridge, Side Planks with Rotations, Supine Scissor Kicks", R.drawable.workout_glute_core));
        featuredWorkouts.add(new WorkoutPlanModel("Intro to Plyometrics", 9, "Hopping and jumping routines can help improve our explosiveness and efficiency while strengthening our bones. This routine is perfect for an athlete who's new to the world of plyos.",
                "Box Jumps - down, Double Leg Hops Laterally Over, 3 Direction Hops, Alternating Single Leg Hops, Out-Ins Off Step", R.drawable.workout_plyos));
        featuredWorkouts.add(new WorkoutPlanModel("Hip Strength Routine", 20, "Our hip muscles support structures all the way through our kinetic chain. This routine has just 1 set of each exercise. You'll breeze through it while building some serious hip strength.",
                "Side Leg Raises, 3 Way Side Plank, Lateral Band Walks Around, Single Leg Squats to Chair, Forward Step Down, Standing Clamshell, Side Plank with Hip Abduction", R.drawable.workout_hip));

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
