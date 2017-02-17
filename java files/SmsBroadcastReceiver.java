package messageindexingpart2.com.newestuserapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static messageindexingpart2.com.newestuserapp.MainActivity.*;
import static messageindexingpart2.com.newestuserapp.MainActivity.instance;

public class SmsBroadcastReceiver extends Service {

    BroadcastReceiver receiver;
    AudioManager mobi;
    static String action;
    Intent heli;

    static String pho, noo;
    public static final String ALERT = "160065DF64C8";
    public static final String DISPLAY = "WARNING!";
    public static final String SMS_BUNDLE = "pdus";
    public static final String URL_MIN_LOC = "http://192.168.1.2/webservice/minimum.php";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        receiver = new Fiver();

        mobi = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        Toast.makeText(this, "in the oncreate-serv", Toast.LENGTH_LONG).show();

        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction("android.provider.Telephony.SMS_RECEIVED"); // SMS
        getApplicationContext().registerReceiver(receiver, filter);


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "in the START-serv", Toast.LENGTH_LONG).show();
        try {
            if (intent != null) {
                noo = intent.getStringExtra("noo");
                pho = intent.getStringExtra("pho");
                Toast.makeText(this, "the intent is fine" + noo, Toast.LENGTH_LONG).show();
            } else {
                heli = new Intent();
                heli.setAction("messageindexingpart2.com.newuserapp");
                heli.putExtra("noo", noo);
                heli.putExtra("pho", pho);
                this.sendBroadcast(heli);
                throw new NullPointerException();

            }

        } catch (NullPointerException e) {
            e.printStackTrace();

        }
        return Service.START_REDELIVER_INTENT;

    }


    public static class Fiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent inte) {

            action = inte.getAction();

            if (action.equals("android.provider.Telephony.SMS_RECEIVED")) {
                //action for sms received

                Bundle myBundle = inte.getExtras();
                SmsMessage[] messages = null;
                String phno = "", mess = "";

                if (myBundle != null) {
                    Object[] pds = (Object[]) myBundle.get(SMS_BUNDLE);
                    messages = new SmsMessage[pds.length];

                    for (int i = 0; i < messages.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pds[i]);
                        phno = messages[i].getOriginatingAddress();

                    }


                }


                //Toast.makeText(context,pho+noo, Toast.LENGTH_LONG).show();

                //get the msg
                if (myBundle != null) {
                    Object[] pdss = (Object[]) myBundle.get(SMS_BUNDLE);
                    messages = new SmsMessage[pdss.length];

                    for (int i = 0; i < messages.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdss[i]);
                        mess = messages[i].getMessageBody();


                    }
                    // Toast.makeText(context,mess, Toast.LENGTH_LONG);

                }
                //check for the key word
                if (mess.contains(ALERT)) {
                    try {
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        Ringtone r = RingtoneManager.getRingtone(context, notification);
                        r.play();
                        Toast.makeText(context, DISPLAY, Toast.LENGTH_LONG);

                            //start activity
                            Intent i = new Intent();
                            i.setClassName("messageindexingpart2.com.newestuserapp", "messageindexingpart2.com.newestuserapp.MainActivity");
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);




                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();

                }
            }
        }


    }

    public void onDestroy() {
        super.onDestroy();
        getApplicationContext().unregisterReceiver(receiver);
        stopSelf();
        Toast.makeText(getApplicationContext(), "this has stopped working", Toast.LENGTH_LONG).show();

    }




}





