package com.opendev.repository.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opendev.entity.Model;
import com.opendev.repository.ModelRepository;
import com.opendev.repository.impl.ModelRepositoryImpl;

public class ModelRepositoryImplTest {
	
	@Test
	public void getOneCorrectoFuncionamiento() {
		ModelRepository mr = new ModelRepositoryImpl();
		Model model = new Model(1, "Sedán", 230000.0);
		assertEquals(model, mr.getOne(1));
		
	}

}
