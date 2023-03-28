package com.opendev.service.impl.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.opendev.entity.Car;
import com.opendev.entity.Model;
import com.opendev.entity.Optional;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.OptionalRepositoryImpl;
import com.opendev.service.CarService;
import com.opendev.service.impl.CarServiceImpl;

public class CarServiceImplTest {
	
	@Test
	public void metodoCreateCorrectoFuncionamiento() {


		Integer idModel = 01;
//		Model model = new Model(idModel, "Sedan", 90000.00);
//		Integer idCar = 26;
//		Optional optional = new Optional(225, "TC", "Techo Corredizo", 12000.00);
		Integer id1 = 551, id2 = 990;
		Set<Integer> idOpcionales = new HashSet<>();
		//Set<Optional> opcionales = new HashSet<>();

		idOpcionales.add(id1);
		idOpcionales.add(id2);
		//opcionales.add(optional);
		
		OptionalRepository or = new OptionalRepositoryImpl();
		or.getOne(id1);
		
	//	Car car = new Car(idCar, model, opcionales);
		
		CarService csi = new CarServiceImpl();
		csi.create(idModel, idOpcionales);
	}

}
