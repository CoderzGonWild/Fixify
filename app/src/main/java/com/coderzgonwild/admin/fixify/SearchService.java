package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;
import static com.coderzgonwild.admin.fixify.MainActivity.selectedProvider;

public class SearchService extends AppCompatActivity {

    public Button backBtn;
    public SearchView searchField;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private  int key;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> servicesSearched = new ArrayList<>();

    public void init(){
        backBtn = (Button)findViewById(R.id.backBtn);
        searchField = (SearchView)findViewById(R.id.searchField);

        servicesSearched.clear();


        //Search field functions
        searchField.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Account account;
                for (Map.Entry<Integer, Account> entry : accountList.entrySet()) {
                    account = entry.getValue();
                    if (account.getAccountType().equals("Service Provider")) {
                        if (account.getUsername().contains(query)) {
                            servicesSearched.add(account.getUsername());
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Apply filtering here (to do later)
                return false;
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ListView listView = (ListView) findViewById(R.id.dynamic);

                final String selected = servicesSearched.get(position);

                for (Map.Entry<Integer, Account> entry : accountList.entrySet()) {
                    if (selected.equals(entry.getValue().getUsername())) {
                        key = entry.getKey();
                    }
                    editor.putInt(selectedProvider, key).apply();
                    Intent providerSelected = new Intent(SearchService.this, UserViewSelected.class);
                    startActivity(providerSelected);
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        preferences = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        editor = preferences.edit();

        listView  = (ListView) findViewById(R.id.dynamic);
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, servicesSearched);
        listView.setAdapter(adapter);

        init();

    }
}
