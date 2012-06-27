package emenu.client.menu.svc;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import client.menu.R;
import emenu.client.menu.dao.DanhMucDAO;
import emenu.client.menu.ui.activity.SplashScreenActivity;

public class SyncService extends IntentService {

    public SyncService() {
        super("SyncService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        sendBroadcast(intent)
        DanhMucDAO.getInstance().syncAll();
        
        Intent notificationIntent = new Intent(this, SplashScreenActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.green_square);
        builder.setTicker("ticker text");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("content title");
        builder.setContentText("content text");
        builder.setContentIntent(pendingIntent);
        
        Notification notification = builder.getNotification();
        
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        
        mNotificationManager.notify(1, notification);
    }
}
