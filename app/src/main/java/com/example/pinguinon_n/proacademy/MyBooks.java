package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyBooks extends AppCompatActivity {

    ListView myBooksList;
    ArrayList<String> lista;
    ArrayAdapter adapter;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        myBooksList = (ListView)findViewById(R.id.lvMyBooksList);
        lista = helper.getTextContent();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        myBooksList.setAdapter(adapter);
        myBooksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                String libro = myBooksList.getItemAtPosition(pos).toString();
                Intent myquesIntent = new Intent(MyBooks.this, MyQuestions.class);
                myquesIntent.putExtra("libro",libro);
                Toast.makeText(getApplicationContext(), libro, Toast.LENGTH_SHORT).show();
                MyBooks.this.startActivity(myquesIntent);
            }
        });
    }




}
