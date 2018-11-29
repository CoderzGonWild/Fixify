package com.coderzgonwild.admin.fixify;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    private Button login;
    private Button getStarted;





    public static HashMap<Integer, Account> accountList = new HashMap<>();
    public static final String loggedInUser = "CurrentLoggedInUserKey";
    public static final String selectedProvider = "UserSelectedProvider";
    public static int nextKey = 1;


    public static ArrayList<Service> serviceList = new ArrayList<>();


    private Account admin = new Account("admin", "admin", "admin");




    public void init() {
        accountList.put(0, admin); //Add admin automatically


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }
}
