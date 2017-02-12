package co.edu.poligran.serviciosalestudiante.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.entities.UserEntity;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.repository.UserRepository;
import co.edu.poligran.serviciosalestudiante.service.RoleService;
import co.edu.poligran.serviciosalestudiante.service.UserService;
import co.edu.poligran.serviciosalestudiante.service.dto.RoleDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO getByUsername(String username) throws UserNotFoundException {
		UserEntity user = userRepository.findByUsername(username);

		if (user != null) {
			return mapper.map(user, UserDTO.class);
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public UserDTO create(UserDTO userDTO, RoleTypeEnum roleType) throws UsernameIsNotUniqueException {
		processValidations(userDTO);

		setUserRole(roleType, userDTO);

		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userDTO.setActive(true);

		UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
		return mapper.map(userRepository.saveAndFlush(userEntity), UserDTO.class);
	}

	private void processValidations(UserDTO userBean) throws UsernameIsNotUniqueException {
		if (!isUsernameUnique(userBean.getId(), userBean.getUsername())) {
			throw new UsernameIsNotUniqueException();
		}
	}

	private void setUserRole(RoleTypeEnum roleType, UserDTO userDTO) {
		RoleDTO role = roleService.findByType(roleType);
		userDTO.getRoles().add(role);
	}

	@Override
	public boolean isUsernameUnique(Long idUsuario, String username) {
		try {
			UserDTO persistedUser = getByUsername(username);
			return persistedUser != null && persistedUser.getId().compareTo(idUsuario) != 0;
		} catch (UserNotFoundException e) {
			return true;
		}
	}

	@Override
	public boolean isUserCreated(String username) {
		return userRepository.isUserCreated(username);
	}
}
