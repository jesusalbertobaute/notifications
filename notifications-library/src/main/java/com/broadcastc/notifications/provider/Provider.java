package com.broadcastc.notifications.provider;

import java.util.Map;

import com.broadcastc.notifications.model.ChannelType;
import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.Notification;

public interface Provider {
	String name();
	ChannelType channelType();
	DeliveryResult sendNotification(Map<String,String> configMetadata,
			Notification notification,NotificationProvider notificationProvider);
	DeliveryResult sendNotification(Notification notification,NotificationProvider notificationProvider);
}
