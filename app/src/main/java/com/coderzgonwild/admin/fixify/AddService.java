package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddService extends AppCompatActivity {

    private Button addService;
    private Button back;
    private TextView serviceName;
    private TextView hourlyRate;
    private TextView invalidEntry;
    private TextView success;


    public void init() {
        //Widget variables
        addService = (Button)findViewById(R.id.addService);
        back = (Button)findViewById(R.id.addService_cancel);
        serviceName = (TextView)findViewById(R.id.serviceName);
        hourlyRate = (TextView)findViewById(R.id.hourlyRate);
        invalidEntry = (TextView)findViewById(R.id.invalidEntry);
        success = (TextView)findViewById(R.id.success);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(serviceName.getText().toString()) || TextUtils.isEmpty(hourlyRate.getText().toString())) {
                    invalidEntry.setText("Invalid Service or Hourly rate entered");
                    success.setText("");
                }
                else {
                    boolean already = false;
                    for (Service service: MainActivity.serviceList) {
                        if (serviceName.getText().toString().equals(service.getName())) {
                            already = true;
                            break;
                        }
                    }
                    if (already == true) {
                        invalidEntry.setText("This service is already offered");
                        success.setText("");
                    }
                    else {
                        Service newService = new Service((serviceName.getText().toString()), Double.parseDouble(hourlyRate.getText().toString()));
                        MainActivity.serviceList.add(newService);
                        success.setText("Successfully added service!");
                        invalidEntry.setText("");
                    }
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent previous = new Intent(AddService.this, AdminMenu.class);
                startActivity(previous);
            }
        });
    }

    //Setter methods
    public void setServiceName(String newServiceName){serviceName.setText(newServiceName);}
    public void setHourlyRate(String newHourlyRate){hourlyRate.setText(newHourlyRate);}

    //Getter methods
    public Button getAddService(){return addService;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        init();
    }
}
