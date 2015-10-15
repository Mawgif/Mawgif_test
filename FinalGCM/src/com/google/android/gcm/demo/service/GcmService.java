/*
Copyright 2015 Google Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.google.android.gcm.demo.service;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.gcm.GcmListenerService;
import com.google.gcm.Dialog;
import com.google.gcm.MainActivity;
import com.google.gcm.R;


/**
 * Service used for receiving GCM messages. When a message is received this service will log it.
 */
public class GcmService extends GcmListenerService {

    private LoggingService.Logger logger;

    public GcmService() {
        logger = new LoggingService.Logger(this);
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
    	
    	String _data=data.getString("message");
    	Notification(this, _data);
    	Intent in=new Intent(this,Dialog.class);
    	in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    	in.putExtra("message", _data);
    	startActivity(in);
        sendNotification("Received: " + data.toString());
    }

    @Override
    public void onDeletedMessages() {
        sendNotification("Deleted messages on server");
    }

    @Override
    public void onMessageSent(String msgId) {
        sendNotification("Upstream message sent. Id=" + msgId);
    }

    @Override
    public void onSendError(String msgId, String error) {
        sendNotification("Upstream message send error. Id=" + msgId + ", error" + error);
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        logger.log(Log.INFO, msg);
        
        
    }
    
	public void Notification(Context context, String message) {
		// Set Notification Title
		String strtitle = context.getString(R.string.app_name);
		// Open NotificationView Class on Notification Click
		Intent intent = new Intent(context, MainActivity.class);
		// Send data to NotificationView Class
		intent.putExtra("title", strtitle);
		intent.putExtra("text", message);
		// Open NotificationView.java Activity
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		// Create Notification using NotificationCompat.Builder
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context)
				// Set Icon
				.setSmallIcon(R.drawable.ic_launcher)
				// Set Ticker Message
				.setTicker(message)
				// Set Title
				.setContentTitle(context.getString(R.string.app_name))
				// Set Text
				.setContentText(message)
				// Add an Action Button below Notification
				.addAction(R.drawable.ic_launcher, "Action Button", pIntent)
				// Set PendingIntent into Notification
				.setContentIntent(pIntent)
				// Dismiss Notification
				.setAutoCancel(true);

		// Create Notification Manager
		NotificationManager notificationmanager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// Build Notification with Notification Manager
		notificationmanager.notify(0, builder.build());

	}
	
	
	
}
