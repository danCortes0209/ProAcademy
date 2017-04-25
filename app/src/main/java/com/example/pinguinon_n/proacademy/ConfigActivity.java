package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    int dedications = 0;
    TextView creators;
    Button buscLibs,myLibs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toast.makeText(getApplicationContext(), "Gracias por usar Pro Academy", Toast.LENGTH_SHORT).show();
        creators = (TextView)findViewById(R.id.creators);
        buscLibs = (Button)findViewById(R.id.bscLibs);
        myLibs = (Button)findViewById(R.id.myLibs);
        creators.setOnClickListener(click);//manda un toast con el mensaje gracias por usar pro academy
        buscLibs.setOnClickListener(click);
        myLibs.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == creators){
                dedications++;
                if (dedications == 14){
                    Toast.makeText(getApplicationContext()
                            ,"Dedicated to VMA, the Woman who's present in every page i read \n~Daniel",
                            Toast.LENGTH_SHORT).show();
                    dedications = 0;
                }
            } else if (v == buscLibs){
                Intent listLibs = new Intent(ConfigActivity.this,BuslibsActivity.class);
                ConfigActivity.this.startActivity(listLibs);
            } else if (v == myLibs){
                Intent listLibs = new Intent(ConfigActivity.this,MyBooks.class);
                ConfigActivity.this.startActivity(listLibs);
            }
        }
    };
}
