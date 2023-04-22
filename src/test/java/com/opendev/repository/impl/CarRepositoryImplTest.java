package com.opendev.repository.impl;
import static com.opendev.repository.impl.ModelRepositoryImpl.dbModels;
import static com.opendev.repository.impl.OptionalRepositoryImpl.dbOptionals;
import static java.util.Set.of;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarRepositoryImplTest {

	CarRepository carRepository = new CarRepositoryImpl();

	@Test
	public void vSave() {

		Model modelo = new Model(3, "Coupé", 270000.0);
		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional3 = new Optional(3, "ABS", "Sistemas de frenos ABS", 14000.0);
		Optional opcional4 = new Optional(4, "LL", "Llantas de aleación", 12000.0);

		Set<Optional> opcionales = Set.of(opcional1, opcional2, opcional3, opcional4);

		Car auto = new Car(null, modelo, opcionales);

		Car autoObtenido = carRepository.save(auto);
		
		Car autoEsperado = new Car(4, modelo, opcionales);
		
		Assertions.assertEquals(autoEsperado, autoObtenido);
	
	}

	@Test
	public void vDeleteById() {

		carRepository.deleteById(3);
		Assertions.assertFalse(carRepository.existsById(3));
	}

	@Test
	public void vExistsById() {

		Assertions.assertTrue(carRepository.existsById(1));
	}

	@Test
	public void vGetOne() {
		
		Model model = new Model(1, "Sedán", 230000.0);
		Optional optional1 = new Optional(4, "LL", "Llantas de aleación", 12000.0); 
		Optional optional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0); 
		Set<Optional> optionals = Set.of(optional2, optional1);
		
		Car car = new Car(1, model, optionals);
		
		Assertions.assertEquals(car, carRepository.getOne(1));
	}

	@Test
	public void vCount() {
		Assertions.assertEquals(4, carRepository.count());
	}

	@Test
	public void statsModel() {
		// TODO arreglar
		int cantCarsParaCadaModelo = 2;
		Assertions.assertEquals(cantCarsParaCadaModelo, carRepository.statsModel().size());

	}

	@Test
	public void statsOptional() {
		// TODO arreglar
		int cantDeOpcionalesUsados = 4;
		Assertions.assertEquals(cantDeOpcionalesUsados, carRepository.statsOptional().size());

	}
	
	@BeforeAll
	public void setUp() {
		System.out.println("pase por aca");
		carRepository.deleteById(1);
		carRepository.deleteById(2);
		carRepository.deleteById(3);
		carRepository.deleteById(4);
		carRepository.deleteById(5);
		carRepository.save(new Car(1, dbModels.get(1), of(dbOptionals.get(2), dbOptionals.get(4))));
		carRepository.save( new Car(2, dbModels.get(1), of(dbOptionals.get(1), dbOptionals.get(4))));
		carRepository.save( new Car(3, dbModels.get(2), of(dbOptionals.get(2), dbOptionals.get(4))));
		carRepository.save( new Car(4, dbModels.get(3), of(dbOptionals.get(1), dbOptionals.get(2), dbOptionals.get(3), dbOptionals.get(4))));
		carRepository.save( new Car(5, dbModels.get(3), of()));	
	}

}
