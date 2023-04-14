package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.impl.CarRepositoryImpl;
import com.opendev.service.CarService;


public class CarServiceImplTest {

	@Test
	public void usoStats() {
		CarService cs = new CarServiceImpl();
		CarRepository cr = mock(CarRepositoryImpl.class);
		
		assertEquals(cr.count(), cs.stats().getCount_car());

	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNoExisteId() {
		CarService cs = new CarServiceImpl();
		int id = 8;
		// try {
		boolean resultado = cs.delete(id);
		// } catch (Exception e) {
		assertFalse(resultado);
	}

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

		// setup
		CarService cs = new CarServiceImpl();
		CarService csMock = mock(CarServiceImpl.class);

		when(csMock.delete(car.getId())).thenReturn(true);
		// exercise
		
		// verify
		assertTrue(cs.delete(car.getId()));

	}

	@Test
	public void queSePuedaActualizarUnAutoDespuesDeCrearloYCalcularSuCosto() {
		
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

		CarService cs = new CarServiceImpl();
		cs.create(model.getId(), idOpcionales);
		cs.update(2, 1, idOpcionales);
		Double resultadoObtenido = cs.calculateCost(model.getId(), idOpcionales);
		Double resultadoEsperado = 328000.0;
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

}
