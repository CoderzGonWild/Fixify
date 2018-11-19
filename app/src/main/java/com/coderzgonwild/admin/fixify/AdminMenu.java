package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMenu extends AppCompatActivity {

    public Button am_add;
    public Button am_editDelete;
    public Button am_logout;

    public void init() {
        //Declare buttons
        am_add = (Button) findViewById(R.id.am_add);
        am_editDelete = (Button) findViewById(R.id.am_editDelete);
        am_logout = (Button) findViewById(R.id.am_logout);

        //Modifying click method for AddService
        am_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent am_add_intent = new Intent(AdminMenu.this, AddService.class);
                startActivity(am_add_intent);
            }
        });

        am_editDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent am_editDelete_intent = new Intent(AdminMenu.this, EditDeleteService.class );
                startActivity(am_editDelete_intent);
            }
        });

        am_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent logout_intent = new Intent(AdminMenu.this, MainActivity.class );
                startActivity(logout_intent);
            }
        });

        //Create intents for the options


    }


    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        init();
    }
}
