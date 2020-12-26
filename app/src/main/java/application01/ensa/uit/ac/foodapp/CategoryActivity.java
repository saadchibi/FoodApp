package application01.ensa.uit.ac.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class CategoryActivity extends AppCompatActivity {
    @BindView(R.id.category_list)
    ListView categoryList;
    private DataBaseManager dataBaseManager;
    private int idCategory;

    ArrayList<String> categoryArray = new ArrayList<String>();

    @OnItemClick(R.id.category_list)
    public void onItemClick(View view, int position){
        //Start Restaurant activity
        Intent intent=new Intent(CategoryActivity.this, RestaurantActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.open();
        //dataBaseManager.dropCategories();
        /*dataBaseManager.insert_category("Pizza","Restaurants spécialisée en pizza");
        dataBaseManager.insert_category("Fast Food","Restaurants pour les sandwichs,..");
        dataBaseManager.insert_category("Tacos","Restaurants spécialisée en Tacps");
        dataBaseManager.insert_category("Marocain","Restaurants spécialisée en cuisine marocaine:tajine,rfissa,...");
        dataBaseManager.insert_category("Asiatique","Restaurants spécialisée en plat asiatique:sushi,wook,...");
        dataBaseManager.insert_category("Italien","Restaurants spécialisée en plat italien:pizza,pates,...");*/

        Cursor cursor = dataBaseManager.fetchCategories();
        while(!cursor.isAfterLast()) {
            categoryArray.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAME_CATEGORY))); //add the item
            cursor.moveToNext();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.cellule_layout,categoryArray);

        categoryList.setAdapter(adapter);
        //dataBaseManager.close();
    }
}