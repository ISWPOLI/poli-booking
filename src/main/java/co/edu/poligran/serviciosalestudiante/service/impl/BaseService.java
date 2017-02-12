package co.edu.poligran.serviciosalestudiante.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected Mapper mapper;

}
