package co.edu.poligran.serviciosalestudiante.service;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.service.dto.RoleDTO;

public interface RoleService {
	RoleDTO findByType(RoleTypeEnum roleType);
}
