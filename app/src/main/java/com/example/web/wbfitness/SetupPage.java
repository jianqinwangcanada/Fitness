package com.example.web.wbfitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SetupPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SetupPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetupPage extends Fragment {

    // FragmentManager
    FragmentManager fm;

    // Declare all of the elements in the layout

    RadioGroup languageGroup;
    RadioButton english;
    RadioButton mandarin;
    EditText name;
    RadioGroup gender;
    RadioButton male;
    RadioButton female;
    RadioGroup measurement;
    RadioButton metric;
    RadioButton imperial;
    EditText height;
    EditText weight;
    Button submit;
    TextView heightTV;
    TextView weightTV;

    // Declare the SharedPreferences file
    SharedPreferences preferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SetupPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetupPage.
     */
    // TODO: Rename and change types and number of parameters
    public static SetupPage newInstance(String param1, String param2) {
        SetupPage fragment = new SetupPage();
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
        View view = inflater.inflate(R.layout.fragment_setup_page, container, false);

        // Get the FragmentManager
        fm = getActivity().getSupportFragmentManager();

        // Pair all of the elements to their values
        languageGroup = view.findViewById(R.id.languageGroup);
        english = view.findViewById(R.id.englishRadio);
        mandarin = view.findViewById(R.id.mandarinRadio);
        name = view.findViewById(R.id.nameInput);
        male = view.findViewById(R.id.maleRadio);
        female = view.findViewById(R.id.femaleRadio);
        metric = view.findViewById(R.id.metricRadio);
        imperial = view.findViewById(R.id.imperialRadio);
        measurement = view.findViewById(R.id.measurementGroup);
        height = view.findViewById(R.id.heightInput);
        weight = view.findViewById(R.id.weightInput);
        weightTV = view.findViewById(R.id.weightTV);
        heightTV = view.findViewById(R.id.heightTV);
        submit = view.findViewById(R.id.setupSubmitButton);

        // Determine if the device is already set to English or Mandarin and check the right box
        if(getResources().getConfiguration().locale.toString().contains("en")) {
            english.setChecked(true);
        } else if(getResources().getConfiguration().locale.toString().contains("zh_CN")) {
            mandarin.setChecked(true);
        }

        // Change the locale if the language radiogropu is changed
        languageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(english.isChecked()) {
                    if(getResources().getConfiguration().locale != Locale.ENGLISH || getResources().getConfiguration().locale != Locale.US) {
                        setLocale(Locale.ENGLISH);
                    }
                } else if (mandarin.isChecked()) {
                    if(getResources().getConfiguration().locale != Locale.SIMPLIFIED_CHINESE) {
                        setLocale(Locale.SIMPLIFIED_CHINESE);
                    }
                }
            }
        });

        // Change the placeholder values and titles if the measurement system is changed
        measurement.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(metric.isChecked()) {
                    height.setText(getResources().getText(R.string.heightPlaceholderCentimeters));
                    weight.setText(getResources().getText(R.string.weightPlaceholderKilograms));
                    heightTV.setText(getResources().getText(R.string.heightCentimeters));
                    weightTV.setText(getResources().getText(R.string.weightKG));

                } else if (imperial.isChecked()) {
                    height.setText(getResources().getText(R.string.heightPlaceholderInches));
                    weight.setText(getResources().getText(R.string.weightPlaceholderPounds));
                    heightTV.setText(getResources().getText(R.string.heightInches));
                    weightTV.setText(getResources().getText(R.string.weightPounds));
                }
            }
        });


        // Get the SharedPreferences file
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        // Submit button action handler
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Write the values to the preferences file

                // Language
                if(english.isChecked()) {
                    preferences.edit().putString(getResources().getString(R.string.language), english.getText().toString()).apply();
                } else if (mandarin.isChecked()) {
                    preferences.edit().putString(getResources().getString(R.string.language), mandarin.getText().toString()).apply();
                }

                // Name
                preferences.edit().putString(getResources().getString(R.string.name), name.getText().toString()).apply();

                // Gender
                if(male.isChecked()) {
                    preferences.edit().putString(getResources().getString(R.string.gender), male.getText().toString()).apply();
                } else if (female.isChecked()) {
                    preferences.edit().putString(getResources().getString(R.string.gender), female.getText().toString()).apply();
                }

                // Measurement System
                if(metric.isChecked()) {
                    preferences.edit().putString(getResources().getString(R.string.measurement), metric.getText().toString()).apply();
                } else if (imperial.isChecked()) {
                    preferences.edit().putString(getResources().getString(R.string.measurement), imperial.getText().toString()).apply();
                }

                // Height
                preferences.edit().putString(getResources().getString(R.string.height), height.getText().toString()).apply();

                // Weight
                preferences.edit().putString(getResources().getString(R.string.weight), weight.getText().toString()).apply();


                // Launch the HomePage

                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content, new HomePage(), "Home Page");
                transaction.addToBackStack(null);
                transaction.commit();
                preferences.edit().putBoolean("initialize", false).apply();
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

    // Change the language
    public void setLocale(Locale myLocal) {
        //Declare the resource
        Resources res = getResources();
        //Declare DisplayMetrics using resources
        DisplayMetrics dm = res.getDisplayMetrics();
        //Declare the conf
        Configuration conf = res.getConfiguration();
        //set the Configuration with myLocal
        conf.locale = myLocal;
        //Update configuration with resource
        res.updateConfiguration(conf, dm);
        //Declare the intent

        Intent refresh = new Intent(getContext(), MainActivity.class);
        //Refresh the mainActivity
        startActivity(refresh);

    }

}
