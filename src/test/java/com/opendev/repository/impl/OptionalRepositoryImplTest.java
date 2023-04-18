package com.opendev.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.opendev.entity.Optional;
import com.opendev.repository.OptionalRepository;

public class OptionalRepositoryImplTest {
	
	@Test
	public void getOneCorrectoFuncionamiento() {
		OptionalRepository or = new OptionalRepositoryImpl();
		Optional optional = new Optional(2, "AA", "Aire acondicionado", 20000.0);
Assertions.assertEquals(optional, or.getOne(optional.getId()));
	}

}
