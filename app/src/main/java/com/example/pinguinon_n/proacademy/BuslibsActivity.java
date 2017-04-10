package com.example.pinguinon_n.proacademy;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class BuslibsActivity extends AppCompatActivity {

    ListView listLibs;
    ArrayAdapter adapter;
    String getAllBookssURL = "http://192.168.0.14/siteacademy/api/getAllBooks.php";
    int pack = 1;
    String libro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslibs);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listLibs = (ListView)findViewById(R.id.lvBooksList);
        listLibs.setAdapter(adapter);
        getNewBooks();
        listLibs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                libro = listLibs.getItemAtPosition(pos).toString();
                Toast.makeText(getApplicationContext(),libro,Toast.LENGTH_LONG).show();
            }
        });
    }


    private void getNewBooks(){
        try{
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("package", ""+pack);
            String queryParams = builder.build().getEncodedQuery();
            performPostCall(getAllBookssURL, queryParams);
        }catch(Exception e){
            Log.e("Error",""+e);
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("package", ""+pack);
            String queryParams = builder.build().getEncodedQuery();
            Log.i("Consulta ",queryParams);
            performPostCall(getAllBookssURL, queryParams);
        }

    }

    private void performPostCall(String requestURL, String query){
        URL url;
        String webServiceResult="";
        try{
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.e("Done","obteniendo conexion url--------------------------------------------");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            Log.e("Done","enviando datos--------------------------------------------");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            Log.e("Done","obteniendo stream de salida--------------------------------------------");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            Log.e("Done","obteniendo escritor--------------------------------------------");
            writer.write(query);
            writer.flush();
            writer.close();
            Log.e("Done","escritor enviado--------------------------------------------");
            os.close();
            Log.e("Done","cerrado el stream de salida--------------------------------------------");
            int responseCode = conn.getResponseCode();
            Log.e("Done","obteniendo el codigo de respuesta--------------------------------------------" + responseCode);
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                Log.e("Done","obteniendo el lector--------------------------------------------");
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    Log.e("Done","leyendo y añadiendo una linea--------------------------------------------");
                    webServiceResult += line;
                    Log.e("Done","Resultado:---------------------------------"+webServiceResult);
                }
                bufferedReader.close();
                Log.e("Done","se cierra un lector--------------------------------------------");
            }else {
                webServiceResult="";
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("SearchActivity","error en el procedimiento----------------------------------------------------" + e);
        }
        if(webServiceResult!=null){
            Log.e("Done","se parcea la informacion--------------------------------------------");
            parseInformation(webServiceResult);
        }else{
            Message("Search","Books not Found");
        }
    }

    private void Message(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    private void parseInformation(String jsonResult){
        Log.e("Done","Resultado:---------------------------------"+jsonResult);
        JSONArray jsonArray = null;
        String book;
        try{
            jsonArray = new JSONArray(jsonResult);
            Log.e("Done","se parcea la informacion y se ingresa a un array--------------------------------------------");
        }catch (JSONException e){
            e.printStackTrace();
        }
        Log.e("Done","se intenta recorrer el array--------------------------------------------");
        for(int i=0;i<jsonArray.length();i++){
            try{
                Log.e("Done","obteniendo datos--------------------------------------------");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                book = jsonObject.getString("book");
                pack = Integer.parseInt(jsonObject.getString("package"));
                adapter.add(book);
                Log.e("Done","se añaden datos al adaptador--------------------------------------------");
            }catch (JSONException e){
                Log.e("error1","ERROR-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-ERROR"+e);
            }
        }
    }
}
