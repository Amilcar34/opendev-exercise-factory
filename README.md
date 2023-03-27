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
  Al importar el proyecto, se podían ver varios errores debido a que parecia que faltaban varias cosas. Nunca iba a poder compilarlo ni poder solucionar los problemas manualmente porque no se permite la edición del codigo source. 
  Sin embargo, Lombok no estaba instalado en mi IDE, si estaba su dependencia en el archivo pom.xml. 
  Varios de los errores de compilacion pude solucionarlos con la correcta instalación de las librerias. 
  Con vavr fue similar, con la diferencia de que tuve que cambiar su dependencia en el archivo y actualizar el IDE. Los errores desaparecieron cuando se actualizo todo en Maven Dependencies.


## Ejecutar

 - Desde una terminal con el directorio donde se encuentra la aplicaci&oacute;n ejecute:  
   <b>mvn exec:java -D"exec.mainClass"="com.opendev.OpenDevApplication"<b>

## Bibliotecas utilizadas

- Lombok
- Vavr 
