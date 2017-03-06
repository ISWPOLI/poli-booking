package co.edu.poligran.serviciosalestudiante;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.edu.poligran.serviciosalestudiante.entities.RoleEntity;
import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.repository.RoleRepository;
import co.edu.poligran.serviciosalestudiante.service.BloquesService;
import co.edu.poligran.serviciosalestudiante.service.EspacioService;
import co.edu.poligran.serviciosalestudiante.service.UserService;
import co.edu.poligran.serviciosalestudiante.service.dto.CubiculoDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UserDTO;

@Component
public class InformacionPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

	private static final boolean INSERTAR_INFO_POR_DEFECTO = true;
	private static final long DIAS_BLOQUES_POR_DEFECTO = 60;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private BloquesService bloquesService;

	@Autowired
	private EspacioService cubiculoService;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
			logger.info("inicializando información por defecto");
			inicializarRolesDeUsuario();
			if (INSERTAR_INFO_POR_DEFECTO) {
				crearUsuariosPorDefecto();

				crearCubiculosPorDefecto();
				crearBloquesDeCubiculosPorDefecto();
			}
			logger.info("finalizó la inicialización de información por defecto");
		} catch (Exception e) {
			logger.error("error inicializando la información por defecto", e);
		}

	}

	private void crearCubiculosPorDefecto() {
		crearCubiculo("cubiculo1");
		crearCubiculo("cubiculo2");
		crearCubiculo("cubiculo3");
		crearCubiculo("cubiculo4");
		crearCubiculo("cubiculo5");
	}

	private void crearCubiculo(String nombre) {
		CubiculoDTO cubiculo = new CubiculoDTO();
		cubiculo.setNombre(nombre);
		cubiculoService.crearCubiculo(cubiculo);
	}

	private void crearBloquesDeCubiculosPorDefecto() {
		List<CubiculoDTO> cubiculos = cubiculoService.getCubiculos();
		for (CubiculoDTO cubiculo : cubiculos) {
			bloquesService.generarBloques(cubiculo, DIAS_BLOQUES_POR_DEFECTO);
		}

	}

	private void inicializarRolesDeUsuario() {
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

	private void crearUsuariosPorDefecto() throws UsernameIsNotUniqueException, UserNotFoundException {
		if (!userService.isUserCreated("admin")) {
			createDefaultAdmin();
		}
	}

	private void createDefaultAdmin() throws UsernameIsNotUniqueException {
		UserDTO admin = new UserDTO();
		admin.setUsername(defaultAdminUsername);
		admin.setPassword(passwordEncoder.encode(defaultAdminPassword));
		admin.setActive(true);
		admin.setEmail(defaultAdminEmail);
		admin.setFullName(defaultAdminFullName);
		userService.create(admin, RoleTypeEnum.ADMIN);
	}
}
