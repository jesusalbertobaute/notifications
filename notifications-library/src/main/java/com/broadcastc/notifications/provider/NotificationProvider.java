package com.broadcastc.notifications.provider;

import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.Notification;

@FunctionalInterface
public interface NotificationProvider {
	DeliveryResult send(Notification notification);
}
