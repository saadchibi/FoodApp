package application01.ensa.uit.ac.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Table NAME Categories
    public static final String TABLENAME_CATEGORY = "CATEGORIES";

    // Table columns Categories
    public static final String _ID_CATEGORY = "_id";
    public static final String NAME_CATEGORY = "name";
    public static final String DESC_CATEGORY = "description";

    // Table NAME Restaurants
    public static final String TABLENAME_RESTAURANT= "RESTAURANTS";

    // Table columns Restaurants
    public static final String _ID_RESTAURANT = "_id";
    public static final String NAME_RESTAURANT = "name";
    public static final String STATUS = "status";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String TEL = "tel";
    public static final String _ID_RESTAURANT_CATEGORY = "id_category";

    // Database Information
    static final String DBNAME = "FoodApp.DB";

    // database versioné
    static final int DB_VERSION = 24;

    // Creating table category query
    private static final String CREATE_TABLE_CATEGORY = "create table IF NOT EXISTS " + TABLENAME_CATEGORY + "(" + _ID_CATEGORY
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_CATEGORY + " TEXT NOT NULL, " + DESC_CATEGORY + " TEXT);";

    // Creating table restaurant query
    private static final String CREATE_TABLE_RESTAURANT= "create table " + TABLENAME_RESTAURANT + "(" + _ID_RESTAURANT
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_RESTAURANT+ " TEXT NOT NULL, " + STATUS + " TEXT, "
            +LATITUDE +" Double,"+ LONGITUDE +" Double, "+TEL+" TEXT, "+_ID_RESTAURANT_CATEGORY+" INTEGER);";

    //Inserting values in category table

    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, DB_VERSION);
    }

    public DataBaseHelper(@Nullable Context context, @Nullable String NAME_CATEGORY, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NAME_CATEGORY, factory, version);
    }

    public void insert_category(SQLiteDatabase database, String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.NAME_CATEGORY, name);
        contentValue.put(DataBaseHelper.DESC_CATEGORY, desc);
        database.insert(DataBaseHelper.TABLENAME_CATEGORY, null, contentValue);
    }
    public void insert_restaurant(SQLiteDatabase database,String name, String status, Double latitude, Double longitude, String tel, int id_categogry) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.NAME_RESTAURANT, name);
        contentValue.put(DataBaseHelper.TEL, tel);
        contentValue.put(DataBaseHelper.LATITUDE, latitude);
        contentValue.put(DataBaseHelper.LONGITUDE, longitude);
        contentValue.put(DataBaseHelper.STATUS, status);
        contentValue.put(DataBaseHelper._ID_RESTAURANT_CATEGORY, id_categogry);
        database.insert(DataBaseHelper.TABLENAME_RESTAURANT, null, contentValue);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("OnCreate sqlite");
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_RESTAURANT);
        insert_category(db,"Fast Food","Restaurants pour les sandwichs,..");
        insert_category(db,"Tacos","Restaurants spécialisée en Tacps");
        insert_category(db,"Marocain","Restaurants spécialisée en cuisine marocaine:tajine,rfissa,...");
        insert_category(db,"Asiatique","Restaurants spécialisée en plat asiatique:sushi,wook,...");
        insert_category(db,"Italien","Restaurants spécialisée en plat italien:pizza,pates,...");

        insert_restaurant(db,"Pizza Hut","Ouvert",34.019858459561085,-6.816464115666627,"+212537736388",1);
        insert_restaurant(db,"Dominos Pizza","Ouvert",33.994266839054525 ,-6.848247841267933,"+212537670606",1);
        insert_restaurant(db,"Metros De Pizza","Ouvert",34.00515321137193,-6.848307010680535,"+212537772425",1);
        insert_restaurant(db,"Pizza des Gourmets","Ouvert",34.002001941325446 ,-6.846299636264378,"+212537686396",1);

        insert_restaurant(db,"Speedway fastfood","Ouvert",34.02249315192782,-6.852556985341557,"+212623128849",2);
        insert_restaurant(db,"KFC","Ouvert",33.9953907793383,-6.847564770360051,"+212522555555",2);
        insert_restaurant(db,"Speedway","Ouvert",34.023035631570615 ,-6.850458250657566,"+212537697638",2);
        insert_restaurant(db,"Homies","Ouvert",33.99734264696052 ,-6.850242215149841,"+212537682440",2);

        insert_restaurant(db,"Tacos de Lyon","Ouvert",33.99584165632876 ,-6.846379296401005,"+212537673852",3);
        insert_restaurant(db,"Tacos Mexico","Ouvert",34.00468925997845 ,-6.848181213820624,"+212537774001",3);
        insert_restaurant(db,"Tacos de Paris","Ouvert",34.01692353387769 ,-6.8281776700813985,"+212537700872",3);

        insert_restaurant(db,"Tajine wa Tanjia","Ouvert",34.01685123671826 ,-6.837535382499516,"+212537729797",4);
        insert_restaurant(db,"Dar Naji","Ouvert",34.0249585778422 ,-6.84186693869741,"+212537262528",4);
        insert_restaurant(db,"Le ziryab","Ouvert",34.031207228182346 ,-6.833445410341241,"+212537733636",4);

        insert_restaurant(db,"Eathai","Ouvert",34.00385664359287 ,-6.845489094517419,"+212537770301",5);
        insert_restaurant(db,"Le Mandarin","Ouvert",34.02064367804581 ,-6.83105075490893,"+212537733636",5);
        insert_restaurant(db,"Asiam","Ouvert",33.95554847243322 ,-6.874474633623834,"+212537543636",5);

        insert_restaurant(db,"Luigi","Ouvert",33.96568255290121 ,-6.868707539966632,"++212537542336",6);
        insert_restaurant(db,"Finzi","Ouvert",33.9794153127374 ,-6.831707419258811,"+212537543676",6);
        insert_restaurant(db,"La Picolla","Ouvert",33.98202591383101 ,-6.882846312306826,"+212537683636",6);

        /*dropRestaurants();
        //ContentValues contentValue = new ContentValues();
        //contentValue.put(DataBaseHelper.NAME_RESTAURANT, "Pizza");
        //contentValue.put(DataBaseHelper.DESC_CATEGORY, "Specialisation italienne");
        //db.insert(TABLENAME_RESTAURANT,null,contentValue);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("OnUpdate sqlite");
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME_RESTAURANT);
        onCreate(db);
    }
}
