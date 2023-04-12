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
  - Luego para Vavr, se debe pegar sus dependencias en ```pom.xml``` .

  <!-- https://mvnrepository.com/artifact/io.vavr/vavr -->
      <dependency>
        <groupId>io.vavr</groupId>
        <artifactId>vavr</artifactId>
        <version>0.9.0</version>
      </dependency>

## Funciones
### Método statsOptional
  StatsOptional pertenece a la clase ```CarRepositoryImpl.java```. Dentro, se agrupan los autos por sus opcionales en un mapa; <em>optionalGroupByCars</em>.
  En <em>optionalTotalSum</em> la funcion Lambda asigna cada objeto Car al tamaño de su lista de opcionales. Despues se reduce a un solo valor sumando los tamaños de todas las
  listas opcionales iniciando por 0. Calcula el numero total de opcionales de Cars en el map sumando el tamaño de opcionales para cada objeto.
  En el foreach, se calcula el porcentaje de opcionales para Car, se almacena y se calcula estadisticas sobre las funciones opcionales de cada Cars.

### Método statsModel
  Dentro de esta funcion, ```models``` obtiene una lista de modelos de coches únicos, toma cada valor del mapa (las claves son el id de cada coche y los valores son cada coche)
  y extrae el modelo, por asi decirlo.
  ```long count``` recupera el conjunto de entradas del mapa y asigna a cada auto su modelo. La condicion comprueba si el modelo actual es igual al modelo del otro objeto y por ultimo se cuentas cuantos cumplieron esa condición y se almacena.
  ```percent ``` multiplica a ```count``` y divide el resultado por el numero total de elementos en ```dbCars```. Esta variable representa el porcentaje de autos que tiene atributo específico. 
  En resumen, el método genera estadisticas para cada modelo de auto único, incluido el recuento y porcentaje de Cars que tienen ese modelo.

## Ejecutar

 - Desde una terminal con el directorio donde se encuentra la aplicaci&oacute;n ejecute:  
   <b>mvn exec:java -D"exec.mainClass"="com.opendev.OpenDevApplication"<b>

## Bibliotecas utilizadas

- Lombok
- Vavr 
