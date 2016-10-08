package com.example.waqas.mhacks8;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by waqas on 10/8/2016.
 */

public class Listings  extends AppCompatActivity {

    RequestQueue queue;
    private static final String TAG = MainActivity.class.getSimpleName();  //prints entire error

    EditText firstName;
    EditText lastName;
    EditText age;
    EditText gender;
    EditText city;
    EditText state;
    EditText startDate;
    EditText endDate;
    EditText interests;

    private String myUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_listing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myUserID = getIntent().getStringExtra("ID");
    }

    public void createListing(View view){
        final String testString = "Hey Chippi";
        StringRequest requestID = new StringRequest(Request.Method.GET,"http://45.33.88.226:8080/createListing", new Response.Listener<String>() {
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
                //params.put("test", testString);   //Sending User Data to Server
                params.put("firstName", firstName.toString());
                params.put("lastName", lastName.toString());
                params.put("age", age.toString());
                params.put("gender", gender.toString());
                params.put("city", city.toString());
                params.put("state", state.toString());
                params.put("startDate", startDate.toString());
                params.put("endDate", endDate.toString());
                params.put("interests", interests.toString());
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
    }

    public void displayListings(View view){

    }
}
