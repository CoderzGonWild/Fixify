package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Map;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;
import static com.coderzgonwild.admin.fixify.MainActivity.selectedProvider;

public class UserViewSelected extends AppCompatActivity {

    private ListView listView;
    private ServiceArrayAdapter adapter;

    private ListView listView2;
    private ArrayAdapter adapter2;

    private SharedPreferences preferences;
    private int providerKey;
    private int userKey;

    private ServiceProvider provider;
    private User user;

    private String selected;
    private Service picked;




    public void init() {
        Button book = (Button)findViewById(R.id.select);
        Button home = (Button)findViewById(R.id.home);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView = (ListView) findViewById(R.id.services);

                picked = provider.getServicesProvided().get(position);

                Toast.makeText(getApplicationContext(), picked.getName() + "selected", Toast.LENGTH_LONG).show();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView2 = (ListView) findViewById(R.id.availabilities);

                selected = provider.getServicesAvail().get(position);

                Toast.makeText(getApplicationContext(), "Selected time", Toast.LENGTH_LONG).show();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            user.addServiceBooked(picked);
            user.addTime(selected);
            MainActivity.accountList.put(userKey, user);

            Intent userHome = new Intent(UserViewSelected.this, UserMenu.class);
            startActivity(userHome);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent userHome = new Intent(UserViewSelected.this, UserMenu.class);
            startActivity(userHome);

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
        setContentView(R.layout.activity_user_view_selected);

        TextView name = (TextView)findViewById(R.id.username);
        TextView noServices = (TextView)findViewById(R.id.noServices);
        TextView noAvail = (TextView)findViewById(R.id.noAvail);

        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        providerKey = preferences.getInt(selectedProvider, -1);
        userKey = preferences.getInt(loggedInUser, -1);
        provider = (ServiceProvider) MainActivity.accountList.get(providerKey);
        user = (User) MainActivity.accountList.get(userKey);

        name.setText("Provider : " + provider.getUsername());


        if (provider.getServicesProvided().isEmpty()) {
            noServices.setVisibility(View.VISIBLE);
        }
        else if (!provider.getServicesProvided().isEmpty()) {
            listView = (ListView) findViewById(R.id.services);
            adapter = new ServiceArrayAdapter(this, provider.getServicesProvided());
            listView.setAdapter(adapter);

            noServices.setVisibility(View.INVISIBLE);
        }

        //Service Availabilities
        if (provider.getServicesAvail().isEmpty()) {
            noAvail.setVisibility(View.VISIBLE);
        }
        else if (!provider.getServicesAvail().isEmpty()) {
            listView2 = (ListView) findViewById(R.id.availabilities);
            adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, provider.getServicesAvail());
            if (listView2 != null) {
                listView2.setAdapter(adapter2);
            }

            noAvail.setVisibility(View.INVISIBLE);
        }

        init();
    }
}
