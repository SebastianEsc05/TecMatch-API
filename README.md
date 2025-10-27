# TecMatch

## Instrucciones de Uso

Nuestro proyecto esta desarrollado como una API Rest para toda la parte de Java debido a que el front se esta desarrollando con React + TS, por lo que para este entregable desarrollamos tanto la parte de la API que sera conectada con el front, como los controllers convencionales para pruebas.

Cabe recalcar que si prefiere hacer las peticiones desde la api tendra que usar Postman para hacer las solicitudes.

### Requisitos

- Version Compilacion JDK 21
- Archivo de configuarcion local para Springboot

### Descarga del Repositorio

Puede clonar el repositorio desde su terminal con la url del repositorio o mismamente en GitHub Desktop

```terminal
git clone https://github.com/SebastianEsc05/TecMatch-API
```

## Archivo Configuracion Local

Para correrlo con Springboot debera crear el siguiente archivo con el nombre `application-local.properties`

```properties
spring.datasource.username=**Su usuario SQL**
spring.datasource.password=**Su contraseña SQL**
jwt.jwt.secret=TWVQdXNlTGFHdWNjaUNvblVuU2hvcnREZU5pa2VCdXpvQ2FkZW5hRXN0b3lRdWVHb3Rlb1NpZ29Wb2xhbmRvQ2l1ZGFkRW5DaXVkYWRUdW1iYW5kb0VsQ2x1YlNob3V0T3V0UGFyYU5lbw==
```

El archivo debe pegarse en la siguiente ruta:

```path
src/main/resources/
```

Cabe recalcar que las funcionalidades de email aun no estan disponibles con esta configuracion y simplemente esta limitada al los requisitos para este entregable.

## Inserts

- Para probar los inserts debe contar con un esquema creado en su localhost:3306
  con el nombre `TecMatch`

- Al correr la clase `Main` podra insertar las distintas entidades solicitadas en el documento

## Pruebas

- El proyecto tambien cuenta con un paquete de pruebas unitarias en las que puede probar las distintas funcionalidades solicitadas.

- Para que todo funcione es neceario correr el script de los SP en la base de datos, el script se encuentra en la raiz del repositorio.

## Capturas de los Resultados de Ejecucion
