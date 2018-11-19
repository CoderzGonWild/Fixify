package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceEditor extends AppCompatActivity {

    private EditText newrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_editor);

        Intent myIntent2 = getIntent();


        Bundle extra = myIntent2.getExtras();
      String serviceName = extra.getString("serviceName");
      String serviceRate = extra.getString("serviceRate");


                //Getting TextFields we are about to update
                //final TextView serName = (TextView) findViewById(R.id.line01);
                //final EditText hourrate = (EditText) findViewById(R.id.line02);


                //Updating contents in this screen
               //serName.setText(servicename);
              // hourrate.setText(servicerate);




               //edit (Save)
                Button saveButton = (Button) findViewById(R.id.buttonSave);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                 //      newrate = (EditText)findViewById(hourlyRate);

               //         Intent launchlistr = new Intent(ServiceEditor.this, EditDeleteService.class);
               //         launchlistr.putExtra("newrate",newrate.getText());

                        //Updating contents in variable



                    }
                });






                //Updating Function of OnClick Button (delete)



       // Button delete = (Button) findViewById(R.id.delete);

       //delete.setOnItemClickListener(new View.OnItemClickListener() {
       //     @Override
       //     public void onItemClick(View view) {

         //               MainActivity.serviceList.remove(position);


            //        }
           //     });

            }
        }

