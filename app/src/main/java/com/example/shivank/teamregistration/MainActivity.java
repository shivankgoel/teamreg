package com.example.shivank.teamregistration;


import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher; // For instant validation
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.shivank.teamregistration.R.string.AboutUsTitle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //URL where post request is to be sent
    private static final String REGISTER_URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
    //Object created to use here
    public static final String teamname = "teamname";
    public static final String name1 = "name1";
    public static final String entry1 = "entry1";
    public static final String name2 = "name2";
    public static final String entry2 = "entry2";
    public static final String name3 = "name3";
    public static final String entry3 = "entry3";//


    private EditText editTextTeamName;
    private EditText editTextName1;
    private EditText editTextEntry1;
    private EditText editTextName2;
    private EditText editTextEntry2;
    private EditText editTextName3;
    private EditText editTextEntry3;


    private Button submitButton;
    private FloatingActionButton fab;


    //Overriding Oncreate function to enable "Submit", "Floating action Button", "TextVarifier" and Sending Field Data to server.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //It will get all the form Inputs/Data
        editTextTeamName = (EditText) findViewById(R.id.editTextTeamName);
        editTextName1 = (EditText) findViewById(R.id.editTextName1);
        editTextEntry1 = (EditText) findViewById(R.id.editTextEntry1);
        editTextName2 = (EditText) findViewById(R.id.editTextName2);
        editTextEntry2 = (EditText) findViewById(R.id.editTextEntry2);
        editTextName3 = (EditText) findViewById(R.id.editTextName3);
        editTextEntry3 = (EditText) findViewById(R.id.editTextEntry3);

        // TextWatcher would let us check validation error on the fly
        editTextTeamName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(editTextTeamName);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTextName1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isName(editTextName1, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTextEntry1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isEntryNumber(editTextEntry1, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        editTextName2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isName(editTextName2, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTextEntry2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isEntryNumber(editTextEntry2, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTextName3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isName(editTextName3, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        editTextEntry3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isEntryNumber(editTextEntry3, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        submitButton = (Button) findViewById(R.id.submitButton);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        submitButton.setOnClickListener(this);

        //Floating Button allow adding of Third button by displaying Snack at the bottom of the page.
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            //CoordinatorLayout enable removing Snack by Right Swap
            public void onClick(View coordinatorLayout) {
                Snackbar.make(coordinatorLayout, "Add 3rd Teammate?", Snackbar.LENGTH_SHORT)
                        .setAction("Add", new View.OnClickListener() {
                            @Override
                            //to make Text boxes  Available for 3rd Teammate and To hide FAB
                            public void onClick(View v) {
                                editTextName3.setVisibility(View.VISIBLE);
                                editTextEntry3.setVisibility(View.VISIBLE);
                                fab.hide();
                            }
                        })
                        .show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            FragmentManager fm = getFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment ();
            dialogFragment.show(fm, "Sample Fragment");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Dialog Fragment to show About Us on clicking menu_main
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class MyDialogFragment extends DialogFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.diag_fragment, container, false);
            getDialog().setTitle(AboutUsTitle);
            return rootView;
        }
    }



    //To send Data at server and to Handle response Arrived from server
    private void registerUser(){
        final String TeamName = editTextTeamName.getText().toString().trim();
        final String Name1 = editTextName1.getText().toString().trim();
        final String Entry1 = editTextEntry1.getText().toString().trim();
        final String Name2 = editTextName2.getText().toString().trim();
        final String Entry2 = editTextEntry2.getText().toString().trim();
        final String Name3 = editTextName3.getText().toString().trim();
        final String Entry3 = editTextEntry3.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("Data")) {
                            Toast.makeText(MainActivity.this, "Sorry, Registration Failed.", Toast.LENGTH_LONG).show();
                        }
                        else if (response.contains("completed")) {
                            Toast.makeText(MainActivity.this, "Congrats, Registration Successful", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Sorry, Already Registered", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Connection Problem", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams () {
                    Map<String, String> params = new HashMap<>();
                    params.put(teamname, TeamName);
                    params.put(entry1, Entry1);
                    params.put(name1, Name1);
                    params.put(entry2, Entry2);
                    params.put(name2, Name2);
                    params.put(entry3, Entry3);
                    params.put(name3, Name3);




                    return params;
                }
        };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



    }
    //Click to Submit
    @Override
    public void onClick(View v) {
        if(v == submitButton){
            if ( !checkValidation () )
                registerUser();
            else {
                Toast.makeText(MainActivity.this, "Form Not Valid", Toast.LENGTH_LONG).show();

            }

        }
    }
    //Validating Form Data
    private boolean checkValidation() {
        boolean ret = true;
        if (!Validation.hasText(editTextTeamName)) ret = false;
        if (!Validation.isName(editTextName1, true)) ret = false;
        if (!Validation.isName(editTextName2, true)) ret = false;
        if (!Validation.isName(editTextName3, false)) ret = false;
        if (!Validation.isEntryNumber(editTextEntry1, true)) ret = false;
        if (!Validation.isEntryNumber(editTextEntry2, true)) ret = false;
        if (!Validation.isEntryNumber(editTextEntry3, false)) ret = false;

        return ret;
    }


}
