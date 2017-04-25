package com.example.pinguinon_n.proacademy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {


    int lvl;
    int idtext;
    TextView textOfBook, textBook, autor;
    Button goBack, goQuestions, addQuestion;
    String book, textofbook, author;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        textBook = (TextView)findViewById(R.id.bookOfOrigin);
        textOfBook = (TextView)findViewById(R.id.textOfBook);
        autor = (TextView)findViewById(R.id.author);
        goBack = (Button)findViewById(R.id.btnBackMain);
        goQuestions = (Button)findViewById(R.id.btnGoAnsw);
        addQuestion = (Button) findViewById(R.id.btnAddQuestion);

        Intent intent = getIntent();
        lvl = intent.getIntExtra("lvl",lvl);

        getTextContent();
        goQuestions.setOnClickListener(clickListener);
        goBack.setOnClickListener(clickListener);
        addQuestion.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == goBack){
                goMain();
            } else if (v == goQuestions) {
                goAnswers();
            } else if (v == addQuestion) {
                goAddQuestion();
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

    public void goAddQuestion() {
        Intent addQuestionIntent = new Intent(TextActivity.this, SuggestQuestionsActivity.class);
        addQuestionIntent.putExtra("ntext", idtext);
        TextActivity.this.startActivity(addQuestionIntent);
    }

    public void getTextContent(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] args = new String[]{""+lvl};
        Cursor c = db.rawQuery("SELECT texts.content, books.book, texts.id_text, books.author FROM texts,books WHERE books.id_book = texts.nbook AND texts.leveltxt =? ",args);
        if (c.moveToFirst()){
            textOfBook.setText("");
            textBook.setText("");
            do {
                textofbook = c.getString(0);
                textOfBook.setText(textofbook);
                book = c.getString(1);
                textBook.setText(book);
                idtext = c.getInt(2);
                author = c.getString(3);
                autor.setText(author);
            }while (c.moveToNext());
        }
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
