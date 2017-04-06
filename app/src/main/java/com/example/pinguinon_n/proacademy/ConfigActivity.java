package com.example.pinguinon_n.proacademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    int dedications = 0;
    TextView creators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toast.makeText(getApplicationContext(),"Gracias por usar Pro Academy",Toast.LENGTH_LONG).show();
        creators = (TextView)findViewById(R.id.creators);
        creators.setOnClickListener(click);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == creators){
                dedications++;
                if (dedications == 14){
                    Toast.makeText(getApplicationContext(),"Dedicated to VMA, the Woman who's present in every page i read \n~Daniel",Toast.LENGTH_LONG).show();
                    dedications = 0;
                }
            }
        }
    };
}
