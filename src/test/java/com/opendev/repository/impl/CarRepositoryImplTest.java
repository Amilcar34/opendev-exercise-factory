package com.opendev.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.impl.CarRepositoryImpl;

public class CarRepositoryImplTest {

	@Test
	public void queFuncioneCorrectamenteElMetodoSave() {
		CarRepository cri = new CarRepositoryImpl();

		Model modelo = new Model(1, "Sedan", 230000.0);

		Optional opcional = new Optional();

		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		Integer id = 1;
		Car auto = new Car(id, modelo, opcionales);

		cri.save(auto);
		assertEquals(6, cri.count());
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
	public void queSePuedaActualizarUnAutoDespuesDeCrearloYCalcularSuCosto() {
		Integer idCar = 4;
		Model model = new Model(3, "Coupé", 270000.0);
		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional3 = new Optional(3, "ABS", "Sistemas de frenos ABS", 14000.0);
		Optional opcional4 = new Optional(4, "AB", "Airbag", 7000.0);
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional1);
		opcionales.add(opcional2);
		opcionales.add(opcional3);
		opcionales.add(opcional4);
		
		Car car = new Car(idCar, model, opcionales);

	}

	@Test
	public void queFuncioneCorrectamenteStatsOptional() {
		// pausa

	}

}
