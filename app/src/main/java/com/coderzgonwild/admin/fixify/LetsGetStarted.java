package com.coderzgonwild.admin.fixify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LetsGetStarted extends AppCompatActivity {

    public Button setUpProfileBtn;
    private String firstTime = "yes";

    /**
    Intent prevIntent = getIntent();
    private String firstTime = prevIntent.getStringExtra("firstTime");
**/
    public void init(){

        setUpProfileBtn = (Button)findViewById(R.id.setUpProfile);

        //Modifying click methods
        setUpProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent profileEditor = new Intent(LetsGetStarted.this,ServiceProviderProfileEditor.class);
                profileEditor.putExtra("firstTime",firstTime);
                startActivity(profileEditor);
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
