package co.brighterbrain.alaramservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by User on 8/24/2016.
 */
public class Alaram_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver", "yay!");
   // create an intent to the ringtone service
        Intent service_intent= new Intent(context,RingtonePlayingService.class);

        //start the ringtone service

        context.startService(service_intent);


    }
}
