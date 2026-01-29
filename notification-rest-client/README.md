##Descripción General
Este es un ejemplo de cliente para usar la libreria notifications-library. Se uso Spring Boot sólo para probar la integración de la libreria y para que sea más amigable de probar.

###Endpoints
####Para Agregar una notificación:
```bash
curl --location 'http://localhost:8080/api/notification' \
--header 'Content-Type: application/json' \
--data '"Message a enviar"'
```
####Para extraer el estado de la notificación cola de estados:
```bash
curl --location 'http://localhost:8080/api/notification'
```

