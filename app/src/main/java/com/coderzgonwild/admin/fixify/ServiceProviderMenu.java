package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;


public class ServiceProviderMenu extends AppCompatActivity {

    private Button viewProfile;
    private Button editAvailibility;
    public Button editService;
    private Button SP_logout;

    private ListView listView2;
    private ArrayAdapter<String> listAdapter2;

    private ListView listView;
    private ServiceArrayAdapter adapter;

    private  int key;

    private SharedPreferences preferences;


    //Instance variables

    public void init(){




        viewProfile = (Button)findViewById(R.id.viewProfile);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(ServiceProviderMenu.this,ServiceProviderProfile.class);
                startActivity(viewProfileIntent);
            }
        });

        editAvailibility = (Button)findViewById(R.id.editAvailability);

        editAvailibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editavailibilityIntent = new Intent(ServiceProviderMenu.this,ServiceProviderAvailibility.class);
                startActivity(editavailibilityIntent);
            }
        });


        editService = (Button)findViewById(R.id.addRemoveServices);
        editService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
                Intent editServiceIntent = new Intent(ServiceProviderMenu.this, ServiceProviderAdd.class);
                startActivity(editServiceIntent);
            }
        });


        SP_logout = (Button)findViewById(R.id.SP_logout);


        SP_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().remove(loggedInUser).apply();
                Intent logoutIntent = new Intent(ServiceProviderMenu.this,MainActivity.class);
                startActivity(logoutIntent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
        listAdapter2.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        listAdapter2.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_menu);

        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);

        key = preferences.getInt(loggedInUser, -1);
        ServiceProvider pro = (ServiceProvider) MainActivity.accountList.get(key);


        init();

        listView2 = (ListView) findViewById(R.id.list3);
        listAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,  pro.getServicesAvail() );
        listView2.setAdapter(listAdapter2);

        //services stuff

        listView  = (ListView) findViewById(R.id.dynamic);
        adapter = new ServiceArrayAdapter(this, pro.getServicesProvided());
        listView.setAdapter(adapter);

        listView.deferNotifyDataSetChanged();

    }


}


