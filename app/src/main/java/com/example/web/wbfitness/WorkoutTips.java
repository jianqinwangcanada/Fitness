package com.example.web.wbfitness;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
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
 * {@link WorkoutTips.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WorkoutTips#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutTips extends Fragment {

    private CustomAdapter adapter;
    Spinner tipsWorkoutSpinner;
    ViewPager viewPager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WorkoutTips() {
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
    public static WorkoutTips newInstance(String param1, String param2) {
        WorkoutTips fragment = new WorkoutTips();
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
        View view = inflater.inflate(R.layout.fragment_workout_tips, container, false);
        viewPager = view.findViewById(R.id.workoutTipsVP);

        // Spinner events
        tipsWorkoutSpinner = view.findViewById(R.id.tipsMuscleGroupsSpinner);
        tipsWorkoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = tipsWorkoutSpinner.getSelectedItem().toString();
                adapter = new CustomAdapter(getChildFragmentManager(), selection);
                viewPager.setAdapter(adapter);
                System.out.println(selection);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                adapter = new CustomAdapter(getChildFragmentManager(), "Chest");
//                viewPager.setAdapter(null);
                viewPager.setAdapter(adapter);
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

    public class CustomAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Workout> workouts;
        private String[] titles;
        private String[] steps;

        public CustomAdapter(FragmentManager fm, String muscle) {
            super(fm);

            if(muscle.equals(getResources().getString(R.string.chest))){
                this.workouts = new ArrayList<>();
                this.titles = getResources().getStringArray(R.array.chestWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.chestWorkoutSteps);
            } else if(muscle.equals(getResources().getString(R.string.arms))){
                this.titles = getResources().getStringArray(R.array.armsWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.armsWorkoutSteps);
            } else if(muscle.equals(getResources().getString(R.string.cardio))){
                this.titles = getResources().getStringArray(R.array.cardioWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.cardioWorkoutSteps);
            } else if(muscle.equals(getResources().getString(R.string.core))){
                this.titles = getResources().getStringArray(R.array.coreWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.coreWorkoutSteps);
            } else if(muscle.equals(getResources().getString(R.string.legs))){
                this.titles = getResources().getStringArray(R.array.legsWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.legsWorkoutSteps);
            } else if(muscle.equals(getResources().getString(R.string.back))){
                this.titles = getResources().getStringArray(R.array.backWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.backWorkoutSteps);
            }


        }


//        ArrayList<Workout> chest = new ArrayList<>();
//        String[] chestTitle = getResources().getStringArray(R.array.chestWorkoutTitles);
//        String[] chestSteps = getResources().getStringArray(R.array.chestWorkoutSteps);


        @Override
        public Fragment getItem(int i) {
                return TipsFragment.newInstance(this.titles[i], this.steps[i]);
        }

        @Override
        public int getCount() {
            return this.titles.length;
        }
    }



}
