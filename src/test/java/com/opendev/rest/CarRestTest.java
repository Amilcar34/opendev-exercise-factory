package com.opendev.rest;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.dto.CarDto;
import com.opendev.entity.Optional;

public class CarRestTest {

	@Test
	public void correctoFuncionamientoCreate() {

		CarRest cr = new CarRest();

		int model = 2;
		Optional opcional = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Set<Integer> opcionales = new HashSet();
		opcionales.add(opcional.getId());

		CarDto car = new CarDto(model, opcionales);

		//assertEquals(, );
	}
}
