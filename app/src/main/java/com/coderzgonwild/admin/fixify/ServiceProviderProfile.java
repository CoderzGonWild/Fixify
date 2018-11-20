package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ServiceProviderProfile extends AppCompatActivity {

    //Instance variables
    private TextView companyName;
    private TextView address;
    private TextView phoneNumber;
    private TextView licensed;
    private TextView about;

    private Button editProfile;
    private Button goBack;

    public void init(){
        //Obtain previous intent
        Intent prevIntent = getIntent();

        //Obtain previously saved content
        final String companyNameContent = prevIntent.getStringExtra("currentCompanyName");
        final String addressContent = prevIntent.getStringExtra("currentAddress");
        final String phoneNumberContent = prevIntent.getStringExtra("currentPhoneNumber");
        final String licensedAnswer = prevIntent.getStringExtra("currentLicense");
        final String aboutContent = prevIntent.getStringExtra("currentAbout");

        //Associating variables with widgets
        companyName = (TextView) findViewById(R.id.nameOfCompany);
        address = (TextView)findViewById(R.id.profileAddress);
        phoneNumber = (TextView)findViewById(R.id.profilePhoneNumber);
        licensed = (TextView)findViewById(R.id.licensedProfileResponse);
        about =  (TextView)findViewById(R.id.profileAbout);
        editProfile = (Button)findViewById(R.id.profilePageEditProfile);
        goBack = (Button)findViewById(R.id.goBack);

        //Assign the inherited intent fields to the respective widgets
        companyName.setText(companyNameContent);
        address.setText(addressContent);
        phoneNumber.setText(phoneNumberContent);
        licensed.setText(licensedAnswer);
        about.setText(aboutContent);

        //Click modifiers
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent editProfileIntent = new Intent(ServiceProviderProfile.this,ServiceProviderProfileEditor.class);
               editProfileIntent.putExtra("currentCompanyName",companyNameContent);
               editProfileIntent.putExtra("currentAddress",addressContent);
               editProfileIntent.putExtra("currentPhoneNumber",phoneNumberContent);
               editProfileIntent.putExtra("currentLicense",licensedAnswer);
               editProfileIntent.putExtra("currentAbout",aboutContent);
               startActivity(editProfileIntent);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackIntent = new Intent(ServiceProviderProfile.this,ServiceProviderMenu.class);
                startActivity(goBackIntent);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);

        init();
    }
}
