package com.opendev.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;

public class EntityTest {

	@Test
	public void queLaClaseCarFuncioneCorrectamente() {
		Model modelo = new Model(1, "Sedán", 8000.00);
		Optional opcional = new Optional();
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional);
		
		Car auto = new Car(4431, modelo, opcionales);
		//assertEquals(4431, auto.getId());
		assertEquals(modelo, auto.getModel());
		assertEquals(opcionales, auto.getOptionals());
	}
	
	@Test
	public void queLaClaseModelFuncioneCorrectamente() {
		Integer id = 1;
		String name = "Sedán";
		Double cost = 230000.0;
		Model model = new Model(id, name, cost);
		assertEquals(id, model.getId());
		assertEquals(name, model.getName());
		assertEquals(cost, model.getCost());
	}
	
	@Test
	public void queLaClaseOptionalFuncioneCorrectamente() {
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
