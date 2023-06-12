# Compilación e instalación

Pre-requisitos: Tener instalado el jdk de java 17
Tener instalada la base de datos PostgreSQL
ejecutar en base de datos el script (Solo basta crear la base de datos):  

CREATE DATABASE evol
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'

Abrir el proyecto con un IDE (Recomendado NetBeans)

Modificar la contraseña para conectarse a la base de datos: en el archivo EVOL > Other Sources > src/main/resources >  application.properties Aquí alteramos la linea 8 con la password de la BD instalada localmente. spring.datasource.password=password

# Compilación (Recomendado)

Click derecho sobre la aplicación (EVOL) Y seleccionar la opción Clean and Build (Esto permite descargar las librerias que utiliza el proyecto)

Buscar el main del proyecto (Evol > com.evol > EvolApplication.java) sobre este archivo dar click en RUN FILE

# Instalación

Nos posisionamos en la raíz del proyecto EVOL y abrimos una consola CMD

Ejcutamos el comando mvn package
abrimos la carpeta target con el comando cd target
Ejecutamos la aplicación creada con el comando java -jar EVOL-0.0.1-SNAPSHOT.jar (O corroborar si el programa asignó otro nombre a la aplicación)

# Probar aplicación

Se ha generado documentación Swagger la que puede ser vista desde la URL http://localhost:8080/swagger-ui/index.html
Aquí se pueden probar los diferentes métodos de la aplicación.

Cómo ejemplo tenemos la siguiente secuencia:

## Client

POST
{
    "cli_rut": "11.111.111-1",
    "cli_nombre": "Prueba Nombre",
    "cli_razon_social": "Nombre Empresa",
    "cli_fecha_inicio_firma": "2023-06-11"
}

-- Ejecutar

{
    "cli_rut": "12.123.123-1",
    "cli_nombre": "Prueba 2",
    "cli_razon_social": "Nombre Empresa",
    "cli_fecha_inicio_firma": "2023-06-11"
}

--Ejecutar

PUT

{
  "cli_fecha_inicio_firma": "2023-06-10",
  "cli_nombre": "Prueba Nombre Actualizar",
  "cli_razon_social": "Nueva empresa",
  "cli_rut": "11.111.111-1"
}

GET

--Ejecutar

DELETE

{
  "cli_rut": "12.123.123-1"
}



## Meter

POST

{
  "cli_rut": {
    "cli_rut": "11.111.111-1"
  },
  "med_direccion": "string",
  "med_numero_instalacion": 0
}


PUT

{
    "id_medidor": 1,
    "cli_rut": {
      "cli_rut": "11.111.111-1"
    },
    "med_direccion": "Otra Dirección",
    "med_numero_instalacion": 11111
}

GET

--Ejecutar

DELETE

{
  "id_medidor": 1
}
