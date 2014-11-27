package willowcheng.postach.io.dailyselfie;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;


/**
 * Created by Tom on 11/22/2014.
 */
public class AlarmNotificationReceiver  extends BroadcastReceiver {

    // Notification Action Elements
    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    private static final int MY_NOTIFICATION_ID = 1;
    private static final String TAG = "AlarmNotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        // The Intent to be used when the user clicks on the Notification View
        mNotificationIntent = new Intent(context, ItemListActivity.class);

        // The PendingIntent that wraps the underlying Intent
        mContentIntent = PendingIntent.getActivity(context, 0,
                mNotificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Build the Notification
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setTicker("Time for taking a picture")
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true).setContentTitle("Time to take a selfie!")
                .setContentText("click to open DailySelfie, be happy")
                .setContentIntent(mContentIntent)
                .setSound(alarmSound);

        // Get the NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Pass the Notification to the NotificationManager:
        mNotificationManager.notify(MY_NOTIFICATION_ID,
                notificationBuilder.build());
    }
}