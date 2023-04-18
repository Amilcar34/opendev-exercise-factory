package com.opendev.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.opendev.entity.Model;
import com.opendev.service.ModelService;

public class ModelServiceImplTest {
	
	@Test
	public void vGetOne() {
		
		ModelService modelServiceI = new ModelServiceImpl();
		
		Model model = new Model(1, "Sedán", 230000.0);
		assertEquals(model, modelServiceI.getOne(model.getId()));
		
		
	}

}
