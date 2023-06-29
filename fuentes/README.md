## Requisitos mínimos:
* JAVA 11
* Postgresql 14.x
* Maven


## Inicialización manual de la base de datos.
1.- Crea una nueva bases de datos en postgresql.
Nota:
* Procura que el usuario de la base de datos tenga los permisos necesarios para crear tablas y ejecutar scripts.


2.- En el archivo application.properties, modifica las siguientes propiedades:
```
spring.datasource.url=jdbc:postgresql://<host:puerto>/<nombre de la base de datos>
spring.datasource.username=<usuario de la base de datos>
spring.datasource.password=<contraseña del usuario de la base de datos>
```

## Ejecutar la aplicación localmente.
1.- Ejecuta estos dos comandos en la raíz del proyecto:
```
mvn clean install
```

```
mvn spring-boot:run
```

2.- La aplicación se ejecutará en el puerto 8080. En el navegador, ingresa a la siguiente url para revisar la documentación de la API:
```
http://localhost:8080/swagger-ui/index.html
```

## Pruebas unitarias.
El proyecto incluye pruebas unitarias, para ejecutarlas, ejecuta el siguiente comando en la raíz del proyecto:
```
mvn test
```