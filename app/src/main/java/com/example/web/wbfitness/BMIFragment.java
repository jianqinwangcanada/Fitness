package com.example.web.wbfitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BMIFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BMIFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 * In this class, default measurement for weight is:pounds, default height is inches
 *
 */
public class BMIFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RadioGroup bmiheightGroup;
    RadioGroup bmiweightGroup;
    RadioButton bmiinches;
    RadioButton bmicentimeters;
    RadioButton bmipounds;
    RadioButton bmikilograms;
    EditText bmiheight;
    EditText bmiweight;
    EditText bmiOutput;
    EditText bmiOutputResult;
    TextView bmiheightTV;
    TextView bmiweightTV;
    //0 stands for centimeter, 1 stands for inches
    int bmiHeightFlag=1;
    //0 stands for kiligrams, 1 stands for ponds
    int bmiWeightFlag=1;
    //Define progress varialbes

    private ProgressBar progressBar;
    //define button to button
    Button calculateButton;

   //declare weight value,and height value
   private float bmiWeightValue;
   private float bmiHeightValue;
   //delcare a variable to store the bmi result and initialate it as 0;
   private float BMIvalue=0;

   //Declare a SharedPreference to grab setting and setting value
    SharedPreferences bmiPreValueSetting;
    //declare a boolean to grab the setting choice for setting activity
    boolean isSaved=true;

    //Declare WeightMeasurement and HeightMeasurement String , which will grab setting page measurement

    String WeightMeasurement;
    String HeightMeasurement;
 //Define float format
 DecimalFormat decimalFormat;

    public BMIFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BMIFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BMIFragment newInstance(String param1, String param2) {
        BMIFragment fragment = new BMIFragment();
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
       //Initializing the SharedPreference, this will used for grad all setting and stored information
        bmiPreValueSetting=PreferenceManager.getDefaultSharedPreferences(getContext());
        //Grab measurement from setting page
        WeightMeasurement=bmiPreValueSetting.getString(getResources().getString(R.string.weightMeasurement),"");
        HeightMeasurement=bmiPreValueSetting.getString(getResources().getString(R.string.heightMeasurement),"");
         //Grab value from setting page
        String weightStr=bmiPreValueSetting.getString(getResources().getString(R.string.weight),"");


        bmiWeightValue=Float.parseFloat(weightStr);

        String heightStr=bmiPreValueSetting.getString(getResources().getString(R.string.height),"");

        System.out.println("myweight :"+weightStr+" myheight"+heightStr);

        bmiHeightValue=Float.parseFloat(heightStr);

        //Define a float formater
        decimalFormat=new DecimalFormat("#.##");

         //because our default is pounds, change it to ponds, if is kilogram, change it to pounds
        if(WeightMeasurement.trim().equals(getResources().getString(R.string.kilograms))){
          //convert to ponds
           bmiWeightValue=bmiWeightValue*2.20f;
          // bmiWeightValue=Float.parseFloat(decimalFormat.format(bmiWeightValue));
        }
        if(HeightMeasurement.trim().equals(getResources().getString(R.string.centimeters).trim())){
          //convert centimeter to inches because our defalut mesurement is inches
          bmiHeightValue=bmiHeightValue*0.39f;
          //bmiHeightValue=Float.parseFloat(decimalFormat.format(bmiHeightValue));
        }


        System.out.println("TestPre :"+bmiHeightValue+" weight:"+bmiWeightValue);

        //grab the setting information from setting menu which created in setting activity, default is true;

        isSaved=bmiPreValueSetting.getBoolean("save_bmi",true);

        //bmiWeightValue=Float.parseFloat(bmiPreValueSetting.getString(getResources().getString(R.string.weightMeasurement),""));
        bmiWeightFlag=1;
        bmiHeightFlag=1;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_bmi, container, false);

        bmiheightGroup = view.findViewById(R.id.bmiheightGroup);
        bmiinches = view.findViewById(R.id.bmiinchesRadio);
        bmicentimeters = view.findViewById(R.id.bmicentimetersRadio);

        bmiweightGroup = view.findViewById(R.id.bmiweightGroup);
        bmikilograms = view.findViewById(R.id.bmikgRadio);
        bmipounds = view.findViewById(R.id.bmipoundsRadio);


        bmiheight = view.findViewById(R.id.bmiheightInput);

        bmiheight.setText(bmiHeightValue+"");



        bmiweight = view.findViewById(R.id.bmiweightInput);

           bmiweight.setText(bmiWeightValue+"");



        bmiweightTV = view.findViewById(R.id.bmiweightTV);
        bmiheightTV = view.findViewById(R.id.bmiheightTV);


        bmiOutput=view.findViewById(R.id.bmiOutput);
        bmiOutput.setText("0");
        bmiOutput.setFocusable(false);

        bmiOutputResult=view.findViewById(R.id.bmiOutputResult);
        bmiOutputResult.setText("Please Calculate");
        bmiOutputResult.setFocusable(false);





        // Change the placeholder values and titles if the measurement system is changed
        bmiweightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //decide whether bmi_seting true or false value
                isSaved=bmiPreValueSetting.getBoolean("save_bmi",true);

                if(bmikilograms.isChecked()) {


                      //convert to kilograms and show
                       bmiweight.setText(Float.parseFloat(decimalFormat.format(bmiWeightValue*0.45f))+"");

                    bmiweightTV.setText(getResources().getText(R.string.weightKG));
                    //set weight flag to 0
                    bmiWeightFlag=0;

                } else if (bmipounds.isChecked()) {

                    bmiweight.setText(bmiWeightValue+"");


                    bmiweightTV.setText(getResources().getText(R.string.weightPounds));
                    //set weight flag to 1;
                    bmiWeightFlag=1;
                }
            }
        });

        // Change the placeholder values and titles if the measurement system is changed
        bmiheightGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //decide whether bmi_seting true or false value
                isSaved=bmiPreValueSetting.getBoolean("save_bmi",true);

                if(bmiinches.isChecked()) {

                        bmiheight.setText(bmiHeightValue+"");

                    bmiheightTV.setText(getResources().getText(R.string.heightInches));
                    bmiHeightFlag=1;

                } else if (bmicentimeters.isChecked()) {

                    //set the default value

                        bmiheight.setText(Float.parseFloat(decimalFormat.format(bmiHeightValue*2.54f))+"");

                    bmiheightTV.setText(getResources().getText(R.string.heightCentimeters));
                    //set height flag to 1
                    bmiHeightFlag=0;
                }
            }
        });

        //Declare two components

        progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
       calculateButton=(Button)view.findViewById(R.id.bmiCalButton);

        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
       //Define the calculator button click action
       calculateButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           float bmiReturnValue=calculateBMI();
           int bmiProgressValue=(int)bmiReturnValue;

           setProgressValue(bmiProgressValue);

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


    //Define a method to run progress bar
    private void setProgressValue(final int pStatus){

        // set the progress
        progressBar.setProgress(0);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            int pvalue=0;
            @Override
            public void run() {
                while(pvalue<=pStatus) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setProgress(pvalue);
                    pvalue++;
                }
            }//run
        });
        thread.start();
    }

    //Define a method to calculate BMI
    private float calculateBMI(){
        //grab the setting condition
        isSaved=bmiPreValueSetting.getBoolean("save_bmi",true);

        float computeWeight=Float.parseFloat(bmiweight.getText().toString().trim());
        float computeHeight=Float.parseFloat(bmiheight.getText().toString().trim());

        if(bmiHeightFlag==1){
            //save the new height value,using inch;
            bmiHeightValue=computeHeight;
            //convert inch to centimeter
            computeHeight=computeHeight*2.54f;
        }else{
            //if it is centimeter,convert it to inches
            bmiHeightValue=computeHeight*0.39f;
            bmiHeightValue=Float.parseFloat(decimalFormat.format(bmiHeightValue));

        }
        if(bmiWeightFlag==1)
        {
            //save the weight value
            bmiWeightValue=computeWeight;
            //convert ponds to Kilogram
            computeWeight=computeWeight*0.45f;
        }else{
            //if it is kilogram ,convert it to ponds
            bmiWeightValue=computeWeight*2.20f;
            bmiWeightValue=Float.parseFloat(decimalFormat.format(bmiWeightValue));

        }



        System.out.println("BMIValue Height:"+computeHeight+" weight:"+computeWeight);

       //because here computeheight is centemeter, we use meter to compute,so divide 10000
      BMIvalue=computeWeight/(computeHeight*computeHeight/10000);



        BMIvalue=Float.parseFloat(decimalFormat.format(BMIvalue));
        bmiOutput.setText(BMIvalue+"");

        //set the BMI evaluation result
        if(BMIvalue>=30){
           bmiOutputResult.setText(getResources().getText(R.string.value_obesity));
        }else if(BMIvalue>=25){
            bmiOutputResult.setText(getResources().getText(R.string.value_overweight));

        }else if(BMIvalue>=18.5){
            bmiOutputResult.setText(getResources().getText(R.string.value_nomal));

        }else if(BMIvalue>0){
            bmiOutputResult.setText(getResources().getText(R.string.value_underweight));

        }else{bmiOutputResult.setText("Input invalid");}



     //Save the new  user input into SharedPreference according user setting (bmi_save )

        bmiPreValueSetting.edit().putString(getResources().getString(R.string.weightMeasurement),
                "Pounds").apply();

        bmiPreValueSetting.edit().putString(getResources().getString(R.string.heightMeasurement),
               "Inches").apply();
        if(isSaved) {
            //if the user choose save bmi information in setting menu
            // Height
            bmiPreValueSetting.edit().putString(getResources().getString(R.string.height), bmiHeightValue + "").apply();

            // Weight
            bmiPreValueSetting.edit().putString(getResources().getString(R.string.weight), bmiWeightValue + "").apply();
        }else{
            //if the user don't want to save the the bmi information

            bmiPreValueSetting.edit().putString(getResources().getString(R.string.height), "0").apply();

            // Weight
            bmiPreValueSetting.edit().putString(getResources().getString(R.string.weight), "0").apply();
        }




        return BMIvalue;
    }

}
