package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceProviderAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_add);

        Intent myIndexIntent = getIntent();
        final int accountIndex = Integer.parseInt(myIndexIntent.getStringExtra("accountIndex"));
        final Account serviceProviderAccount = MainActivity.accountList.get(accountIndex);


        ListView listView = (ListView) findViewById(R.id.list);
        final ServiceArrayAdapter adapter = new ServiceArrayAdapter(this, MainActivity.serviceList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView = (ListView) findViewById(R.id.list);

                boolean already = false;
                final Service select = MainActivity.serviceList.get(position);
                TextView message = (TextView)findViewById(R.id.message);

                for (Service service: serviceProviderAccount.servicesProvided) {
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
                    serviceProviderAccount.servicesProvided.add(select);
                    MainActivity.accountList.set(accountIndex, serviceProviderAccount);
                }
            }
            });
        };
}
