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
		
		// exercise
		when(carRepositoryMock.statsModel()).thenReturn(statsModel);
		when(carRepositoryMock.statsOptional()).thenReturn(statsOptionals);
		when(carRepositoryMock.count()).thenReturn(5);
		
		statsCar.setCount_car(5);
		statsCar.setCars(statsModel);
		statsCar.setOptionals(statsOptionals);
		
		// verify
		Assertions.assertEquals(statsCar, carServiceImpl.stats());
	}

	@Test
	public void deleteNoExisteId(){
		
		// set up
		Integer id = 3;
		when(carRepositoryMock.existsById(id)).thenReturn(false);
		
		// exercise
		Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			carServiceImpl.delete(id);
	    });

		// verify
	    String expectedMessage = "No existe un Auto con este id:" + id;
	    String actualMessage = exception.getMessage();

	    Assertions.assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void calcularElCostoCuandoIdsOptionalsEsNull() {

		// set up
		Model model = new Model(1, "Sedán", 230_000.0);

		// exercise
		when(modelServiceMock.getOne(1)).thenReturn(model);

		// verify
		Double resultadoEsperado = 230000.0;
		Assertions.assertEquals(resultadoEsperado, carServiceImpl.calculateCost(1, null));
	}

	@Test
	public void calcularElCostoCuandoIdsOptionalEstaVacio() {

		// set up
		Set<Integer> idsOptionals = new HashSet<>();
		Model model = new Model(3, "Coupé", 270000.0);

		// exercise
		when(modelServiceMock.getOne(3)).thenReturn(model);
		
		// verify
		Double resultadoEsperado = 270000.0;
		Assertions.assertEquals(resultadoEsperado, carServiceImpl.calculateCost(3, idsOptionals));

	}

	@Test
	public void vCalculateCost() {
		
		// set up
		Model model = new Model(3, "Coupé", 270000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20000.0);

		Set<Optional> optionals = Set.of(opcional);
		Set<Integer> idsOptionals = Set.of(opcional.getId());

		// exercise
		when(modelServiceMock.getOne(3)).thenReturn(model);
		when(optionalServiceMock.getByIds(idsOptionals)).thenReturn(optionals);
		when(optionalServiceMock.sumCost(optionals)).thenReturn(20000.0);

		// verify
		Double resultadoEsperado = 290000.0;
		Assertions.assertEquals(resultadoEsperado, carServiceImpl.calculateCost(3, idsOptionals));
	}

	@Test
	@Order(1)
	public void vCrear() {

		// set up
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(null, model, opcionals);

		// exercise
		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(optionalServiceMock.getByIds(opcionalsId)).thenReturn(opcionals);
		when(optionalServiceMock.sumCost(opcionals)).thenReturn(20_000.0);
		when(carRepositoryMock.save(car)).thenReturn(car);
	
		// verify
		Car resultado = carServiceImpl.create(1, opcionalsId);
		Assertions.assertEquals(car, resultado);

	}

	@Test
	public void vUpdate() {
		
		// set up
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of(opcional.getId());

		Car car = new Car(1, model, opcionals);

		// exercise
		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(carRepositoryMock.getOne(car.getId())).thenReturn(car);
		when(carRepositoryMock.save(car)).thenReturn(car);

		// verify
		Car resultado = carServiceImpl.update(1, 1, opcionalsId);
		Assertions.assertEquals(car, resultado);

	}

	@Test
	public void vDelete() {

		// set up
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);
		Set<Optional> opcionals = Set.of(opcional);

		Car car = new Car(1, model, opcionals);

		// exercise
		when(carRepositoryMock.existsById(car.getId())).thenReturn(true);

		// verify
		Assertions.assertTrue(carServiceImpl.delete(car.getId()));
	}

	@Test
	public void vSaveCarIdsOptionalsNull() {
		
		// set up
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);

		Car car = new Car(null, model, opcionals);

		// exercise
		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(optionalServiceMock.getByIds(null)).thenReturn(null);
		when(optionalServiceMock.sumCost(opcionals)).thenReturn(20_000.0);
		when(carRepositoryMock.save(car)).thenReturn(car);

		// verify
		Assertions.assertNull(carServiceImpl.create(1, null));
	}

	@Test
	public void vSaveIdsOptionalsEmpty() {
		
		// set up
		Model model = new Model(1, "Sedán", 230_000.0);
		Optional opcional = new Optional(2, "AA", "Aire acondicionado", 20_000.0);

		Set<Optional> opcionals = Set.of(opcional);
		Set<Integer> opcionalsId = Set.of();

		Car car = new Car(null, model, opcionals);

		// exercise
		when(modelServiceMock.getOne(model.getId())).thenReturn(model);
		when(optionalServiceMock.getByIds(opcionalsId)).thenReturn(opcionals);
		when(optionalServiceMock.sumCost(opcionals)).thenReturn(20_000.0);
		when(carRepositoryMock.save(car)).thenReturn(car);

		// verify
		Assertions.assertNull(carServiceImpl.create(1, opcionalsId));

	}

}
