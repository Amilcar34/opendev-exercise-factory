package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.Order;

import com.opendev.contracts.StatsCar;
import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.impl.CarRepositoryImpl;
import com.opendev.service.CarService;
import com.opendev.service.ModelService;
import com.opendev.service.OptionalService;

public class CarServiceImplTest {

	CarRepository carRepositoryMock = mock(CarRepositoryImpl.class);
	OptionalService optionalServiceMock = mock(OptionalServiceImpl.class);
	ModelService modelServiceMock = mock(ModelServiceImpl.class);

	CarService carServiceMock = new CarServiceImpl(carRepositoryMock, optionalServiceMock, modelServiceMock);

	@Test
	public void vStats() {
		
		StatsCar statsCar = new StatsCar();
		statsCar.setCount_car(carRepositoryMock.count());  
		statsCar.setCars(carRepositoryMock.statsModel());
		statsCar.setOptionals(carRepositoryMock.statsOptional());
		 
		assertEquals(statsCar, carServiceMock.stats());
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNoExisteId() {

		int id = 8;
		// try {
		boolean resultado = carServiceMock.delete(id);
		// } catch (Exception e) {
		assertThrows(IllegalArgumentException.class, () -> carServiceMock.toString());
		assertFalse(resultado);
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalsEsNull() {

		Model model = new Model(1, "Sedán", 230_000.0);

		when(modelServiceMock.getOne(1)).thenReturn(model);

		Double resultadoEsperado = 230000.0;
		assertEquals(resultadoEsperado, carServiceMock.calculateCost(1, null));
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalEstaVacio() {

		Set<Integer> idsOptionals = new HashSet<>();
		Model model = new Model(3, "Coupé", 270000.0);

		when(modelServiceMock.getOne(3)).thenReturn(model);
		Double resultadoEsperado = 270000.0;

		assertEquals(resultadoEsperado, carServiceMock.calculateCost(3, idsOptionals));

	}

	@Test
	public void calcularCosto() {

		Model model = new Model(3, "Coupé", 270000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);

		Set<Optional> optionals = Set.of(opcional);
		Set<Integer> idsOptionals = Set.of(opcional.getId());

		when(modelServiceMock.getOne(3)).thenReturn(model);
		when(optionalServiceMock.getByIds(idsOptionals)).thenReturn(optionals);
		when(optionalServiceMock.sumCost(optionals)).thenReturn(20000.0);

		Double resultadoEsperado = 290000.0;
		assertEquals(resultadoEsperado, carServiceMock.calculateCost(3, idsOptionals));
	}

	@Test
	@Order(1)
	public void vCrear() {

		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(null, model, opcionals);

		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(optionalServiceMock.getByIds(opcionalsId)).thenReturn(opcionals);
		when(optionalServiceMock.sumCost(opcionals)).thenReturn(20_000.0);
		when(carRepositoryMock.save(car)).thenReturn(car);

		Car resultado = carServiceMock.create(1, opcionalsId);
		assertEquals(car, resultado);

	}

	@Test
	public void vUpdate() {

		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(1, model, opcionals);

		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(carRepositoryMock.save(car)).thenReturn(car);

		carServiceMock.create(1, opcionalsId);
		Car resultado = carServiceMock.update(3, 1, opcionalsId);
		assertEquals(car, resultado);

	}

	@Test
	public void vDelete() {

		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);
		Set<Optional> opcionals = Set.of(opcional);

		Car car = new Car(1, model, opcionals);

		when(carRepositoryMock.existsById(car.getId())).thenReturn(true);
		when(carRepositoryMock.deleteById(car.getId())).thenReturn(true);

		assertTrue(carServiceMock.delete(car.getId()));
	}

}
