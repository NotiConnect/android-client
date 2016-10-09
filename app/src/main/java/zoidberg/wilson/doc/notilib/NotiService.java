package zoidberg.wilson.doc.notilib;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class NotiService extends AccessibilityService {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onServiceConnected() {
        Log.i(TAG, "onServiceConnected()");
        super.onServiceConnected();
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Parcelable data = event.getParcelableData();
        Log.i(TAG, "onAccessibilityEvent()");
        if (data instanceof Notification) {
            Log.i(TAG, "Notification received.");

            NotificationParser np = new NotificationParser(NotiService.this, (Notification) data, event.getPackageName());

            Log.d(TAG, "Name: " + np.getPackageName());
            Log.d(TAG, "Title: " + np.getTitle());
            Log.d(TAG, "Text: " + np.getText());
            Log.d(TAG, "SubText: " + np.getSubText());
            Log.d(TAG, "Group: " + np.getGroup());
            Log.d(TAG, "Icon: " + np.getIconBase64());
        }
    }

    @Override
    public void onInterrupt() {
        Log.i(TAG, "onInterrupt()");
    }
}
