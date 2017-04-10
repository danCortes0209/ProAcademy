package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyQuestions extends AppCompatActivity {

    ListView myQuesList;
    ArrayList<String> lista;
    ArrayAdapter adapter;
    String book;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_questions);
        myQuesList = (ListView)findViewById(R.id.lvMyQues);
        Intent intent = getIntent();
        book = intent.getStringExtra("libro");
        Toast.makeText(getApplicationContext(),book,Toast.LENGTH_LONG).show();
        lista = helper.getQuestionsBook(book);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        myQuesList.setAdapter(adapter);
    }
}
