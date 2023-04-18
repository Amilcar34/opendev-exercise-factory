package com.opendev.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.opendev.contracts.StatsCar;
import com.opendev.dto.CarDto;
import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.service.impl.CarServiceImpl;

public class CarRestTest {

	CarServiceImpl carServiceMock = mock(CarServiceImpl.class);
	CarRest carRest = new CarRest(carServiceMock);

	@Test
	public void vUpdate() {

		Model model = new Model(2, "Familiar", 270000.0);
		Optional opcional1 = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);

		Set<Integer> opcionalesId = Set.of(opcional1.getId());

		Set<Optional> opcionales = Set.of(opcional1);

		CarDto car = new CarDto(2, opcionalesId);
		Car auto = new Car(1, model, opcionales);

		when(carServiceMock.update(1, model.getId(), opcionalesId)).thenReturn(auto);

		String resultado = carRest.update(1, car);
		String jsonExpected = "{\"id\":1,\"model\":{\"id\":2,\"name\":\"Familiar\",\"cost\":270000.0},\"optionals\":[{\"id\":3,\"name\":\"ABS\",\"fullName\":\"Sistema de frenos ABS\",\"cost\":14000.0}],\"price\":284000.0}";

		Assertions.assertEquals(jsonExpected, resultado);

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

		Assertions.assertEquals(jsonExpected, resultado);
	}

	@Test
	public void metodoDelete() {
		
		when(carServiceMock.delete(1)).thenReturn(true);
		
		Assertions.assertTrue(carRest.delete(1));
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

		Assertions.assertEquals(resultadoEsperado, resultado);
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

		Assertions.assertEquals(resultado, jsonExpected);

	}
}
