package com.opendev.repository.impl.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opendev.entity.Optional;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.OptionalRepositoryImpl;

public class OptionalRepositoryImplTest {
	
	@Test
	public void getOneCorrectoFuncionamiento() {
		OptionalRepository or = new OptionalRepositoryImpl();
		Optional optional = new Optional(2, "AA", "Aire acondicionado", 20000.0);
		Integer id = 2;
		assertEquals(optional, or.getOne(id));
	}

}
