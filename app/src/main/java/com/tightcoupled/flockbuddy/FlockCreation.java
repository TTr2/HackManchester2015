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

public class FlockCreation extends AppCompatActivity {

    PopupWindow popUp;
    LinearLayout layout;
    TextView phoneNumber, name;
    EditText thePhoneNumber, theName;
    LayoutParams params;
    LinearLayout mainLayout;
    Button sheepAddition, sheepView, flockCreate, confirmSheep;
    boolean click=true;
    View rootView;

    Spinner distanceSpinner, durationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flock_creation);

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
            }
        });




        // mainLayout.addView(sheepAddition, params);
        // setContentView(mainLayout);


        sheepView = (Button) findViewById(R.id.viewCreationSheepButton);
        sheepView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), FlockSheepList.class);

                startActivity(nextScreen);

            }
        });

        flockCreate = (Button) findViewById(R.id.flockCreationButton);
        flockCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //NEED A POPUP HERE SAYING THAT FLOCK HAS BEEN CREATED!
                PopupMenu flockCreateConfirm = new PopupMenu(getApplicationContext(), v);


                //Need to check if all input parameters have been entered correctly here!

                flockCreateConfirm.inflate(R.menu.flock_create_confirm);

                flockCreateConfirm.show();
                //popUp.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
                //popUp.update(50, 50, 300, 80);

                //Starting a new Intent
                //Intent nextScreen = new Intent(getApplicationContext(),  .class);

                //startActivity(nextScreen);
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
}
