package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceProviderDelete extends AppCompatActivity {


    private ListView listView;
    private ServiceArrayAdapter adapter;
    private Button back;

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
        setContentView(R.layout.activity_service_provider_delete);


        //getting accountIndex
        Intent myIndexIntent = getIntent();
        final int accountIndex = myIndexIntent.getIntExtra("accountIndex", 0);
        final Account serviceProviderAccount = MainActivity.accountList.get(accountIndex);

        //Creating ListView
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new ServiceArrayAdapter(this, serviceProviderAccount.getServicesProvided());
        listView.setAdapter(adapter);

        //Detecting Click

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView = (ListView) findViewById(R.id.list);

                final Service select = serviceProviderAccount.getServicesProvided().get(position);
                TextView message = (TextView) findViewById(R.id.message);


                message.setTextColor(Color.parseColor("#008000"));
                message.setText("You no longer provide this service");
                serviceProviderAccount.servicesProvided.remove(select);
                MainActivity.accountList.set(accountIndex, serviceProviderAccount);
                adapter.notifyDataSetChanged();

            }
        });

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteServiceIntent = new Intent(ServiceProviderDelete.this, ServiceProviderMenu.class);
                startActivity(deleteServiceIntent);
            }
        });

    }

}

