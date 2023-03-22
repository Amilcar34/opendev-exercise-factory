package com.opendev.repository.impl;

import static com.opendev.repository.impl.ModelRepositoryImpl.dbModels;
import static com.opendev.repository.impl.OptionalRepositoryImpl.dbOptionals;
import static java.util.Set.of;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.opendev.contracts.StatsModel;
import com.opendev.contracts.StatsOptional;
import com.opendev.entity.Car;
import com.opendev.repository.CarRepository;

public class CarRepositoryImpl implements CarRepository {

	@Override
	public Car save(Car entity) {

		int id = dbCars.size() + 1;
		entity.setId(id);
		dbCars.put(id, entity);
		return dbCars.get(dbCars.size());
	}

	@Override
	public void deleteById(int id) {
		dbCars.remove(id);
	}

	@Override
	public boolean existsById(int id) {
		return dbCars.containsKey(id);
	}

	@Override
	public Car getOne(int id) {
		return dbCars.get(id);
	}

	@Override
	public int count() {
		return dbCars.size();
	}

	@Override
	public Set<StatsModel> statsModel() {

		Set<StatsModel> statsModel = new HashSet<>();
		var models = dbCars.entrySet().stream().map(v -> v.getValue().getModel()).distinct();

		models.forEach(m -> {
			String name = m.getName();
			long count = dbCars.entrySet().stream().map(Entry::getValue).map(Car::getModel)
					.filter(f -> f.getId().equals(m.getId())).count();
			double percent = 100 * count / dbCars.size();
			statsModel.add(new StatsModel(name, count, percent));
		});

		return statsModel;
	}

	@Override
	public Set<StatsOptional> statsOptional() {

		Set<StatsOptional> statsOptionals = new HashSet<>();

		var optionalGroupByCars = dbCars.entrySet().stream().map(Entry::getValue)
				.flatMap(car -> car.getOptionals().stream().map(optional -> new AbstractMap.SimpleEntry<>(optional, car)))
				.collect(groupingBy(AbstractMap.SimpleEntry::getKey, mapping(AbstractMap.SimpleEntry::getValue, toList())));

		var optionalTotalSum = dbCars.entrySet().stream().map(Entry::getValue).map(c -> c.getOptionals().size()).reduce(0, Integer::sum);

		optionalGroupByCars.forEach((k, v) -> {
			double percent = 100 * v.size() / optionalTotalSum;
			statsOptionals.add(new StatsOptional(k.getName(), v.size(), percent));
		});
		return statsOptionals;
	}

	private transient static final Map<Integer, Car> dbCars = new HashMap<>() {
		{
			put(1, new Car(1, dbModels.get(1), of(dbOptionals.get(2), dbOptionals.get(4))));
			put(2, new Car(2, dbModels.get(1), of(dbOptionals.get(1), dbOptionals.get(4))));
			put(3, new Car(3, dbModels.get(2), of(dbOptionals.get(2), dbOptionals.get(4))));
			put(4, new Car(4, dbModels.get(3), of(dbOptionals.get(1), dbOptionals.get(2), dbOptionals.get(3), dbOptionals.get(4))));
			put(5, new Car(5, dbModels.get(3), of()));
		}
	};
}
