package com.opendev.service.impl;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import com.opendev.entity.Optional;
import com.opendev.repository.OptionalRepository;
import com.opendev.repository.impl.OptionalRepositoryImpl;
import com.opendev.service.OptionalService;

public class OptionalServiceImpl implements OptionalService {
  
  OptionalRepository optionalRepository = new OptionalRepositoryImpl();

  @Override
  public Double sumCost(Set<Optional> optionals){
	return optionals.stream().map(optional -> optional.getCost()).reduce(Double::sum).get();
  }
  
  public Set<Optional> getByIds(Set<Integer> idsOptionals){
	return idsOptionals.stream().map(id -> optionalRepository.getOne(id)).collect(toSet());
  }

}
