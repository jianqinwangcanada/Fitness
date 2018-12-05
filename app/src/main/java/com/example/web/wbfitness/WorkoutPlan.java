package com.example.web.wbfitness;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.web.wbfitness.JavaBean.Workout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WorkoutPlan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WorkoutPlan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutPlan extends Fragment {

    RecyclerView workoutRV;
    Spinner workoutSpinner;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WorkoutPlan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutPlan.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutPlan newInstance(String param1, String param2) {
        WorkoutPlan fragment = new WorkoutPlan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_workout_plan, container, false);

        workoutRV = view.findViewById(R.id.workoutPlanRV);

        final ArrayList<Workout> chest = new ArrayList<>();
        String[] chestTitle = getResources().getStringArray(R.array.chestWorkoutTitles);
        String[] chestSets = getResources().getStringArray(R.array.chestWorkoutSets);

        for(int i=0; i < chestTitle.length; i++) {
            chest.add(new Workout(chestTitle[i], chestSets[i]));
        }

        final ArrayList<Workout> legs = new ArrayList<>();
        String[] legsTitle = getResources().getStringArray(R.array.legsWorkoutTitles);
        String[] legsSets = getResources().getStringArray(R.array.legsWorkoutSets);

        for(int i=0; i < legsTitle.length; i++) {
            legs.add(new Workout(legsTitle[i], legsSets[i]));
        }

        final ArrayList<Workout> core = new ArrayList<>();
        String[] coreTitle = getResources().getStringArray(R.array.coreWorkoutTitles);
        String[] coreSets = getResources().getStringArray(R.array.coreWorkoutSets);

        for(int i=0; i < coreTitle.length; i++) {
            core.add(new Workout(coreTitle[i], coreSets[i]));
        }

        final ArrayList<Workout> cardio = new ArrayList<>();
        String[] cardioTitle = getResources().getStringArray(R.array.cardioWorkoutTitles);
        String[] cardioSets = getResources().getStringArray(R.array.cardioWorkoutSets);

        for(int i=0; i < cardioTitle.length; i++) {
            cardio.add(new Workout(cardioTitle[i], cardioSets[i]));
        }

        final ArrayList<Workout> back = new ArrayList<>();
        String[] backTitle = getResources().getStringArray(R.array.backWorkoutTitles);
        String[] backSets = getResources().getStringArray(R.array.backWorkoutSets);

        for(int i=0; i < backTitle.length; i++) {
            back.add(new Workout(backTitle[i], backSets[i]));
        }

        final ArrayList<Workout> arms = new ArrayList<>();
        String[] armsTitle = getResources().getStringArray(R.array.armsWorkoutTitles);
        String[] armsSets = getResources().getStringArray(R.array.armsWorkoutSets);

        for(int i=0; i < armsTitle.length; i++) {
            arms.add(new Workout(armsTitle[i], armsSets[i]));
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        workoutRV.setLayoutManager(manager);

        // Workout item event handler
        workoutRV = view.findViewById(R.id.workoutPlanRV);


        // Spinner events
        workoutSpinner = view.findViewById(R.id.planMuscleGroupsSpinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.muscleGroups, R.layout.spinner_item);

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);

        workoutSpinner.setAdapter(spinnerAdapter);





        workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = workoutSpinner.getSelectedItem().toString();

                if(selection.equals(getResources().getString(R.string.chest))){
                    workoutRV.setAdapter(new WorkoutAdapter(chest, position));
                } else if(selection.equals(getResources().getString(R.string.arms))){
                    workoutRV.setAdapter(new WorkoutAdapter(arms, position));
                } else if(selection.equals(getResources().getString(R.string.cardio))){
                    workoutRV.setAdapter(new WorkoutAdapter(cardio, position));
                } else if(selection.equals(getResources().getString(R.string.core))){
                    workoutRV.setAdapter(new WorkoutAdapter(core, position));
                } else if(selection.equals(getResources().getString(R.string.legs))){
                    workoutRV.setAdapter(new WorkoutAdapter(legs, position));
                } else if(selection.equals(getResources().getString(R.string.back))){
                    workoutRV.setAdapter(new WorkoutAdapter(back, position));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                workoutRV.setAdapter(chestAdapter);
            }
        });




        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImageView iv = view.findViewById(R.id.planLogoIV);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            iv.setVisibility(View.GONE);
        } else {
            iv.setVisibility(View.VISIBLE);
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
