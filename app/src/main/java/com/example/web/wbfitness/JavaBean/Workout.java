package com.example.web.wbfitness.JavaBean;

import java.util.ArrayList;

public class Workout {

    private String name;
    private String description;
    private String plan;
    private Boolean complete;


    public Workout(String name, String plan) {
        this.name = name;
        this.description = null;
        this.plan = plan;
        this.complete = false;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
