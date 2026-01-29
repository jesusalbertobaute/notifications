package com.broadcastc.notifications.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.broadcastc.notifications.model.DeliveryResult;
import com.broadcastc.notifications.model.Notification;

public class NotificationService {
   private List<NotificationEvent> events = new ArrayList<>();
   private BlockingQueue<DeliveryResult> queue = new LinkedBlockingQueue<>();
   
   public boolean subscribe(NotificationEvent notificationEvent) {
	   return events.add(notificationEvent);
   }
   
   public boolean unsuscribe(NotificationEvent notificationEvent) {
	   return events.remove(notificationEvent);
   }
   
   public void sendNotification(Notification notification) {
	   for (NotificationEvent e:events) {
		   queue.offer(e.sendNotification(notification));
	   }
   }
   
   public DeliveryResult pollResult() {
	   return queue.poll();
   }

}
