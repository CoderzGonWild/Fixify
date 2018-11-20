package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.os.Bundle;
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


public class ServiceProviderMenu extends AppCompatActivity {

    private Button viewProfile;
    private Button editAvailibility;
    public Button editService;

    private ListView listView2;
    private ArrayAdapter<String> listAdapter2;

    private ListView listView;
    private ServiceArrayAdapter adapter;

    //Instance variables

    public void init(){
        //Obtain previous intent content
        Intent prevIntent = getIntent();

        //Associate variables with widgets
        final String companyNameContent = prevIntent.getStringExtra("companyNameText");
        final String addressContent = prevIntent.getStringExtra("addressText");
        final String phoneNumberContent = prevIntent.getStringExtra("phoneNumberText");
        final String licensedAnswer = prevIntent.getStringExtra("licensedText");
        final String aboutContent = prevIntent.getStringExtra("aboutText");

        viewProfile = (Button)findViewById(R.id.viewProfile);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(ServiceProviderMenu.this,ServiceProviderProfile.class);
                viewProfileIntent.putExtra("currentCompanyName",companyNameContent);
                viewProfileIntent.putExtra("currentAddress",addressContent);
                viewProfileIntent.putExtra("currentPhoneNumber",phoneNumberContent);
                viewProfileIntent.putExtra("currentLicense",licensedAnswer);
                viewProfileIntent.putExtra("currentAbout",aboutContent);
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


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
        listAdapter2.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_menu);

        init();

        listView2 = (ListView) findViewById(R.id.list3);
        listAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,  ServiceProviderAvailibility.servicesprovided );
        listView2.setAdapter(listAdapter2);

        //services stuff
        Intent myIndexIntent = getIntent();
        int accountIndex = myIndexIntent.getIntExtra("accountIndex", 0);
        Account serviceProviderAccount = MainActivity.accountList.get(accountIndex);

        listView  = (ListView) findViewById(R.id.dynamic);
        adapter = new ServiceArrayAdapter(this, serviceProviderAccount.getServicesProvided());
        listView.setAdapter(adapter);
    }

}


