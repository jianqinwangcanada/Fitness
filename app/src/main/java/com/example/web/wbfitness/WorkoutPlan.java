package com.example.web.wbfitness;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        View view = inflater.inflate(R.layout.fragment_workout_plan, container, false);

        workoutRV = view.findViewById(R.id.workoutPlanRV);

        ArrayList<Workout> chest = new ArrayList<>();
        chest.add(new Workout("Push-Up", "Move the earth."));
        chest.add(new Workout("Push-Up", "Move the earth."));
        chest.add(new Workout("Push-Up", "Move the earth."));
        chest.add(new Workout("Push-Up", "Move the earth."));
        chest.add(new Workout("Push-Up", "Move the earth."));
        chest.add(new Workout("Push-Up", "Move the earth."));
        chest.add(new Workout("Push-Up", "Move the earth."));

        ArrayList<Workout> legs = new ArrayList<>();
        legs.add(new Workout("Squat", "Move the earth."));
        legs.add(new Workout("Squat", "Move the earth."));
        legs.add(new Workout("Squat", "Move the earth."));
        legs.add(new Workout("Squat", "Move the earth."));
        legs.add(new Workout("Squat", "Move the earth."));
        legs.add(new Workout("Squat", "Move the earth."));
        legs.add(new Workout("Squat", "Move the earth."));

        ArrayList<Workout> core = new ArrayList<>();
        core.add(new Workout("Plank", "Move the earth."));
        core.add(new Workout("Plank", "Move the earth."));
        core.add(new Workout("Plank", "Move the earth."));

        ArrayList<Workout> cardio = new ArrayList<>();
        cardio.add(new Workout("Bike", "Move the earth."));
        cardio.add(new Workout("Bike", "Move the earth."));
        cardio.add(new Workout("Bike", "Move the earth."));

        ArrayList<Workout> back = new ArrayList<>();
        back.add(new Workout("Lawnmower", "Move the earth."));
        back.add(new Workout("Lawnmower", "Move the earth."));
        back.add(new Workout("Lawnmower", "Move the earth."));

        ArrayList<Workout> arms = new ArrayList<>();
        arms.add(new Workout("Curl", "Move the earth."));
        arms.add(new Workout("Curl", "Move the earth."));
        arms.add(new Workout("Curl", "Move the earth."));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        workoutRV.setLayoutManager(manager);




        final WorkoutAdapter chestAdapter = new WorkoutAdapter(chest);
        final WorkoutAdapter armsAdapter = new WorkoutAdapter(arms);
        final WorkoutAdapter backAdapter = new WorkoutAdapter(back);
        final WorkoutAdapter coreAdapter = new WorkoutAdapter(core);
        final WorkoutAdapter legsAdapter = new WorkoutAdapter(legs);
        final WorkoutAdapter cardioAdapter = new WorkoutAdapter(cardio);


        // Workout item event handler
        workoutRV = view.findViewById(R.id.workoutPlanRV);


        // Spinner events
        workoutSpinner = view.findViewById(R.id.muscleGroupsSpinner);
        workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = workoutSpinner.getSelectedItem().toString();

                if(selection.equals(getResources().getString(R.string.chest))){
                    workoutRV.setAdapter(chestAdapter);
                } else if(selection.equals(getResources().getString(R.string.arms))){
                    workoutRV.setAdapter(armsAdapter);
                } else if(selection.equals(getResources().getString(R.string.cardio))){
                    workoutRV.setAdapter(cardioAdapter);
                } else if(selection.equals(getResources().getString(R.string.core))){
                    workoutRV.setAdapter(coreAdapter);
                } else if(selection.equals(getResources().getString(R.string.legs))){
                    workoutRV.setAdapter(legsAdapter);
                } else if(selection.equals(getResources().getString(R.string.back))){
                    workoutRV.setAdapter(backAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                workoutRV.setAdapter(chestAdapter);
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
