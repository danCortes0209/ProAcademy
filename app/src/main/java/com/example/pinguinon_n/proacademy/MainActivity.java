package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnConf; //Declara las viariables de todos los componentes
    TextView tvPlay, tvConf;
    DatabaseHelper helper = new DatabaseHelper(this);//manda llamar la clase de la base de datos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = (Button)findViewById(R.id.btnStart);
        btnConf = (Button)findViewById(R.id.btnConf);
        tvPlay = (TextView)findViewById(R.id.tvStart);
        tvConf = (TextView)findViewById(R.id.tvConf);
        btnPlay.setOnClickListener(clickListener);
        btnConf.setOnClickListener(clickListener);
        tvPlay.setOnClickListener(clickListener);
        tvConf.setOnClickListener(clickListener);
        helper.openDatabase(); //solo en caso de crearla, se abre la base de datos y se cierra para implementarla dentro del movil
        helper.closeDatabase();


    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if (v == btnPlay || v == tvPlay){
                goLevels();
            } else if (v == btnConf || v == tvConf){
                goConfig();
            }
        }
    };

    public void goConfig(){
        Intent configIntent = new Intent(MainActivity.this,ConfigActivity.class);
        MainActivity.this.startActivity(configIntent);//envia hacia la actividad de config
    }

    public void goLevels(){
        Intent loginIntent = new Intent(MainActivity.this, LevelsActivity.class);
        MainActivity.this.startActivity(loginIntent);//envia hacia la actividad de los niveles
        finish();
    }


}
