package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceProviderAdd extends AppCompatActivity {

    private ListView listView;
    private ServiceArrayAdapter adapter;
    Button deleteService;
    Button back;

    private  int accountIndex;

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_add);

        //getting accountIndex
        Intent myIndexIntent = getIntent();


        accountIndex   = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE).getInt(MainActivity.loggedInUser, -1);

        final ServiceProvider serviceProviderAccount = (ServiceProvider) MainActivity.accountList.get(accountIndex);

        //Creating ListView
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ServiceArrayAdapter(this, MainActivity.serviceList);
        listView.setAdapter(adapter);

        //Detecting Click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView = (ListView) findViewById(R.id.list);

                boolean already = false;
                final Service select = MainActivity.serviceList.get(position);
                TextView message = (TextView)findViewById(R.id.message);

                for (Service service: serviceProviderAccount.getServicesProvided()) {
                    if (select.equals(service)) {
                        message.setTextColor(Color.parseColor("#ff0000"));
                        message.setText("You already provide this service");
                        already = true;
                        break;
                    }
                }
                if (already == false) {
                    message.setTextColor(Color.parseColor("#008000"));
                    message.setText("You now provide this service");

                    ArrayList<Service> servicesProvided = serviceProviderAccount.getServicesProvided();

                    servicesProvided.add(select);
                    serviceProviderAccount.setServicesProvided(servicesProvided);
                    //   MainActivity.ServiceProviderList.set(accountIndex, serviceProviderAccount);
                    adapter.notifyDataSetChanged();
                }
            }


        });

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteServiceIntent = new Intent(ServiceProviderAdd.this, ServiceProviderMenu.class);
                startActivity(deleteServiceIntent);
            }
        });

        deleteService = (Button)findViewById(R.id.delete);
        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteServiceIntent = new Intent(ServiceProviderAdd.this, ServiceProviderDelete.class);
                startActivity(deleteServiceIntent);
            }
        });
    }


}
