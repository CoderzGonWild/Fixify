package com.coderzgonwild.admin.fixify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import java.io.Serializable;

public class Welcome extends AppCompatActivity {

    public TextView message;
    public SignUp signUp = new SignUp();
    public Button continue_btn;
    private String firstTime;

    public void message() {
        message = (TextView)findViewById(R.id.message);
        continue_btn = (Button)findViewById(R.id.continue_btn);

        //Create intent for admin menu
        final Intent admin_menu = new Intent(Welcome.this,AdminMenu.class);
        final Intent service_provider_menu = new Intent(Welcome.this,ServiceProviderMenu.class);
        final Intent lets_get_started = new Intent(Welcome.this,LetsGetStarted.class);

        Intent myIntent = getIntent(); // gets the previously created intent
        final String username = myIntent.getStringExtra("usernameContent"); // will return "FirstKeyValue"
        final String account = myIntent.getStringExtra("accountType");
        final boolean newAccount = myIntent.getBooleanExtra("newAccount",true);


        message.setText("Welcome " + username + " you have a(n) " + account + " account");

        //Continue button function that leads user to the appropriate page
        continue_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (username.equals("admin")) {
                    startActivity(admin_menu);
                }
                else if(account.equals("Service Provider")){
                    Intent letsGetStarted = new Intent(Welcome.this,LetsGetStarted.class);
                    if(newAccount){ //Bring user to profile page right away
                        firstTime = "yes";
                        startActivity(service_provider_menu);
                    }
                    else { //Bring them to menu
                        firstTime = "no";
                        letsGetStarted.putExtra("firstTime",firstTime);
                        startActivity(service_provider_menu);
                    }
                }
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        message();
    }
    
}
