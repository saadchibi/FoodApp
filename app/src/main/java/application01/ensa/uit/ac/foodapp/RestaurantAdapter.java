package application01.ensa.uit.ac.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    ArrayList <Restaurant> listRestaurants;

    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurant> listRestaurants) {
        super(context, resource, listRestaurants);
        this.listRestaurants = listRestaurants;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.restaurant_cellule_layout,parent,false);

        TextView nameRestaurants =(TextView) convertView.findViewById(R.id.restaurantName);
        nameRestaurants.setText(listRestaurants.get(position).getName());

        TextView statusRestaurants =(TextView) convertView.findViewById(R.id.restaurantStatus);
        statusRestaurants.setText(listRestaurants.get(position).getStatus());

        TextView distanceRestaurants =(TextView) convertView.findViewById(R.id.restaurantDistance);
        distanceRestaurants.setText(listRestaurants.get(position).getDistance().toString()+" m");

        return convertView;
    }

}
