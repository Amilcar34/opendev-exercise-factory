# exercise-factory-sysone

## Pre requisitos

 - Asegurese de tener instalado Maven 3.6.3 o superior
 - Asegurese de tener instalado JAVA 11
 
 ## Consignas generales
   1) Generar una suite de Test-Automáticos con Junit y que la aplicación tenga un Code coverage de al menos el 90%
   2) Sacar porciones de código que no se usen.
   3) Agregar porciones de código que se necesita.
   
## Consigna
 Una fábrica de automóviles produce uno de sus modelos en tres variantes, llamadas sedán, coupé y familiar. Cada una tiene un precio de venta básico sin opcionales. 
A su vez, a cada variante se le pueden agregar opciones como techo corredizo, aire acondicionado, sistema de frenos ABS, airbag y llantas de aleación. Cada uno de estos opcionales tiene un precio que se suma al básico. 
En este caso, cada auto vendrá caracterizado por su variante y podrá tener ninguno, uno o más opcionales. Asumiendo los siguientes costos:

Autos
• Básico sedán 230.000
• Básico familiar 245.000
• Básico coupé 270.000
Opcionales:
• Techo corredizo (TC) 12.000
• Aire acondicionado (AA) 20.000
• Sistemas de frenos ABS (ABS) 14.000
• Airbag (AB) 7.000
• Llantas de aleación (LL) 12.000

### Ejercicio 1
 Crear un programa con un método o función que permita calcular el costo final de un automóvil.

### Ejercicio 2
 Crear y desarrollar unos servicios en la misma que me permitan crear, modificar, eliminar y listar automóviles. Nota: Las operaciones de alta/consulta deberán almacenar/mostrar el costo del mismo.

### Ejercicio 3
 Anexar una base de datos la cual guarde los automóviles creados con la API, con su costo final y adicionales correspondientes.
 
### Ejercicio 4
 Exponer un nuevo metodo “stats” que devuelva un Json con las estadísticas de la cantidad de automóviles y las distintas características:
 ![image](https://user-images.githubusercontent.com/86163213/227006655-1044f9e1-1c02-436b-a6c5-08be5e366673.png)

### Ejercicio 5
 Instrucciones de cómo ejecutar el programa en README de github junto con la documentación.
 
## Ejecutar

 - Desde una terminal con el directorio donde se encuentra la aplicaci&oacute;n ejecute:  
   <b>mvn exec:java -D"exec.mainClass"="com.opendev.OpenDevApplication"<b>

## Bibliotecas utilizadas

- Lombok
- Vavr 

