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

public class MenuRestaurantAdapter extends ArrayAdapter<Menu> {
    ArrayList <Menu> listMenus;

    public MenuRestaurantAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Menu> listMenus) {
        super(context, resource, listMenus);
        this.listMenus = listMenus;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.menu_cellule_layout,parent,false);

        TextView nameMenu =(TextView) convertView.findViewById(R.id.nomMenu);
        nameMenu.setText(listMenus.get(position).getName());

        TextView priceMenu =(TextView) convertView.findViewById(R.id.prixMenu);
        priceMenu.setText(listMenus.get(position).getPrice());


        return convertView;
    }
}
