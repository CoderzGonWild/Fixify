

package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.*;



public class ServiceProviderAvailibility extends AppCompatActivity {
    boolean add = true;
    private ListView listView1;
    private ArrayAdapter<String> listAdapter;
    public static ArrayList<String> servicesavail = new ArrayList<String>();
    private Button goBack;



    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void init(){
        goBack = (Button)findViewById(R.id.goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackIntent = new Intent(ServiceProviderAvailibility.this, ServiceProviderMenu.class);
                startActivity(goBackIntent);

            }
        });



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_availibility);

        preferences   = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();
        init();

        goBack = (Button)findViewById(R.id.goBack);

        listView1 = (ListView) findViewById(R.id.avail);

        String[] timeslots = new String[]{
                "Monday 9:00am-10:00am",
                "Monday 10:00am-11:00am",
                "Monday 11:00am-12:00pm",
                "Monday 12:00pm-1:00pm",
                "Monday 1:00pm-2:00pm",
                "Monday 2:00pm-3:00pm",
                "Monday 3:00pm-4:00pm",
                "Monday 4:00pm-5:00pm",
                "Tuesday 9:00am-10:00am",
                "Tuesday 10:00am-11:00am",
                "Tuesday 11:00am-12:00pm",
                "Tuesday 12:00pm-1:00pm",
                "Tuesday 1:00pm-2:00pm",
                "Tuesday 2:00pm-3:00pm",
                "Tuesday 3:00pm-4:00pm",
                "Tuesday 4:00pm-5:00pm",
                "Wednesday 9:00am-10:00am",
                "Wednesday 10:00am-11:00am",
                "Wednesday 11:00am-12:00pm",
                "Wednesday 12:00pm-1:00pm",
                "Wednesday 1:00pm-2:00pm",
                "Wednesday 2:00pm-3:00pm",
                "Wednesday 3:00pm-4:00pm",
                "Wednesday 4:00pm-5:00pm",
                "Thursday 9:00am-10:00am",
                "Thursday 10:00am-11:00am",
                "Thursday 11:00am-12:00pm",
                "Thursday 12:00pm-1:00pm",
                "Thursday 1:00pm-2:00pm",
                "Thursday 2:00pm-3:00pm",
                "Thursday 3:00pm-4:00pm",
                "Thursday 4:00pm-5:00pm",
                "Friday 9:00am-10:00am",
                "Friday 10:00am-11:00am",
                "Friday 11:00am-12:00pm",
                "Friday 12:00pm-1:00pm",
                "Friday 1:00pm-2:00pm",
                "Friday 2:00pm-3:00pm",
                "Friday 3:00pm-4:00pm",
                "Friday 4:00pm-5:00pm",
        };

        final ArrayList<String> timeList = new ArrayList<String>();
        timeList.addAll(Arrays.asList(timeslots));
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, timeList);
        listView1.setAdapter(listAdapter);


        // adding items to an arraylist of services provided


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int key = preferences.getInt(MainActivity.loggedInUser, -1);

                ServiceProvider pro = (ServiceProvider) MainActivity.accountList.get(key);




                for(String s: pro.getServicesAvail()){
                    if(s.equals(timeList.get(position))){
                        add = false;

                    }
                }
                if(add == true){

                    pro.getServicesAvail().add(timeList.get(position));


                }



            }
        });



        // Intent ser = new Intent(ServiceProviderAvailibility.this,ServiceProviderProfile.class);

        // ser.putExtra("services user does", servicesprovided);


    }

}


















