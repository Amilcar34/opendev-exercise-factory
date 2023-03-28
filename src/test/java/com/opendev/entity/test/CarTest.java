package com.opendev.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;

class CarTest {

	@Test
	void queLaClaseCarFuncioneCorrectamente() {
		Model modelo = new Model(8572, "modelo 09", 8000.00);
		Optional opcional = new Optional();
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		
		Car auto = new Car(4431, modelo, opcionales);
		assertEquals(4431, auto.getId());
		assertEquals(modelo, auto.getModel());
		assertEquals(opcionales, auto.getOptionals());
	}

}
