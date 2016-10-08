package com.example.waqas.mhacks8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    private static final String TAG = MainActivity.class.getSimpleName();  //prints entire error

    private String userID = null;
    Button testButton;

    EditText firstName;
    EditText lastName;
    EditText age;
    EditText gender;
    EditText city;
    EditText state;
    EditText startDate;
    EditText endDate;
    EditText interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

        testButton = (Button) findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void connectToDatabase(View view){
        final String testString = "Hey Chippi";
        StringRequest requestID = new StringRequest(Request.Method.GET,"http://45.33.88.226:8080/createProfile", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // code for response
                    //final JSONObject obj = new JSONObject(response);
                    userID = response;
                    System.out.println(userID);
                } catch (Throwable t) {
                    Log.e("Extractor", "Could not parse malformed JSON: \"" + response + "\"");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString(), error);
                Toast.makeText(
                        getApplicationContext(),
                        "No ID",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("test", testString);   //Sending User Data to Server
                return params;
            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("X-AYLIEN-TextAPI-Application-Key","ac6f5d725a9c90e8373c77c233f2273e");
//                params.put("X-AYLIEN-TextAPI-Application-ID","6432fd32");
//                return params;
//            }
        };
        queue.add(requestID);

        //Create intent to open second page
        if(userID != null){
            Intent intent = new Intent(getApplicationContext(), CreateUser.class);
            intent.putExtra("ID", userID);
            startActivity(intent);
        }

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





//        URL url = null;
//        String userID = null;
//        HttpURLConnection client = null;
//        try {
//            url = new URL("http://exampleurl.com/");   //using IP address
//            client = (HttpURLConnection) url.openConnection();
//            client.setRequestMethod("POST");
//            client.setRequestProperty("Key","Value");
//            client.setDoOutput(true);
//
//            OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
//            writeStream(outputPost);
//
//            InputStream in = new BufferedInputStream(client.getInputStream());
//            readStream(in);
//
//            outputPost.flush();
//            outputPost.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch(Exception e){
//            e.printStackTrace();
//        }