package com.example.web.wbfitness;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.web.wbfitness.JavaBean.Workout;

import java.util.ArrayList;

public class WorkoutAdapter extends Adapter {
    private ArrayList<Workout> workouts;
    private int muscle;

    public WorkoutAdapter(ArrayList<Workout> workouts, int muscle) {
        this.workouts = workouts;
        this.muscle = muscle;
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
        ((CustomViewHolder) holder).plan.setText(workout.getPlan());


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
        protected TextView plan;
        protected Spinner spinner;

        public CustomViewHolder(View view){
            super(view);
            this.name = view.findViewById(R.id.workoutTitle);
            this.plan = view.findViewById(R.id.workoutSets);
            this.spinner = view.findViewById(R.id.planMuscleGroupsSpinner);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    activity.getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.shrinkfade_out, R.anim.shrinkfade_in, R.anim.shrinkfade_back_out, R.anim.shrinkfade_back_in)
                            .replace(R.id.content, WorkoutTips.newInstance(getAdapterPosition(), muscle))
                             .addToBackStack(null)
                             .commit();

                }
            });
        }

    }


    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }
}