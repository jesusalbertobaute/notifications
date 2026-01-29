package com.broadcastc.notifications.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.broadcastc.notifications.model.ChannelType;
import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.DeliveryStatus;
import com.broadcastc.notifications.model.Notification;
import com.broadcastc.notifications.provider.NotificationProvider;
import com.broadcastc.notifications.provider.Provider;
import com.broadcastc.notifications.provider.ProviderBuilder;

public class NotificationServiceTest {
	
	private NotificationService notificationService;
	
	@BeforeEach
	public void setup() {
		this.notificationService = new NotificationService();
	}
	
	@Test
	public void testSendNotification() {
		Notification notification = Notification.builder()
				                    .content("Prueba")
				                    .from("Emisor")
				                    .to("Receptor")
				                    .build();
		Provider provider = ProviderBuilder.builder()
				            .name("twilio")
				            .channelType(new ChannelType("SMS"))
				            .build();
		NotificationEvent notificationEventSuccess = this.createNotificationEvent(provider, (n)->{
			return DeliveryResult.sent(n.id() + ":Success");
		});
		
		NotificationEvent notificationEventFailed = this.createNotificationEvent(provider, (n)->{
			return DeliveryResult.failed(n.id() + ":Failed");
		});
		this.notificationService.subscribe(notificationEventSuccess);
		this.notificationService.subscribe(notificationEventFailed);
		
		this.notificationService.sendNotification(notification);
	    
		assertNotNull(this.notificationService.pollResult());
	}
	
	@Test
	public void testSendNotificationSucess() {
		Notification notification = Notification.builder()
				                    .content("Prueba")
				                    .from("Emisor")
				                    .to("Receptor")
				                    .build();
		Provider provider = ProviderBuilder.builder()
				            .name("twilio")
				            .channelType(new ChannelType("SMS"))
				            .build();
		NotificationEvent notificationEventSuccess = this.createNotificationEvent(provider, (n)->{
			return DeliveryResult.sent(n.id() + ":Success");
		});
		
		
		this.notificationService.subscribe(notificationEventSuccess);
		
		this.notificationService.sendNotification(notification);
	    
		DeliveryResult deliveryResult = this.notificationService.pollResult();
		
		assertNotNull(deliveryResult);
		assertEquals(deliveryResult.deliveryStatus(),DeliveryStatus.SENT);
	}
	
	@Test
	public void testSendNotificationFailed() {
		Notification notification = Notification.builder()
				                    .content("Prueba")
				                    .from("Emisor")
				                    .to("Receptor")
				                    .build();
		Provider provider = ProviderBuilder.builder()
				            .name("twilio")
				            .channelType(new ChannelType("SMS"))
				            .build();
		NotificationEvent notificationEventFailed = this.createNotificationEvent(provider, (n)->{
			return DeliveryResult.failed(n.id() + ":Failed");
		});
		
		
		this.notificationService.subscribe(notificationEventFailed);
		
		this.notificationService.sendNotification(notification);
	    
		DeliveryResult deliveryResult = this.notificationService.pollResult();
		
		assertNotNull(deliveryResult);
		assertEquals(deliveryResult.deliveryStatus(),DeliveryStatus.FAILED);
	}
	
	private NotificationEvent createNotificationEvent(
			 Provider provider,
			 NotificationProvider notificationProvider
	) {
		return new NotificationEvent(provider,notificationProvider);
	}

}
