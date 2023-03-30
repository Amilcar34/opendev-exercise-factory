package com.opendev.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;

class EntityTest {

	@Test
	void queLaClaseCarFuncioneCorrectamente() {
		Model modelo = new Model(1, "Sedán", 8000.00);
		Optional opcional = new Optional();
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		
		Car auto = new Car(4431, modelo, opcionales);
		assertEquals(4431, auto.getId());
		assertEquals(modelo, auto.getModel());
		assertEquals(opcionales, auto.getOptionals());
	}
	
	@Test
	void queLaClaseModelFuncioneCorrectamente() {
		Integer id = 1;
		String name = "Sedán";
		Double cost = 230000.0;
		Model model = new Model(id, name, cost);
		assertEquals(id, model.getId());
		assertEquals(name, model.getName());
		assertEquals(cost, model.getCost());
	}
	
	@Test
	void queLaClaseOptionalFuncioneCorrectamente() {
		Integer id = 1;
		String name = "TC";
		String fullName = "Techo corredizo";
		Double cost = 12000.0;
		Optional optional = new Optional(id, name, fullName, cost);
		assertEquals(id, optional.getId());
		assertEquals(name, optional.getName());
		assertEquals(fullName, optional.getFullName());
		assertEquals(cost, optional.getCost());
	}
	
	

}
