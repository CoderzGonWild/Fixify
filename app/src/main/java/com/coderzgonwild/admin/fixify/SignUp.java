package com.coderzgonwild.admin.fixify;

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

    private String isNewAccount = "yes";

    public void init() {
        //initializing variables to their corresponding widget
        signUp = (Button)findViewById(R.id.process);
        newUsername = (EditText)findViewById(R.id.newUsername);
        invalidUsername = (TextView)findViewById(R.id.badUsername);
        newPassword = (EditText)findViewById(R.id.newPassword);
        invalidPassword = (TextView)findViewById(R.id.badPassword);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        invalidRadioGroup = (TextView)findViewById(R.id.invalidRadiogroup);
        credentials = (TextView)findViewById(R.id.credentials);

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
                if(usernameContent.equals("admin") || usernameContent.equals("Admin")){
                    invalidUsername.setText("You cannot register with this username");
                    validUsername = false;
                    validPassword = false;
                }

                if (radioGroup.getCheckedRadioButtonId() == -1) {
                    invalidRadioGroup.setText("Please select an option");
                }
                else {
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

                    for (Account account : MainActivity.accountList) {
                        if (account.getUsername().equals(usernameContent)) {
                            newAccount = false;
                        }
                    }
                    for (ServiceProvider serviceProviderAccount : MainActivity.ServiceProviderList) {
                        if (serviceProviderAccount.getUsername().equals(usernameContent)) {
                            newAccount = false;
                        }
                    }

                    if (newAccount == true) {
                        if (accountType.equals("User")) {
                            credentials.setText(" ");
                            Intent welcome = new Intent(SignUp.this, Welcome.class);
                            Account account1 = new Account(usernameContent, passwordContent, accountType);
                            MainActivity.accountList.add(account1);
                            int accountIndex = MainActivity.accountList.indexOf(account1);
                            Integer obj = new Integer(accountIndex);
                            welcome.putExtra("usernameContent", usernameContent);
                            welcome.putExtra("accountType", accountType);
                            welcome.putExtra("isNewAccount", isNewAccount); //Variable for sending user to profile entry immediately
                            startActivity(welcome);
                        }

                        if (accountType.equals("Service Provider")) {
                            credentials.setText(" ");
                            Intent welcome = new Intent(SignUp.this, Welcome.class);
                            ServiceProvider serviceProviderAccount = new ServiceProvider(usernameContent, passwordContent, accountType);
                            MainActivity.ServiceProviderList.add(serviceProviderAccount);
                            int accountIndex = MainActivity.accountList.indexOf(serviceProviderAccount);
                            Integer obj = new Integer(accountIndex);
                            welcome.putExtra("usernameContent", usernameContent);
                            welcome.putExtra("accountType", accountType);
                            welcome.putExtra("isNewAccount", isNewAccount); //Variable for sending user to profile entry immediately
                            startActivity(welcome);
                            Intent home = new Intent(SignUp.this, ServiceProviderMenu.class);
                            home.putExtra("obj", obj);

                            Intent add = new Intent(SignUp.this, ServiceProviderAdd.class);
                            add.putExtra("obj", obj);

                            Intent delete = new Intent(SignUp.this, ServiceProviderDelete.class);
                            delete.putExtra("obj", obj);
                            startActivity(welcome);
                        }
                    }
                    else {
                        credentials.setText("Username has already been taken");
                    }
                }
            }
        });
    }

    //Getter methods
    public String getNewUsername(){
        return newUsername.getText().toString();
    }

    //Setter methods
    public void setNewUsername(String newNewUsername){ newUsername.setText(newNewUsername); }
    public void setNewPassword(String newNewPassword){ newPassword.setText(newNewPassword); }

    public void setUsernameContent(String newUsernameContent){usernameContent = newUsernameContent; }
    public void setPasswordContent(String newPasswordContent){usernameContent = newPasswordContent; }

    public void setAccountType(String newAccountType){ accountType = newAccountType;}


    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }
}
