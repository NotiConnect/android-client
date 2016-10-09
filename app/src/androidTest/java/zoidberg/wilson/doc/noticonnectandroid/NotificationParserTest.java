package zoidberg.wilson.doc.noticonnectandroid;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.test.InstrumentationRegistry;
import android.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;

import zoidberg.wilson.doc.notiservicelib.NotificationParser;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by doc on 10/9/16.
 */

@RunWith(JUnit4.class)
public class NotificationParserTest {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private NotificationParser mNotificationParser;
    private final Notification mTestNotification;

    public NotificationParserTest() {
        mContext = InstrumentationRegistry.getTargetContext();
        mTestNotification = buildTestNotification();
        mNotificationParser = new NotificationParser(mContext, mTestNotification, TAG);
    }

    private Notification buildTestNotification() {
        Notification.Builder nb = new Notification.Builder(mContext);
        nb.setContentText("Test notification");
        nb.setContentTitle("Title");
        nb.setSmallIcon(R.mipmap.ic_launcher);

        return nb.build();
    }

    private Bitmap extractBitmapFromIcon(Icon icon) {
        Drawable drawable = icon.loadDrawable(mContext);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        return bitmap;
    }

    @Test
    public void iconDecodedByteArrayTest() throws Exception {
        Icon originalIcon = mNotificationParser.getIcon();
        Bitmap originalBitmap = extractBitmapFromIcon(originalIcon);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        originalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        final byte[] originalByteArray = stream.toByteArray();

        final String newByteArrayHash = mNotificationParser.getIconBase64();
        final byte[] newByteArray = Base64.decode(newByteArrayHash, Base64.URL_SAFE);

        assertArrayEquals("Assert icon decoded is the same as before.", originalByteArray, newByteArray);
    }
}
