package com.example.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "USSRcomedy";
    private static final String TABLE_films = "USSRcomedy";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DIR = "director";
    private static final String KEY_NUM = "series_number";
    private static final String KEY_YEAR = "year";
    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_films + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " varchar(150),"
                + KEY_DIR + " varchar(50),"
                + KEY_NUM + " INTEGER,"
                + KEY_YEAR + " INTEGER"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_films);
        // Create tables again
        onCreate(db);
    }
    public void refrashDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_films);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    void insertDetails(String name, String director, int number, int year){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_DIR, director);
        cValues.put(KEY_NUM, number);
        cValues.put(KEY_YEAR, year);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_films,null, cValues);
        db.close();
    }
    // Get Details
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getFilms(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            userList.add(film);
        }
        return  userList;
    }
    // Get Details based on id
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getFilmById(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> filmList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films;
        Cursor cursor = db.query(TABLE_films, new String[]{KEY_NAME, KEY_DIR, KEY_NUM, KEY_YEAR}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            filmList.add(film);
        }
        return  filmList;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> q4(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films+" order by name asc";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            userList.add(film);
        }
        return  userList;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> q3(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films+" where series_number>1 and year < 1978";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            userList.add(film);
        }
        return  userList;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> q2(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films+" order by director asc";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            userList.add(film);
        }
        return  userList;
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> q1(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films+" order by year asc";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            userList.add(film);
        }
        return  userList;
    }
    // Get Details based on name
    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getFilmByName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> filmList = new ArrayList<>();
        String query = "SELECT name, director, series_number, year FROM "+ TABLE_films;
        Cursor cursor = db.query(TABLE_films, new String[]{KEY_NAME, KEY_DIR, KEY_NUM, KEY_YEAR}, KEY_NAME+ "=?",new String[]{String.valueOf(username)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> film = new HashMap<>();
            film.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            film.put("series_number",cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            film.put("director",cursor.getString(cursor.getColumnIndex(KEY_DIR)));
            film.put("year",cursor.getString(cursor.getColumnIndex(KEY_YEAR)));
            filmList.add(film);
        }
        return  filmList;
    }
    // Delete
    public void deleteFilm(int filmid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_films, KEY_ID+" = ?",new String[]{String.valueOf(filmid)});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String name, String director,  int number, int year, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_DIR, director);
        cValues.put(KEY_NUM, number);
        cValues.put(KEY_YEAR, year);
        int count = db.update(TABLE_films, cValues, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}
