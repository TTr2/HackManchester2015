package com.tightcoupled.flockbuddy;

import android.app.*;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.test.mock.MockContext;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Context;
import android.location.*;
import android.telephony.*;
import android.webkit.WebView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//import java.security.Provider;

import static com.tightcoupled.flockbuddy.R.layout.activity_sheep_or_shephard;

public class SheepOrShephard extends AppCompatActivity implements LocationListener{

    Button theSheepButton, theShephardButton;
   // View rootView;
    LocationManager locationManager;
    static Location location;
    private String provider;

    private int latitude, longtitude;
    private CountDownTimer countDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_sheep_or_shephard);

        //View rootView= new View(new MockContext());

        InputStream stream = null;
        try {
            stream = getAssets().open("shepherdsheepanim.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GifWebView view = new GifWebView(this, "file:///android_asset    /piggy.gif");


        theSheepButton = (Button) findViewById(R.id.sheepButton);
        theShephardButton = (Button) findViewById(R.id.shephardButton);

        theSheepButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), SheepTrackingEnabler.class);

                //Sending data to another Activity
                //nextScreen.putExtra("name", inputName.getText().toString());
                //nextScreen.putExtra("email", inputEmail.getText().toString());

                //Log.e("n", inputName.getText()+"."+ inputEmail.getText());

                startActivity(nextScreen);
            }
        });

        theShephardButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), ShephardFlockManager.class);

                startActivity(nextScreen);
            }
        });

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        try
        {
            location = locationManager.getLastKnownLocation(provider);
        }
        catch(Exception failed)
        {

        }

        runTimer();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sheep_or_shephard, menu);
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


    //GPS Location overriden classes

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public static double[] getLocationCoordinates()
    {
        double[] coordinates = new double[2];

        coordinates[0] = location.getLatitude();
        coordinates[1] = location.getLongitude();
        return coordinates;
    }

    public static int getPhoneNumber()
    {
        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        return Integer.parseInt(mPhoneNumber);
    }

    private void sendInfo()
    {
        double[] coordinates = getLocationCoordinates();

        int phoneNumber = getPhoneNumber();

        //Send HTTP Request here...

    }

    private void runTimer()
    {
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished % 100 == 0)
                {
                    System.out.println("Timer is working...");
                }
            }
            @Override
            public void onFinish() {
                sendInfo();
                runTimer();
            }
        };
    }

    public class GifWebView extends WebView {

        public GifWebView(Context context, String path) {
            super(context);
            loadUrl(path);
        }
    }


}
