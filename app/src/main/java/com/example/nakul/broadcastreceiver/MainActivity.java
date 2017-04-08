package com.example.nakul.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mbatterylevelText;
    private ProgressBar mbatteryLevelProgress;
    private BroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbatterylevelText=(TextView)findViewById(R.id.tv);
        mbatteryLevelProgress=(ProgressBar)findViewById(R.id.progressBar);
        mReceiver=new BatteryBroadcastReceiver();

    }

    @Override
    protected void onStart() {
        registerReceiver(mReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    public class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            mbatterylevelText.setText("Battery Level"+ " "+ level);
            mbatteryLevelProgress.setProgress(level);        }
    }
}
