package com.opendev.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;

public class CarRepositoryImplTest {

	CarRepository criMock = mock(CarRepositoryImpl.class);
	
	@Test
	public void vSave() {

		Model modelo = new Model(1, "Sedan", 230000.0);
		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);
		
		Set<Optional> opcionales = Set.of(opcional);
		
		Car auto = new Car(1, modelo, opcionales);
		
		when(criMock.save(auto)).thenReturn(auto);
		
		assertEquals(auto, criMock.save(auto));
	}

	@Test
	public void vDeleteById() {

		Model model = new Model(2, "Familiar", 270000.0);
		Optional optional1 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional optional2 = new Optional(4, "AB", "Airbag", 7000.0);
		
		Set<Optional> opcionales = Set.of(optional1, optional2);
		
		Car auto = new Car(3, model, opcionales);
	
		when(criMock.deleteById(auto.getId())).thenReturn(true);
		
		assertTrue(criMock.deleteById(auto.getId()));
	}

	@Test
	public void statsModel() {
		
		CarRepository criMock = new CarRepositoryImpl();
		
		int cantCarsParaCadaModelo = 3;
		// si no creo y guardo un auto, entonces la variable cantCarsParaCadaModelo
		// almacena el valor de 3.
		
		assertEquals(cantCarsParaCadaModelo, criMock.statsModel().size());

	}

	@Test
	public void statsOptional() {
		
		CarRepository cr = new CarRepositoryImpl();
		//CarRepository criMock = mock(CarRepositoryImpl.class);
		
		int cantDeOpcionalesUsados = 4;
		//when(criMock.statsOptional().size()).thenReturn(4);
		assertEquals(cantDeOpcionalesUsados, cr.statsOptional().size());

	}

}
