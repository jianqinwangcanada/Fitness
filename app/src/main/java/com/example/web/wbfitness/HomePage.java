package com.example.web.wbfitness;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Declare the FragmentManager
    FragmentManager fm;

    // Declare the buttons that are on the page
    ImageButton bmiButton;
    ImageButton planButton;
    ImageButton contactButton;
    ImageButton tipsButton;

    View view;


    private OnFragmentInteractionListener mListener;

    public HomePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePage.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePage newInstance(String param1, String param2) {
        HomePage fragment = new HomePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_page, container, false);

        fm = getActivity().getSupportFragmentManager();

        // Grab the buttons from the layout

        bmiButton = view.findViewById(R.id.bmiButton);
        planButton = view.findViewById(R.id.workoutPlanButton);
        contactButton = view.findViewById(R.id.contactButton);
        tipsButton = view.findViewById(R.id.workoutTipsButton);

        // Launch the appropriate page when a button is selected

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.anim.shrinkfade_out, R.anim.shrinkfade_in, R.anim.shrinkfade_back_out, R.anim.shrinkfade_back_in);
                transaction.replace(R.id.content, new ContactFragment(), "Contact");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        planButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.anim.shrinkfade_out, R.anim.shrinkfade_in, R.anim.shrinkfade_back_out, R.anim.shrinkfade_back_in);
                transaction.replace(R.id.content, new WorkoutPlan(), "Workout Plan");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.anim.shrinkfade_out, R.anim.shrinkfade_in, R.anim.shrinkfade_back_out, R.anim.shrinkfade_back_in);
                transaction.replace(R.id.content, new BMIFragment(),"BMI");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        tipsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setCustomAnimations(R.anim.shrinkfade_out, R.anim.shrinkfade_in, R.anim.shrinkfade_back_out, R.anim.shrinkfade_back_in);
                transaction.replace(R.id.content, new WorkoutTips(),"Workout Tips");
                transaction.addToBackStack(null);
                transaction.commit();
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
        ImageView iv = view.findViewById(R.id.homePageLogo);
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
