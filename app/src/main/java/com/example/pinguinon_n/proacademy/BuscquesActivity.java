package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class BuscquesActivity extends AppCompatActivity {

    String getQuestionsURL = "http://130.100.4.161/siteacademy/api/getQuestions.php";
    ArrayAdapter adapter;
    ListView listQues;

    String libro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscques);
        listQues = (ListView) findViewById(R.id.busquestlv);
        Intent intent = getIntent();
        libro = intent.getStringExtra("book");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listQues.setAdapter(adapter);
        getNewQues();
    }

    private void getNewQues() {
        try {
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("book", libro);
            String queryParams = builder.build().getEncodedQuery();
            performPostCall(getQuestionsURL, queryParams);
        } catch (Exception e) {
            Log.e("Error", "" + e);
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("book", libro);
            String queryParams = builder.build().getEncodedQuery();
            Log.i("Consulta ", queryParams);
            performPostCall(getQuestionsURL, queryParams);

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
        Log.e("Done", "Resultado:---------------------------------" + jsonResult);
        JSONArray jsonArray = null;
        String question;
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
                question = jsonObject.getString("content");
                adapter.add(question);
                Log.e("Done", "se añaden datos al adaptador--------------------------------------------");
            } catch (JSONException e) {
                Log.e("error1", "ERROR-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-ERROR" + e);
            }
        }
    }
}
