package com.opendev.service.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Double.sum;

import java.util.HashSet;
import java.util.Set;

import com.opendev.contracts.StatsCar;
import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.CarRepository;
import com.opendev.repository.ModelRepository;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.CarRepositoryImpl;
import com.opendev.repository.impl.ModelRepositoryImpl;
import com.opendev.repository.impl.OptionalRepositoryImpl;
import com.opendev.service.CarService;
import com.opendev.service.OptionalService;

import io.vavr.control.Try;

public class CarServiceImpl implements CarService {

	CarRepository carRepository = new CarRepositoryImpl();
	ModelRepository modelRepository = new ModelRepositoryImpl();
	OptionalService optionalService = new OptionalServiceImpl();
	OptionalRepository optionalRepository = new OptionalRepositoryImpl();

	public Car create(int idModel, Set<Integer> idsOptionals) {

		return saveCar(new Car(), idModel, idsOptionals);
	}

	public Car update(int id, int idModel, Set<Integer> idsOptionals) {

		return saveCar(carRepository.getOne(id), idModel, idsOptionals);
	}

	public boolean delete(int id) {

		if (FALSE.equals(carRepository.existsById(id)))
			throw new IllegalArgumentException("No existe un Auto con este id:" + id);
		return Try.run(() -> carRepository.deleteById(id)).isSuccess();
	}

	public Double calculateCost(int idModel, Set<Integer> idsOptionals) {

		if (idsOptionals == null || idsOptionals.isEmpty())
			return modelRepository.getOne(idModel).getCost();
		return calculateCost(modelRepository.getOne(idModel), optionalService.getByIds(idsOptionals));
	}

	public StatsCar stats() {
		
		var statsCar = new StatsCar();
		statsCar.setCount_car(carRepository.count());
		statsCar.setCars(carRepository.statsModel());
		statsCar.setOptionals(carRepository.statsOptional());
		return statsCar;
	}

	private Car saveCar(Car entity, int idModel, Set<Integer> idsOptionals) {

		var model = modelRepository.getOne(idModel);
		entity.setModel(model);
		Set<Optional> optionals;
		optionals = idsOptionals == null || idsOptionals.isEmpty() ? new HashSet<Optional>() : optionalService.getByIds(idsOptionals);
		entity.setOptionals(optionals);
		entity.setPrice(calculateCost(model, optionals));
		return carRepository.save(entity);
	}

	private Double calculateCost(Model model, Set<Optional> optionals) {

		if (optionals == null || optionals.isEmpty())
			return model.getCost();
		return sum(model.getCost(), optionalService.sumCost(optionals));
	}

}
