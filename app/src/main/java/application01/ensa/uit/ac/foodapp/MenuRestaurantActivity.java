package application01.ensa.uit.ac.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuRestaurantActivity extends AppCompatActivity {
    @BindView(R.id.menuRestaurant)
    ListView listMenuView;

    ArrayList<Menu> listMenus = new ArrayList<Menu>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);
        ButterKnife.bind(this);

        listMenus.add(new Menu("menu1","10e"));
        listMenus.add(new Menu("menu2","12e"));
        listMenus.add(new Menu("menu3","14e"));


        MenuRestaurantAdapter adapter = new MenuRestaurantAdapter(getApplicationContext(),R.layout.menu_cellule_layout,listMenus);

        listMenuView.setAdapter(adapter);
    }
}