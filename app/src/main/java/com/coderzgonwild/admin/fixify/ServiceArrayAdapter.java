package com.coderzgonwild.admin.fixify;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderzgonwild.admin.fixify.Service;

import java.util.ArrayList;


public class ServiceArrayAdapter extends ArrayAdapter<Service>  {

    private final Context context;
    private final ArrayList<Service> services2;


    public ServiceArrayAdapter(Context context, ArrayList<Service> values) {
        super(context, R.layout.service_item_layout, values);
        this.context = context;
        this.services2 = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Getting Recipe
        Service ser = services2.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.service_item_layout, parent, false);
        TextView recipeName = (TextView) rowView.findViewById(R.id.serviceName);
        TextView recipeDescription = (TextView) rowView.findViewById(R.id.serviceRate);
        ImageView recipeImage = (ImageView) rowView.findViewById(R.id.icon);

        //Placing content into recipe List Item
        recipeName.setText(ser.getName());
        String stringdouble = "$"+Double.toString(ser.getRate());
        recipeDescription.setText(stringdouble);



        return rowView;
    }
}


