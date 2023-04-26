package com.opendev.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.opendev.contracts.StatsCar;
import com.opendev.contracts.StatsModel;
import com.opendev.contracts.StatsOptional;
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
		
		// setup
		StatsCar statsCar = new StatsCar();
		
		StatsModel modelo = new StatsModel("Sedán", 2, 40.0);
		Set<StatsModel> statsModel = Set.of(modelo);
		
		StatsOptional optional = new StatsOptional("AA", 3, 30.0);
		Set<StatsOptional> statsOptionals = Set.of(optional);
		
		// config
		when(carRepositoryMock.statsModel()).thenReturn(statsModel);
		when(carRepositoryMock.statsOptional()).thenReturn(statsOptionals);
		when(carRepositoryMock.count()).thenReturn(5);
		
		statsCar.setCount_car(5);
		statsCar.setCars(statsModel);
		statsCar.setOptionals(statsOptionals);
		
		// execute
		StatsCar resultado = carServiceImpl.stats();
		
		// verify
		Assertions.assertEquals(statsCar, resultado);
	}

	@Test
	public void deleteNoExisteId(){
		
		Integer id = 3;
		when(carRepositoryMock.existsById(id)).thenReturn(false);
		
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			carServiceImpl.delete(id);
	    });

	    String expectedMessage = "No existe un Auto con este id:" + id;
	    String actualMessage = exception.getMessage();

	    Assertions.assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void calcularElCostoCuandoIdsOptionalsEsNull() {

		Model model = new Model(1, "Sedán", 230_000.0);

		when(modelServiceMock.getOne(1)).thenReturn(model);
		Double resultado = carServiceImpl.calculateCost(1, null);

		Double resultadoEsperado = 230000.0;
		Assertions.assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalEstaVacio() {

		Set<Integer> idsOptionals = new HashSet<>();
		Model model = new Model(3, "Coupé", 270000.0);

		when(modelServiceMock.getOne(3)).thenReturn(model);
		Double resultado = carServiceImpl.calculateCost(3, idsOptionals);
		
		Double resultadoEsperado = 270000.0;
		Assertions.assertEquals(resultadoEsperado, resultado);

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
		Double resultado = carServiceImpl.calculateCost(3, idsOptionals);
		
		Double resultadoEsperado = 290000.0;
		Assertions.assertEquals(resultadoEsperado, resultado);
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
		Boolean resultado = carServiceImpl.delete(car.getId());

		Assertions.assertTrue(resultado);
	}

	@Test
	public void vSaveCarIdsOptionalsNull() {
		
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);

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
