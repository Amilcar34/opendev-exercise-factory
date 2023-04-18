package com.opendev.repository;

import java.util.Set;

import com.opendev.contracts.StatsModel;
import com.opendev.contracts.StatsOptional;
import com.opendev.entity.Car;

public interface CarRepository {

	Car save(Car entity);

	void deleteById(int id);

	boolean existsById(int id);

	int count();

	Car getOne(int id);

	Set<StatsModel> statsModel();
	
	Set<StatsOptional> statsOptional();

}
