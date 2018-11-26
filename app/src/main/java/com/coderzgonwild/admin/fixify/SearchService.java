package com.coderzgonwild.admin.fixify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SearchService extends AppCompatActivity {

    public Button searchBtn;
    public Button backBtn;
    public EditText searchField;

    public void init(){
        searchBtn = (Button)findViewById(R.id.searchBtn);
        backBtn = (Button)findViewById(R.id.backBtn);
        searchField = (EditText)findViewById(R.id.searchField);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_service);

        init();
    }
}
