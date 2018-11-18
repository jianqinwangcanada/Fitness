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

        ArrayList<Workout> workouts = new ArrayList<>();
        workouts.add(new Workout("Push-Up", "Move the earth."));
        workouts.add(new Workout("Push-Up", "Move the earth."));
        workouts.add(new Workout("Push-Up", "Move the earth."));
        workouts.add(new Workout("Push-Up", "Move the earth."));
        workouts.add(new Workout("Push-Up", "Move the earth."));
        workouts.add(new Workout("Push-Up", "Move the earth."));
        workouts.add(new Workout("Push-Up", "Move the earth."));


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        workoutRV.setLayoutManager(manager);




        WorkoutAdapter adapter = new WorkoutAdapter(workouts);
        workoutRV.setAdapter(adapter);

        // Workout item event handler
        workoutRV = view.findViewById(R.id.workoutPlanRV);



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
