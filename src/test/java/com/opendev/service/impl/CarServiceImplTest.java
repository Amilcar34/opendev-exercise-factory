package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.JUnit4;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.service.CarService;

@RunWith(JUnit4ClassRunner.class)

public class CarServiceImplTest {
	

	@Test
	public void calcularElCostoCuandoIdsOptionalsEsNull() {
		// setup
		CarService cs = new CarServiceImpl();
		Model model = new Model(1, "Sedán", 230000.0);
		Integer idModel = model.getId();

		// exercise
		Double resultado = cs.calculateCost(idModel, null);
		Double resultadoEsperado = 230000.0;
		// verify
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalEstaVacio() {
		CarService cs = new CarServiceImpl();
		Model model = new Model(2, "Familiar", 245000.0);
		Set<Integer> idsOptionals = new HashSet<>();

		Integer idModel = model.getId();
		Double resultadoObtenido = cs.calculateCost(idModel, idsOptionals);
		Double resultadoEsperado = 245000.0;
		assertEquals(resultadoEsperado, resultadoObtenido);

	}

	@Test
	public void calcularElCostoCorrectoFuncionamiento() {
		CarService cs = new CarServiceImpl();
		Model model = new Model(3, "Coupé", 270000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional2 = new Optional(3, "ABS", "Sistemas de frenos ABS", 14000.0);

		Set<Integer> idsOptionals = new HashSet<>();
		idsOptionals.add(opcional.getId());
		idsOptionals.add(opcional2.getId());

		Integer idModel = model.getId();

		Double resultadoObtenido = cs.calculateCost(idModel, idsOptionals);
		Double resultadoEsperado = model.getCost() + opcional.getCost() + opcional2.getCost();
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void queSePuedaCrearYBorrarUnAuto() {

		Integer id = 2;
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
		//cs.create(model.getId(), idOptionals);
		// verify
		assertTrue(cs.delete(car.getId()));

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
		Double resultadoObtenido = cs.calculateCost(model.getId(), idOpcionales);
		Double resultadoEsperado = 328000.0;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

}
