package com.example.web.wbfitness;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.wbfitness.JavaBean.Workout;
import com.example.web.wbfitness.R;

import java.util.ArrayList;

public class WorkoutAdapter extends Adapter {
    private ArrayList<Workout> workouts;



    public WorkoutAdapter(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_row, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Workout workout = workouts.get(position);
        ((CustomViewHolder) holder).name.setText(workout.getName());
        ((CustomViewHolder) holder).description.setText(workout.getDescription());
    }

    @Override
    public int getItemCount() {
        if(workouts != null) {
            return workouts.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView description;

        public CustomViewHolder(View view){
            super(view);
            this.name = view.findViewById(R.id.workoutTitle);
            this.description = view.findViewById(R.id.workoutDesc);
        }
    }


}