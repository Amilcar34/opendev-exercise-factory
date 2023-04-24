package com.opendev.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.opendev.entity.Optional;
import com.opendev.repository.OptionalRepository;

public class OptionalRepositoryImpl implements OptionalRepository {

	@Override
	public Optional getOne(Integer id) {
		return dbOptionals.get(id);
	}

	transient static final Map<Integer, Optional> dbOptionals = new HashMap<>() {{
		put(1, new Optional(1, "TC", "Techo corredizo", 12000.0));
		put(2, new Optional(2, "AA", "Aire acondicionado", 20000.0));
		put(3, new Optional(3, "ABS", "Sistemas de frenos ABS", 14000.0));
		put(4, new Optional(4, "AB", "Airbag", 7000.0));
		put(5, new Optional(5, "LL", "Llantas de aleación", 12000.0));
	}};
}
