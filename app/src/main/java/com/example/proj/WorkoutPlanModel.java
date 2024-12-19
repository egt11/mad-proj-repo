package com.example.proj;

public class WorkoutPlanModel {
    private final String name;
    private final int minutes;
    private final String description;
    private final String exercises;
    private final int image;

    public WorkoutPlanModel(String name, int minutes, String description, String exercises, int image) {
        this.name = name;
        this.minutes = minutes;
        this.description = description;
        this.exercises = exercises;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getDescription() {
        return description;
    }

    public String getExercises() {
        return exercises;
    }

    public int getImage() {
        return image;
    }

}
