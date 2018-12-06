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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.lang.Math;

import static com.coderzgonwild.admin.fixify.MainActivity.accountList;
import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;
import static com.coderzgonwild.admin.fixify.MainActivity.selectedProvider;
import static com.coderzgonwild.admin.fixify.MainActivity.serviceProviderList;

public class SearchService extends AppCompatActivity {

    public Button backBtn;
    public SearchView searchField;

    //Search options
    public RadioGroup searchOptions;
    public RadioButton searchServiceOption;
    public RadioButton searchTimeOption;
    public RadioButton searchRatingOption;

    public RadioButton selectedFilter;

    public int filterInt; //Value of filter
    public String filter = null; //Search query filter

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private  int key;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> servicesSearched = new ArrayList<>();

    private ListView listView2;
    private ArrayAdapter adapter2;

    public void init(){
        //Associating variables with widgets
        backBtn = (Button)findViewById(R.id.backBtn);
        searchField = (SearchView)findViewById(R.id.searchField);

        searchOptions = (RadioGroup)findViewById(R.id.searchOptions);
        searchServiceOption = (RadioButton)findViewById(R.id.searchServiceOption);
        searchTimeOption = (RadioButton)findViewById(R.id.searchTimeOption);
        searchRatingOption = (RadioButton)findViewById(R.id.searchRatingOption);

        servicesSearched.clear();


        //Search field functions
        searchField.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Account account;
                        ServiceProvider serviceProvider;
                        if (searchOptions.getCheckedRadioButtonId() == -1) { //Search service provider name by default
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
                        } else if (searchOptions.getCheckedRadioButtonId() != -1) {
                            filterInt = searchOptions.getCheckedRadioButtonId();
                            selectedFilter = (RadioButton) findViewById(filterInt);
                            filter = selectedFilter.getText().toString();

                            if (filter.equals("Service")) {
                                for (int i = 0; i < serviceProviderList.size(); i++) {
                                    serviceProvider = serviceProviderList.get(i);
                                    for (int j = 0; j < serviceProvider.getServicesProvided().size(); j++) {
                                        Service serviceQuery = serviceProvider.getServicesProvided().get(j);
                                        String serviceName = serviceQuery.getName();
                                        if (serviceName.contains(query)) {
                                            servicesSearched.add(serviceProvider.getUsername());
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                }
                                return false;
                            } else if (filter.equals("Timeslot")) {
                                for (int i = 0; i < serviceProviderList.size(); i++) {
                                    serviceProvider = serviceProviderList.get(i);
                                    for (int j = 0; j < serviceProvider.getServicesAvail().size(); j++) {
                                        String timeQuery = serviceProvider.getServicesAvail().get(j);
                                        if (timeQuery.contains(query)) {
                                            servicesSearched.add(serviceProvider.getUsername());
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                }
                            }
                            else if (filter.equals("Rating")) {
                                for (int i = 0; i < serviceProviderList.size(); i++) {
                                    serviceProvider = serviceProviderList.get(i);
                                    int ratingQuery = (int)serviceProvider.getProviderRating();
                                    String ratingQueryString = Integer.toString(ratingQuery);
                                        if (ratingQueryString.contains(query)) {
                                            servicesSearched.add(serviceProvider.getUsername());
                                        }
                                        adapter.notifyDataSetChanged();


                                }
                            }
                        }
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
