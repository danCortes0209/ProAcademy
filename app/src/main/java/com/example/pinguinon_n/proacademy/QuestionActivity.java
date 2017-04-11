package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {
    Random ran = new Random();
    String question, answer1,answer2,answer3, answer4;
    int idtext, idquestion, score;
    TextView tvQuestion, ans1, ans2, ans3, ans4;
    EditText edAnswer;
    Button sendAnswer;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        idtext = intent.getIntExtra("idtext",idtext);
        tvQuestion = (TextView)findViewById(R.id.question1);
        sendAnswer = (Button)findViewById(R.id.btnSendAnsw);
        ans1 = (TextView)findViewById(R.id.ans1);
        ans2 = (TextView)findViewById(R.id.ans2);
        ans3 = (TextView)findViewById(R.id.ans3);
        ans4 = (TextView)findViewById(R.id.ans4);
        edAnswer = (EditText)findViewById(R.id.answerEd);
        getTextContent();

        sendAnswer.setOnClickListener(clickListener);
    }

    public void getTextContent(){
        int randQuery = ran.nextInt(4);
        String query = "";
        switch (randQuery) {
            case 1:
                query = "SELECT * FROM question WHERE ntext= ? LIMIT 1";
                break;
            case 2:
                query = "SELECT * FROM question WHERE ntext = ? LIMIT 1,1";
                break;
            case 3:
                query = "SELECT * FROM question WHERE ntext = ? LIMIT 2,1";
                break;
            case 4:
                query = "SELECT * FROM question WHERE ntext = ? LIMIT 3,1";
                break;
            default:
                query = "SELECT * FROM question WHERE ntext= ? LIMIT 1";
                break;
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] args = new String[]{""+idtext};
        Cursor c = db.rawQuery(query, args);
        if (c.moveToFirst()){
            do {
                idquestion = c.getInt(0);
                question = c.getString(1);
                tvQuestion.setText(question);
                answer1 = c.getString(3);
                answer1 = "a)" + answer1;
                ans1.setText(answer1);
                answer2 = c.getString(4);
                answer2 = "b)" + answer2;
                ans2.setText(answer2);
                answer3 = c.getString(5);
                answer3 = "c)" + answer3;
                ans3.setText(answer3);
                answer4 = c.getString(6);
                answer4 = "d)" + answer4;
                ans4.setText(answer4);
            }while (c.moveToNext());
        }
    }

    public void sendAns(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String respuesta = edAnswer.getText().toString();
        if (respuesta.equals("a")||respuesta.equals("A")){
            score = 100;
        } else if (respuesta.equals("b")||respuesta.equals("B")){
            score= 70;
        } else if (respuesta.equals("c")||respuesta.equals("C")){
            score = 50;
        } else if (respuesta.equals("d")||respuesta.equals("D")){
            score = 20;
        }
        db.execSQL("INSERT INTO answergot (nquestion,score) VALUES('"+idquestion+"','"+score+"')");
        goScoreActivity();
    }

    public void goScoreActivity(){
        Intent scoreInten = new Intent(QuestionActivity.this,ScoreActivity.class);
        scoreInten.putExtra("score",score);
        QuestionActivity.this.startActivity(scoreInten);
        finish();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==sendAnswer){
                sendAns();
            }
        }
    };
}
