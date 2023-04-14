package com.opendev.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;

public class CarRepositoryImplTest {

	@Test
	public void queFuncioneCorrectamenteElMetodoSave() {
		CarRepository criMock = mock(CarRepositoryImpl.class);

		Integer id = 1;
		Model modelo = new Model(1, "Sedan", 230000.0);
		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		
		Car auto = new Car(id, modelo, opcionales);
		
		when(criMock.save(auto)).thenReturn(auto);

		//verify(criMock).save(auto);
		
		//assertEquals(, cri.count());
	}

	@Test
	public void borrarUnAuto() {
		
		CarRepository criMock = mock(CarRepositoryImpl.class);
		// setup
		Model model = new Model(2, "Familiar", 270000.0);
		Optional optional1 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional optional2 = new Optional(4, "AB", "Airbag", 7000.0);
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(optional1);
		opcionales.add(optional2);
		
		Car auto = new Car(3, model, opcionales);
		// exercise
		when(criMock.existsById(auto.getId())).thenReturn(false);
		
		//verify(criMock).existsById(auto.getId());
		
		assertFalse(criMock.existsById(auto.getId()));
	}

	@Test
	public void queFuncioneCorrectamenteStatsModel() {
		
		CarRepository criMock = new CarRepositoryImpl();
		
		int cantCarsParaCadaModelo = 3;
		// si no creo y guardo un auto, entonces la variable cantCarsParaCadaModelo
		// almacena el valor de 3.
		
		assertEquals(cantCarsParaCadaModelo, criMock.statsModel().size());

	}

	@Test
	public void queFuncioneCorrectamenteStatsOptional() {
		
		CarRepository cr = new CarRepositoryImpl();
		//CarRepository criMock = mock(CarRepositoryImpl.class);
		
		int cantDeOpcionalesUsados = 4;
		//when(criMock.statsOptional().size()).thenReturn(4);
		assertEquals(cantDeOpcionalesUsados, cr.statsOptional().size());

	}

}
