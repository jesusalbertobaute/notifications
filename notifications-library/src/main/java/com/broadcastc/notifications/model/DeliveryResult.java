package com.broadcastc.notifications.model;

import com.broadcastc.notifications.provider.Provider;

public record DeliveryResult(
		DeliveryStatus deliveryStatus,
		String description) {
	public static DeliveryResult sent(String description) {
		return new DeliveryResult(DeliveryStatus.SENT,description);
	}
	
	public static DeliveryResult failed(String description) {
		return new DeliveryResult(DeliveryStatus.FAILED,description);
	}
	
	public static DeliveryResult pending(String description) {
		return new DeliveryResult(DeliveryStatus.PENDING,description);
	}

}
