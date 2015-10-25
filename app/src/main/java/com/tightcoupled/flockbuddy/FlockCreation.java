package com.tightcoupled.flockbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Log;
import java.util.ArrayList;

import java.net.*;
import java.io.*;

public class FlockCreation extends AppCompatActivity {


    //Flock Information


    public static ArrayList flocks;
    public static ArrayList<FlockObject> flockObject;



    PopupWindow popUp;
    LinearLayout layout;
    TextView phoneNumber, name;
    EditText thePhoneNumber, theName, flockNameText;
    LayoutParams params;
    LinearLayout mainLayout;
    Button sheepAddition, sheepView, flockCreate, confirmSheep;
    boolean click=true;
    View rootView;

    boolean flockCreated;

    Spinner distanceSpinner, durationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flock_creation);

        flocks = new ArrayList();
        flockObject = new ArrayList<FlockObject>();
        flockCreated = false;

        //confirmSheep = new Button(this, null, ;

        layout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        phoneNumber = new TextView(this);
        name = new TextView(this);
        thePhoneNumber = new EditText(this);
        theName = new EditText(this);

        phoneNumber.setText("Enter Phone Number: ");
        name.setText("Enter Name: ");


        sheepAddition = (Button) findViewById(R.id.addCreationSheepButton);
        sheepAddition.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                //NEED POP UP FOR ADDING SHEEP!
/*
                popUp = new PopupWindow(v);

                params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layout.setOrientation(LinearLayout.VERTICAL);

                layout.addView(phoneNumber, params);
                layout.addView(thePhoneNumber, params);
                layout.addView(name, params);
                layout.addView(theName, params);


               // layout.addView(confirmSheep, params);

                //layout.addView(, params);
                popUp.setContentView(layout);
                popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
                popUp.showAsDropDown(v);

                    /*if (click) {
                        popUp.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
                        popUp.update(50, 50, 300, 80);
                        click = false;
                } else {
                    popUp.dismiss();
                    click = true;
                }*/


                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), SheepAdditionScreen.class);

                startActivity(nextScreen);

            }
        });




        // mainLayout.addView(sheepAddition, params);
        // setContentView(mainLayout);


        sheepView = (Button) findViewById(R.id.viewCreationSheepButton);
        sheepView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flockCreated)
                {

                    excutePost("http://46.101.60.51/flockbuddy/newflock.php/", "?mobile="+SheepOrShephard.getPhoneNumber()+"&sheepName="+);

                    //Starting a new Intent
                    Intent nextScreen = new Intent(getApplicationContext(), FlockSheepList.class);

                    startActivity(nextScreen);
                }

            }
        });

        flockCreate = (Button) findViewById(R.id.flockCreationButton);
        flockCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!flockCreated)
                {
                    //Need to check if all input parameters have been entered correctly here!

                    flockNameText = (EditText) findViewById(R.id.flockNameTextBox);

                    if(!(flockNameText.getText().toString().equals(""))) {

                        FlockObject thisFlock = new FlockObject();
                        thisFlock.setFlockName(flockNameText.getText().toString());
                        thisFlock.setMaxDistance(Integer.parseInt(distanceSpinner.getSelectedItem().toString()));
                        thisFlock.setMaxDuration(Integer.parseInt(durationSpinner.getSelectedItem().toString()));


                        flocks.add(thisFlock);
                        flockCreated = true;
                        //NEED A POPUP HERE SAYING THAT FLOCK HAS BEEN CREATED!
                        PopupMenu flockCreateConfirm = new PopupMenu(getApplicationContext(), v);
                        flockCreateConfirm.inflate(R.menu.flock_create_confirm);

                        flockCreateConfirm.show();
                        //popUp.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
                        //popUp.update(50, 50, 300, 80);

                    }

                }


            }
        });

        distanceSpinner = (Spinner) findViewById(R.id.distanceSetter);

        //Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] items = new String[] { "50", "100", "150", "200", "250", "300", "350", "400", "450", "500" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        distanceSpinner.setAdapter(adapter);

        distanceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        durationSpinner = (Spinner) findViewById(R.id.durationSetter);

        String[] theitems = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };

        ArrayAdapter<String> theadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, theitems);

        durationSpinner.setAdapter(theadapter);

        durationSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("theitem", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flock_creation, menu);
        return true;
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


    private static String excutePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
            String line;
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }


    private String getPhoneUserName()
    {
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            // TODO: Check possibleEmail against an email regex or treat
            // account.name as an email address only for certain account.type
            // values.


            possibleEmails.add(account.name);
        }

        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");
            if (parts.length > 0 && parts[0] != null)
                return parts[0];
            else
                return null;
        } else
            return null;
    }
}
