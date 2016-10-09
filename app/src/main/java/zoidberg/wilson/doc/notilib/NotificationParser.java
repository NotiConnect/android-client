package zoidberg.wilson.doc.notilib;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * Created by doc on 10/8/16.
 */

public class NotificationParser {

    public static final String NOTIFICATION_TITLE = "android.title";
    public static final String NOTIFICATION_TEXT = "android.text";
    public static final String NOTIFICATION_SUB_TEXT = "android.subText";

    private Context mContext;

    private final Bundle mNotificationBundle;
    private final String mPackageName;
    private final String mGroup;
    private final Icon mSmallIcon;
    private final Icon mLargeIcon;

    /**
     *
     * @param context
     * @param notification
     * @param packageName
     */
    public NotificationParser(Context context, Notification notification, CharSequence packageName) {
        mContext = context;
        mNotificationBundle = notification.extras;
        mPackageName = packageName.toString();
        mSmallIcon = notification.getSmallIcon();
        mLargeIcon = notification.getLargeIcon();
        mGroup = notification.getGroup();
    }

    /**
     * Get the sender's package name
     * @return String
     */
    public final String getPackageName() {
        return mPackageName;
    }

    /**
     * Get the notification's title
     * @return String
     */
    public final String getTitle() {
        return mNotificationBundle.getString(NOTIFICATION_TITLE);
    }

    /**
     * Get the notification's text
     * @return String
     */
    public final String getText() {
        return mNotificationBundle.getString(NOTIFICATION_TEXT);
    }

    /**
     * Get the notification's sub text
     * @return String
     */
    public final String getSubText() {
        return mNotificationBundle.getString(NOTIFICATION_SUB_TEXT);
    }

    /**
     * 
     */
    public final String getGroup() {
        return mGroup;
    }

    /**
     * Get the large icon if one exists, otherwise use the small one
     * @return Icon
     */
    public final Icon getIcon() {
        return mLargeIcon != null ? mLargeIcon : mSmallIcon;
    }

    /**
     * Get the icon as Base64 string with URL_SAFE flags
     * @return String
     */
    public final String getIconBase64() {
        return getIconBase64(Base64.URL_SAFE);
    }

    /**
     *
     * @param flags
     */
    public final String getIconBase64(int flags) {
        byte[] icon = getIconByteArray();
        return Base64.encodeToString(icon, flags);
    }

    /**
     * 
     */
    public final byte[] getIconByteArray() {
        Drawable drawable = getIcon().loadDrawable(mContext);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }
}


