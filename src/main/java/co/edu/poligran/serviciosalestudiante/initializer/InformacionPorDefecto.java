package co.edu.poligran.serviciosalestudiante.initializer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacio;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.repository.RoleRepository;
import co.edu.poligran.serviciosalestudiante.service.BloqueService;
import co.edu.poligran.serviciosalestudiante.service.EspacioService;
import co.edu.poligran.serviciosalestudiante.service.ReservaService;
import co.edu.poligran.serviciosalestudiante.service.UsuarioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;

@Component
public class InformacionPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

    private static final boolean INSERTAR_INFO_POR_DEFECTO = true;
    private static final long DIAS_BLOQUES_POR_DEFECTO = 15;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BloqueService bloqueService;

    @Autowired
    private EspacioService cubiculoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReservaService reservaService;

    @Value("${app.default.admin.email}")
    private String defaultAdminEmail;

    @Value("${app.default.admin.fullName}")
    private String defaultAdminFullName;

    @Value("${security.user.name}")
    private String defaultAdminUsername;

    @Value("${security.user.password}")
    private String defaultAdminPassword;

    @Value("${app.default.estudiante.username}")
    private String defaultEstudianteUsername;

    @Value("${app.default.estudiante.email}")
    private String defaultEstudianteEmail;

    @Value("${app.default.estudiante.fullName}")
    private String defaultEstudianteFullName;

    @Value("${app.default.estudiante.password}")
    private String defaultEstudiantePassword;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {
        try {
            logger.info("inicializando información por defecto");
            settearTimeZone();
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

    private void settearTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-5:00"));
    }

    private void crearCubiculosPorDefecto() {
        logger.info("creando cubiculos por defecto");

        crearCubiculo("cubiculo1");
        crearCubiculo("cubiculo2");
    }

    private void crearCubiculo(String nombre) {
        EspacioDTO cubiculo = new EspacioDTO();
        cubiculo.setNombre(nombre);
        cubiculo.setTipoEspacio(TipoEspacio.CUBICULO);
        cubiculoService.crearEspacio(cubiculo);
    }

    private void crearBloquesDeCubiculosPorDefecto() {
        logger.info("creando bloques de cubiculos por defecto");

        List<EspacioDTO> cubiculos = cubiculoService.getCubiculos();
        for (EspacioDTO cubiculo : cubiculos) {
            bloqueService.generarBloques(cubiculo, DIAS_BLOQUES_POR_DEFECTO);
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
        logger.info("creando usuarios por defecto");
        if (!usuarioService.isUserCreated(defaultAdminUsername)) {
            createDefaultAdmin();
        }
        if (!usuarioService.isUserCreated(defaultEstudianteUsername)) {
            createDefaultEstudiante();
        }
    }

    private void createDefaultAdmin() throws UsernameIsNotUniqueException {
        UsuarioDTO admin = new UsuarioDTO();
        admin.setUsername(defaultAdminUsername);
        admin.setPassword(passwordEncoder.encode(defaultAdminPassword));
        admin.setActive(true);
        admin.setEmail(defaultAdminEmail);
        admin.setFullName(defaultAdminFullName);
        usuarioService.create(admin, RoleTypeEnum.ADMIN);
    }

    private void createDefaultEstudiante() throws UsernameIsNotUniqueException {
        UsuarioDTO estudiante = new UsuarioDTO();
        estudiante.setUsername(defaultEstudianteUsername);
        estudiante.setPassword(passwordEncoder.encode(defaultEstudiantePassword));
        estudiante.setActive(true);
        estudiante.setEmail(defaultEstudianteEmail);
        estudiante.setFullName(defaultEstudianteFullName);
        usuarioService.create(estudiante, RoleTypeEnum.STUDENT);
    }
}
