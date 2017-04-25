package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SuggestQuestionsActivity extends AppCompatActivity {

    EditText edQuestion, edAns1, edAns2, edAns3, edAns4;
    Button btnSendData;
    int ntext;
    String question, ans1, ans2, ans3, ans4;
    String putQuestionsURL = "http://130.100.4.161/siteacademy/api/insertQuestions.php";
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_questions);
        edQuestion = (EditText) findViewById(R.id.edSendQues);
        edAns1 = (EditText) findViewById(R.id.edSendAns1);
        edAns2 = (EditText) findViewById(R.id.edSendAns2);
        edAns3 = (EditText) findViewById(R.id.edSendAns3);
        edAns4 = (EditText) findViewById(R.id.edSendAns4);
        Intent intent = getIntent();
        ntext = intent.getIntExtra("ntext", ntext);
        btnSendData = (Button) findViewById(R.id.btnSendQues);
        btnSendData.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnSendData) {
                getNewQues();
            }
        }
    };

    private void getNewQues() {
        try {
            Uri.Builder builder = new Uri.Builder();
            question = edQuestion.getText().toString();
            ans1 = edAns1.getText().toString();
            ans2 = edAns2.getText().toString();
            ans3 = edAns3.getText().toString();
            ans4 = edAns4.getText().toString();
            builder.appendQueryParameter("ntext", "" + ntext);
            builder.appendQueryParameter("question", question);
            builder.appendQueryParameter("ans1", ans1);
            builder.appendQueryParameter("ans2", ans2);
            builder.appendQueryParameter("ans3", ans3);
            builder.appendQueryParameter("ans4", ans4);
            String queryParams = builder.build().getEncodedQuery();
            Log.i("Consulta ", queryParams);
            performPostCall(putQuestionsURL, queryParams);
        } catch (Exception e) {
            Log.e("Error", "" + e);
            Uri.Builder builder = new Uri.Builder();
            question = edQuestion.getText().toString();
            ans1 = edAns1.getText().toString();
            ans2 = edAns2.getText().toString();
            ans3 = edAns3.getText().toString();
            ans4 = edAns4.getText().toString();
            builder.appendQueryParameter("ntext", "" + ntext);
            builder.appendQueryParameter("question", question);
            builder.appendQueryParameter("ans1", ans1);
            builder.appendQueryParameter("ans2", ans2);
            builder.appendQueryParameter("ans3", ans3);
            builder.appendQueryParameter("ans4", ans4);
            String queryParams = builder.build().getEncodedQuery();
            Log.i("Consulta ", queryParams);
            performPostCall(putQuestionsURL, queryParams);

        }

    }

    private void performPostCall(String requestURL, String query) {
        URL url;
        String webServiceResult = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.e("Done", "obteniendo conexion url--------------------------------------------");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            Log.e("Done", "enviando datos--------------------------------------------");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            Log.e("Done", "obteniendo stream de salida--------------------------------------------");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            Log.e("Done", "obteniendo escritor--------------------------------------------");
            writer.write(query);
            writer.flush();
            writer.close();
            Log.e("Done", "escritor enviado--------------------------------------------");
            os.close();
            Log.e("Done", "cerrado el stream de salida--------------------------------------------");
            int responseCode = conn.getResponseCode();
            Log.e("Done", "obteniendo el codigo de respuesta--------------------------------------------" + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                Log.e("Done", "obteniendo el lector--------------------------------------------");
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    Log.e("Done", "leyendo y añadiendo una linea--------------------------------------------");
                    webServiceResult += line;
                    Log.e("Done", "Resultado:---------------------------------" + webServiceResult);
                }
                bufferedReader.close();
                Log.e("Done", "se cierra un lector--------------------------------------------");
            } else {
                webServiceResult = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SearchActivity", "error en el procedimiento----------------------------------------------------" + e);
        }
        if (webServiceResult != null) {
            Log.e("Done", "se parcea la informacion--------------------------------------------");
            parseInformation(webServiceResult);
        } else {
            Message("Search", "Books not Found");
        }
    }

    private void Message(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    private void parseInformation(String jsonResult) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.e("Done", "Resultado:---------------------------------" + jsonResult);
        JSONArray jsonArray = null;
        boolean squestion;
        try {
            jsonArray = new JSONArray(jsonResult);
            Log.e("Done", "se parcea la informacion y se ingresa a un array--------------------------------------------");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Done", "se intenta recorrer el array--------------------------------------------");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Log.e("Done", "obteniendo datos--------------------------------------------");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                squestion = jsonObject.getBoolean("success");
                if (squestion) {
                    Log.e("Done", "se añaden Datos al Servidor--------------------------------------------");
                    Message("Action", "Preguntas añadidas correctamente!");
                    db.execSQL("INSERT INTO question (content,ntext,ans1,ans2,ans3,ans4) VALUES('" + question + "','" + ntext + "','" + ans1 + "','" + ans2 + "','" + ans3 + "','" + ans4 + "')");
                }
            } catch (JSONException e) {
                Log.e("error1", "ERROR-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-ERROR" + e);
            }
        }
    }
}
