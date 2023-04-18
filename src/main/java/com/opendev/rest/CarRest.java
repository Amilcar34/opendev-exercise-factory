package com.opendev.rest;

import com.google.gson.Gson;
import com.opendev.dto.CarDto;
import com.opendev.service.CarService;

public class CarRest {

	CarService carService;
	Gson gson = new Gson();

	public CarRest(CarService carService) {
		super();
		this.carService = carService;
	}

	public String create(CarDto carDto) {
		return gson.toJson(carService.create(carDto.getModel(), carDto.getOptionals()));
	}

	public String update(int id, CarDto carDto) {
		return gson.toJson(carService.update(id, carDto.getModel(), carDto.getOptionals()));
	}

	public boolean delete(int id) {
		return carService.delete(id);
	}

	public String calculateCost(CarDto carDto) {
		return gson.toJson(carService.calculateCost(carDto.getModel(), carDto.getOptionals()));
	}

	public String stats() {
		return gson.toJson(carService.stats());
	}

}
