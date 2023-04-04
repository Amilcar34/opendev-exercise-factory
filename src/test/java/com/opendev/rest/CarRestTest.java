package com.opendev.rest;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.dto.CarDto;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.impl.CarRepositoryImpl;

public class CarRestTest {

	@Test
	public void correctoFuncionamientoCreate() {

		CarRest cr = new CarRest();

		int modelId = 2;

		Optional opcional = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Set<Integer> opcionales = new HashSet<Integer>();
		opcionales.add(opcional.getId());

		CarDto car = new CarDto(modelId, opcionales);
		cr.create(car);
		assertEquals(modelId, car.getModel());
		assertEquals(opcionales, car.getOptionals());
	}

	//@Test
	public void actualizarUnAuto() {
		CarRest cr = new CarRest();

		int id = 1;
		Optional opcional = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);
		Set<Integer> opcionales = new HashSet<Integer>();
		opcionales.add(opcional.getId());

		CarDto car = new CarDto(3, opcionales);
		
		cr.update(id, car);
		
		
	}
	
	// test delete por id

	@Test
	public void calculateCost() {
		CarRest cr = new CarRest();

		Integer modelId = 1;
		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);
		Optional opcional2 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Set<Integer> opcionales = new HashSet<Integer>();
		opcionales.add(opcional.getId());
		opcionales.add(opcional2.getId());

		CarDto car = new CarDto(modelId, opcionales);
		String resultado = cr.calculateCost(car);
		String resultadoEsperado =  "254000.0";
		assertEquals(resultadoEsperado, resultado);
	}
	
	@Test
	public void metodoStats() {
		CarRest cr = new CarRest();
		// no se 
		

	}
}
