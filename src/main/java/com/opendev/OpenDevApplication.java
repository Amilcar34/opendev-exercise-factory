package com.opendev;

import com.opendev.dto.CarDto;
import com.opendev.rest.CarRest;
import com.opendev.service.impl.CarServiceImpl;

public class OpenDevApplication {

	static CarRest rest = new CarRest(new CarServiceImpl());

	public static void main(String[] args) {
		
		System.err.println("---------------------- Servicio 'stats' --------------------");
		System.out.println(rest.stats());
		System.out.println();
		
		System.err.println("---------------------- Servicio 'create' --------------------");
		System.out.println(rest.create(new CarDto(1, null)));
		System.out.println();
		
		System.err.println("---------------------- Servicio 'stats' luego de agregar un auto--------------------");
		System.out.println(rest.stats());
		System.out.println();
		
		System.err.println("---------------------- Servicio 'delete by ID=2' --------------------");
		System.out.println("El elemento fue eliminado: " + rest.delete(2));
		System.out.println();

		System.err.println("---------------------- Servicio 'stats' luego de eliminar un auto--------------------");
		System.out.println(rest.stats());
		System.out.println();
	}

}
