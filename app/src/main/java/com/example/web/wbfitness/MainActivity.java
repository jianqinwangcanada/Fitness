package com.example.web.wbfitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                 ContactFragment.OnFragmentInteractionListener,
                 HomePage.OnFragmentInteractionListener,
                SetupPage.OnFragmentInteractionListener{


    //Declare FragmentManager to manage transaction
    FragmentManager fm = getSupportFragmentManager();

    // Declare the SharedPreferences File
    SharedPreferences preferences = null;
    //Declare Locale variable
    Locale myLocal;
    SharedPreferences setting;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // Determine if the app is being launched for the first time and display the appropriate page
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println(preferences.getBoolean("initialize",false));
        if(preferences.getBoolean("initialize", true)) {
            // Launch the Home Page when the app is opened
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content, new SetupPage(), "First Time Setup");
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            // Launch the Home Page when the app is opened
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content, new HomePage(), "Home Page");
            transaction.addToBackStack(null);
            transaction.commit();
        }




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //declare TransactionManager
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_in,R.anim.fragment_back_out,R.anim.fragment_back_in,R.anim.fragment_back_out);
        Fragment selectedFragment;


        if (id == R.id.nav_home) {
            selectedFragment=fm.findFragmentByTag("Home Page");
            if(selectedFragment == null) {
                transaction.replace(R.id.content, new HomePage(),"Home Page");
            }
            else if(!selectedFragment.isVisible()){
                transaction.replace(R.id.content, selectedFragment);
            }
        } else if (id == R.id.nav_bmi) {

        } else if (id == R.id.nav_workoutplan) {

        } else if (id == R.id.nav_tips) {

        } else if (id == R.id.nav_contact) {
           //Just whether it is visible or null , if invisilbe or null ,show it
            selectedFragment=fm.findFragmentByTag("Contact");
            if(selectedFragment == null)
            {transaction.replace(R.id.content, new ContactFragment(),"Contact");
            }
            else if(!selectedFragment.isVisible()){
                transaction.replace(R.id.content, selectedFragment);
            }

        } else if (id == R.id.nav_credits)
        {

        }else if(id==R.id.nav_refresh){
            // this method will refresh language the setting choice
            //Populate the the setting SharedPreference
            setting=PreferenceManager.getDefaultSharedPreferences(this);

            //Grab the setting value user chosen
            String lanugageChoice=setting.getString(getResources().getString(R.string.language_key),"");
            System.out.println("God Luanguage"+lanugageChoice);


            //If user chose Chinese
            if(lanugageChoice.equals("0")){
                myLocal=Locale.SIMPLIFIED_CHINESE;
                  }
                  //if user choose english
            else if(lanugageChoice.equals("1")){
                myLocal=Locale.ENGLISH;
                   }
           //Call method to refresh mainavtivity
            setLocale(myLocal);
        }




        //commit the transaction for all navigation drawer item selectd
        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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

        Intent refresh = new Intent(this, MainActivity.class);
        //Refresh the mainActivity
        startActivity(refresh);

    }
}
