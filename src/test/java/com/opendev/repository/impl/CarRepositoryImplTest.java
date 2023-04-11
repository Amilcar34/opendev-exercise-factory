package com.opendev.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.ModelRepository;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.CarRepositoryImpl;
import com.opendev.service.CarService;
import com.opendev.service.impl.CarServiceImpl;

public class CarRepositoryImplTest {

	@Test
	public void queFuncioneCorrectamenteElMetodoSave() {
		CarRepository cri = new CarRepositoryImpl();

		Model modelo = new Model(1, "Sedan", 230000.0);

		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);

		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		Integer id = 1;
		Car auto = new Car(id, modelo, opcionales);

		cri.save(auto);
		assertEquals(cri.count(), cri.count());
		cri.deleteById(id);
	}

	@Test
	public void queSePuedaGuardarUnAutoYDespuesBorrarlo() {
		CarRepository cri = new CarRepositoryImpl();
		// setup
		Model model = new Model(2, "Familiar", 270000.0);
		Optional optional1 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional optional2 = new Optional(4, "AB", "Airbag", 7000.0);
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(optional1);
		opcionales.add(optional2);
		Car auto = new Car(3, model, opcionales);
		// exercise
		cri.save(auto);
		cri.deleteById(auto.getId());
		boolean resultado = cri.existsById(auto.getId());
		// verify
		assertFalse(resultado);
	}

	@Test
	public void queFuncioneCorrectamenteStatsModel() {
		/*
		 * models obtiene una lista de modelos de coches únicos, toma cada valor del
		 * mapa(las claves son el id de cada coche y los vlaores son cada coche) y
		 * extrae el modelo
		 */

		/*
		 * long count recupera el conjunto de entradas del mapa y asigna a cada auto su
		 * modelo. la condicion comprueba si el modelo actual es igual al modelo del
		 * otro objeto y por ultimo se cuentan cuantos cumplieron esa condicion y se
		 * almacena
		 */

		/*
		 * percent multiplica a count y divide el resultado por el numero total de
		 * elementos en dbcars. percent representa el porcentaje de autos que tiene
		 * atributo especifico
		 */

		/*
		 * en resumen el metodo genera estadisticas para cada modelo de auto unico,
		 * incluido del recuento y porcentaje de cars que tienen ese modelo
		 */
		
		// setup
		Model model = new Model(2, "Familiar", 270000.0);
		Optional optional1 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional optional2 = new Optional(4, "AB", "Airbag", 7000.0);
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(optional1);
		opcionales.add(optional2);

		Integer id = 3;
		Car auto = new Car(id, model, opcionales);

		// exercise
		CarRepository cr = new CarRepositoryImpl();
		cr.save(auto);
		int cantCarsParaCadaModelo = 3;
		// si no creo y guardo un auto, entonces la variable cantCarsParaCadaModelo
		// almacena el valor de 3.
		
		// verify
		assertEquals(cantCarsParaCadaModelo, cr.statsModel().size());
		cr.deleteById(id);

	}

	@Test
	public void queFuncioneCorrectamenteStatsOptional() {
		/* optionalGroupByCars es un mapa que agrupo los autos por sus optionals */
		/*
		 * optionalTotalSum: la funcion lamba asigna cada objeto car al tamaño de su
		 * lista de opcionales. despues se reduce a un solo valor sumando los tamaños de
		 * todas las listas opcionales iniciando por 0} calcula el numero total de
		 * opcionales de cars en el map sumando el tamaño de opcionales para cada
		 * objeto.
		 */
		/*
		 * en el foreach, se calcula el porcentaje de opcionales para car se almacena y
		 * calcula estadisticas sobre las funciones opcionales de cada cars.
		 */
		
		CarRepository cr = new CarRepositoryImpl();
		CarService cs = new CarServiceImpl();
		
		int cantDeOpcionalesUsados = 3;
		assertEquals(cantDeOpcionalesUsados, cr.statsOptional().size());

	}

}
