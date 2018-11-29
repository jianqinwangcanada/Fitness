package com.example.web.wbfitness.JavaBean;

import java.util.ArrayList;

public class Workout {

    private String name;
    private String description;
    private ArrayList<String> steps;

    public Workout(String name, String description, ArrayList<String> steps) {
        this.name = name;
        this.description = description;
        this.steps = steps;
    }

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
        this.steps = null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }
}