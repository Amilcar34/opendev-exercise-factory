package com.opendev.repository.impl.test;

import static org.junit.Assert.assertEquals;

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
		
		Model modelo = new Model(8572, "modelo 09", 8000.00);
		
		Optional opcional = new Optional();
		
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		
		Car auto = new Car(4431, modelo, opcionales);
		
		cri.save(auto);
		assertEquals(6, cri.count());
	}
	
	@Test
	public void queFuncioneCorrectamenteStatsModelYStatsOptional() {
		
	}
	
	

}
