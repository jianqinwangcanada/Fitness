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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.web.wbfitness.JavaBean.Workout;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


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
    CircleIndicator indicator;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "position";
    private static final String ARG_MUSCLE = "muscle";

    // TODO: Rename and change types of parameters
    private int mPosition;
    private int mMuscle;

    private OnFragmentInteractionListener mListener;

    public WorkoutTips() {
        // Required empty public constructor
    }



    public static WorkoutTips newInstance(int position, int muscle) {
        WorkoutTips fragment = new WorkoutTips();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putInt(ARG_MUSCLE, muscle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
            mMuscle = getArguments().getInt(ARG_MUSCLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_tips, container, false);
        viewPager = view.findViewById(R.id.workoutTipsVP);

        // Spinner
        tipsWorkoutSpinner = view.findViewById(R.id.tipsMuscleGroupsSpinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.muscleGroups, R.layout.spinner_item);

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);

        tipsWorkoutSpinner.setAdapter(spinnerAdapter);

        if(getArguments() != null) {
            tipsWorkoutSpinner.setSelection(mMuscle);
        }

        indicator = view.findViewById(R.id.indicator);


        tipsWorkoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != mMuscle) {
                    String selection = tipsWorkoutSpinner.getSelectedItem().toString();
                    adapter = new CustomAdapter(getChildFragmentManager(), selection);
                    viewPager.setAdapter(adapter);
                    indicator.setViewPager(viewPager);
                    viewPager.setPageTransformer(true, new TipAnimation());
                } else {
                    adapter = new CustomAdapter(getChildFragmentManager(), tipsWorkoutSpinner.getSelectedItem().toString());
                    viewPager.setAdapter(adapter);
                    indicator.setViewPager(viewPager);
                    viewPager.setPageTransformer(true, new TipAnimation());
                    viewPager.setCurrentItem(mPosition);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
        private String[] sources;

        public CustomAdapter(FragmentManager fm, String muscle) {
            super(fm);

            if (muscle.equals(getResources().getString(R.string.chest))) {
                this.workouts = new ArrayList<>();
                this.titles = getResources().getStringArray(R.array.chestWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.chestWorkoutSteps);
                this.sources = getResources().getStringArray(R.array.chestWorkoutSources);
            } else if (muscle.equals(getResources().getString(R.string.arms))) {
                this.titles = getResources().getStringArray(R.array.armsWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.armsWorkoutSteps);
                this.sources = getResources().getStringArray(R.array.armsWorkoutSources);
            } else if (muscle.equals(getResources().getString(R.string.cardio))) {
                this.titles = getResources().getStringArray(R.array.cardioWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.cardioWorkoutSteps);
                this.sources = getResources().getStringArray(R.array.cardioWorkoutSources);
            } else if (muscle.equals(getResources().getString(R.string.core))) {
                this.titles = getResources().getStringArray(R.array.coreWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.coreWorkoutSteps);
                this.sources = getResources().getStringArray(R.array.coreWorkoutSources);
            } else if (muscle.equals(getResources().getString(R.string.legs))) {
                this.titles = getResources().getStringArray(R.array.legsWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.legsWorkoutSteps);
                this.sources = getResources().getStringArray(R.array.legsWorkoutSources);
            } else if (muscle.equals(getResources().getString(R.string.back))) {
                this.titles = getResources().getStringArray(R.array.backWorkoutTitles);
                this.steps = getResources().getStringArray(R.array.backWorkoutSteps);
                this.sources = getResources().getStringArray(R.array.backWorkoutSources);
            }


        }



        @Override
        public Fragment getItem(int i) {
            return TipsFragment.newInstance(this.titles[i], this.steps[i], this.sources[i]);
        }

        @Override
        public int getCount() {
            return this.titles.length;
        }
    }

    public class TipAnimation implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float v) {
            int pageWidth = view.getWidth();

            if (v < -1) {
                view.setAlpha(0f);
            } else if (v <= 0) {
                view.setAlpha(1 - v);


            } else if (v <= 1) {
                view.setAlpha(1 - v);

                view.setTranslationX(pageWidth * -v);

                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(v));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else {
                view.setAlpha(0f);
            }
        }
    }


}
