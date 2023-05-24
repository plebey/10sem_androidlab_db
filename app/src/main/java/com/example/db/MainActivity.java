package com.example.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button insert, delete, update, find, q1, q2, q3, q4;
    EditText name, director, num, year;
    SQLiteDatabase db;
    ListView listView;
    DbHandler dbHandler;
    int elem_id = -2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        name = findViewById(R.id.edit_name);
        director=findViewById(R.id.edit_director);
        num = findViewById(R.id.edit_num);
        year = findViewById(R.id.edit_year);
        insert = findViewById(R.id.insert);
        delete = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        find = findViewById(R.id.find);
        q1 = findViewById(R.id.button5);
        q2 = findViewById(R.id.button6);
        q3 = findViewById(R.id.button7);
        q4 = findViewById(R.id.button8);
        insert.setOnClickListener(view -> insert());
        delete.setOnClickListener(view->delete(elem_id));
        update.setOnClickListener(view->change(elem_id));
        find.setOnClickListener(view->find());
        q1.setOnClickListener(view->q1());
        q2.setOnClickListener(view->q2());
        q3.setOnClickListener(view->q3());
        q4.setOnClickListener(view->q4());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                elem_id = (int)id+1;
                setInfo(elem_id);
            }
        });


        dbHandler = new DbHandler(this);
        tableCreate();
        ArrayList<HashMap<String, String>> userList = dbHandler.getFilms();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.gridrow,new String[]{"name","director","series_number", "year"},
                new int[]{R.id.list_name, R.id.list_director, R.id.list_num, R.id.list_year});
        listView.setAdapter(adapter);
        //db = getBaseContext().openOrCreateDatabase("USSRcomedy.db", MODE_PRIVATE, null);

        //tableShow();

    }

    private void delete(int key){
        dbHandler.deleteFilm(key);
        updateDB();
    }
    private void setInfo(int key){
        HashMap<String, String> answer = dbHandler.getFilmById(key).get(0);
        name.setText(answer.get("name"));
        director.setText(answer.get("director"));
        num.setText(answer.get("series_number"));
        year.setText(answer.get("year"));

    }

    private void find (){
        String key = String.valueOf(name.getText());
        HashMap<String, String> answer = dbHandler.getFilmByName(key).get(0);
        name.setText(answer.get("name"));
        director.setText(answer.get("director"));
        num.setText(answer.get("series_number"));
        year.setText(answer.get("year"));
    }

    private void q4(){
        ArrayList<HashMap<String, String>> userList = dbHandler.q4();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.gridrow,new String[]{"name","director","series_number", "year"},
                new int[]{R.id.list_name, R.id.list_director, R.id.list_num, R.id.list_year});
        listView.setAdapter(adapter);
    }

    private void q3(){
        ArrayList<HashMap<String, String>> userList = dbHandler.q3();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.gridrow,new String[]{"name","director","series_number", "year"},
                new int[]{R.id.list_name, R.id.list_director, R.id.list_num, R.id.list_year});
        listView.setAdapter(adapter);
    }
    private void q2(){
        ArrayList<HashMap<String, String>> userList = dbHandler.q2();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.gridrow,new String[]{"name","director","series_number", "year"},
                new int[]{R.id.list_name, R.id.list_director, R.id.list_num, R.id.list_year});
        listView.setAdapter(adapter);
    }
    private void q1(){
        ArrayList<HashMap<String, String>> userList = dbHandler.q1();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.gridrow,new String[]{"name","director","series_number", "year"},
                new int[]{R.id.list_name, R.id.list_director, R.id.list_num, R.id.list_year});
        listView.setAdapter(adapter);
    }

    private void change(int key){
        String n = String.valueOf(name.getText());
        String d = String.valueOf(director.getText());
        int nu = Integer.parseInt(String.valueOf(num.getText()));
        int y = Integer.parseInt(String.valueOf(year.getText()));
        dbHandler.UpdateUserDetails(n,d,nu,y,key);
        updateDB();
    }
    private void insert(){
        String n = name.getText().toString();
        String d = director.getText().toString();
        String nu = num.getText().toString();
        String y = year.getText().toString();
        try {
            int i_nu = Integer.parseInt(nu);
            int i_y = Integer.parseInt(y);
            dbHandler.insertDetails(n, d, i_nu, i_y);
        }catch (NumberFormatException e){

        }
        updateDB();
    }
    private void tableCreate(){
        dbHandler.refrashDB();
        dbHandler.insertDetails("Двенадцать стульев","Гайдай", 2, 1971);
        dbHandler.insertDetails("Осенний марафон","Данелия", 1, 1979);
        dbHandler.insertDetails("Служебный роман","Рязанов", 2, 1977);
        dbHandler.insertDetails("Карнавал","Лиознова", 2, 1981);
        dbHandler.insertDetails("Мимино","Данелия", 1, 1977);
        dbHandler.insertDetails("Гараж","Рязанов", 1, 1979);
        dbHandler.insertDetails("Кавказская пленница","Гайдай", 1, 1966);
        dbHandler.insertDetails("Суета сует","Сурикова", 1, 1978);

    }

    private void updateDB(){
        ArrayList<HashMap<String, String>> userList = dbHandler.getFilms();
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.gridrow,new String[]{"name","director","series_number", "year"},
                new int[]{R.id.list_name, R.id.list_director, R.id.list_num, R.id.list_year});
        listView.setAdapter(adapter);
    }

}
