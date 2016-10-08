package com.example.waqas.mhacks8;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by waqas on 10/8/2016.
 */

public class CreateUser  extends AppCompatActivity {

    private Context context = this;

    private String myFirstName;
    private String myLastName;
    private String myAge;
    private String myGender;
    private String myCity;
    private String myState;
    private String myStartDate;
    private String myEndDate;
    private String myInterests;
    private String myUserID = null;

    EditText firstName;
    EditText lastName;
    EditText age;
    Spinner gender;
    EditText city;
    EditText state;
    EditText startDate;
    EditText endDate;
    EditText interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myUserID = getIntent().getStringExtra("ID");

        setContentView(R.layout.activity_main);

        ImageView imageViewProfilePicture = (ImageView) findViewById(R.id.imageViewProfilePicture);

        imageViewProfilePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.get_user_profile_picture_layout);
                dialog.setTitle("Get A Profile Picture");

                ImageView imageViewSelectPicture = (ImageView) dialog.findViewById(R.id.imageViewSelectImage);
                imageViewSelectPicture.setImageResource(R.drawable.folderphoto);

                ImageView imageViewTakePicture = (ImageView) dialog.findViewById(R.id.imageViewTakePicture);
                imageViewTakePicture.setImageResource(R.drawable.camera);

                dialog.show();
            }
        });



        Spinner genderDropDown = (Spinner) findViewById(R.id.spinnerMaleFemale);
        String[] genderItems = new String[]{"M", "F"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genderItems);
        genderDropDown.setAdapter(genderAdapter);


    }


    public void sendToTextFile(View view){
        firstName = (EditText) findViewById(R.id.etFirstName);
        lastName = (EditText) findViewById(R.id.etLastName);
        age = (EditText) findViewById(R.id.etAge);
        gender = (Spinner) findViewById(R.id.spinnerMaleFemale);   //this is now a spinner
        String info = firstName + "\n" + lastName + "\n" + age + "\n" + gender + "\n" + myUserID;

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("userData.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(info);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void readFile(View view){

        try{
            InputStream inputStream = this.openFileInput("userData.txt");
            if(inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                myFirstName = bufferedReader.readLine().toString();
                myLastName = bufferedReader.readLine().toString();
                myAge = bufferedReader.readLine().toString();
                myGender = bufferedReader.readLine().toString();
                myUserID = bufferedReader.readLine().toString();
            }
            else{
                //Open first page to create a userID
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

            inputStream.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void switchView(View view){
        Intent intent = new Intent(getApplicationContext(), Listings.class);
        intent.putExtra("ID", myUserID);
        startActivity(intent);
    }
}
