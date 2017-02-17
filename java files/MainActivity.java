package messageindexingpart2.com.gaurdianapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
//import android.preference.PreferenceManager;
//import android.provider.ContactsContract;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    //   private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    NextPage Np;
    Button b1;
    EditText E1, E2;
    //http://172.16.7.8/webservice/create_product.php     college ip
    // url to create new product
    private static String url_create_product = "http://192.168.1.2/webservice/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);

        E1 = (EditText) findViewById(R.id.editText);
        E2 = (EditText) findViewById(R.id.editText2);


        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if (pref.getBoolean("activity_executed", false)) {
            Intent intent = new Intent(this, NextPage.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creating new product in background thread
                String nme = E1.getText().toString();
                String phone = E2.getText().toString();
                new AddNewGuardian().execute(nme, phone);
                Intent i = new Intent(MainActivity.this, NextPage.class);
                i.putExtra("phone", phone);
                startActivity(i);
                finish();


            }
        });
    }


    /**
     * Background Async Task to Create new product
     */
    class AddNewGuardian extends AsyncTask<String, String, String> {


        protected String doInBackground(String... args) {
            // String NAME = E1.getText().toString();
            // String phone = E2.getText().toString();
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String nm = args[0];
            String pho = args[1];
            params.add(new BasicNameValuePair("Guard_Name", nm));
            params.add(new BasicNameValuePair("phone_number", pho));


            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                    "POST", params);


            return null;
        }

    }

}
