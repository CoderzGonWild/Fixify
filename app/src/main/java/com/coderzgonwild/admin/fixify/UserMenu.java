package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;

public class UserMenu extends AppCompatActivity {

    //Instance variables
    public Button searchService;
    public Button user_logout;

    private SharedPreferences preferences;
    private  int key;

    public void init(){
        //Associate variables to widgets
        searchService = (Button)findViewById(R.id.searchService);
        user_logout = (Button)findViewById(R.id.user_logout);

        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userLogoutIntent = new Intent(UserMenu.this, MainActivity.class);
                startActivity(userLogoutIntent);
            }
        });
        searchService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchServices = new Intent(UserMenu.this, SearchService.class);
                startActivity(searchServices);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);


        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);

        key = preferences.getInt(loggedInUser, -1);
        Account user = (Account)MainActivity.accountList.get(key);




        init();
    }
}
