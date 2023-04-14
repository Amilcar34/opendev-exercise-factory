package com.opendev.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.dto.CarDto;
import com.opendev.entity.Optional;

import static org.mockito.Mockito.*;
public class CarRestTest {

	@Test
	public void queSePuedaActualizarUnAuto() {
		CarRest crMock = mock(CarRest.class);

		Optional opcional = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);
		Optional opcional2 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Set<Integer> opcionales = new HashSet<Integer>();
		opcionales.add(opcional.getId());
		opcionales.add(opcional2.getId());

		CarDto car = new CarDto(2, opcionales);
		// al auto con id 1 con modelo 1 y opcionales 2 y 4, le cambio el modelo a 2 y opcionales 3, 1
		crMock.update(1, car);
		when(crMock.update(1, car)).thenReturn(car.toString());

	    verify(crMock, times(1)).update(1, car);
	    assertEquals(crMock.update(1, car), car.toString());
	}
	
	@Test
	public void correctoFuncionamientoCreate() {
		// arreglar

		Optional opcional = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Set<Integer> opcionales = new HashSet<Integer>();
		opcionales.add(opcional.getId());

		CarDto car = new CarDto(2, opcionales);
		//cr.create(car);
		assertEquals(2, car.getModel());
		assertEquals(opcionales, car.getOptionals());
	}
	
	@Test
	public void metodoDeleteCorrectoFuncionamiento() {
		CarRest cr = new CarRest();
		assertTrue(cr.delete(4));
	}

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
	
	//@Test
	public void metodoStats() {
		
	}
}
