package com.coderzgonwild.admin.fixify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private Button login;
    private Button getStarted;
    public static ArrayList<Account> accountList = new ArrayList<Account>();
    private Account admin = new Account("admin", "admin", "administrator");

    public static ArrayList<Service> serviceList = new ArrayList<>();


    public void init() {
        accountList.add(admin); //Add admin automatically

        //Button variables
        login = (Button)findViewById(R.id.login);
        getStarted = (Button)findViewById(R.id.getStarted);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });

        getStarted.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               Intent getStarted = new Intent(MainActivity.this, SignUp.class );
               startActivity(getStarted);
           }
        });

    }

    //Brings user back to front page when back button pressed
    public void onBackPressed(){
        Intent mainActivity = new Intent(MainActivity.this,MainActivity.class);
        startActivity(mainActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
}
