package com.coderzgonwild.admin.fixify;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.coderzgonwild.admin.fixify.MainActivity.loggedInUser;

public class RatingMenu extends AppCompatActivity {
    private ArrayList<ServiceProvider> toberated;
    private User user1;
    private int ratingGiven;
    private ServiceProvider provider1;
    ListView providerList1;
    ArrayAdapter<ServiceProvider> advp;
    private SharedPreferences preferences;
    private  int key;
    private TextView info;
    private EditText comment;
    private Button submitRating;
    private HashMap<ServiceProvider, Integer> ratedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ratedBy = new HashMap<>();
        preferences  = getSharedPreferences("my_prefs", Activity.MODE_PRIVATE);
        key = preferences.getInt(loggedInUser, -1);
        user1 = (User)MainActivity.accountList.get(key);
        setContentView(R.layout.activity_rating_menu);
        toberated = user1.getProviderBooked();
        providerList1 = (ListView)findViewById(R.id.providerList);
        advp = new ArrayAdapter<ServiceProvider>(this, android.R.layout.simple_list_item_1, toberated);
        providerList1.setAdapter(advp);
        info = (TextView)findViewById(R.id.infobutton);
        providerList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                provider1 = toberated.get(position);
                if(provider1.checkRecord(loggedInUser)){
                    info.setText("You have already rated "+provider1+". Please choose another provider.");


                }
                else{
                    info.setText("Giving a rating for " + provider1);
                }
            }
        });
        final RatingBar stars = (RatingBar) findViewById(R.id.ratingBar);
        comment = (EditText)findViewById(R.id.comment);

        submitRating = (Button) findViewById(R.id.submitRating);
        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(provider1.checkRecord(loggedInUser)){
                    info.setText("You already rated this provider!");
                }
                else{
                    provider1.addToRecord(loggedInUser, comment.toString());
                    provider1.setRating((int) stars.getRating());
                    info.setText("Rated for " + stars.getRating() + " stars!");
                }


            };



        })
        ;}

    /*public class ViewHolder{
        ImageView thumbnail;
        TextView providername;
        Button button;
    }*/
    public void init(){

    }
}
