package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {


    int lvl;
    int idtext;
    TextView textOfBook, textBook;
    Button goBack, goQuestions;
    String book, textofbook;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textBook = (TextView)findViewById(R.id.bookOfOrigin);
        textOfBook = (TextView)findViewById(R.id.textOfBook);
        goBack = (Button)findViewById(R.id.btnBackMain);
        goQuestions = (Button)findViewById(R.id.btnGoAnsw);

        Intent intent = getIntent();
        lvl = intent.getIntExtra("lvl",lvl);

        getTextContent();
        goQuestions.setOnClickListener(clickListener);
        goBack.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == goBack){
                goMain();
            } else {
                goAnswers();
            }
        }
    };

    public void goMain(){
        Intent mainIntent = new Intent(TextActivity.this,MainActivity.class);
        TextActivity.this.startActivity(mainIntent);
        finish();
    }

    public void goAnswers(){
        Intent answersIntent = new Intent(TextActivity.this,QuestionActivity.class);
        answersIntent.putExtra("idtext",idtext);
        TextActivity.this.startActivity(answersIntent);
        finish();
    }

    public void getTextContent(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] args = new String[]{""+lvl};
        Cursor c = db.rawQuery("SELECT texts.content, books.book, texts.id_text FROM texts,books WHERE books.id_book = texts.nbook AND texts.leveltxt =? ",args);
        if (c.moveToFirst()){
            textOfBook.setText("");
            textBook.setText("");
            do {
                textofbook = c.getString(0);
                textOfBook.setText(textofbook);
                book = c.getString(1);
                textBook.setText(book);
                idtext = c.getInt(2);
            }while (c.moveToNext());
        }
    }

}
