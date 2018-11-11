package com.example.web.wbfitness;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.wbfitness.JavaBean.ContactItem;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //Delcare a Listview
    ListView contactListView;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        View view=inflater.inflate(R.layout.fragment_contact, container, false);

        contactListView=(ListView)view.findViewById(R.id.contact_listView);
        //Declare a arraylist to store the list of the items
       final ArrayList<ContactItem>  contactItems=new ArrayList<>();
       contactItems.add(new ContactItem(R.string.contact_tel,R.drawable.call));
       contactItems.add(new ContactItem(R.string.contact_SMS,R.drawable.sms));
       contactItems.add(new ContactItem(R.string.contact_email,R.drawable.email));
       contactItems.add(new ContactItem(R.string.contact_location,R.drawable.map));
       contactItems.add(new ContactItem(R.string.contact_website,R.drawable.web));
        //Declare a CustomerAdapter adapter
        CustomerAdapter adapter=new CustomerAdapter(getContext(),contactItems);
       //Bind the adapter to contactView
        contactListView.setAdapter(adapter);

       //Add Listener to contactListView
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactItem currentItem=(ContactItem)contactListView.getItemAtPosition(position);
                switch (position){
                    //Telephone Intent
                    case 0:{
                        //declare intent
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        //set intent with telephone number
                        intent.setData(Uri.parse("tel:5199974000"));
                        //Decide whether the user's phone has related software to run this functionality
                        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),"You do not have the correct software",Toast.LENGTH_SHORT).show();
                        }
                       break;
                    }
                    //SMS
                    case 1:{
                        //Declare intent and set data with the telephone number
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:5199974000"));
                        //Decide whether the user's phone has related software to run this functionality
                        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),
                                    "You do not have the correct software",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    //send email
                    case 2:{
                        //declare a intent bind the email address
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:jianqin.wang01@stclairconnect.ca"));
                        //intent.putExtra(Intent.EXTRA_EMAIL, "jianqin.wang01@stclairconnect.ca");
                        // //Decide whether the user's phone has related software to run this functionality
                        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(getContext(),
                                    "You do not have the FoxMail software",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    //view map
                    case 3:{
                        //Declare a Intent and set data with the location data
                        //"geo:0,0?q=42.2470072,-83.0149074(Tim Hortons)"
                        Uri geoLocation = Uri.parse("geo:0,0?q=42.2615179,-83.0423989(W and B Fitness)");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(geoLocation);
                        // //Decide whether the user's phone has related software to run this functionality
                        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),"You do not have the correct software",Toast.LENGTH_SHORT).show();
                        }
                        break;

                    }
                    //view website
                    case 4:{
                        //Declare the intent and set data with website
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.stclaircollege.ca"));
                        // //Decide whether the user's phone has related software to run this functionality
                        if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),
                                    "You do not have the correct software",
                                    Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                    default:{Toast.makeText(getContext(),"Please choice one item to contact us!",Toast.LENGTH_LONG).show();}
                }
            }
        });



        return view;
    }
    //Declare a cumstomerAdapter class
    public class CustomerAdapter extends ArrayAdapter<ContactItem> {

        public CustomerAdapter(@NonNull Context context, ArrayList<ContactItem> items) {
            super(context,0, items);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.contact_item,parent,false);
            }
            TextView itemName=convertView.findViewById(R.id.contact_item_text);
            ContactItem item=getItem(position);
            itemName.setText(item.getItemTitle());
            ImageView imageView=convertView.findViewById(R.id.contact_item_iamge);
            imageView.setImageResource(item.getImageID());

            return convertView;
        }
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
