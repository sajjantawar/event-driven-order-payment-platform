package com.sajjantawar.notification;
import org.springframework.kafka.annotation.KafkaListener;import org.springframework.stereotype.Component;
@Component public class NotificationListener {
 @KafkaListener(topics={"payment.result","inventory.result"},groupId="notification-service")
 public void onEvent(Object event){System.out.println("Notification event received: "+event);}
}