package com.opendev.service.impl;

import com.opendev.entity.Model;
import com.opendev.repository.ModelRepository;
import com.opendev.repository.impl.ModelRepositoryImpl;
import com.opendev.service.ModelService;

public class ModelServiceImpl implements ModelService {

	ModelRepository modelRepository = new ModelRepositoryImpl();
	
	@Override
	public Model getOne(int idModel) {
		return modelRepository.getOne(idModel);
	}
  
}
