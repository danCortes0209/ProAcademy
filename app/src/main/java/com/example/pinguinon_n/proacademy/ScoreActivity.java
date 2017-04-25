package com.example.pinguinon_n.proacademy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {

    int score;
    Button goMain;
    TextView scoreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        score = intent.getIntExtra("score",score);
        goMain = (Button)findViewById(R.id.gotScore);
        scoreview = (TextView)findViewById(R.id.tvScoreGot) ;
        scoreview.setText(""+score);

        goMain.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==goMain){
                goBackToMain();
            }
        }
    };

    public void goBackToMain(){
        Intent mainIntent = new Intent(ScoreActivity.this, MainActivity.class);
        ScoreActivity.this.startActivity(mainIntent);
        finish();
        Toast.makeText(getApplicationContext(),"Gracias por usar Pro Academy",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        messageButtonOkCancel("Hey!", "¿Deseas salir de la app?", "Aceptar", "Cancelar");
    }

    public void messageButtonOkCancel(String title, String message, String aceptText, String cancelText) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(title);
        alerta.setMessage(message);
        alerta.setCancelable(false);
        alerta.setPositiveButton(aceptText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface alerta, int id) {
                finish();
            }
        });
        alerta.setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface alerta, int id) {
            }
        });
        alerta.show();
    }
}
