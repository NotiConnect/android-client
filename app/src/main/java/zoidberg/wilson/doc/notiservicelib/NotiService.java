package zoidberg.wilson.doc.notiservicelib;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

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
