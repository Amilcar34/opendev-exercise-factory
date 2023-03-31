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

//	@Test
//	public void metodoCreateUpdateYDeleteCorrectoFuncionamiento() {
//		CarService csi = new CarServiceImpl();
//		Integer idModel = 21;
//		Model model = new Model(idModel, "", 200000.00);
//
//		Optional opcional = new Optional();
//		Optional opcional2 = new Optional();
//
//		Integer idOptional1 = opcional.getId();
//		Integer idOptional2 = opcional2.getId();
//
//		Set<Integer> opcionales = new HashSet<Integer>();
//		opcionales.add(idOptional1);
//		opcionales.add(idOptional2);
//
//		// Car car = new Car();
//		Integer newId = 550;
////		car.setId(newId);
//
//		csi.create(model.getId(), opcionales);
//		csi.update(newId, idModel, opcionales);
//		assertTrue(csi.delete(newId));
//	}

}
