package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.impl.CarRepositoryImpl;
import com.opendev.service.CarService;

public class CarServiceImplTest {

	// @Test
	public void vStats() {
		CarService carService = new CarServiceImpl();
		CarRepository carRepoImplMock = mock(CarRepositoryImpl.class);

		assertEquals(carRepoImplMock.count(), carService.stats().getCount_car());

	}
	
	@Test(expected = IllegalArgumentException.class)
	@Order(2)
	public void deleteNoExisteId() {

		CarService carService = new CarServiceImpl();
		int id = 8;
		// try {
		boolean resultado = carService.delete(id);
		// } catch (Exception e) {
		assertFalse(resultado);
	}
	
	@Test
	@Order(3)
	public void calcularElCostoCuandoIdsOptionalsEsNull() {

		CarService carService = new CarServiceImpl();

		Double resultadoEsperado = 230000.0;

		assertEquals(resultadoEsperado, carService.calculateCost(1, null));
	}
	
	@Test
	@Order(4)
	public void calcularElCostoCuandoIdsOptionalEstaVacio() {

		CarService carService = new CarServiceImpl();

		Set<Integer> idsOptionals = new HashSet<>();

		Double resultadoEsperado = 245000.0;

		assertEquals(resultadoEsperado, carService.calculateCost(2, idsOptionals));

	}

	@Test
	@Order(5)
	public void calcularCosto() {
		CarService cs = new CarServiceImpl();

		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional2 = new Optional(3, "ABS", "Sistemas de frenos ABS", 14000.0);

		Set<Integer> idsOptionals = Set.of(opcional.getId(), opcional2.getId());

		Double resultadoEsperado = 304000.0;

		assertEquals(resultadoEsperado, cs.calculateCost(3, idsOptionals));
	}

	@Test
	@Order(1)
	public void vCrear() {

		CarService carService = new CarServiceImpl();
		CarRepository carRepoImplMock = mock(CarRepositoryImpl.class);

		Model model = new Model(1, "Sedán", 230000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(2, model, opcionals);

		when(carRepoImplMock.save(car)).thenReturn(car);

		System.out.println(carRepoImplMock.save(car));
		System.out.println(carService.create(1, opcionalsId));
		System.out.println(car);

		Car resultado = carService.create(1, opcionalsId);
		assertEquals(car, resultado);

	}

	@Test
	@Order(6)
	public void vBorrar() {
		
		CarService carService = new CarServiceImpl();
		CarRepository carRepoImplMock = mock(CarRepositoryImpl.class);
		
		assertTrue(carService.delete(3));
		
		when(carRepoImplMock.existsById(3)).thenReturn(false);
		
		assertFalse(carRepoImplMock.existsById(3));
	}

	@Test
	@Order(7)
	public void vUpdate() {
		
//		CarService carService = new CarServiceImpl();
//		CarRepository carRepoImplMock = mock(CarRepositoryImpl.class);
//		
//		Model model = new Model(1, "Sedán", 230000.0);
//		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);
//
//		Set<Optional> opcionals = Set.of(opcional);
//		Set<Integer> opcionalsId = Set.of(opcional.getId());
//
//		Car car = new Car(2, model, opcionals);
//		
//		when(carRepoImplMock.save(car)).thenReturn(car);
//		System.out.println(carService.update(3, 1, opcionalsId);
		
		
	}

}
