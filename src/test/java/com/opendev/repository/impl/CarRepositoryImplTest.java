package com.opendev.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;

public class CarRepositoryImplTest {

	CarRepository carRepositoryImpl = new CarRepositoryImpl();

	@Test
	public void vSave() {

		Model modelo = new Model(3, "Coupé", 270000.0);
		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional3 = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);
		Optional opcional4 = new Optional(4, "LL", "Llantas de aleación", 12000.0);

		Set<Optional> opcionales = Set.of(opcional1, opcional2, opcional3, opcional4);

		Car auto = new Car(4, modelo, opcionales);

		Assertions.assertEquals(auto, carRepositoryImpl.save(auto));
	}

	@Test
	public void vDeleteById() {

		Model model = new Model(2, "Familiar", 270000.0);
		Optional optional1 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional optional2 = new Optional(4, "AB", "Airbag", 7000.0);

		Set<Optional> opcionales = Set.of(optional1, optional2);

		Car auto = new Car(3, model, opcionales);

		carRepositoryImpl.deleteById(auto.getId());
		Assertions.assertFalse(carRepositoryImpl.existsById(auto.getId()));
	}

	@Test
	public void vExistsById() {

		Model modelo = new Model(1, "Sedan", 230000.0);
		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);

		Set<Optional> opcionales = Set.of(opcional);

		Car auto = new Car(1, modelo, opcionales);

		Assertions.assertTrue(carRepositoryImpl.existsById(auto.getId()));
	}

	@Test
	public void vGetOne() {
		
		Model model = new Model(1, "Sedán", 230000.0);
		Optional optional1 = new Optional(4, "LL", "Llantas de aleación", 12000.0); 
		Optional optional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0); 
		Set<Optional> optionals = Set.of(optional2, optional1);
		
		Car car = new Car(1, model, optionals);
		
		Assertions.assertEquals(car, carRepositoryImpl.getOne(1));
	}

	@Test
	public void vCount() {

		Assertions.assertEquals(4, carRepositoryImpl.count());
	}

	@Test
	public void statsModel() {

		CarRepository cri = new CarRepositoryImpl();

		int cantCarsParaCadaModelo = 2;
		// si no creo y guardo un auto, entonces la variable cantCarsParaCadaModelo
		// almacena el valor de 3.

		Assertions.assertEquals(cantCarsParaCadaModelo, cri.statsModel().size());

	}

	@Test
	public void statsOptional() {

		CarRepository cri = new CarRepositoryImpl();
		// CarRepository criMock = mock(CarRepositoryImpl.class);

		int cantDeOpcionalesUsados = 4;
		// when(criMock.statsOptional().size()).thenReturn(4);
		Assertions.assertEquals(cantDeOpcionalesUsados, cri.statsOptional().size());

	}

}
