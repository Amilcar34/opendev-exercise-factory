package com.opendev.repository.impl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.impl.CarRepositoryImpl;

public class CarRepositoryImplTest {

	@Test
	public void queFuncioneCorrectamenteElMetodoSave() {
		CarRepositoryImpl cri = new CarRepositoryImpl();

		Model modelo = new Model(8572, "Sedan", 8000.00);

		Optional opcional = new Optional();

		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		Integer id = 30;
		Car auto = new Car(id, modelo, opcionales);

		cri.save(auto);
		assertEquals(6, cri.count());
	}

//	@Test
//	public void queFuncioneCorrectamenteDeleteById() {
//		CarRepositoryImpl cri = new CarRepositoryImpl();
//
//		Integer id = 30;
//		Model modelo = new Model(8572, "modelo 09", 8000.00);
//		Optional opcional = new Optional();
//		Set<Optional> opcionales = new HashSet<>();
//		opcionales.add(opcional);
//
//		Car auto = new Car(id, modelo, opcionales);
//		cri.save(auto);
//		cri.deleteById(id);
//		cri.getOne(id);
//		assertEquals(true, cri.existsById(id));
//	}
	@Test
	public void queFuncioneCorrectamenteStatsOptional() {
		
	}
	

}
