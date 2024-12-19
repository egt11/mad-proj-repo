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

public class RecipePage extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RecipeModel> recipeList;
    ImageView ivBackIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivBackIcon = findViewById(R.id.ivBackIcon);
        recyclerView = findViewById(R.id.recipeRecyclerView);

        ivBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recipeList = getRecipes();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecipeAdapter(recipeList));
    }

    private static class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
        private final List<RecipeModel> recipes;

        public RecipeAdapter(List<RecipeModel> recipes) {
            this.recipes = recipes;
        }

        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
            return new RecipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            RecipeModel recipe = recipes.get(position);
            holder.tvRecipeName.setText(recipe.getName());
            holder.tvRecipeInfo.setText(String.format("%d kcal | %d min", recipe.getCalories(), recipe.getTime()));
            holder.ivRecipeImage.setImageResource(recipe.getImage());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), RecipeDetails.class);
                intent.putExtra("name", recipe.getName());
                intent.putExtra("calories", recipe.getCalories());
                intent.putExtra("time", recipe.getTime());
                intent.putExtra("overview", recipe.getOverview());
                intent.putExtra("ingredients", recipe.getIngredients());
                intent.putExtra("directions", recipe.getDirections());
                intent.putExtra("image", recipe.getImage());
                v.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return recipes.size();
        }

        public static class RecipeViewHolder extends RecyclerView.ViewHolder {
            TextView tvRecipeName, tvRecipeInfo;
            ImageView ivRecipeImage;

            public RecipeViewHolder(@NonNull View itemView) {
                super(itemView);
                tvRecipeName = itemView.findViewById(R.id.tvRecipeName);
                tvRecipeInfo = itemView.findViewById(R.id.tvRecipeInfo);
                ivRecipeImage = itemView.findViewById(R.id.ivRecipeImage);
            }
        }
    }

    private List<RecipeModel> getRecipes() {
        List<RecipeModel> recipes = new ArrayList<>();
        recipes.add(new RecipeModel("Stuffed Pepper Soup", 570, 25,
                "Inspired by stuffed peppers, this soup is ready faster and with fewer dishes too! Have fun with the toppings—we top it with onion, Cheddar cheese and tortilla chips here, but salsa, sour cream and corn would also make stellar additions.",
                "1 tablespoon extra-virgin olive oil\n" +
                        "3 large bell peppers, chopped\n" +
                        "1 poblano pepper, chopped\n" +
                        "1 medium onion, chopped, plus more for serving\n" +
                        "1 pound lean ground beef\n" +
                        "2 cloves garlic, minced\n" +
                        "2 teaspoons ground cumin\n" +
                        "1 teaspoon ground coriander\n" +
                        "½ teaspoon ground pepper\n" +
                        "¼ teaspoon salt\n" +
                        "4 cups low-sodium chicken broth\n" +
                        "1 cup quick-cooking brown rice\n" +
                        "¼ cup chopped fresh cilantro, plus more for serving\n" +
                        "Shredded Cheddar cheese & crushed tortilla chips for serving",
                "1. Heat oil in a large pot over medium-high heat. Add bell peppers, poblano and onion and cook, stirring often, until starting to soften, about 10 minutes. Push the vegetables to the edges. Add beef, garlic, cumin, coriander, ground pepper and salt to the middle and cook, crumbling the beef with a wooden spoon, until it's no longer pink, 3 to 5 minutes.\n" +
                        "2. Stir in broth and rice and bring to a boil. Reduce heat to maintain a low simmer, cover and cook until the rice is tender, 15 to 20 minutes. Remove from heat and stir in cilantro.\n" +
                        "3. Serve the soup topped with cheese, corn chips and more onion and cilantro, if desired.",
                R.drawable.recipe_stuffed_pepper_soup));
        recipes.add(new RecipeModel("Peppery Barbecue-Glazed Shrimp with Vegetables & Orzo", 360, 30,
                "In this healthy BBQ shrimp recipe, shrimp are seasoned with a peppery spice blend and served with zucchini, peppers and whole-grain orzo for a delicious and easy dinner that's ready in just 30 minutes. The shrimp and veggies are cooked in the same skillet, so cleanup is a snap too.",
                "1 pound peeled and deveined jumbo shrimp, thawed if frozen (see Tip)\n" +
                        "1 teaspoon paprika\n" +
                        "½ teaspoon garlic powder\n" +
                        "½ teaspoon dried oregano, crushed\n" +
                        "¼ teaspoon ground pepper\n" +
                        "⅛ teaspoon cayenne pepper\n" +
                        "1 cup whole-grain orzo\n" +
                        "3 scallions\n" +
                        "2 tablespoons olive oil, divided\n" +
                        "2 cups coarsely chopped zucchini\n" +
                        "1 cup coarsely chopped bell pepper\n" +
                        "½ cup thinly sliced celery\n" +
                        "1 cup cherry tomatoes, halved\n" +
                        "½ teaspoon salt\n" +
                        "2 tablespoons barbecue sauce\n" +
                        "Lemon wedges for serving",
                "1. Place shrimp in a medium bowl. Combine paprika, garlic powder, oregano, pepper and cayenne in a small bowl. Sprinkle the spice mixture over the shrimp; toss to coat and set aside.\n" +
                        "2. Bring a large saucepan of water to a boil. Cook orzo according to package directions; drain. Return to the hot pot; cover and keep warm.\n" +
                        "3. Meanwhile, slice scallions, separating white and green parts. Heat 1 tablespoon oil in a medium skillet over medium-high heat. Add the scallion whites, zucchini, bell pepper and celery; cook, stirring occasionally, until the vegetables are crisp-tender, about 5 minutes. Add tomatoes; cook until softened, 2 to 3 minutes more. Add the vegetables to the pot with the orzo. Add salt; toss to combine.\n" +
                        "4. In same skillet, heat the remaining 1 tablespoon oil over medium heat. Add the shrimp; cook, turning once, until opaque, 4 to 6 minutes. Drizzle with barbecue sauce. Cook and stir until the shrimp are coated, about 1 minute.\n" +
                        "5. Serve the shrimp with the vegetable mixture. Top with scallion greens and serve with lemon wedges, if desired.",
                R.drawable.recipe_peppery_bbq));
        recipes.add(new RecipeModel("Chicken Parmesan & Quinoa Stuffed Peppers", 559, 15,
                "Chicken Parm gets a fun low-carb and gluten-free twist with these cheesy stuffed peppers with chicken and quinoa. Serve with a salad for a healthy dinner that's easy to prep too.",
                "1 tablespoon olive oil\n" +
                        "1 medium onion, chopped (about 1 1/2 cups)\n" +
                        "4 cloves garlic, minced\n" +
                        "1 cup quinoa, rinsed\n" +
                        "1 ¼ cups water\n" +
                        "3 cups shredded cooked chicken breast\n" +
                        "1 ½ cups lower-sodium marinara sauce\n" +
                        "⅓ cup grated Parmesan cheese\n" +
                        "¾ cup sliced fresh basil, divided\n" +
                        "4 large red bell peppers (about 8 ounces each)\n" +
                        "2 ounces low-moisture, part-skim mozzarella cheese, shredded (about 1/2 cup)",
                "1. Preheat oven to 350 degrees F. Heat oil in a medium saucepan over medium-high heat. Add onion and garlic; cook, stirring occasionally, until the onion is translucent, 4 to 5 minutes. Add quinoa; cook, stirring occasionally, for 30 seconds. Add water; increase heat to high and bring to a boil. Reduce heat to medium; cover and cook for 15 minutes. Remove from heat; let stand, covered, for 5 minutes. Stir in chicken, marinara, Parmesan and 1/2 cup basil.\n" +
                        "2. Trim top 1/2 inch from peppers; remove seeds and membranes. Arrange the peppers, cut-sides up, in an 8-inch-square glass baking dish. Cover with plastic wrap; microwave on High for 3 minutes. Remove plastic wrap. Spoon the quinoa mixture evenly into the pepper halves (about 1 1/4 cups each).\n" +
                        "3. Bake the stuffed peppers until they are softened, about 15 minutes. Sprinkle evenly with mozzarella. Continue baking until the cheese is melted, 5 to 7 minutes. Sprinkle evenly with the remaining 1/4 cup basil.",
                R.drawable.recipe_chicker_parmesan));
        recipes.add(new RecipeModel("Spinach & Artichoke Dip Pasta", 371, 20,
                "If you've ever wanted to make a meal out of warm spinach and artichoke dip, this creamy pasta is for you. And here's what's almost as good as the flavor of this comforting dish: the fact that this healthy dinner takes just 20 minutes to prepare.",
                "8 ounces whole-wheat rotini\n" +
                        "1 (5 ounce) package baby spinach, roughly chopped\n" +
                        "4 ounces reduced-fat cream cheese, cut into chunks\n" +
                        "¾ cup reduced-fat milk\n" +
                        "½ cup grated Parmesan cheese, plus more for garnish, if desired\n" +
                        "2 teaspoons garlic powder\n" +
                        "¼ teaspoon ground pepper\n" +
                        "1 (14 ounce) can artichoke hearts, rinsed, squeezed dry and chopped (see Tip)",
                "1. Bring a large saucepan of water to a boil. Cook pasta according to package directions. Drain.\n" +
                        "2. Combine spinach and 1 tablespoon water in a large saucepan over medium heat. Cook, stirring occasionally, until just wilted, about 2 minutes. Transfer to a small bowl. \n" +
                        "3. Add cream cheese and milk to the pan; whisk until the cream cheese is melted.\n" +
                        "4. Add Parmesan, garlic powder and pepper; cook, whisking until thickened and bubbling.\n" +
                        "5. Drain as much liquid as possible from the spinach. Stir the drained spinach into the sauce, along with artichokes and the pasta. Cook until warmed through.",
                R.drawable.recipe_spinach));
        recipes.add(new RecipeModel("Stuffed Sweet Potatoes with Chili", 266, 20,
                "Sweet potatoes pair wonderfully with this simple chili recipe. Add one more chipotle pepper if you want to spice up this healthy sweet potato recipe.",
                "2 small sweet potatoes (about 8 oz. each)\n" +
                        "1 tablespoon canola oil\n" +
                        "8 ounces 95%-lean ground beef\n" +
                        "2 scallions, sliced, green and white parts separated\n" +
                        "2 tablespoons tomato paste\n" +
                        "1-2 canned chipotle peppers in adobo, chopped\n" +
                        "1 tablespoon paprika\n" +
                        "¼ teaspoon salt\n" +
                        "1 cup canned low-sodium kidney beans, rinsed\n" +
                        "½ cup water\n" +
                        "¼ cup shredded Mexican-blend cheese\n" +
                        "4 teaspoons reduced-fat sour cream for garnish",
                "1. Preheat oven to 425 degrees F. Line a baking sheet with foil.\n" +
                        "2. Prick sweet potatoes in several places with a fork and place on the prepared pan. Bake until tender when pierced with a knife, 40 to 45 minutes. Let cool slightly.\n" +
                        "3. Meanwhile, heat oil in a large skillet over medium-high heat. Add ground beef and scallion whites. Cook, stirring and breaking up the beef with a wooden spoon, until it is no longer pink, about 4 minutes. Add tomato paste, chipotles to taste, paprika, and salt. Cook, stirring, until the tomato paste begins to brown, about 1 minute. Add beans and water; cook, stirring, until most of the liquid has evaporated, about 1 minute more. Cover and keep warm.\n" +
                        "4. Slice the sweet potatoes in half lengthwise. Leaving the skins intact, carefully scoop the flesh into a medium bowl. Add 1 cup of the chili mixture and stir to combine. Fill each potato skin with the sweet potato-chili mixture (the skins will be full). Return to the baking sheet and top with remaining chili mixture and cheese.\n" +
                        "5. Bake until the cheese is melted and the potatoes are heated through, 3 to 5 minutes. Top with scallion greens and garnish with sour cream, if desired.",
                R.drawable.recipe_stuffed_sweet));
        recipes.add(new RecipeModel("Instant Pot Chicken Soup with Root Vegetables & Barley", 219, 40,
                "Be sure to use bone-in chicken here--it enhances the flavor of the broth, and the bones are easy to remove after cooking. This healthy chicken soup can be made in an Instant Pot or pressure cooker.",
                "1 tablespoon olive oil or canola oil\n" +
                        "1 large onion, chopped (2 cups)\n" +
                        "2 stalks celery, chopped (1/2 cup)\n" +
                        "3 cloves garlic, minced\n" +
                        "½ cup pearled barley, rinsed\n" +
                        "1 (32 ounce) carton low-sodium chicken broth\n" +
                        "1 pound bone-in chicken breast or thighs\n" +
                        "4 medium carrots, peeled and cut into 1-inch chunks (2 cups)\n" +
                        "2 large parsnips, cored, peeled, and cut into 1-inch chunks (2 cups)\n" +
                        "¼ cup chopped fresh dill or 1 Tbsp. dried\n" +
                        "3 tablespoons lemon juice, plus lemon wedges for serving\n" +
                        "½ teaspoon salt\n" +
                        "¼ teaspoon ground pepper",
                "1. Heat oil in a multicooker using the Saute setting. Add onion and celery; cook, stirring occasionally, until softened, 3 to 5 minutes. (Adjust temperature as necessary by pressing the Saute button for more or less heat.) Add garlic and cook, stirring, for 30 seconds. Add barley and stir to coat. Add broth, chicken, carrots, and parsnips.\n" +
                        "2. Lock the lid in place and twist the steam-release handle to the sealed position. Select Pressure Cook/High for 8 minutes (or follow manufacturer's directions to pressure-cook for 8 minutes).\n" +
                        "3. When pressure-cooking is complete, let the pressure release naturally for 10 minutes. Release the remaining pressure manually. Check for doneness: an instant-read thermometer inserted in the thickest part of the chicken should register at least 165F and the barley and vegetables should be tender. (If necessary, switch to the Saute setting and cook until everything is done.)\n" +
                        "4. Using tongs, transfer the chicken to a clean cutting board. When it is cool enough to handle, remove and discard the skin and bones. Shred or cut the chicken into bite-size pieces; return to the soup and heat through using the Saute setting, if necessary. Stir in dill, lemon juice, salt, and pepper. Serve with lemon wedges, if desired.",
                R.drawable.recipe_instant_chicken));
        recipes.add(new RecipeModel("Baked Halibut with Brussels Sprouts & Quinoa", 406, 15,
                "Fish plus two sides? It seems fancy but this healthy dinner comes together in just 30 minutes.",
                "1 pound Brussels sprouts, trimmed and sliced\n" +
                        "1 fennel bulb, trimmed and cut into strips\n" +
                        "1 tablespoon plus 1 teaspoon olive oil, divided\n" +
                        "½ teaspoon salt, divided\n" +
                        "½ teaspoon ground pepper, divided\n" +
                        "1 (1 pound) halibut fillet, cut into 4 portions\n" +
                        "4 cloves garlic, minced, divided\n" +
                        "3 tablespoons lemon juice\n" +
                        "2 tablespoons unsalted butter, melted\n" +
                        "2 cups cooked quinoa\n" +
                        "¼ cup chopped sun-dried tomatoes\n" +
                        "¼ cup chopped pitted Kalamata olives\n" +
                        "2 tablespoons chopped fresh Italian parsley or fennel fronds",
                "1. Position racks in upper and lower thirds of oven; preheat to 400 degrees F.\n" +
                        "2. Combine Brussels sprouts, fennel, 1 Tbsp. oil, and 1/4 tsp. each salt and pepper in a large bowl; toss to coat. Spread in a single layer on a large rimmed baking sheet. Bake, stirring occasionally, until tender, 20 to 25 minutes.\n" +
                        "3. Meanwhile, place halibut on another large rimmed baking sheet and top with half of the garlic and the remaining 1/4 tsp. each salt and pepper. Combine lemon juice and melted butter in a small bowl. Drizzle or brush half of the mixture over the fish. Bake until the fish is opaque and flakes easily with a fork, 12 to 15 minutes.\n" +
                        "4. Meanwhile, combine quinoa, the remaining 1 tsp. oil, sun-dried tomatoes, olives, and parsley (or fennel fronds) in a medium bowl.\n" +
                        "5. Add the remaining garlic to the lemon-butter mixture. Pour the mixture over the vegetables and bake for 1 minute more. Serve the halibut and vegetables alongside the quinoa mixture.",
                R.drawable.recipe_baked_halibut));
        recipes.add(new RecipeModel("Smoky Collards & Shrimp with Cheesy Grits", 443, 45,
                "Be sure to slice the collards extra thin; it will help expedite the cooking process and produce the most tender greens.",
                "3 cups water\n" +
                        "¾ cup whole milk\n" +
                        "¾ cup grits or polenta\n" +
                        "⅓ cup grated Parmesan cheese\n" +
                        "¾ teaspoon kosher salt, divided\n" +
                        "½ teaspoon ground pepper, divided\n" +
                        "2 tablespoons extra-virgin olive oil, divided\n" +
                        "1 large onion, chopped\n" +
                        "3 cloves garlic, sliced\n" +
                        "1 (14 ounce) can no-salt-added fire-roasted diced tomatoes\n" +
                        "3 teaspoons smoked paprika, divided\n" +
                        "2 pounds collards, stemmed and thinly sliced\n" +
                        "1 pound raw shrimp (31-35 count), peeled and deveined",
                "1. Combine water and milk in a large saucepan; bring to a boil. Whisk in grits (or polenta) until smooth. Cover and reduce heat to low. Cook, whisking occasionally, until creamy and thickened, about 25 minutes. Remove from heat.\n" +
                        "2. Stir in Parmesan, 1/2 teaspoon salt and 1/4 teaspoon pepper. Cover and set aside.\n" +
                        "3. Meanwhile, heat 1 tablespoon oil in a large high-sided skillet or large pot over medium heat. Add onion and cook, stirring occasionally, until soft, about 3 minutes. Add garlic and cook for 1 minute. Stir in tomatoes, 2 teaspoons paprika and the remaining 1/4 teaspoon each salt and pepper. Add collards by the handful, letting them wilt slightly after each addition. Continue to cook, stirring frequently, until the collards are tender and beginning to darken, about 10 minutes. Transfer to a bowl and cover to keep warm.\n" +
                        "4. Add the remaining 1 tablespoon oil to the pan and increase heat to medium-high. Toss shrimp with the remaining 1 teaspoon paprika in a medium bowl. Add the shrimp to the pan and cook, stirring occasionally, until they turn pink, 3 to 4 minutes.\n" +
                        "5. Serve the shrimp and collards with the grits.",
                R.drawable.recipe_smoky_collards));
        recipes.add(new RecipeModel("Instant Pot Goulash", 490, 25,
                "Your Instant Pot (or other multicooker) cuts hours off the cooking time of this Hungarian beef stew recipe. This Instant Pot goulash, a saucy dish flavored with caraway and smoked paprika, is served over whole-wheat egg noodles that cook while your multicooker works its magic. It's comfort food at its best!",
                "1 ½ pounds beef stew meat such as chuck, trimmed and cut into 1 1/2-inch pieces\n" +
                        "3 tablespoons all-purpose flour\n" +
                        "1 tablespoon canola oil\n" +
                        "2 cups thinly sliced red onions\n" +
                        "1 tablespoon chopped fresh thyme\n" +
                        "5 cloves garlic, chopped\n" +
                        "1 tablespoon no-salt-added tomato paste\n" +
                        "1 tablespoon smoked paprika\n" +
                        "2 teaspoons caraway seeds\n" +
                        "3 cups angel hair coleslaw mix or finely shredded cabbage\n" +
                        "1 (14 ounce) can stewed tomatoes, undrained\n" +
                        "¾ teaspoon salt\n" +
                        "½ teaspoon ground pepper\n" +
                        "12 ounces whole-wheat egg noodles\n" +
                        "¾ cup whole-milk plain yogurt",
                "1. Toss beef and flour together in a medium bowl. Select Sauté setting on a programmable pressure multicooker (such as an Instant Pot; times, instructions and settings may vary according to cooker brand or model). Select High temperature setting, and allow to preheat. Add oil to the cooker. Add the flour-coated beef; cook, stirring often, until browned, 7 to 8 minutes. Add onions, thyme and garlic; cook, stirring often, until the onions soften, 5 to 6 minutes. Add tomato paste, paprika and caraway seeds; cook, stirring constantly, for 1 minute. Stir in cabbage, tomatoes and their juice, salt and pepper. Press Cancel.\n" +
                        "2. Cover the cooker and lock the lid in place. Turn the steam release handle to Sealing position. Select Manual/Pressure Cook setting. Select High pressure for 30 minutes. (It will take 5 to 7 minutes for the cooker to come up to pressure before cooking begins.) When cooking is complete, let the pressure release naturally (the float valve will drop; this will take 5 to 7 minutes) before removing the lid from the cooker.\n" +
                        "3. Meanwhile, prepare noodles according to package directions; drain.\n" +
                        "4. Divide the noodles evenly among 6 bowls; top with the goulash and yogurt.",
                R.drawable.recipe_instant_goulash));
        recipes.add(new RecipeModel("Chicken Hummus Bowls", 485, 25,
                "The spiced chicken atop these bowls is ready fast with the help of the broiler. Serve with warm whole-wheat pita for scooping up extra hummus at the bottom of the bowl.",
                "1 pound boneless, skinless chicken thighs, trimmed and cut into 1-inch pieces\n" +
                        "3 tablespoons extra-virgin olive oil, divided\n" +
                        "1 teaspoon ground cumin\n" +
                        "1 teaspoon paprika\n" +
                        "¼ teaspoon cayenne pepper\n" +
                        "¼ teaspoon salt, divided\n" +
                        "2 cloves garlic, finely chopped\n" +
                        "2 tablespoons lemon juice\n" +
                        "2 cups hummus\n" +
                        "1 English cucumber, halved lengthwise and sliced\n" +
                        "1 pint cherry tomatoes, halved\n" +
                        "¼ cup slivered red onion\n" +
                        "¼ cup chopped fresh parsley",
                "1. Position rack in upper third of oven; preheat broiler to high. Line a rimmed baking sheet with foil.\n" +
                        "2. Toss chicken with 1 tablespoon oil, cumin, paprika, cayenne and 1/8 teaspoon salt. Spread evenly on the prepared pan. Broil until just cooked through, 5 to 7 minutes.\n" +
                        "3. Meanwhile, mash garlic and the remaining 1/8 teaspoon salt into a paste with a fork. Transfer to a medium bowl and whisk in lemon juice and the remaining 2 tablespoons oil. Add the chicken and let stand for 5 minutes, stirring occasionally.\n" +
                        "4. Divide hummus among 4 shallow bowls or plates. Top with the chicken and any remaining dressing, cucumber, tomatoes, onion and parsley.",
                R.drawable.recipe_chicken_humus));
        recipes.add(new RecipeModel("California Turkey Burgers & Baked Sweet Potato Fries", 463, 20,
                "Inspired by an option at the West Coast chain In-N-Out Burger, this turkey burger recipe keeps the carbs in check with a lettuce wrap instead of a bun. The side of sweet potato fries bakes while you prep the burgers, so this entire healthy dinner is ready in under 30 minutes.",
                "1 medium sweet potato (12 oz.), peeled\n" +
                        "2 teaspoons canola oil\n" +
                        "⅛ teaspoon salt\n" +
                        "8 ounces lean ground turkey\n" +
                        "¼ cup shredded sharp Cheddar cheese\n" +
                        "2 teaspoons Worcestershire sauce\n" +
                        "½ teaspoon garlic powder\n" +
                        "½ teaspoon ground pepper\n" +
                        "¼ teaspoon salt\n" +
                        "2 teaspoons canola oil\n" +
                        "2 slices sweet onion, such as Vidalia\n" +
                        "2 thick slices tomato\n" +
                        "½ avocado\n" +
                        "4-6 large, soft lettuce leaves, such as green leaf or butterhead",
                "1. To prepare fries: Preheat oven to 425 degrees F.\n" +
                        "2. Slice sweet potato into 1/4-inch-thick matchsticks. Place on a baking sheet, drizzle with oil, and toss to coat. Sprinkle with 1/8 tsp. salt. Bake, flipping halfway through, until tender and crisp-edged, 20 to 25 minutes.\n" +
                        "3. Meanwhile, prepare burgers: Using your hands, mix turkey, Cheddar, Worcestershire, garlic powder, pepper, and salt in a medium bowl. Shape into two 4-inch patties, about 3/4 inch thick.\n" +
                        "4. Brush oil over a medium nonstick or cast-iron skillet; set it over medium heat for 2 minutes. (Alternatively, to grill, see Tip.) Add the patties and cook for 2 minutes. Flip and cook for 2 more minutes. Cover the pan and continue to cook until the patties are lightly browned but still juicy (the juices should run clear, not pink), and an instant-read thermometer inserted in the center registers 165 degrees F, 2 to 4 minutes more.\n" +
                        "5. To assemble the burgers, top each patty with a slice of onion and tomato, and half the avocado slices. Carefully wrap each one in 2 to 3 lettuce leaves, wrapping as tightly as you can. Serve with the sweet potato fries.",
                R.drawable.recipe_turkey));
        recipes.add(new RecipeModel("Forbidden Rice & White Kimchi Steak Salad", 495, 45,
                "This steak salad doesn't feature a typical dressing, but tangy kimchi, savory pan-fried shallots and lemon juice pack it with flavor. Forbidden rice helps add more color and nutrition to this tangy dish.",
                "8 ounces hanger or boneless rib-eye steak, trimmed\n" +
                        "¾ teaspoon salt, divided\n" +
                        "¾ cup plus 2 tablespoons water\n" +
                        "½ cup forbidden rice or brown rice\n" +
                        "5 tablespoons extra-virgin olive oil, divided, plus more to taste\n" +
                        "1 cup thinly sliced shallots\n" +
                        "2 cups sliced shiitake mushrooms\n" +
                        "4 scallions, trimmed and cut into 1/2-inch pieces\n" +
                        "1 large ripe avocado, cubed\n" +
                        "½ cup white kimchi (baek kimchi) or fresh sauerkraut, drained\n" +
                        "4 cups tatsoi, spinach or other tender greens\n" +
                        "1 tablespoon lemon juice\n" +
                        "¼ cup toasted sunflower seeds",
                "1. Sprinkle steak with 1/4 teaspoon salt; set aside for 30 minutes or refrigerate for up to 1 day.\n" +
                        "2. Bring water to a boil in a small saucepan. Add rice, reduce heat to low, cover and cook until the water is just absorbed, about 30 minutes for forbidden rice or 40 minutes for brown rice. Stir in 1 tablespoon oil and 1/4 teaspoon salt and spread the rice on a baking sheet to cool.\n" +
                        "3. Meanwhile, separate shallot slices into rings. Heat 3 tablespoons oil in a medium skillet over medium heat. Add shallots and cook, stirring often, until browned and crispy, 6 to 8 minutes. Using a slotted spoon, transfer the shallots to a plate; set aside.\n" +
                        "4. Add mushrooms and 1/8 teaspoon salt to the pan; cook, stirring occasionally, until browned, about 5 minutes. Add scallions and cook, stirring, until tender, about 1 minute. Transfer to a large bowl.\n" +
                        "5. Add the remaining 1 tablespoon oil to the pan. Cook the steak, turning once and adjusting the heat as necessary, until browned and an instant-read thermometer inserted in the thickest part registers 135 degrees F, 3 to 4 minutes per side. Transfer to a clean cutting board and let rest for 10 minutes. Cut into cubes.\n" +
                        "6. Transfer the rice to the bowl with the vegetables and add avocado, kimchi (or sauerkraut) and the steak; gently mix to combine. Divide among 4 shallow bowls. Add greens to the large bowl and toss with lemon juice and the remaining 1/8 teaspoon salt. Top the rice mixture with the greens, the reserved shallots and sunflower seeds. Drizzle with more oil, if desired.",
                R.drawable.recipe_forbidden_rice));
        recipes.add(new RecipeModel("Spinach Ravioli with Artichokes & Olives", 454, 15,
                "Store-bought spinach ravioli and a handful of basic pantry items are all you need to get a healthy dinner on the table in 15 minutes. Ingredients like oil-packed sun-dried tomatoes, briny Kalamata olives and toasty pine nuts help to build big flavor fast. If you can't find frozen artichokes, swap in a 15-ounce can (just be sure to drain and rinse them well).",
                "2 (8 ounce) packages frozen or refrigerated spinach-and-ricotta ravioli\n" +
                        "½ cup oil-packed sun-dried tomatoes, drained (2 tablespoons oil reserved)\n" +
                        "1 (10 ounce) package frozen quartered artichoke hearts, thawed\n" +
                        "1 (15 ounce) can no-salt-added cannellini beans, rinsed\n" +
                        "¼ cup Kalamata olives, sliced\n" +
                        "3 tablespoons toasted pine nuts\n" +
                        "¼ cup chopped fresh basil",
                "1. Bring a large pot of water to a boil. Cook ravioli according to package directions. Drain and toss with 1 tablespoon reserved oil; set aside.\n" +
                        "2. Heat the remaining 1 tablespoon oil in a large nonstick skillet over medium heat. Add artichokes and beans; sauté until heated through, 2 to 3 minutes.\n" +
                        "3. Fold in the cooked ravioli, sun-dried tomatoes, olives, pine nuts and basil.",
                R.drawable.recipe_spinach_ravioli));
        recipes.add(new RecipeModel("Roasted Pork Tenderloin with Vegetables & Quinoa", 490, 20,
                "For the best flavor in this easy roasted pork tenderloin dish, start marinating the pork the night before or get it going before you head off to work in the morning. Then, when you get home, all that's left to do is roast the pork and vegetables and prepare the quinoa for this easy healthy dinner. This recipe makes extra quinoa—use the leftovers as a base for easy meal-prep lunches, salads, stir-fries later in the week.",
                "¾ cup red-wine vinegar\n" +
                        "5 tablespoons water\n" +
                        "1 ½ tablespoons sugar\n" +
                        "1 tablespoon Dijon mustard\n" +
                        "1 large clove garlic\n" +
                        "2 teaspoons dried basil\n" +
                        "2 teaspoons dried oregano\n" +
                        "½ teaspoon salt\n" +
                        "½ teaspoon ground pepper\n" +
                        "1 ¾ cups extra-virgin olive oil\n" +
                        "1 pound pork tenderloin\n" +
                        "4 medium carrots\n" +
                        "2 medium parsnips\n" +
                        "1 medium broccoli crown\n" +
                        "3 tablespoons extra-virgin olive oil, divided\n" +
                        "2 teaspoons Italian seasoning\n" +
                        "¾ teaspoon salt, divided\n" +
                        "¾ teaspoon ground pepper, divided\n" +
                        "4 tablespoons balsamic glaze\n" +
                        "3 cups low-sodium chicken broth\n" +
                        "1 tablespoon extra-virgin olive oil\n" +
                        "¼ teaspoon salt\n" +
                        "1 ½ cups quinoa",
                "1. To prepare dressing: Combine vinegar, water, sugar, mustard, garlic, basil, oregano, salt and pepper in a blender. Puree until smooth. With the motor running, slowly add oil and puree until creamy. (Measure out 1/4 cup plus 2 tablespoons and transfer the remaining dressing to a large mason jar; refrigerate for up to 1 week.)\n" +
                        "2. To prepare pork & vegetables: Place pork and 1/4 cup dressing in a large sealable bag. Press out air and seal. Massage the dressing all over the pork. Refrigerate for at least 4 hours or up to 24 hours. (Reserve the 2 tablespoons dressing for Step 8.)\n" +
                        "3. Position racks in upper and lower thirds of oven; preheat to 425 degrees F.\n" +
                        "4. Peel carrots and parsnips and cut into 1-inch pieces. Cut broccoli into large florets, about 1 1/2 inches wide. Toss the vegetables with 2 tablespoons oil, Italian seasoning and 1/2 teaspoon each salt and pepper. Spread on a large rimmed baking sheet.\n" +
                        "5. Remove the pork from the marinade and pat dry with paper towels (discard the marinade). Sprinkle the pork with the remaining 1/4 teaspoon each salt and pepper. Heat the remaining 1 tablespoon oil in a large ovenproof skillet over medium-high heat. Add the pork and cook until browned on one side, 3 to 5 minutes. Turn the pork over and transfer the pan to the upper rack. Place the vegetables on the lower rack.\n" +
                        "6. Roast the pork until an instant-read thermometer inserted in the thickest part registers 145 degrees F, about 20 minutes. Roast the vegetables, stirring once or twice, until tender and browned in spots, 20 to 25 minutes.\n" +
                        "7. Meanwhile, prepare quinoa: Combine broth, oil and salt in a large saucepan. Bring to a simmer over high heat. Stir in quinoa and return to a simmer. Reduce heat and simmer until the quinoa has absorbed all the liquid and the grains have burst, 15 to 20 minutes. Remove from heat, cover and let stand for 5 minutes. (Reserve 3 cups for another use.)\n" +
                        "8. Transfer the pork to a clean cutting board and let rest for 5 minutes. Toss the vegetables with the remaining 2 tablespoons dressing. Slice the pork and serve with the roasted vegetables and quinoa, drizzled with balsamic glaze.",
                R.drawable.recipe_roasted_pork));
        recipes.add(new RecipeModel("Sheet-Pan Chicken Fajita Bowls", 343, 20,
                "Skip the tortillas in favor of this warm fajita salad, which features a nutritious medley of chicken with roasted kale, bell peppers and black beans. The chicken, beans and vegetables are all cooked on the same pan, so this healthy dinner is easy to make and the cleanup is easy too.",
                "2 teaspoons chili powder\n" +
                        "2 teaspoons ground cumin\n" +
                        "¾ teaspoon salt, divided\n" +
                        "½ teaspoon garlic powder\n" +
                        "½ teaspoon smoked paprika\n" +
                        "¼ teaspoon ground pepper\n" +
                        "2 tablespoons olive oil, divided\n" +
                        "1 ¼ pounds chicken tenders\n" +
                        "1 medium yellow onion, sliced\n" +
                        "1 medium red bell pepper, sliced\n" +
                        "1 medium green bell pepper, sliced\n" +
                        "4 cups chopped stemmed kale\n" +
                        "1 (15 ounce) can no-salt-added black beans, rinsed\n" +
                        "¼ cup low-fat plain Greek yogurt\n" +
                        "1 tablespoon lime juice\n" +
                        "2 teaspoons water",
                "1. Place a large rimmed baking sheet in the oven; preheat to 425 degrees F.\n" +
                        "2. Combine chili powder, cumin, 1/2 tsp. salt, garlic powder, paprika, and ground pepper in a large bowl. Transfer 1 tsp. of the spice mixture to a medium bowl and set aside. Whisk 1 Tbsp. oil into the remaining spice mixture in the large bowl. Add chicken, onion, and red and green bell peppers; toss to coat.\n" +
                        "3. Remove the pan from the oven; coat with cooking spray. Spread the chicken mixture in an even layer on the pan. Roast for 15 minutes.\n" +
                        "4. Meanwhile, combine kale and black beans with the remaining 1/4 tsp. salt and 1 Tbsp. olive oil in a large bowl; toss to coat.\n" +
                        "5. Remove the pan from the oven. Stir the chicken and vegetables. Spread kale and beans evenly over the top. Roast until the chicken is cooked through and the vegetables are tender, 5 to 7 minutes more.\n" +
                        "6. Meanwhile, add yogurt, lime juice, and water to the reserved spice mixture; stir to combine.\n" +
                        "7. Divide the chicken and vegetable mixture among 4 bowls. Drizzle with the yogurt dressing and serve.",
                R.drawable.recipe_sheet_pan));
        recipes.add(new RecipeModel("Quick Shrimp Puttanesca", 390, 15,
                "Because refrigerated fresh pasta cooks much faster than dried pasta, this Italian-inspired pasta dish will be on the table lickety-split! Puttanesca, traditionally made with tomatoes, olives, capers, anchovies and garlic, gets shrimp for extra protein and artichoke hearts to boost the vegetable servings (and the fiber!). If you can't find frozen artichoke hearts, sub in drained canned artichoke hearts.",
                "8 ounces refrigerated fresh linguine noodles, preferably whole-wheat\n" +
                        "1 tablespoon extra-virgin olive oil\n" +
                        "1 pound peeled and deveined large shrimp\n" +
                        "1 (15 ounce) can no-salt-added tomato sauce\n" +
                        "1 ¼ cups frozen quartered artichoke hearts, thawed (8 ounces)\n" +
                        "¼ cup chopped pitted Kalamata olives\n" +
                        "1 tablespoon capers, rinsed\n" +
                        "¼ teaspoon salt",
                "1. Bring a large pot of water to a boil. Cook linguine according to package instructions. Drain.\n" +
                        "2. Meanwhile, heat oil in a large skillet over high heat. Add shrimp in a single layer and cook, undisturbed, until browned on the bottom, 2 to 3 minutes. Stir in tomato sauce. Add artichoke hearts, olives, capers and salt; cook, stirring often, until the shrimp is cooked through and the artichoke hearts are hot, 2 to 3 minutes longer.\n" +
                        "3. Add the drained noodles to the sauce and stir to combine. Divide among 4 pasta bowls. Serve hot.",
                R.drawable.recipe_putanesca));

        return recipes;
    }
}
