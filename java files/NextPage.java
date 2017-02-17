package messageindexingpart2.com.gaurdianapp;

import android.app.Activity;
import android.app.Service;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 29-04-2016.
 */
public class NextPage extends Activity  {
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    //http://172.16.7.8/webservice/get_location.php
    private static String url_update_product = "http://192.168.1.2/webservice/get_location.php";
    JSONParser jsonParser2 = new JSONParser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);


        textView = (TextView) findViewById(R.id.textView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
         locationListener = new LocationListener() {
             @Override
             public void onLocationChanged(Location location) {
                 textView.append("\n"+location.getLatitude() + " "+location.getLongitude());
                 Bundle extras = getIntent().getExtras();
                 String phonenumber = extras.getString("phone");



                 double lat =location.getLatitude();
                 double longi = location.getLongitude();

                 String Lati = Double.toString(lat);
                 String longit = Double.toString(longi);

                 new UpdateGuardloc().execute(Lati,longit,phonenumber);

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

         };
        locationManager.requestLocationUpdates("network", 500 , 0, locationListener);

    }

    class UpdateGuardloc extends AsyncTask<String, String, String> {


        protected String doInBackground(String... args) {
            // String NAME = E1.getText().toString();
            // String phone = E2.getText().toString();
            HttpClient httpClient=new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String Latitude = args[0];
            String longitude = args[1];
            String phe = args[2];
          params.add(new BasicNameValuePair("Guard_Lattitude", Latitude));
            params.add(new BasicNameValuePair("Guard_Longitude", longitude));
            params.add(new BasicNameValuePair("phone_number", phe));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser2.makeHttpRequest(url_update_product,
                    "POST", params);


            return null;
        }

    }


}
