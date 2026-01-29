# Notification Library

## 📌 Descripción General

Esta librería provee una forma simple, extensible y desacoplada de enviar notificaciones a través de distintos canales y proveedores, manteniendo una arquitectura orientada a eventos.

El diseño se basa en los siguientes conceptos:
- Notification: mensaje a enviar
- Provider: proveedor concreto de notificaciones (Email, SMS, Push, etc.)
- NotificationProvider: lógica real de envío
- NotificationEvent: vínculo entre proveedor y envío
- NotificationService: orquestador central
- DeliveryResult: resultado del envío

Permite registrar múltiples proveedores, enviar notificaciones a todos los suscriptos y procesar resultados de manera asincrónica.

---

## 🧩 Componentes Principales

### Notification

```java
Notification notification = Notification.builder()
    .id("123")
    .from("system")
    .to("user@email.com")
    .content("Hola mundo")
    .addMetadata("priority", "high")
    .build();
```

### ChannelType

```java
ChannelType emailChannel = new ChannelType("EMAIL");
```
### DeliveryResult

```java
DeliveryResult result = DeliveryResult.sent("Mensaje enviado correctamente");
```
Estados posibles:
- SENT
- FAILED
- PENDING

### Provider

```java
public interface Provider {
    String name();
    ChannelType channelType();
    DeliveryResult sendNotification(
        Notification notification,
        NotificationProvider notificationProvider
    );
}
```

### NotificationProvider

```java
@FunctionalInterface
public interface NotificationProvider {
    DeliveryResult send(Notification notification);
}
```

### NotificationEvent

```java
NotificationEvent event =
    new NotificationEvent(provider, notificationProvider);
```

### NotificationService

```java
NotificationService service = new NotificationService();
```
Métodos principales:
- subscribe(NotificationEvent)
- unsuscribe(NotificationEvent)
- sendNotification(Notification)
- pollResult()

## 🚀 Guía de Uso

### Crear un Provider

```java
Provider emailProvider = ProviderBuilder.builder()
    .name("smtp-provider")
    .channelType(new ChannelType("EMAIL"))
    .notificationProvider(notification ->
        DeliveryResult.sent("Email enviado correctamente")
    )
    .build();
```

### Registrar el Provider

```java
NotificationEvent event =
    new NotificationEvent(emailProvider, emailProvider::sendNotification);

NotificationService service = new NotificationService();
service.subscribe(event);
```

### Enviar una notificación

```java
service.sendNotification(notification);
```

### Obtener resultados

```java
DeliveryResult result;
while ((result = service.pollResult()) != null) {
    System.out.println(result.deliveryStatus() + " - " + result.description());
}

```

## Extensión de la Librería

### Agregar un nuevo canal
```java
ChannelType smsChannel = new ChannelType("SMS");
```

### Crear un nuevo proveedor
```java
Provider smsProvider = ProviderBuilder.builder()
    .name("twilio")
    .channelType(new ChannelType("SMS"))
    .notificationProvider(notification ->
        DeliveryResult.sent("SMS enviado")
    )
    .build();
```

### Múltiples proveedores por canal
```java
service.subscribe(new NotificationEvent(providerA, providerA::sendNotification));
service.subscribe(new NotificationEvent(providerB, providerB::sendNotification));
```

## 🧪 Testing
La librería es fácilmente testeable usando mocks de Provider y NotificationProvider.

Compatible con:
- JUnit 5
- Mockito

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT.

Copyright (c) 2026 Tu Nombre o Organización

Se permite, de forma gratuita, a cualquier persona que obtenga una copia de este
software y de los archivos de documentación asociados, utilizar el software sin
restricción, incluyendo sin limitación los derechos a usar, copiar, modificar,
fusionar, publicar, distribuir, sublicenciar y/o vender copias del software.

El software se proporciona "tal cual", sin garantía de ningún tipo, expresa o
implícita. Ver el archivo LICENSE para más detalles.