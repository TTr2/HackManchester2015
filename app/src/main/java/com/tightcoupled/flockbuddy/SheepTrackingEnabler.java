package com.tightcoupled.flockbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SheepTrackingEnabler extends AppCompatActivity {

    String response;
    boolean isEnabled;
    ToggleButton enabler;
    final String serverURL = "http://chrisdromey.ddns.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheep_tracking_enabler);


        enabler = (ToggleButton) findViewById(R.id.trackingEnabler);
        enabler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enabler.isEnabled())
                {
                    isEnabled = false;

                    //HTTPRequest to stop text messaging
                    response = executePost(serverURL + "/receiveSMS.php/", "?mobile="+SheepOrShephard.getPhoneNumber(App.getContext())+"&latitude="+SheepOrShephard.getLocationCoordinates()[0]+"&longtitude="+SheepOrShephard.getLocationCoordinates()[1]+"&isTracked=false"+"&keyword=SUSPEND");

                }

                else
                {
                    isEnabled = true;
                    //HTTPRequest to start text messaging
                    response = executePost(serverURL + "/receiveSMS.php", "?mobile="+SheepOrShephard.getPhoneNumber(App.getContext())+"&latitude="+SheepOrShephard.getLocationCoordinates()[0]+"&longtitude="+SheepOrShephard.getLocationCoordinates()[1]+"&isTracked=true"+"&keyword=FLOCKME");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sheep_tracking_enabler, menu);
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

    private static String executePost(String targetURL, String urlParameters) {
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
}
