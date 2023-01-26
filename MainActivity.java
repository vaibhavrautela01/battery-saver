package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView iv_battery;
    TextView tv_battery;

    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_battery = (ImageView) findViewById(R.id.iv_battery);
        tv_battery = (TextView) findViewById(R.id.tv_battery);

        runnable = new Runnable() {
            @Override
            public void run() {
                int level = (int)  batteryLevel();

                tv_battery.setText("BATTERY: " + level + "0");

                if (level > 75)
                {
                    iv_battery.setImageResource(R.drawable.battery100);
                }

                if (level > 50 && level <= 75)
                {
                    iv_battery.setImageResource(R.drawable.battery75);
                }

                if (level > 25 && level <= 50)
                {
                    iv_battery.setImageResource(R.drawable.battery50);
                }

                if (level > 5 && level <= 25)
                {
                    iv_battery.setImageResource(R.drawable.battery25);
                }

                if (level <= 5)
                {
                    iv_battery.setImageResource(R.drawable.battery5);
                }
                handler.postDelayed(runnable,5000);

            }
        };

        handler = new Handler();
        handler.postDelayed(runnable,0);
    }

    public float batteryLevel(){
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);

        if(level == -1 || scale ==-1){
            return 50.0f;
        }

        return ((float) level / (float) scale) * 100.0f;

    }




}