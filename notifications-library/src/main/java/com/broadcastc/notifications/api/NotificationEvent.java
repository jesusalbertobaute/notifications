package com.broadcastc.notifications.api;

import java.util.Objects;

import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.Notification;
import com.broadcastc.notifications.provider.NotificationProvider;
import com.broadcastc.notifications.provider.Provider;

public class NotificationEvent {
	
	private Provider provider;
	private NotificationProvider notificationProvider;

	public NotificationEvent(Provider provider,NotificationProvider notificationProvider) {
		this.provider = provider;
		this.notificationProvider = notificationProvider;
	}
	
	public DeliveryResult sendNotification(Notification notification) {
		return this.provider.sendNotification(notification, this.notificationProvider);
	}

	@Override
	public int hashCode() {
		return Objects.hash(provider.channelType() + provider.name());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationEvent other = (NotificationEvent) obj;
		return Objects.equals(provider.channelType() + provider.name(),
				other.provider.channelType() + other.provider.name());
	}
	
	

}
