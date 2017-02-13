package co.edu.poligran.serviciosalestudiante;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import co.edu.poligran.serviciosalestudiante.entities.RoleEntity;
import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.repository.RoleRepository;
import co.edu.poligran.serviciosalestudiante.service.UserService;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

@Component
public class DefaultDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	private static final boolean INSERT_TEST_DATA = true;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;
	
	@Value("${app.default.admin.email}")
	private String defaultAdminEmail;
	
	@Value("${app.default.admin.fullName}")
	private String defaultAdminFullName;
	
	@Value("${security.user.name}")
	private String defaultAdminUsername;
	
	@Value("${security.user.password}")
	private String defaultAdminPassword;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent cre) {
		try {
			logger.info("initializing default data");
			initializeUserRoles();
			if (INSERT_TEST_DATA) {
				createDefaultUsers();
			}
			logger.info("finished initializing default data");
		} catch (Exception e) {
			logger.error("error initializing default data", e);
		}

	}

	private void initializeUserRoles() {
		if (roleRepository.count() == 0) {
			List<RoleEntity> roles = new ArrayList<>();

			RoleEntity adminRole = new RoleEntity();
			adminRole.setType(RoleTypeEnum.ADMIN);
			roles.add(adminRole);

			RoleEntity facultyOrStaffRole = new RoleEntity();
			facultyOrStaffRole.setType(RoleTypeEnum.FACULTY_OR_STAFF);
			roles.add(facultyOrStaffRole);

			RoleEntity studentRole = new RoleEntity();
			studentRole.setType(RoleTypeEnum.STUDENT);
			roles.add(studentRole);

			roleRepository.save(roles);
		}
	}

	private void createDefaultUsers() throws UsernameIsNotUniqueException, UserNotFoundException {
		if (!userService.isUserCreated("admin")) {
			createDefaultAdmin();
		}
	}

	private void createDefaultAdmin() throws UsernameIsNotUniqueException {
		UserDTO admin = new UserDTO();
		admin.setUsername(defaultAdminUsername);
		admin.setPassword(defaultAdminPassword);
		admin.setActive(true);
		admin.setEmail(defaultAdminEmail);
		admin.setFullName(defaultAdminFullName);
		userService.create(admin, RoleTypeEnum.ADMIN);
	}
}
