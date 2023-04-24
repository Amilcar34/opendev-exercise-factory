package com.opendev.entity;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class CarTest {
	
	@Test
	public void allArgsConstructor() {
		Model model = new Model();
		Optional optional = new Optional();
		Set<Optional> optionals = Set.of(optional);
		
		Car car = new Car(2, model, optionals, 100.0);
	}

}
