package com.opendev.entity;

import static java.lang.Double.sum;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	private Integer id;

	private Model model;

	private Set<Optional> optionals;

	private Double price;
	
	public Car(Integer id, Model model, Set<Optional> optionals) {

		this.id = id;
		this.model = model;
		this.optionals = optionals;
		
		Double optionalsCost;
		if (optionals == null || optionals.isEmpty())
			optionalsCost = 0.0;
		else 
			optionalsCost = optionals.stream().map(Optional::getCost).mapToDouble(Double::doubleValue).reduce(0, Double::sum);
		this.price = sum(optionalsCost, model.getCost());
		
	}

}
