# Ejecutar Notification REST Client con Docker
  
Este README explica como construir y ejecutar el cliente usando Docker.

---
## Construccion de la imagen Docker

Desde la raiz del proyecto, ejecuta:

```bash
docker build -t notifications:1.0 .

```
## Ejecutar el cliente con Docker
```bash
docker run -p 8080:8080 notifications:1.0
```

# Importante
- Para detalles acerca de la libreria ver notification-library/README.md 
- Para detalles acerca del cliente ver notification-rest-client/README.md 

