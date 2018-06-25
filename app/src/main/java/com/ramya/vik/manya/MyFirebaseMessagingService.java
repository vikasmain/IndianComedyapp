package com.ramya.vik.manya;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.RemoteMessage;
//Base class for receiving messages from Firebase Cloud Messaging.
//
//Extending this class is required to be able to handle downstream messages. It also provides functionality to automatically display notifications, and has methods that are invoked to give the status of upstream messages.
//
//Override base class methods to handle any events required by the application. Methods are invoked on a background thread.
//it contains various functions like onMessageReceived(),onMessageSent(),onMessageDeleted().

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "Dev Sir";
      SharedPreferences sharedPreferences;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Called when a message is received.

        //This is also called when a notification message is received while the app is in the foreground.
        // The notification parameters can be retrieved with getNotification().

        Log.e(TAG, "Message Body: " + remoteMessage.getNotification().getBody());
        if (remoteMessage.getData().size() > 0) {

            final String title, img_url, url, message;
            title = remoteMessage.getData().get("title");
            img_url = remoteMessage.getData().get("img_url");
            message = remoteMessage.getData().get("message");
            url = remoteMessage.getData().get("url");
            sharedPreferences=getApplicationContext().getSharedPreferences("urlname",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("url",url);
            editor.commit();
            Intent intent = new Intent(this, Notify.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.cool)
                    .setContentTitle(title)
                    .setContentText(message)

                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
            ImageRequest stringRequest = new ImageRequest(
                    img_url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                        notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                            NotificationManager notificationManager =
                                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                            notificationManager.notify(0, notificationBuilder.build());


                        }
                    },0,0,null,Bitmap.Config.RGB_565,
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something when get error
                        }
                    }
            );

            // Add StringRequest to the RequestQueue
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);




        }
    }
}

