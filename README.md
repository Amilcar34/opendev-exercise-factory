# exercise-factory-sysone

## Pre requisitos

 - Asegurese de tener instalado Maven 3.6.3 o superior
 - Asegurese de tener instalado JAVA 11

## Descripción
  Proyecto de OpenDev creado para evaluar el testing del usuario. Se implementa el uso de dos librerias para reducir el código y mejorarlo, ahorrar tiempo y aumentar la legibilidad.

## Criterios

  <ol>
  <li>Generar una suite de Test-Automáticos con Junit y que la aplicación tenga un Code coverage de al menos el 90%</li>
  <li>Sacar porciones de código que no se usen.</li>
  <li>Agregar porciones de código que se necesita.</li>
  </ol>

## Consigna general
  Disponible para su realización en ```documentos/CONSIGNA.docx```.


## Cambios para su compilación
  Para compilar el proyecto, es necesario agregar las bibliotecas pedidas más abajo. 
  - Para ejecutar Lombok en Eclipse, se puede instalar desde Install New Software. Se incluye como parte de la configuración de implementación.
      Se agrega el siguiente link en la opción de <em>Location</em>: https://projectlombok.org/p2 y se finaliza el proceso de intalación reiniciando el IDE.
  - Luego pegar sus dependencias en ```pom.xml``` .

  <!-- https://mvnrepository.com/artifact/io.vavr/vavr -->
      <dependency>
        <groupId>io.vavr</groupId>
        <artifactId>vavr</artifactId>
        <version>0.9.0</version>
      </dependency>



## Ejecutar

 - Desde una terminal con el directorio donde se encuentra la aplicaci&oacute;n ejecute:  
   <b>mvn exec:java -D"exec.mainClass"="com.opendev.OpenDevApplication"<b>

## Bibliotecas utilizadas

- Lombok
- Vavr 
