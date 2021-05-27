# Challenge MeLi

Aplicación construida para que suministrada una dirección IP, encuentre el país al que pertenece y la informaciòn a detalle del pais, asi mismo muestre la informacion relaciona

● El nombre y código.

● Los idiomas.

● Zonas horarias y horas.

● Distancia estimada en km.

● Moneda local y equivalencia en dolares.

Asi como estadisticas de invocacion del servicio, distancias y promedios.

## Comenzando 🚀

Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

```
git clone https://github.com/paguerrero1122/PaisesService.git
```


### Pre-requisitos 📋

Las herramientas necesarias para ejecutar el proyecto son:

● Docker

● Maven


### Instalación 🔧

Una vez clonado este repositorio es necesario construir el archivo .jar que contiene la soluciòn y ejecutar docker compose que permite construir las imagenes y subir los contenedores utilizados.
Abrir una consola de comandos y ejecutar el siguiente comando

```
mvn clean install -Dmaven.test.skip && docker-compose up --build
```
De esta forma quedaran expuestos los servicios Rest de Spring Boot y el servidor Redis necesario para funcionamiento correcto de la aplicaciòn.

## Ejecutando las pruebas ⚙️

Una vez terminada la instalaciòn es hora de realizar la prueba.
Abrir una ventana de navegador de preferencia y direccionar a:

```
http://localhost:8080/localizacion.html
```
Aparecera una sencilla pagina donde realizar la prueba como la vista en siguiente imagen:

https://raw.githubusercontent.com/paguerrero1122/PaisesService/main/front.png

Ya solo que realizar algunas pruebas ingresando algunas ips

Aqui algunas pruebas:

https://raw.githubusercontent.com/paguerrero1122/PaisesService/main/Prueba1.png
https://raw.githubusercontent.com/paguerrero1122/PaisesService/main/Prueba2.png
https://raw.githubusercontent.com/paguerrero1122/PaisesService/main/Prueba3.png
https://raw.githubusercontent.com/paguerrero1122/PaisesService/main/Prueba4.png

## Construido con 🛠️

* [Spring boot + Java 1.8] Tomcat Auto embebido
* [Maven]
* [Docker]
* [Redis] para acceso rapido a BD

## Autores ✒️

* **Pedro Alejandro Guerrero** 
