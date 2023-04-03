package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Optional;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.OptionalRepositoryImpl;
import com.opendev.service.OptionalService;
import com.opendev.service.impl.OptionalServiceImpl;

public class OptionalServiceImplTest {

	@Test
	public void metodoSumCostCorrectoFuncionamiento() {
		OptionalService os = new OptionalServiceImpl();
		Set<Optional> optionals = new HashSet<Optional>();
		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		optionals.add(opcional1);
		optionals.add(opcional2);
		Double suma = 32000.0;
		Double resultado = os.sumCost(optionals);
		assertEquals(suma, resultado);
	}
	
	@Test
	public void metodogetByIds() {
		// assert equals 1, optional.getbyid
		// preguntar como comparar getbyid (devuelve cada id del set de ids) 
		
		// setup
		OptionalService os = new OptionalServiceImpl();
		OptionalRepository op = new OptionalRepositoryImpl();
		
		Optional opcional1 = new Optional(1, "TC", "Techo corredizo", 12000.0);
		Optional opcional2 = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Optional opcional3 = new Optional(3, "ABS", "Sistema de frenos ABS", 14000.0);
		
		Set<Optional> opcionales = new HashSet<>();
		opcionales.add(opcional1);
		//opcionales.add(opcional2);
		//opcionales.add(opcional3);

		Set<Integer> idOptionals = new HashSet<>();
		idOptionals.add(opcional1.getId());
		//idOptionals.add(opcional2.getId());
		//idOptionals.add(opcional3.getId());
		
		//Set<Optional> resultadoEsperado = os.getByIds(idOptionals);
		assertEquals(os.getByIds(idOptionals), op.getOne(opcional1.getId()));

	}
	

}
