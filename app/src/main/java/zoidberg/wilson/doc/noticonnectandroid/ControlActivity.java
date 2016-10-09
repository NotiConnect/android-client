package zoidberg.wilson.doc.noticonnectandroid;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zoidberg.wilson.doc.notilib.NotiService;

public class ControlActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private Button mToggleButton;
    private Button mPostNotificationButton;
    private Handler mHandler;
    private TextView mStatusLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mToggleButton = (Button) findViewById(R.id.toggle_service_button);
        mPostNotificationButton = (Button) findViewById(R.id.post_notification_button);
        mStatusLabel = (TextView) findViewById(R.id.status_label);

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, NotiService.class);

                if(isMyServiceRunning(NotiService.class)) {
                    stopService(intent);
                }
                else {
                    startService(intent);
                }

            }
        });

        mPostNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification.Builder nb = new Notification.Builder(ControlActivity.this);
                nb.setContentTitle("Test notification.");
                nb.setContentText("Subtext");
                nb.setSmallIcon(R.mipmap.ic_launcher);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify((int)System.currentTimeMillis(), nb.build());
            }
        });
        mHandler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                String message = isMyServiceRunning(NotiService.class)
                        ? "Running!"
                        : "Not Running!";

                //Log.d(TAG, message);
                mStatusLabel.setText(message);
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(runnable, 1000);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
