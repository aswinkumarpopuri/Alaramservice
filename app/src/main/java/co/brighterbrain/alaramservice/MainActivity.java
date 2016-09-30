package co.brighterbrain.alaramservice;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    AlarmManager alaram_manager;
    TimePicker alaram_timepicker;
    TextView update_text;
    Context context;
    PendingIntent pending_intent;
    Button alaram_on;

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context=this;


        alaram_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // initialize the time picker
        alaram_timepicker= (TimePicker)findViewById(R.id.timePicker);

        //initialise the text.

        update_text = (TextView)findViewById(R.id.update_text);

        //create a instance of calender

        final Calendar calendar = Calendar.getInstance();

        // create an intent
        final Intent my_intent=  new Intent(this,Alaram_Receiver.class);
        this.sendBroadcast(my_intent);

        //initialise the button
        alaram_on = (Button) findViewById(R.id.alaram_on);

        alaram_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(calendar.HOUR_OF_DAY,alaram_timepicker.getHour());
                calendar.set(calendar.MINUTE,alaram_timepicker.getMinute());

                int hour= alaram_timepicker.getHour();
                int minute= alaram_timepicker.getMinute();


                String hour_string=String.valueOf(hour);
                String minute_string =String.valueOf(minute);


                if (minute < 10) {
                   minute_string = "0" +String.valueOf(minute);
                }

                set_alaram_text ("alaram set to" +hour_string +":" +minute_string);

                // create a pending intent to delay intent until thespecified calender time;

                pending_intent= PendingIntent.getBroadcast(context,0,my_intent,PendingIntent.FLAG_UPDATE_CURRENT);


                //set the alaram manager

                alaram_manager.set(AlarmManager.RTC,calendar.getTimeInMillis() + 10000,pending_intent);


            }
        });

        Button alaram_off = (Button) findViewById(R.id.alaram_off);

 alaram_off.setOnClickListener(new View.OnClickListener(){

     @Override
     public void onClick(View view) {
         set_alaram_text("alaramoff!");


         //cancel the alaram

         alaram_manager.cancel(pending_intent);

     }
 });



    }

    private void set_alaram_text(String  output) {
        update_text.setText(output);
    }


}
