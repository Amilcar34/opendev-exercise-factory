package com.opendev.service.impl;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

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

	CarService carServiceImpl = new CarServiceImpl(carRepositoryMock, optionalServiceMock, modelServiceMock);

	@Test
	public void vStats() {

		StatsCar statsCar = new StatsCar();
		statsCar.setCount_car(carRepositoryMock.count());
		statsCar.setCars(carRepositoryMock.statsModel());
		statsCar.setOptionals(carRepositoryMock.statsOptional());

		Assertions.assertEquals(statsCar, carServiceImpl.stats());
	}

	@Test // (expected = IllegalArgumentException.class)
	public void deleteNoExisteId() {

		int id = 8;
		when(carRepositoryMock.existsById(id)).thenReturn(false);

		// assertThrows: verificar excepcion
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			// la expresion lambda se pasa como argumento a assertThrows y lanza la
			// excepcion
			// si se aprueba la excepcion, se pasa la prueba.
			carServiceImpl.delete(id);
		});

		String expectedMessage = "No existe un Auto con este id:" + id;
		Assertions.assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalsEsNull() {

		Model model = new Model(1, "Sedán", 230_000.0);

		when(modelServiceMock.getOne(1)).thenReturn(model);
		// when(optionalServiceMock.getByIds(null)).thenReturn(null);

		Double resultadoEsperado = 230000.0;
		Assertions.assertEquals(resultadoEsperado, carServiceImpl.calculateCost(1, null));
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalEstaVacio() {

		Set<Integer> idsOptionals = new HashSet<>();
		Model model = new Model(3, "Coupé", 270000.0);

		when(modelServiceMock.getOne(3)).thenReturn(model);
		Double resultadoEsperado = 270000.0;

		Assertions.assertEquals(resultadoEsperado, carServiceImpl.calculateCost(3, idsOptionals));

	}

	@Test
	public void vCalculateCost() {

		Model model = new Model(3, "Coupé", 270000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);

		Set<Optional> optionals = Set.of(opcional);
		Set<Integer> idsOptionals = Set.of(opcional.getId());

		when(modelServiceMock.getOne(3)).thenReturn(model);
		when(optionalServiceMock.getByIds(idsOptionals)).thenReturn(optionals);
		when(optionalServiceMock.sumCost(optionals)).thenReturn(20000.0);

		Double resultadoEsperado = 290000.0;
		Assertions.assertEquals(resultadoEsperado, carServiceImpl.calculateCost(3, idsOptionals));
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

		Car resultado = carServiceImpl.create(1, opcionalsId);
		Assertions.assertEquals(car, resultado);

	}

	@Test
	public void vUpdate() {

		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(1, model, opcionals);

		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(carRepositoryMock.getOne(car.getId())).thenReturn(car);
		when(carRepositoryMock.save(car)).thenReturn(car);

		Car resultado = carServiceImpl.update(1, 1, opcionalsId);
		Assertions.assertEquals(car, resultado);

	}

	@Test
	public void vDelete() {

		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);
		Set<Optional> opcionals = Set.of(opcional);

		Car car = new Car(1, model, opcionals);

		when(carRepositoryMock.existsById(car.getId())).thenReturn(true);
		//when(carRepositoryMock.deleteById(car.getId())).thenReturn(true);

		Assertions.assertTrue(carServiceImpl.delete(car.getId()));
	}

	@Test
	public void vSaveCarIdsOptionalsNull() {
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		// Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(null, model, opcionals);

		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(optionalServiceMock.getByIds(null)).thenReturn(null);
		when(optionalServiceMock.sumCost(opcionals)).thenReturn(20_000.0);
		when(carRepositoryMock.save(car)).thenReturn(car);

		Assertions.assertNull(carServiceImpl.create(1, null));
	}

	@Test
	public void vSaveIdsOptionalsEmpty() {
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of();

		Car car = new Car(null, model, opcionals);

		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(optionalServiceMock.getByIds(opcionalsId)).thenReturn(opcionals);
		when(optionalServiceMock.sumCost(opcionals)).thenReturn(20_000.0);
		when(carRepositoryMock.save(car)).thenReturn(car);

		Assertions.assertNull(carServiceImpl.create(1, opcionalsId));

	}

}
