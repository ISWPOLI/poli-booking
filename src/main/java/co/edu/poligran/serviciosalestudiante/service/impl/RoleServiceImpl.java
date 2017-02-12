package co.edu.poligran.serviciosalestudiante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.entities.RoleEntity;
import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.repository.RoleRepository;
import co.edu.poligran.serviciosalestudiante.service.RoleService;
import co.edu.poligran.serviciosalestudiante.service.dto.RoleDTO;

@Service
@Transactional
public class RoleServiceImpl extends BaseService implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDTO findByType(RoleTypeEnum roleType) {
		RoleEntity userRoleEntity = roleRepository.findByType(roleType);
		return mapper.map(userRoleEntity, RoleDTO.class);
	}
}
