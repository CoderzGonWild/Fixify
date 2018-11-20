package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    //declaring necessary variables for validation
    private Button login;
    private EditText username;
    private EditText password;
    private TextView invalidPassword;
    private TextView credentials;
    private boolean error = false;
    private int accountIndex;

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
                String accountType;


                //If correct credentials are entered, then open welcome page

                for (Account account: MainActivity.accountList) {
                    //Successful login if condition
                    if(usernameContent.equals(account.getUsername()) && passwordContent.equals(account.getPassword())){
                        credentials.setText(" ");
                        accountType = account.getAccountType();
                        accountIndex = MainActivity.accountList.indexOf(account);
                        Intent welcome = new Intent(Login.this, Welcome.class);
                        welcome.putExtra("usernameContent", usernameContent);
                        welcome.putExtra("accountType",accountType);

                        if (accountType.equals("Service Provider")) {
                            Intent home = new Intent(Login.this, ServiceProviderMenu.class);
                            home.putExtra("accountIndex", accountIndex);

                            Intent add = new Intent(Login.this, ServiceProviderAdd.class);
                            add.putExtra("accountIndex", Integer.toString(accountIndex));

                            Intent delete = new Intent(Login.this, ServiceProviderDelete.class);
                            delete.putExtra("accountIndex", Integer.toString(accountIndex));
                        }
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

    //Getter methods
    public String getUsername(){ return this.username.getText().toString(); }
    public String getPassword() {return this.password.getText().toString(); }

    //Setter methods
    public void setUsername(String newUsername){ this.username.setText(newUsername); }
    public void setPassword(String newPassword) {this.password.setText(newPassword); }

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("login");
        setContentView(R.layout.activity_login);

        init();
    }




}
