package com.opendev.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.opendev.contracts.StatsCar;
import com.opendev.contracts.StatsModel;
import com.opendev.dto.CarDto;
import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.service.impl.CarServiceImpl;

import static org.mockito.Mockito.*;

public class CarRestTest {
	
	CarServiceImpl carServiceMock = mock(CarServiceImpl.class);
	CarRest carRest = new CarRest(carServiceMock);

	@Test
	@Order(1)
	public void vUpdate() {

		Model model = new Model(2, "Familiar", 270000.0);
		Optional opcional1 = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);
		Optional opcional2 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		
		Set<Integer> opcionalesId = Set.of(opcional1.getId(), opcional2.getId());
		
		Set<Optional> opcionales = Set.of(opcional2, opcional1);

		CarDto car = new CarDto(2, opcionalesId);
		Car auto = new Car(1, model, opcionales);

		when(carServiceMock.update(1, model.getId(), opcionalesId)).thenReturn(auto);

		String resultado = carRest.update(1, car);
		String jsonExpected = "{\"id\":1,\"model\":{\"id\":2,\"name\":\"Familiar\",\"cost\":270000.0},\"optionals\":[{\"id\":3,\"name\":\"ABS\",\"fullName\":\"Sistema de frenos ABS\",\"cost\":14000.0},{\"id\":1,\"name\":\"TC\",\"fullName\":\"Techo corredizo\",\"cost\":12000.0}],\"price\":296000.0}";
	 
		assertEquals(jsonExpected, resultado);
	
	}

	@Test
	public void vCreate() {

		Optional opcional = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Model model = new Model(2, "Familiar", 270000.0);
		
		Set<Integer> opcionales = Set.of(opcional.getId());
		
		Set<Optional> setOPtionales = Collections.singleton(opcional);

		CarDto car = new CarDto(2, opcionales);
		Car auto = new Car(1, model, setOPtionales);

		when(carServiceMock.create(2, opcionales)).thenReturn(auto);

		String resultado = carRest.create(car);
		String jsonExpected = "{\"id\":1,\"model\":{\"id\":2,\"name\":\"Familiar\",\"cost\":270000.0},\"optionals\":[{\"id\":1,\"name\":\"TC\",\"fullName\":\"Techo corredizo\",\"cost\":12000.0}],\"price\":282000.0}";
		
		assertEquals(jsonExpected, resultado);
	}

	@Test
	public void metodoDelete() {
		
		when(carServiceMock.delete(1)).thenReturn(true);
		
		assertTrue(carRest.delete(1));
	}

	@Test
	public void calculateCost() {
		
		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);
		Optional opcional2 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		
		Set<Integer> opcionalesId = Set.of(opcional.getId(), opcional2.getId());

		CarDto car = new CarDto(2, opcionalesId);
		
		when(carServiceMock.calculateCost(2, opcionalesId)).thenReturn(294000.0);
		
		String resultado = carRest.calculateCost(car);
		String resultadoEsperado = "294000.0";
		
		assertEquals(resultadoEsperado, resultado);
	}

	@Test
	public void metodoStats() {
		
		StatsCar statsCar = new StatsCar();
		statsCar.setCount_car(3);
		statsCar.setCars(null);
		statsCar.setOptionals(null);
		
		when(carServiceMock.stats()).thenReturn(statsCar);
		
		String resultado = carRest.stats();
		String jsonExpected = "{\"count_car\":3}";
		
		assertEquals(resultado, jsonExpected);
		
	}
}
