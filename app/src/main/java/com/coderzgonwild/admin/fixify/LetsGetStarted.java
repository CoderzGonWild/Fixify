package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LetsGetStarted extends AppCompatActivity {

    //Instance variable for continue button
    private Button continueBtn;

    public void init(){

        continueBtn = (Button)findViewById(R.id.continueBtn);
        final Intent editProfileIntent = new Intent(LetsGetStarted.this,ServiceProviderProfileEditor.class);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(editProfileIntent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_get_started);

        init();
    }
}
