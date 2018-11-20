

package com.coderzgonwild.admin.fixify;

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
    public static ArrayList<String> servicesprovided = new ArrayList<String>();
    private Button goBack;

    public void init(){
        goBack = (Button)findViewById(R.id.goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackIntent = new Intent(ServiceProviderAvailibility.this,ServiceProviderMenu.class);
                startActivity(goBackIntent);
               
            }
        });



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_availibility);
        goBack = (Button)findViewById(R.id.goBack);

        listView1 = (ListView) findViewById(R.id.avail);

        String[] timeslots = new String[]{"Mon                                      9:00am-10:00am",
                "Mon                                     10:00am-11:00am",
                "Mon                                     11:00am-12:00pm",
                "Mon                                     12:00pm-1:00pm",
                "Mon                                      1:00pm-2:00pm",
                "Mon                                      2:00pm-3:00pm",
                "Mon                                      3:00pm-4:00pm",
                "Mon                                      4:00pm-5:00pm",
                "Tue                                      9:00am-10:00am",
                "Tue                                     10:00am-11:00am",
                "Tue                                     11:00am-12:00pm",
                "Tue                                     12:00pm-1:00pm",
                "Tue                                      1:00pm-2:00pm",
                "Tue                                      2:00pm-3:00pm",
                "Tue                                      3:00pm-4:00pm",
                "Tue                                      4:00pm-5:00pm",
                "Wed                                      9:00am-10:00am",
                "Wed                                     10:00am-11:00am",
                "Wed                                     11:00am-12:00pm",
                "Wed                                     12:00pm-1:00pm",
                "Wed                                      1:00pm-2:00pm",
                "Wed                                      2:00pm-3:00pm",
                "Wed                                      3:00pm-4:00pm",
                "Wed                                      4:00pm-5:00pm",
                "Thu                                      9:00am-10:00am",
                "Thu                                     10:00am-11:00am",
                "Thu                                     11:00am-12:00pm",
                "Thu                                     12:00pm-1:00pm",
                "Thu                                      1:00pm-2:00pm",
                "Thu                                      2:00pm-3:00pm",
                "Thu                                      3:00pm-4:00pm",
                "Thu                                      4:00pm-5:00pm",
                "Fri                                      9:00am-10:00am",
                "Fri                                     10:00am-11:00am",
                "Fri                                     11:00am-12:00pm",
                "Fri                                     12:00pm-1:00pm",
                "Fri                                      1:00pm-2:00pm",
                "Fri                                      2:00pm-3:00pm",
                "Fri                                      3:00pm-4:00pm",
                "Fri                                      4:00pm-5:00pm",
        };

        final ArrayList<String> timeList = new ArrayList<String>();
        timeList.addAll(Arrays.asList(timeslots));
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, timeList);
        listView1.setAdapter(listAdapter);


        // adding items to an arraylist of services provided


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                for(String s: servicesprovided){
                    if(s.equals(timeList.get(position))){
                      add = false;

                    }
                    }
                    if(add == true){

                    servicesprovided.add(timeList.get(position));
                    }



            }
        });



       // Intent ser = new Intent(ServiceProviderAvailibility.this,ServiceProviderProfile.class);

       // ser.putExtra("services user does", servicesprovided);


    }

}


















