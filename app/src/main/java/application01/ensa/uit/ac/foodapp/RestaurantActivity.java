package application01.ensa.uit.ac.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.app.SearchManager;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class RestaurantActivity extends AppCompatActivity {
    @BindView(R.id.listRestaurants)
    ListView listRestaurantsView;
    private DataBaseManager dataBaseManager;
    Location myLocation;
    ArrayList<Restaurant> listRestaurants = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        Integer position = new Integer(extras.getInt("position"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    99);
        } else {
            // permission has been granted, continue as usual
            dataBaseManager = new DataBaseManager(this);
            dataBaseManager.open();
            Cursor cursor = dataBaseManager.fetchRestaurants(position);
            myLocation = getLocation();
            while(!cursor.isAfterLast()) {
                //Calcul de distance
                Location destination = new Location("destination");
                destination.setLatitude(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.LATITUDE)));
                destination.setLongitude(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.LONGITUDE)));
                float distance = myLocation.distanceTo(destination);
                //float distance = 2;
                listRestaurants.add(new Restaurant(cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAME_RESTAURANT)),cursor.getString(cursor.getColumnIndex(DataBaseHelper.STATUS)),cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.LATITUDE)),cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.LONGITUDE)),distance,cursor.getString(cursor.getColumnIndex(DataBaseHelper.TEL)))); //add the item
                cursor.moveToNext();
            }
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION },
                    99);
        } else {
            // permission has been granted, continue as usual
        }

        RestaurantAdapter adapter = new RestaurantAdapter(getApplicationContext(),R.layout.restaurant_cellule_layout,listRestaurants);

        listRestaurantsView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:{
                //If results is cancelled the result array are empty
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_DENIED){
                    //myLocation = getLocation();
                }
                else {
                }
                return ;
            }
        }
    }

    public Location getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            LocationManager managerLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = String.valueOf(managerLoc.getBestProvider(criteria, true)).toString();
            Location location = managerLoc.getLastKnownLocation(bestProvider);
            if (location != null) {
                Log.e("TAG", "GPS is on");
                return location;
            }
            else{
                //This is what you need:
                managerLoc.requestLocationUpdates(bestProvider, 1000, 0, (LocationListener) this);
            }
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        //Action Listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // collapse the view ?
                //menu.findItem(R.id.menu_search).collapseActionView();
                Log.e("queryText",query);
                if(!query.isEmpty()){
                    ArrayList<Restaurant> listRestaurantsQuery = new ArrayList<Restaurant>();
                    Cursor cursorQuery = dataBaseManager.fetchQuery(query);
                    if(cursorQuery != null){
                        Location destination = new Location("destination");
                        destination.setLatitude(cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LATITUDE)));
                        destination.setLongitude(cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LONGITUDE)));
                        float distance = myLocation.distanceTo(destination);
                        listRestaurantsQuery.add(new Restaurant(cursorQuery.getString(cursorQuery.getColumnIndex(DataBaseHelper.NAME_RESTAURANT)),cursorQuery.getString(cursorQuery.getColumnIndex(DataBaseHelper.STATUS)),cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LATITUDE)),cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LONGITUDE)),distance,cursorQuery.getString(cursorQuery.getColumnIndex(DataBaseHelper.TEL))));
                        RestaurantAdapter adapter = new RestaurantAdapter(getApplicationContext(),R.layout.restaurant_cellule_layout,listRestaurantsQuery);
                        listRestaurantsView.setAdapter(adapter);
                    }
                }
                else {
                    RestaurantAdapter adapter = new RestaurantAdapter(getApplicationContext(),R.layout.restaurant_cellule_layout,listRestaurants);

                    listRestaurantsView.setAdapter(adapter);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // search goes here !!
                // listAdapter.getFilter().filter(query);
                Log.e("queryText",newText);
                ArrayList<Restaurant> listRestaurantsQuery = new ArrayList<Restaurant>();
                Cursor cursorQuery = dataBaseManager.fetchQueryContains(newText);
                if(cursorQuery != null && cursorQuery.moveToFirst()){
                    Log.e("test",newText);
                    while(!cursorQuery.isAfterLast()) {
                        //Calcul de distance
                        Location destination = new Location("destination");
                        destination.setLatitude(cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LATITUDE)));
                        destination.setLongitude(cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LONGITUDE)));
                        float distance = myLocation.distanceTo(destination);
                        listRestaurantsQuery.add(new Restaurant(cursorQuery.getString(cursorQuery.getColumnIndex(DataBaseHelper.NAME_RESTAURANT)),cursorQuery.getString(cursorQuery.getColumnIndex(DataBaseHelper.STATUS)),cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LATITUDE)),cursorQuery.getDouble(cursorQuery.getColumnIndex(DataBaseHelper.LONGITUDE)),distance,cursorQuery.getString(cursorQuery.getColumnIndex(DataBaseHelper.TEL))));
                        cursorQuery.moveToNext();
                    }
                    RestaurantAdapter adapter = new RestaurantAdapter(getApplicationContext(),R.layout.restaurant_cellule_layout,listRestaurantsQuery);
                    listRestaurantsView.setAdapter(adapter);
                }
                else {
                    Log.e("Noresult for ",newText);
                }
                return true;
            }
        });

        return true;
    }

    @OnItemClick(R.id.listRestaurants)
    public void onItemClick(View view, int position){
        //Start Restaurant activity
        Intent intent=new Intent(RestaurantActivity.this, DetailsRestaurantActivity.class);
        intent.putExtra("position",position);
        intent.putExtra("restaurant",listRestaurants.get(position));
        startActivity(intent);
    }

}