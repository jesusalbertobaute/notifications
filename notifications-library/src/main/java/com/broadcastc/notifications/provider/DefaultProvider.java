package com.broadcastc.notifications.provider;

import java.util.HashMap;
import java.util.Map;

import com.broadcastc.notifications.model.ChannelType;
import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.Notification;

public class DefaultProvider implements Provider {
	
	private final String name;
	private final ChannelType channelType;
    private final NotificationProvider notificationProvider;
    

    DefaultProvider(String name,
    		ChannelType channelType,
            NotificationProvider notificationProvider) {
		this.name = name;
		this.channelType = channelType;
		this.notificationProvider = notificationProvider;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public DeliveryResult sendNotification(Map<String,String> configMetadata,Notification notification, 
			NotificationProvider notificationProvider) {
		return notificationProvider.send(notification);
	}

	@Override
	public ChannelType channelType() {
		return channelType;
	}

	@Override
	public DeliveryResult sendNotification(Notification notification,NotificationProvider notificationProvider) {
		return notificationProvider.send(notification);
	}


}
