package com.notification_rest_client.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.broadcastc.notifications.api.NotificationEvent;
import com.broadcastc.notifications.api.NotificationService;
import com.broadcastc.notifications.model.ChannelType;
import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.Notification;
import com.broadcastc.notifications.provider.Provider;
import com.broadcastc.notifications.provider.ProviderBuilder;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	private final NotificationService notificationService;

	public NotificationController() {
		notificationService = new NotificationService();
	}
	
	@PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody String message) {
		Provider emailProvider = ProviderBuilder.builder()
			    .name("smtp-provider")
			    .channelType(new ChannelType("EMAIL"))
			    .notificationProvider(notification ->
			        DeliveryResult.sent("Email enviado correctamente")
			    )
			    .build();
		
		Notification notification = Notification.builder()
				.id(UUID.randomUUID().toString())
                .content(message)
                .from("Emisor")
                .to("Receptor")
                .addMetadata("subject", "Esto es una prueba")
                .build();
	
		NotificationEvent notificationEventSuccess = new NotificationEvent(emailProvider, (n)->{
		    return DeliveryResult.sent("La Notificacion :" + n.id() + "se ha enviado con exito");
		});
		
		
		this.notificationService.subscribe(notificationEventSuccess);
		
		this.notificationService.sendNotification(notification);
		
		return ResponseEntity.status(HttpStatus.OK).body("Mensaje Enviado");

    }
	
	@GetMapping
    public ResponseEntity<DeliveryResult> getNotificationStatus() {
		
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.pollResult());

    }

}
