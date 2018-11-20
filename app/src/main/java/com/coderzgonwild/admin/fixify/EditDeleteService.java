package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditDeleteService extends AppCompatActivity  {

    private Button back;
    private Button editbutton;
    private Button delete;
    private TextView newrate1;
    private Button save1;
    private TextView serviceName;
    private TextView serviceRate;


    public void init() {


        back = (Button)findViewById(R.id.editDelete_cancel);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent previous = new Intent(EditDeleteService.this, AdminMenu.class );
            startActivity(previous);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_service);
        init();

        serviceName = (TextView)findViewById(R.id.serviceName);
        serviceRate = (TextView)findViewById(R.id.serviceRate);



       // String[] services = new String[MainActivity.serviceList.size()];
      //  services =  MainActivity.serviceList.toArray(services);

        // Get ListView object from xml layout
        ListView listView = (ListView) findViewById(R.id.list);

       final ServiceArrayAdapter adapter = new ServiceArrayAdapter(this, MainActivity.serviceList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView = (ListView) findViewById(R.id.list);



                editbutton = findViewById(R.id.edit );
                delete = findViewById(R.id.delete);
                save1 = findViewById(R.id.save);
                newrate1 = findViewById(R.id.newrate);

                delete.setVisibility(View.VISIBLE);
                editbutton.setVisibility(View.VISIBLE);
                final Service select = MainActivity.serviceList.get(position);



                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.serviceList.remove(position);
                        adapter.notifyDataSetChanged();
                        delete.setVisibility(View.INVISIBLE);
                        editbutton.setVisibility(View.INVISIBLE);
                        save1.setVisibility(View.INVISIBLE);
                        newrate1.setVisibility(View.INVISIBLE);


                    }
                });






                //editbutton = findViewById(R.id.edit );
                //editbutton.setVisibility(View.VISIBLE);

                editbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        save1 = findViewById(R.id.save);
                        newrate1 = findViewById(R.id.newrate);
                        save1.setVisibility(View.VISIBLE);
                        newrate1.setVisibility(View.VISIBLE);


                        save1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                              select.setRate(Double.parseDouble(newrate1.getText().toString()));
                                Service newservice = new Service(select.getName(),select.getRate());

                                   MainActivity.serviceList.remove(position);
                                   MainActivity.serviceList.add(newservice);
                                   adapter.notifyDataSetChanged();
                                   delete.setVisibility(View.INVISIBLE);
                                   editbutton.setVisibility(View.INVISIBLE);
                                   save1.setVisibility(View.INVISIBLE);
                                   newrate1.setVisibility(View.INVISIBLE);
                            }
                        });

                                              }











                        //BACKUP PLAN TO USE OTHER ACTIVITY SERVICE EDITOR


                        //Intent launchServiceEditor = new Intent(EditDeleteService.this, ServiceEditor.class);
                        //launchServiceEditor.putExtra("servicename",serviceName.getText());
                        //launchServiceEditor.putExtra("servicerate",serviceRate.getText());
                        // startActivity(launchServiceEditor);




                    });
                };










            });
        }
}
