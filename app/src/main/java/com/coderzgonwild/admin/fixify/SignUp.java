package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.nextKey;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;
import static com.coderzgonwild.admin.fixify.MainActivity.serviceProviderList;


public class SignUp extends AppCompatActivity {

    //declaring necessary variables for validation
    private Button signUp;
    private EditText newUsername;
    private TextView invalidUsername;
    private EditText newPassword;
    private TextView invalidPassword;
    private RadioGroup radioGroup;
    private TextView invalidRadioGroup;
    private TextView credentials;
    private RadioButton selectedRadioButton;
    private boolean validUsername = false;
    private boolean validPassword = false;
    private boolean selectedType = false;
    private boolean newAccount = true;
    private String usernameContent;
    private String passwordContent;
    private String accountType;



    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    private String isNewAccount = "yes";

    public void init() {
        //initializing variables to their corresponding widget
        signUp = (Button) findViewById(R.id.process);
        newUsername = (EditText) findViewById(R.id.newUsername);
        invalidUsername = (TextView) findViewById(R.id.badUsername);
        newPassword = (EditText) findViewById(R.id.newPassword);
        invalidPassword = (TextView) findViewById(R.id.badPassword);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        invalidRadioGroup = (TextView) findViewById(R.id.invalidRadiogroup);
        credentials = (TextView) findViewById(R.id.credentials);

        //modifying onClick method
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newAccount = true;
                usernameContent = newUsername.getText().toString();
                passwordContent = newPassword.getText().toString();

                //username requirements
                if (usernameContent.length() < 4) {
                    invalidUsername.setText("Invalid Username");
                } else {
                    invalidUsername.setText(" ");
                    validUsername = true;
                }
                //password requirements
                if (passwordContent.length() < 4) {
                    invalidPassword.setText("Invalid Password");
                } else {
                    invalidPassword.setText(" ");
                    validPassword = true;
                }
                //Prevent non-admins from registering as admins
                if (usernameContent.equals("admin") || usernameContent.equals("Admin")) {
                    invalidUsername.setText("You cannot register with this username");
                    validUsername = false;
                    validPassword = false;
                }

                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    invalidRadioGroup.setText("Please select an option");
                } else {
                    invalidRadioGroup.setText(" ");
                    // one of the radio buttons is checked
                    // get selected radio button from radioGroup
                    int selectedOption = radioGroup.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = (RadioButton) findViewById(selectedOption);
                    accountType = selectedRadioButton.getText().toString();
                    selectedType = true;
                }

                if (validUsername == true && validPassword == true && selectedType == true) {


                    if (newAccount == true) {
                        credentials.setText(" ");
                        Intent welcome = new Intent(SignUp.this, Welcome.class);


                        if (accountType.equals("User")) {

                            User userAccount = new User(usernameContent, passwordContent, accountType);
                            accountList.put(nextKey, userAccount);
                            editor.putInt(loggedInUser, nextKey).apply();

                        } else {

                            ServiceProvider serviceProvider1 = new ServiceProvider(usernameContent, passwordContent, accountType);

                            accountList.put(nextKey, serviceProvider1);
                            serviceProviderList.add(serviceProvider1);
                            editor.putInt(loggedInUser, nextKey).apply();
                            welcome.putExtra("newUser", true);
                        }

                        nextKey++;
                        startActivity(welcome);
                    }
                } else {
                    credentials.setText("Username has already been taken");
                }
            }

        });
    }




    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        preferences = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();


        init();
    }
}
