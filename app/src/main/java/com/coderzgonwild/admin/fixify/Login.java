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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;

public class Login extends AppCompatActivity {


    //declaring necessary variables for validation
    private Button login;
    private EditText username;
    private EditText password;
    private TextView invalidPassword;
    private TextView credentials;


    SharedPreferences preferences;
    SharedPreferences.Editor editor;



    public void init() {
        //initializing variables to their corresponding widget
        login = (Button)findViewById(R.id.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        invalidPassword = (TextView)findViewById(R.id.badPassword);
        credentials = (TextView)findViewById(R.id.combo);

        //modifying onClick method
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameContent = username.getText().toString(); //gets you the contents
                String passwordContent = password.getText().toString();



                //If correct credentials are entered, then open welcome page
                //users and admin


                for (Map.Entry<Integer, Account> entry : accountList.entrySet()) {
                    int key  = entry.getKey();

                    Account account = accountList.get(key);
                    //Successful login if condition
                    if(usernameContent.equals(account.getUsername()) && passwordContent.equals(account.getPassword())){

                        Intent welcome = new Intent(Login.this, Welcome.class);
                        editor.putInt(loggedInUser, key).apply();
                        startActivity(welcome);
                    }
                    else {
                        credentials.setText("Invalid username/password combo");
                        //break;
                    }
                }



            }

        });
    }


    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("login");
        setContentView(R.layout.activity_login);


        preferences = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();





        init();



    }




}
