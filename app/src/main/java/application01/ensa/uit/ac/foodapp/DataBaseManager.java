package application01.ensa.uit.ac.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DataBaseManager(Context c) {
        context = c;
    }

    public DataBaseManager() {

    }

    public DataBaseManager open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert_category(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.NAME_CATEGORY, name);
        contentValue.put(DataBaseHelper.DESC_CATEGORY, desc);
        database.insert(DataBaseHelper.TABLENAME_CATEGORY, null, contentValue);
    }

    public void insert_restaurant(String name, String status, Double latitude, Double longitude, String tel, int id_categogry) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.NAME_RESTAURANT, name);
        contentValue.put(DataBaseHelper.TEL, tel);
        contentValue.put(DataBaseHelper.LATITUDE, latitude);
        contentValue.put(DataBaseHelper.LONGITUDE, longitude);
        contentValue.put(DataBaseHelper.STATUS, status);
        contentValue.put(DataBaseHelper._ID_RESTAURANT_CATEGORY, id_categogry);
        database.insert(DataBaseHelper.TABLENAME_RESTAURANT, null, contentValue);
    }

    public Cursor fetchCategories() {
        String[] columns = new String[] { DataBaseHelper._ID_CATEGORY, DataBaseHelper.NAME_CATEGORY, DataBaseHelper.DESC_CATEGORY};
        Cursor cursor = database.query(DataBaseHelper.TABLENAME_CATEGORY, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchRestaurants(int position) {
        String[] columns = new String[] { DataBaseHelper.NAME_RESTAURANT, DataBaseHelper.STATUS, DataBaseHelper.LONGITUDE, DataBaseHelper.LATITUDE, DataBaseHelper.TEL};
        Cursor cursor = null;
        switch (position){
            case 0:{
                cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "id_category=?", new String[]{"1"}, null, null, null);
                break;
            }
            case 1:{
                cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "id_category=?", new String[]{"2"}, null, null, null);
                break;
            }
            case 2:{
                cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "id_category=?", new String[]{"3"}, null, null, null);
                break;
            }
            case 3:{
                cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "id_category=?", new String[]{"4"}, null, null, null);
                break;
            }
            case 4:{
                cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "id_category=?", new String[]{"5"}, null, null, null);
                break;
            }
            case 5:{
                cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "id_category=?", new String[]{"6"}, null, null, null);
                break;
            }
        }
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchQuery(String query){
        String[] columns = new String[] { DataBaseHelper.NAME_RESTAURANT, DataBaseHelper.STATUS, DataBaseHelper.LONGITUDE, DataBaseHelper.LATITUDE, DataBaseHelper.TEL};
        Cursor cursor = null;
        cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "name=?", new String[]{query}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchQueryContains(String query){
        String[] columns = new String[] { DataBaseHelper.NAME_RESTAURANT, DataBaseHelper.STATUS, DataBaseHelper.LONGITUDE, DataBaseHelper.LATITUDE, DataBaseHelper.TEL};
        Cursor cursor = null;
        cursor = database.query(DataBaseHelper.TABLENAME_RESTAURANT, columns, "name LIKE ?", new String[]{"%"+query+"%"}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.NAME_CATEGORY, name);
        contentValues.put(DataBaseHelper.DESC_CATEGORY, desc);
        int i = database.update(DataBaseHelper.TABLENAME_CATEGORY, contentValues, DataBaseHelper._ID_CATEGORY + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DataBaseHelper.TABLENAME_CATEGORY, DataBaseHelper._ID_CATEGORY + "=" + _id, null);
    }
    public void dropRestaurants(){
        database.delete(DataBaseHelper.TABLENAME_RESTAURANT,null,null);
    }
    public void dropCategories(){
        database.delete(DataBaseHelper.TABLENAME_CATEGORY,null,null);
    }
}
