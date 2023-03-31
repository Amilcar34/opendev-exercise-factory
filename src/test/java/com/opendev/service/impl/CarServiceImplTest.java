package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.CarRepositoryImpl;
import com.opendev.repository.impl.OptionalRepositoryImpl;
import com.opendev.service.CarService;
import com.opendev.service.impl.CarServiceImpl;

public class CarServiceImplTest {

	public void queSePuedaCrearYBorrarUnAuto() {

		Integer id = 1;
		Model model = new Model(1, "Sedán", 230000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Set<Optional> opcionals = new HashSet<Optional>();
		opcionals.add(opcional);

		Car car = new Car(id, model, opcionals);

		Set<Integer> idOptionals = new HashSet<Integer>();
		idOptionals.add(opcional.getId());
		// setup
		CarService cs = new CarServiceImpl();

		// exercise
		cs.create(model.getId(), idOptionals);
		boolean resultadoEsperado = cs.delete(car.getId());
		// verify
		assertTrue(resultadoEsperado);

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
		
		Set<Integer> idOpcionales = new HashSet<>();
		idOpcionales.add(opcional1.getId());
		idOpcionales.add(opcional2.getId());
		idOpcionales.add(opcional3.getId());
		idOpcionales.add(opcional4.getId());

		Car car = new Car(idCar, model, opcionales);
		
		CarService cs = new CarServiceImpl();
		cs.create(model.getId(), idOpcionales);
		cs.update(2, 1, idOpcionales);

	}

}
