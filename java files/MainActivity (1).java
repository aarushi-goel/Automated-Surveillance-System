package messageindexingpart2.com.newestuserapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity  {
//192.168.0.7
    private static MainActivity inst;
    public static final String URL = "http://192.168.1.2/webservice/minimum.php";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_PRODUCTS = "product";
    public static final String TAG_NAME = "phone_number";

    JSONParser jParser = new JSONParser();

    BroadcastReceiver receiver;
    public static MainActivity instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }






    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone =null;
               new JSONAsyncTask().execute();
            }
        });


    }

       class JSONAsyncTask extends AsyncTask<String, Void, String> {
        JSONArray products =null;
        public String phoneNo;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
       public String doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(URL);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    Log.d("JOBJECT ", " "+jsono);
                    products =jsono.getJSONArray(TAG_PRODUCTS);
                    JSONObject c = products.getJSONObject(0);
                    Log.d("NUMBer", " "+c);
                    phoneNo = c.getString("phone_number");
                    Log.d("No.", ""+phoneNo);

                    return phoneNo;
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {

                e.printStackTrace();
            }
                        return null;
        }
        protected void onPostExecute(String result) {
            String num = result;
            Log.d("LOL", " " + result);
           call(result);
        }


       }


    public void call(String phone) {
        String num= phone;




        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:" +num));
        Log.d("E", " " + num);
        try{
            startActivity(callIntent);
        }

        catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
        }
    }

}

