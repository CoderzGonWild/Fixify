package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;
import static com.coderzgonwild.admin.fixify.MainActivity.selectedProvider;

public class UserViewSelected extends AppCompatActivity {

    private ListView listView;
    private ServiceArrayAdapter adapter;

    private ListView listView2;
    private ArrayAdapter adapter2;

    private SharedPreferences preferences;
    private int key;




    public void init() {

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
        key = preferences.getInt(selectedProvider, -1);
        ServiceProvider provider = (ServiceProvider) MainActivity.accountList.get(key);

        //name.setText("Provider : " + provider.getUsername());



        if (provider.getServicesProvided().isEmpty()) {
            noServices.setVisibility(View.VISIBLE);
        }
        else if (!provider.getServicesProvided().isEmpty()) {
            listView = (ListView) findViewById(R.id.dynamic);
            adapter = new ServiceArrayAdapter(this, provider.getServicesProvided());
            listView.setAdapter(adapter);

            listView.deferNotifyDataSetChanged();
        }

        //Service Availabilities
        if (provider.getServicesAvail().isEmpty()) {
            noAvail.setVisibility(View.VISIBLE);
        }
        else if (!provider.getServicesAvail().isEmpty()) {
            listView2 = (ListView) findViewById(R.id.dynamic);
            adapter2 = new ArrayAdapter(this, R.layout.simple_list_item, provider.getServicesAvail());
            listView2.setAdapter(adapter);

            listView2.deferNotifyDataSetChanged();
        }

        init();
    }
}
