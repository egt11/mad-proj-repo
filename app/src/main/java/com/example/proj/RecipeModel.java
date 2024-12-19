package com.example.proj;

public class RecipeModel {
    private final String name;
    private final int calories;
    private final int time;
    private final String overview;
    private final String ingredients;
    private final String directions;
    private final int image;

    public RecipeModel(String name, int calories, int time, String overview, String ingredients, String directions, int image) {
        this.name = name;
        this.calories = calories;
        this.time = time;
        this.overview = overview;
        this.ingredients = ingredients;
        this.directions = directions;
        this.image = image;
    }

    public String getName() { return name; }
    public int getCalories() { return calories; }
    public int getTime() { return time; }
    public String getOverview() { return overview; }
    public String getIngredients() { return ingredients; }
    public String getDirections() { return directions; }
    public int getImage() { return image; }
}
