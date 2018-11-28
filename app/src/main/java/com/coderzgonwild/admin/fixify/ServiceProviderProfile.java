package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public void init(){


        int key = preferences.getInt(MainActivity.loggedInUser, -1);

        ServiceProvider pro = (ServiceProvider) MainActivity.accountList.get(key);

        //Associating variables with widgets
        companyName = (TextView) findViewById(R.id.nameOfCompany);
        address = (TextView)findViewById(R.id.profileAddress);
        phoneNumber = (TextView)findViewById(R.id.profilePhoneNumber);
        licensed = (TextView)findViewById(R.id.licensedProfileResponse);
        about =  (TextView)findViewById(R.id.profileAbout);
        goBack = (Button)findViewById(R.id.goBack);

        //Assign the inherited intent fields to the respective widgets
        companyName.setText(pro.getCompanyNameContent());
        address.setText(pro.getAddressContent());
        phoneNumber.setText(pro.getPhoneNumberContent());
        licensed.setText(pro.getLicensedContent());
        about.setText(pro.getAboutContent());

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

        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();

        init();
    }
}
