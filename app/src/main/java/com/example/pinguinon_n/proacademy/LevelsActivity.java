package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelsActivity extends AppCompatActivity {

    int id;
    int lvl;
    String nameuser;
    Button btnlvl1;
    Button btnlvl2;
    Button btnlvl3;
    Button btnlvl4;
    Button btnlvl5;
    Button btnlvl6;
    Button btnlvl7;
    Button btnlvl8;
    Button btnlvl9;
    Button btnlvl10;
    Button btnlvl11;
    Button btnlvl12;
    Button btnlvl13;
    Button btnlvl14;
    Button btnlvl15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        btnlvl1 = (Button)findViewById(R.id.btnLvl1);
        btnlvl2 = (Button)findViewById(R.id.btnLvl2);
        btnlvl3 = (Button)findViewById(R.id.btnLvl3);
        btnlvl4 = (Button)findViewById(R.id.btnLvl4);
        btnlvl5 = (Button)findViewById(R.id.btnLvl5);
        btnlvl6 = (Button)findViewById(R.id.btnLvl6);
        btnlvl7 = (Button)findViewById(R.id.btnLvl7);
        btnlvl8 = (Button)findViewById(R.id.btnLvl8);
        btnlvl9 = (Button)findViewById(R.id.btnLvl9);
        btnlvl10 = (Button)findViewById(R.id.btnLvl10);
        btnlvl11 = (Button)findViewById(R.id.btnLvl11);
        btnlvl12 = (Button)findViewById(R.id.btnLvl12);
        btnlvl13 = (Button)findViewById(R.id.btnLvl13);
        btnlvl14 = (Button)findViewById(R.id.btnLvl14);
        btnlvl15 = (Button)findViewById(R.id.btnLvl15);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
        nameuser = intent.getStringExtra("nameuser");
        btnlvl1.setOnClickListener(click);
        btnlvl2.setOnClickListener(click);
        btnlvl3.setOnClickListener(click);
        btnlvl4.setOnClickListener(click);
        btnlvl5.setOnClickListener(click);
        btnlvl6.setOnClickListener(click);
        btnlvl7.setOnClickListener(click);
        btnlvl8.setOnClickListener(click);
        btnlvl9.setOnClickListener(click);
        btnlvl10.setOnClickListener(click);
        btnlvl11.setOnClickListener(click);
        btnlvl12.setOnClickListener(click);
        btnlvl13.setOnClickListener(click);
        btnlvl14.setOnClickListener(click);
        btnlvl15.setOnClickListener(click);
    }

    public View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnlvl1){
                lvl = 1;
                goText();
            } else if (v == btnlvl2){
                lvl = 2;
                goText();
            } else if (v == btnlvl3){
                lvl = 3;
                goText();
            } else if (v == btnlvl4){
                lvl = 4;
                goText();
            } else if (v == btnlvl5){
                lvl = 5;
                goText();
            } else if (v == btnlvl6){
                lvl = 6;
                goText();
            } else if (v == btnlvl7){
                lvl = 7;
                goText();
            } else if (v == btnlvl8){
                lvl = 8;
                goText();
            } else if (v == btnlvl9){
                lvl = 9;
                goText();
            } else if (v == btnlvl10){
                lvl = 10;
                goText();
            } else if (v == btnlvl11){
                lvl = 11;
                goText();
            } else if (v == btnlvl12){
                lvl = 12;
                goText();
            } else if (v == btnlvl13){
                lvl = 13;
                goText();
            } else if (v == btnlvl14){
                lvl = 14;
                goText();
            } else if (v == btnlvl15){
                lvl = 15;
                goText();
            }
        }
    };

    public void goText(){
        Intent textIntent = new Intent(LevelsActivity.this,TextActivity.class);
        textIntent.putExtra("lvl",lvl);
        LevelsActivity.this.startActivity(textIntent);
        finish();
    }
}
