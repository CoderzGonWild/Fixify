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
import android.widget.TextView;

public class ServiceProviderMenu extends AppCompatActivity {

    private Button viewProfile;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_menu);

        init();
    }

}
