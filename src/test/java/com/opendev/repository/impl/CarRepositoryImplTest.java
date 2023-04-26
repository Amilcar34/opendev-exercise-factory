package com.opendev.repository.impl;

import static com.opendev.repository.impl.ModelRepositoryImpl.dbModels;
import static com.opendev.repository.impl.OptionalRepositoryImpl.dbOptionals;
import static java.util.Set.of;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.opendev.contracts.StatsModel;
import com.opendev.contracts.StatsOptional;
import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarRepositoryImplTest {

	CarRepository carRepository = new CarRepositoryImpl();

	@Test
	public void vGetOne() {

		Model model = new Model(1, "Sedán", 230000.0);
		Optional optional1 = new Optional(4, "AB", "Airbag", 7000.0);
		Optional optional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Set<Optional> optionals = Set.of(optional2, optional1);

		Car car = new Car(1, model, optionals);

		Car resultado = carRepository.getOne(1);
		Assertions.assertEquals(car, resultado);
	}

	@Test
	@Disabled
	public void vSave() {

		Model modelo = new Model(3, "Coupé", 270000.0);
		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional3 = new Optional(3, "ABS", "Sistemas de frenos ABS", 14000.0);
		Optional opcional4 = new Optional(4, "AB", "Airbag", 7000.0);

		Set<Optional> opcionales = Set.of(opcional1, opcional2, opcional3, opcional4);

		Car auto = new Car(null, modelo, opcionales);

		Car autoObtenido = carRepository.save(auto);

		Car autoEsperado = new Car(6, modelo, opcionales);

		Assertions.assertEquals(autoEsperado, autoObtenido);

	}

	@Test
	public void vDeleteById() {

		carRepository.deleteById(3);
		Assertions.assertFalse(carRepository.existsById(3));
	}

	@Test
	public void vExistsById() {

		Assertions.assertTrue(carRepository.existsById(5));
	}

	@Test
	public void vCount() {
		Assertions.assertEquals(5, carRepository.count());
	}

	@Test
	public void vStatsModels() {
		Set<StatsModel> expectedStatsModel = new HashSet<>();

		StatsModel modelo1 = new StatsModel("Sedán", 2, 40.0);
		StatsModel modelo2 = new StatsModel("Coupé", 2, 40.0);
		StatsModel modelo3 = new StatsModel("Familiar", 1, 20.0);

		expectedStatsModel.add(modelo1);
		expectedStatsModel.add(modelo2);
		expectedStatsModel.add(modelo3);

		Assertions.assertIterableEquals(expectedStatsModel, carRepository.statsModel());

	}

	@Test
	public void statsOptional() {
		Set<StatsOptional> statsOptionals = new HashSet<>();
		StatsOptional optional1 = new StatsOptional("AA", 3, 30.0);
		StatsOptional optional3 = new StatsOptional("TC", 2, 20.0);
		StatsOptional optional4 = new StatsOptional("ABS", 1, 10.0);
		StatsOptional optional5 = new StatsOptional("AB", 4, 40.0);

		statsOptionals.add(optional1);
		statsOptionals.add(optional5);
		statsOptionals.add(optional3);
		statsOptionals.add(optional4);

		Assertions.assertEquals(statsOptionals, carRepository.statsOptional());

	}

	@BeforeEach
	public void setUp() {

		carRepository.deleteById(1);
		carRepository.deleteById(2);
		carRepository.deleteById(3);
		carRepository.deleteById(4);
		carRepository.deleteById(5);
		carRepository.save(new Car(1, dbModels.get(1), of(dbOptionals.get(2), dbOptionals.get(4))));
		carRepository.save(new Car(2, dbModels.get(1), of(dbOptionals.get(1), dbOptionals.get(4))));
		carRepository.save(new Car(3, dbModels.get(2), of(dbOptionals.get(2), dbOptionals.get(4))));
		carRepository.save(new Car(4, dbModels.get(3),
				of(dbOptionals.get(1), dbOptionals.get(2), dbOptionals.get(3), dbOptionals.get(4))));
		carRepository.save(new Car(5, dbModels.get(3), of()));
	}

}
