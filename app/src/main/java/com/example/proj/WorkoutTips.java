package com.example.proj;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class WorkoutTips extends AppCompatActivity {
    TextView tvTipsTitle, tvTipsContent;
    ImageView ivBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_tips);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<CardItem> cardItems = new ArrayList<>();
        cardItems.add(new CardItem("Eat Slow-Digesting Carbs Before Workouts",
                "Researchers at Loughborough University (U.K.) discovered that when athletes ate slow-digesting carbs such as whole grains for breakfast and lunch, they had lower insulin levels and burned more fat during the day. The athletes also had more endurance and burned more fat during exercise compared to those who ate fast-digesting carbs such as white bread or plain bagels. Be sure that all the meals you eat before your workout, including the one immediately before, include about 40g of slow-digesting carbs such as oatmeal, sweet potatoes, fruit, buckwheat (see tip No.4), or whole-wheat bread.."));
        cardItems.add(new CardItem("Avoid Higher-Fat Meals For Up to Four Hours Before Workouts",
                "A University of Maryland School of Medicine (Baltimore) study reported that a high-fat meal blunts the ability of nitric oxide (NO) to dilate blood vessels for up to four hours. That means less blood flow to muscles and less of a muscle pump, which is even more costly if you’ve invested in an NO supplement. In the four hours before your workout, avoid eating large amounts of fats, such as the obvious fast-food fare and packaged foods (even if you’re in a mass-gaining phase)."));
        cardItems.add(new CardItem("Eat a Green Salad With Your Last Whole-Food Meal Before The Gym",
                "The same University of Maryland researchers also discovered that consuming a small green salad with a high-fat meal prevented the adverse effects on blood vessel dilation, likely by enhancing NO. About two hours before you hit the gym, include a green salad with low-fat dressing with your meal."));
        cardItems.add(new CardItem("Eat Buckwheat As Part Of Your Pre-Workout Carb Intake",
                "Buckwheat, found in buckwheat pancakes and soba noodles, is a fruit seed that’s often used as a substitute for grains. It digests slowly, which helps increase endurance and fat-burning. Buckwheat also contains a flavonoid called chiroinositol, which mimics insulin. A cup of cooked soba noodles before workouts can help get more pre-workout creatine (see tip No.5) into your muscle cells without blunting fat loss, which can occur from high insulin spikes."));
        cardItems.add(new CardItem("Take Whey Protein Creatine Supplement",
                "Take 20g of whey protein and 3-5g of a creatine supplement. Researchers from Victoria University (Australia) reported that subjects who consumed a protein and creatine supplement immediately before and after workouts over a 10-week period increased muscle mass by 87%, bench press strength by 36%, squat strength by 27%, and deadlift strength by 25%, and decreased bodyfat by 3%, more than a group taking the supplement before breakfast and before bed."));
        cardItems.add(new CardItem("Take Caffeine Before Your Workout",
                "Take 200-400mg of caffeine 1-2 hours before your workout. Research shows that caffeine taken pre-workout increases fat-burning and endurance and blunts muscle pain during training, which means you can do more reps. A more recent study, from the University of Nebraska (Lincoln), indicates that subjects who took a caffeine supplement before their workouts immediately increased their one-rep max (1RM) on the bench press by about 5 pounds. Studies show caffeine supplements work better than caffeine from coffee."));
        cardItems.add(new CardItem("Take Arginine Before Workouts",
                "One study reported in the journal Nutrition that trained subjects who took arginine supplements for eight weeks increased their 1RMs for the bench press by almost 20 pounds more than those who took a placebo. Take 3-5g of arginine 30-45 minutes before workouts"));
        cardItems.add(new CardItem("Add Cocoa Extract to Your Shake",
                "Add 2 teaspoons of cocoa extract to your preworkout protein shake. University of California, Davis, scientists discovered that a flavonol called epicathechin in cocoa boosts NO levels and blood vessel dilation. If you’ve taken your NO and had a preworkout salad, this will keep NO levels higher longer."));
        cardItems.add(new CardItem("Use Forced Reps on Your Last Sets",
                "A Finnish study found that when subjects performed a workout with forced reps (a spotter helped them get through their sticking points to get a few more reps), their growth hormone (GH) levels were almost 4,000% higher than without using forced reps. For the last set of each exercise after reaching failure, go for 2-3 extra forced reps, but utilize these sparingly to prevent overtraining."));
        cardItems.add(new CardItem("Don’t Train to Failure on Every Set",
                "Australian scientists have reported that training with one set to failure increases strength better than taking no sets to failure. However, when subjects did more than one set to failure, strength gains were lowered by almost half compared to the subjects doing just one set to failure."));
        cardItems.add(new CardItem("Keep Your Focus on the Muscle You’re Training",
                "British researchers discovered that subjects who focused on their biceps while doing biceps curls had significantly more muscle activity than those who thought about other things. More muscle recruitment can result in more muscle growth in the long run. Be sure that for every rep of every set during your workout you’re thinking about the muscle(s) being trained, instead of wondering where that blonde wearing the short shorts went to do her bent-over rows."));
        cardItems.add(new CardItem("Vary Your Rep Speed",
                "In another Australian study, subjects performing fast repetitions (one second each on the positive and the negative portions of the rep) gained more strength than subjects using slow reps (three seconds each on the positive and negative) because fast-twitch muscle fibers have the greatest potential for strength increases. But the slow-rep subjects gained more muscle mass than the fast-rep subjects, likely due to the muscular time under tension and increased microtrauma. A good mix of both is the best way to maximize strength and size. Try changing from your regular controlled rep speed to 2-3 weeks of fast reps followed by 2-3 weeks of slow reps. "));
        cardItems.add(new CardItem("Train With Several Partners",
                "Research shows that when trained lifters attempt a 1RM in front of a group of people, they’re stronger than when they lift in front of just one."));
        cardItems.add(new CardItem("Listen To Music",
                "A study done at the Weider Research Group found that when trained bodybuilders performed a shoulder workout while listening to music, they were able to complete an average of 1-2 more reps per set for all sets of all exercises. So for another source of motivation, create a playlist of your favorite songs that jack up your adrenaline and bring it to the gym."));
        cardItems.add(new CardItem("Don’t Train Too Heavy For Too Long",
                "Yes, training with a heavy weight that prevents you from getting more than 4-5 reps is good for strength and overall mass when done in conjunction with lighter training that allows you to get 8-12 reps. Yet too much heavy training may work against muscle growth. Baylor University (Waco, TX) scientists found that when athletes trained using their 6RMs, they had higher levels of active myostatin (a protein that limits muscle growth) than when they did the same workout using their 18RMs. Keep to your heavy rep ranges for no longer than 6-8 weeks, then switch to a lighter-weight, higher-rep scheme to keep your myostatin levels in check."));
        cardItems.add(new CardItem("Stay Off the Exercise Balls, Wobble Boards, and Other Instability Devices",
                "Canadian scientists reported that doing dumbbell chest presses on an exercise ball resulted in a 40% decrease in strength compared to doing the exercise on a bench. And a study from Appalachian State University (Boone, NC) found that when athletes performed squats on instability discs, they had less force production and muscle activity of the quadriceps compared to when they did squats on firm ground. Since any marked increase in strength or muscle mass is going to come through moving progressively heavier weights on key lifts, you might want to avoid exercise balls and instability boards. Plant yourself for better gains."));
        cardItems.add(new CardItem("Save Cardio For After Your Weight Workout",
                "Researchers from Japan found that when subjects did cardio before weights, their GH response to the weight workout was blunted by about 1,100% compared to when they lifted first and ended with cardio. Try to do your cardio either after weights or on a different day."));
        cardItems.add(new CardItem("Drink Your Shake",
                "Researchers from Baylor University (Waco, TX) reported that subjects taking a whey-plus-casein protein powder immediately post-workout for 10 weeks increased muscle mass significantly more than those taking whey without casein.Drink a shake with 20g each of whey and casein proteins."));
        cardItems.add(new CardItem("Don’t Drop Dietary Cholesterol Too Low",
                "We know cholesterol is important for maintaining testosterone levels, but Kent State University (OH) scientists reported that older adults eating a diet higher in cholesterol while weight training for 12 weeks gained 55% more strength and had more than five times the muscle growth as those following a diet lower in cholesterol. Keep some cholesterol in your diet by eating at least 1-2 egg yolks with your egg whites at breakfast and at least one meal of lean red meat each day. Liver, shellfish and duck are other good sources of healthy cholesterol."));
        cardItems.add(new CardItem("Take Creatine, Fast-Digesting Carbs, Alpha-Lipoic Acid",
                "A study from the University of Saskatchewan (Canada) found that subjects who took ALA, creatine and sucrose right after a workout increased muscle creatine levels significantly more than those taking creatine and sucrose or creatine only. Take 3-5g of creatine with 50-100g of fast-digesting carbs, and 300-500mg of alpha-lipoic acid (ALA)"));

        recyclerView.setAdapter(new SimpleAdapter(cardItems));

        ivBackIcon = findViewById(R.id.ivBackIcon);
        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showBottomSheetDialog(String title, String content) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(WorkoutTips.this);
        View view1 = LayoutInflater.from(WorkoutTips.this).inflate(R.layout.activity_workout_tips_content, null);
        bottomSheetDialog.setContentView(view1);

        tvTipsTitle = view1.findViewById(R.id.tvTipsTitle);
        tvTipsContent = view1.findViewById(R.id.tvTipsContent);

        tvTipsTitle.setText(title);
        tvTipsContent.setText(content);

        bottomSheetDialog.show();
    }

    private static class CardItem {
        String title;
        String content;

        CardItem(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    private class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
        private final List<CardItem> items;

        public SimpleAdapter(List<CardItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workout_tips, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            CardItem item = items.get(position);
            holder.title.setText(item.title);
            holder.number.setText(String.valueOf(position + 1));

            holder.itemView.setOnClickListener(v -> {
                showBottomSheetDialog(item.title, item.content);
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, number;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.tvTitle);
                number = itemView.findViewById(R.id.tvNumber);
            }
        }
    }
}
