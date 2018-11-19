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
    private boolean newAccount = true;
    private String usernameContent;
    private String passwordContent;
    private String accountType;

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
                    selectedRadioButton = (RadioButton)findViewById(selectedOption);
                    accountType = selectedRadioButton.getText().toString();
                    if (validUsername == true && validPassword == true) {



                        for (Account account: MainActivity.accountList) {
                            if (account.equals(usernameContent)){
                                newAccount = false;
                            }
                        }

                        if (newAccount == true) {
                            credentials.setText(" ");
                            Intent welcome = new Intent(SignUp.this, Welcome.class);
                            Account account1 = new Account(usernameContent, passwordContent, accountType);
                            MainActivity.accountList.add(account1);
                            int accountIndex = MainActivity.accountList.indexOf(account1);
                            welcome.putExtra("usernameContent", usernameContent);
                            welcome.putExtra("accountType", accountType);
                            welcome.putExtra("newAccount",newAccount); //Variable for sending user to profile entry immediately


                            if (accountType.equals("Service Provider")) {

                                Intent home = new Intent(SignUp.this, HomeServicesFragment.class);
                                home.putExtra("accountIndex", Integer.toString(accountIndex));

                                Intent add = new Intent(SignUp.this, ServiceProviderAdd.class);
                                add.putExtra("accountIndex", Integer.toString(accountIndex));

                                Intent delete = new Intent(SignUp.this, ServiceProviderDelete.class);
                                delete.putExtra("accountIndex", Integer.toString(accountIndex));
                            }
                            startActivity(welcome);
                        }
                        else {
                            credentials.setText("Username has already been taken");

                        }
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
