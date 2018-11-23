package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMenu extends AppCompatActivity {

    //Instance variables
    public Button searchService;
    public Button user_logout;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        init();
    }
}
