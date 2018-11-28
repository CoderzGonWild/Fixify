package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.nextKey;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;

import java.io.Serializable;

public class Welcome extends AppCompatActivity {

    public TextView message;
    public SignUp signUp = new SignUp();
    public Button continue_btn;


    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Integer loggedInUserKey;

    public void message() {
        message = (TextView)findViewById(R.id.message);
        continue_btn = (Button)findViewById(R.id.continue_btn);

        //Create intent for admin menu
        final Intent admin_menu = new Intent(Welcome.this, AdminMenu.class);
        final Intent service_provider_menu = new Intent(Welcome.this, ServiceProviderMenu.class);
        final Intent userMenuIntent = new Intent(Welcome.this,UserMenu.class);
        final Intent serviceProviderIntent = new Intent(Welcome.this,ServiceProviderProfileEditor.class);


        Account currentAccount = accountList.get(loggedInUserKey);

        final  String username = currentAccount.getUsername();
        final String accountType = currentAccount.getAccountType();


        message.setText("Welcome " + username + " you have a(n) " + accountType + " account");

        //Continue button function that leads user to the appropriate page
        continue_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (accountType.equals("admin")) {
                    startActivity(admin_menu);
                }
                else if(accountType.equals("Service Provider")){

                    Intent intent = getIntent();
                    if (intent.getBooleanExtra("newUser", false) == true) {
                        startActivity(serviceProviderIntent);
                    } else {
                        startActivity(service_provider_menu);
                    }

                }
                else{

                    startActivity(userMenuIntent);
                }
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();

        loggedInUserKey = preferences.getInt(loggedInUser, -1);

        message();
    }

}
