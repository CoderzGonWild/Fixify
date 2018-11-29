package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;

public class UserMenu extends AppCompatActivity {

    //Instance variables
    public Button searchService;
    public Button user_logout;
    public Button rateService;

    private SharedPreferences preferences;
    private  int key;

    private ListView listView;
    private ServiceArrayAdapter adapter;

    private ListView listView2;
    private ArrayAdapter adapter2;

    public void init(){
        //Associate variables to widgets
        searchService = (Button)findViewById(R.id.searchService);
        user_logout = (Button)findViewById(R.id.user_logout);
        rateService = (Button)findViewById(R.id.rateService);

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
        rateService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rateService = new Intent(UserMenu.this, RatingMenu.class);
                startActivity(rateService);
            }
        });
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        adapter.notifyDataSetChanged();
//        adapter2.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//        adapter2.notifyDataSetChanged();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);


        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        key = preferences.getInt(loggedInUser, -1);
        User user = (User)MainActivity.accountList.get(key);


        TextView noServices = (TextView)findViewById(R.id.noServices);
        TextView noAvail = (TextView)findViewById(R.id.noAvail);

        if (user.getServicesBooked().isEmpty()) {
            noServices.setVisibility(View.VISIBLE);
        }
        else if (!user.getServicesBooked().isEmpty()) {
            listView = (ListView) findViewById(R.id.services);
            adapter = new ServiceArrayAdapter(this, user.getServicesBooked());
            listView.setAdapter(adapter);

            noServices.setVisibility(View.INVISIBLE);
        }

        //Service Availabilities
        if (user.getBookedTimes().isEmpty()) {
            noAvail.setVisibility(View.VISIBLE);
        }
        else if (!user.getBookedTimes().isEmpty()) {
            listView2 = (ListView) findViewById(R.id.availabilities);
            adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, user.getBookedTimes());
            if (listView2 != null) {
                listView2.setAdapter(adapter2);
            }

            noAvail.setVisibility(View.INVISIBLE);
        }



        init();
    }
}
