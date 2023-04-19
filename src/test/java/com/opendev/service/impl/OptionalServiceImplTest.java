package com.opendev.service.impl;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.opendev.entity.Optional;
import com.opendev.service.OptionalService;

public class OptionalServiceImplTest {

	@Test
	public void vSumCost() {

		OptionalService os = new OptionalServiceImpl();

		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);

		Set<Optional> optionals = Set.of(opcional1, opcional2);

		Double suma = 32000.0;
		Double resultado = os.sumCost(optionals);
		Assertions.assertEquals(suma, resultado);
	}
	
	@Test
	public void vGetByIds() {
		
		OptionalService os = new OptionalServiceImpl();
		
		Optional optional = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Set<Integer> idsOptionals = Set.of(optional.getId());
		
		Assertions.assertEquals(optional, os.getByIds(idsOptionals));
		
	}

}
