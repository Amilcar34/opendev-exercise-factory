package com.opendev.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.opendev.dto.CarDto;
import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.service.impl.CarServiceImpl;

import static org.mockito.Mockito.*;

public class CarRestTest {

	@Test
	public void vUpdate() {
		
		CarServiceImpl carServiceMock = mock(CarServiceImpl.class);
		CarRest carRest = new CarRest(carServiceMock);

		Model model = new Model(2, "Familiar", 270000.0);
		Optional opcional1 = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);
		Optional opcional2 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		
		Set<Integer> opcionalesId = Set.of(opcional1.getId(), opcional2.getId());
		
		Set<Optional> opcionales = Set.of(opcional1, opcional2);

		CarDto car = new CarDto(2, opcionalesId);
		Car auto = new Car(1, model, opcionales);

		when(carServiceMock.update(1, model.getId(), opcionalesId)).thenReturn(auto);

		String resultado = carRest.update(1, car);
		String jsonExpected = "{\"id\":1,\"model\":{\"id\":2,\"name\":\"Familiar\",\"cost\":270000.0},\"optionals\":[{\"id\":3,\"name\":\"ABS\",\"fullName\":\"Sistema de frenos ABS\",\"cost\":14000.0},{\"id\":1,\"name\":\"TC\",\"fullName\":\"Techo corredizo\",\"cost\":12000.0}],\"price\":296000.0}";
	 
		assertEquals(jsonExpected, resultado);
	
	}

	@Test
	public void vCreate() {

		CarServiceImpl carServiceMock = mock(CarServiceImpl.class);
		CarRest carRest = new CarRest(carServiceMock);

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
		
		CarServiceImpl carServiceMock = mock(CarServiceImpl.class);
		CarRest carRest = new CarRest(carServiceMock);
		
		when(carServiceMock.delete(1)).thenReturn(true);
		
		assertTrue(carRest.delete(1));
	}

	@Test
	public void calculateCost() {
		
		CarServiceImpl carServiceMock = mock(CarServiceImpl.class);
		CarRest carRest = new CarRest(carServiceMock);
		
		Optional opcional = new Optional(4, "LL", "Llantas de aleación", 12000.0);
		Optional opcional2 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		
		Set<Integer> opcionalesId = Set.of(opcional.getId(), opcional2.getId());

		CarDto car = new CarDto(2, opcionalesId);
		
		when(carServiceMock.calculateCost(2, opcionalesId)).thenReturn(294000.0);
		
		String resultado = carRest.calculateCost(car);
		String resultadoEsperado = "294000.0";
		
		assertEquals(resultadoEsperado, resultado);
	}

	//@Test
	public void metodoStats() {
		CarServiceImpl carService = new CarServiceImpl();
		CarRest cr = new CarRest(carService);
		
		String resultado = cr.stats();
		String jsonExpected = "{\"count_car\":5,\"cars\":[{\"model\":\"Familiar\",\"count\":1,\"percent\":20.0},{\"model\":\"Coupé\",\"count\":2,\"percent\":40.0},{\"model\":\"Sedán\",\"count\":2,\"percent\":40.0}],\"optionals\":[{\"optional\":\"AA\",\"count\":3,\"percent\":30.0},{\"optional\":\"LL\",\"count\":4,\"percent\":40.0},{\"optional\":\"TC\",\"count\":2,\"percent\":20.0},{\"optional\":\"ABS\",\"count\":1,\"percent\":10.0}]}";
		
		assertEquals(resultado, jsonExpected);
		
	}
}
