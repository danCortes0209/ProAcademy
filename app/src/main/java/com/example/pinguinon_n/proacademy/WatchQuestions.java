package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WatchQuestions extends AppCompatActivity {
    String question;
    ListView myAnsList;
    ArrayAdapter adapter;
    DatabaseHelper helper = new DatabaseHelper(this);
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_questions);
        myAnsList = (ListView) findViewById(R.id.lvMyAns);
        Intent intent = getIntent();
        question = intent.getStringExtra("question");
        lista = helper.getAnswers(question);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        myAnsList.setAdapter(adapter);
    }
}
