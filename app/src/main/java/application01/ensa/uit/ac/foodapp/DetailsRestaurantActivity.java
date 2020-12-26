package application01.ensa.uit.ac.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsRestaurantActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Restaurant restaurant;
    @BindView(R.id.tvresult)
    TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_restaurant);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        Integer position = new Integer(extras.getInt("position"));
        Restaurant restaurant = (Restaurant) extras.get("restaurant");
        this.restaurant = restaurant;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng destination = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        mMap.addMarker(new MarkerOptions().position(destination).title(restaurant.getName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(destination,15f));
    }

    @OnClick(R.id.AppelerButton)
    public void onCallBtnClicked(){
        Uri telNumber = Uri.parse("tel:"+restaurant.getTel());
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(telNumber);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            System.out.println("call"+restaurant.getTel());
            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    99);
        }
        else {
            startActivity(call);
        }

    }
    @OnClick(R.id.QRCodebutton)
    public void onScanBtnClicked(){
        Intent intent = new Intent(DetailsRestaurantActivity.this, ScanActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.MenuButton)
    public void onMenuSBtnClicked(){
        Intent intent = new Intent(DetailsRestaurantActivity.this, MenuRestaurantActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ItineraireButton)
    public void onItineraireBtnClicked(){
        System.out.println("iter");
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+restaurant.getName());
        //Uri gmmIntentUri = Uri.parse("google.navigation:q="+restaurant.getLatitude()+","+restaurant.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}