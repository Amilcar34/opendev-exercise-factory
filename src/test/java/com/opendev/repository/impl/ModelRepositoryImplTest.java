package com.opendev.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.opendev.entity.Model;
import com.opendev.repository.ModelRepository;

public class ModelRepositoryImplTest {
	
	@Test
	public void getOneCorrectoFuncionamiento() {
		ModelRepository mr = new ModelRepositoryImpl();
		Model model = new Model(1, "Sedán", 230000.0);
		Assertions.assertEquals(model, mr.getOne(1));
		
	}

}
