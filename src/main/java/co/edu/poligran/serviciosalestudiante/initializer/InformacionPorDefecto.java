package co.edu.poligran.serviciosalestudiante.initializer;

import co.edu.poligran.serviciosalestudiante.entities.RoleEntity;
import co.edu.poligran.serviciosalestudiante.entities.RoleTypeEnum;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.exception.UsernameIsNotUniqueException;
import co.edu.poligran.serviciosalestudiante.repository.RoleRepository;
import co.edu.poligran.serviciosalestudiante.repository.TipoEspacioRepository;
import co.edu.poligran.serviciosalestudiante.service.*;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
public class InformacionPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

    public static final LocalTime BLOQUE1_INICIO = new LocalTime(7, 0);
    public static final LocalTime BLOQUE1_FIN = new LocalTime(8, 30);
    public static final LocalTime BLOQUE2_INICIO = new LocalTime(8, 40);
    public static final LocalTime BLOQUE2_FIN = new LocalTime(10, 10);
    public static final LocalTime BLOQUE3_INICIO = new LocalTime(10, 20);
    public static final LocalTime BLOQUE3_FIN = new LocalTime(11, 50);
    public static final LocalTime BLOQUE4_INICIO = new LocalTime(12, 0);
    public static final LocalTime BLOQUE4_FIN = new LocalTime(13, 30);
    public static final LocalTime BLOQUE5_INICIO = new LocalTime(13, 40);
    public static final LocalTime BLOQUE5_FIN = new LocalTime(15, 10);
    public static final LocalTime BLOQUE6_INICIO = new LocalTime(15, 20);
    public static final LocalTime BLOQUE6_FIN = new LocalTime(16, 50);
    public static final LocalTime BLOQUE7_INICIO = new LocalTime(17, 0);
    public static final LocalTime BLOQUE7_FIN = new LocalTime(18, 30);
    public static final LocalTime BLOQUE8_INICIO = new LocalTime(18, 40);
    public static final LocalTime BLOQUE8_FIN = new LocalTime(20, 10);
    public static final LocalTime BLOQUE9_INICIO = new LocalTime(20, 20);
    public static final LocalTime BLOQUE9_FIN = new LocalTime(21, 50);
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BloqueService bloqueService;

    @Autowired
    private EspacioService espacioService;

    @Autowired
    private TipoEspacioRepository tipoEspacioRepository;

    @Autowired
    private TipoEspacioService tipoEspacioService;

    @Autowired
    private BloquePlantillaService bloquePlantillaService;

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

    @Value("${app.default.estudiante.username}")
    private String defaultEstudianteUsername;

    @Value("${app.default.estudiante.email}")
    private String defaultEstudianteEmail;

    @Value("${app.default.estudiante.fullName}")
    private String defaultEstudianteFullName;

    @Value("${app.default.estudiante.password}")
    private String defaultEstudiantePassword;

    @Value("${app.default.generar_datos_de_prueba}")
    private boolean isGenerarDatosDePrueba;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent cre) {
        try {
            logger.info("inicializando información por defecto");
            settearTimeZone();
            inicializarRolesDeUsuario();
            crearTiposEspacio();
            crearBloquesPlantillas();
            if (isGenerarDatosDePrueba) {
                crearUsuariosPorDefecto();

                crearCubiculosPorDefecto();
                crearCanchasPorDefecto();
                crearGimnasioPorDefecto();
                crearComputadorPorDefecto();
                CrearLaboratorioPorDefecto();

                crearBloquesPorDefecto();
            }
            logger.info("finalizó la inicialización de información por defecto");
        } catch (Exception e) {
            logger.error("error inicializando la información por defecto", e);
        }

    }

    private void crearTiposEspacio() {
        crearTipoEspacio(TipoEspacioDTO.CUBICULO_ESTUDIO);
        crearTipoEspacio(TipoEspacioDTO.CUBICULO_VIDEO);
        crearTipoEspacio(TipoEspacioDTO.GIMNASIO);
        crearTipoEspacio(TipoEspacioDTO.CANCHA_TENIS);
        crearTipoEspacio(TipoEspacioDTO.CANCHA_MULTIPLE);
        crearTipoEspacio(TipoEspacioDTO.CANCHA_FUTBOL);
        crearTipoEspacio(TipoEspacioDTO.COMPUTADOR);
        crearTipoEspacio(TipoEspacioDTO.LABORATORIO_FISICA);
        crearTipoEspacio(TipoEspacioDTO.LABORATORIO_QUIMICA);
        crearTipoEspacio(TipoEspacioDTO.LABORATORIO_ELECTRONICA);
        crearTipoEspacio(TipoEspacioDTO.LABORATORIO_TELECO);
    }

    private void crearTipoEspacio(String nombre) {
        if (tipoEspacioRepository.findByNombre(nombre) == null) {
            TipoEspacioEntity tipoEspacioEntity = new TipoEspacioEntity();
            tipoEspacioEntity.setNombre(nombre);
            tipoEspacioRepository.saveAndFlush(tipoEspacioEntity);
        }
    }

    private void crearBloquesPlantillas() {
        crearBloquesPlantillaEspacio(TipoEspacioDTO.CUBICULO_ESTUDIO);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.CUBICULO_VIDEO);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.CANCHA_FUTBOL);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.CANCHA_TENIS);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.CANCHA_MULTIPLE);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.GIMNASIO);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.COMPUTADOR);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.LABORATORIO_FISICA);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.LABORATORIO_QUIMICA);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.LABORATORIO_ELECTRONICA);
        crearBloquesPlantillaEspacio(TipoEspacioDTO.LABORATORIO_TELECO);
    }

    private void crearBloquesPlantillaEspacio(String tipoEspacio) {
        DayOfWeek[] dias = DayOfWeek.values();
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);

        for (DayOfWeek dia : dias) {
            if (!dia.equals(DayOfWeek.SUNDAY)) {
                crearBloquePlantilla(dia, BLOQUE1_INICIO, BLOQUE1_FIN, tipoEspacioDTO);
                crearBloquePlantilla(dia, BLOQUE2_INICIO, BLOQUE2_FIN, tipoEspacioDTO);
                crearBloquePlantilla(dia, BLOQUE3_INICIO, BLOQUE3_FIN, tipoEspacioDTO);
                crearBloquePlantilla(dia, BLOQUE4_INICIO, BLOQUE4_FIN, tipoEspacioDTO);
                crearBloquePlantilla(dia, BLOQUE5_INICIO, BLOQUE5_FIN, tipoEspacioDTO);
                crearBloquePlantilla(dia, BLOQUE6_INICIO, BLOQUE6_FIN, tipoEspacioDTO);

                if (!dia.equals(DayOfWeek.SATURDAY)) {
                    crearBloquePlantilla(dia, BLOQUE7_INICIO, BLOQUE7_FIN, tipoEspacioDTO);
                    crearBloquePlantilla(dia, BLOQUE8_INICIO, BLOQUE8_FIN, tipoEspacioDTO);
                    crearBloquePlantilla(dia, BLOQUE9_INICIO, BLOQUE9_FIN, tipoEspacioDTO);
                }
            }
        }
    }

    private void crearBloquePlantilla(DayOfWeek dia, LocalTime inicio, LocalTime fin, TipoEspacioDTO tipoEspacio) {
        if (!bloquePlantillaService.existePlantillaParaTipoEspacioYDia(tipoEspacio, dia, inicio
                .toDateTimeToday().toDate()
        )) {
            BloquePlantillaDTO bloquePlantilla = new BloquePlantillaDTO();
            bloquePlantilla.setDia(dia);
            bloquePlantilla.setTipoEspacio(tipoEspacio);
            bloquePlantilla.setHoraInicio(inicio.toDateTimeToday().toDate());
            bloquePlantilla.setHoraFin(fin.toDateTimeToday().toDate());

            bloquePlantillaService.crearBloquePlantilla(bloquePlantilla);
        }
    }

    private void settearTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-5:00"));
    }

    private void crearCubiculosPorDefecto() {
        logger.info("creando cubiculos por defecto");

        TipoEspacioDTO tipoCubiculoEstudio = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO
                .CUBICULO_ESTUDIO);
        crearCubiculo("cubiculo1", tipoCubiculoEstudio);
        crearCubiculo("cubiculo2", tipoCubiculoEstudio);

        TipoEspacioDTO tipoCubiculoVideo = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CUBICULO_VIDEO);
        crearCubiculo("cubiculoVideo1", tipoCubiculoVideo);
        crearCubiculo("cubiculoVideo2", tipoCubiculoVideo);
    }

    private void crearCanchasPorDefecto() {
        logger.info("creando canchas por defecto");

        TipoEspacioDTO tipoCanchaFutbol = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_FUTBOL);
        crearCancha("canchaFutbolTenis1", tipoCanchaFutbol);
        crearCancha("canchaFutbolTenis2", tipoCanchaFutbol);

        TipoEspacioDTO tipoCanchaTenis = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_TENIS);
        crearCancha("canchaTenis", tipoCanchaTenis);

        TipoEspacioDTO tipoCanchaMultiple = tipoEspacioService
                .buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_MULTIPLE);
        crearCancha("canchaMultiple", tipoCanchaMultiple);
    }

    private void crearGimnasioPorDefecto() {
        logger.info("creando gimnasio por defecto");
        TipoEspacioDTO tipoGimnasio = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.GIMNASIO);
        crearGimansio("cupoGimnasio", tipoGimnasio);
    }
    
    private void crearComputadorPorDefecto(){
    	logger.info("creando computador por defecto");
    	TipoEspacioDTO tipoComputador=tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.COMPUTADOR);
    	crearComputador("computador1", tipoComputador);
    	crearComputador("computador2", tipoComputador);
    }
    
    private void CrearLaboratorioPorDefecto(){
    	logger.info("creando laboratorio por defecto");
    	TipoEspacioDTO tipoFisica=tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.LABORATORIO_FISICA);
    	crearLaboratorio("Lab Fisica", tipoFisica);
    	
    	TipoEspacioDTO tipoQuimica=tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.LABORATORIO_QUIMICA);
    	crearLaboratorio("Lab Quimica", tipoQuimica);
    	
    	
    	TipoEspacioDTO tipoElectronica=tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.LABORATORIO_ELECTRONICA);
    	crearLaboratorio("Lab Electronica", tipoElectronica);
    	
    	
    	TipoEspacioDTO tipoTeleco=tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.LABORATORIO_TELECO);
    	crearLaboratorio("Lab Teleco", tipoTeleco);
    	
    }

    private void crearCubiculo(String nombre, TipoEspacioDTO tipoEspacioDTO) {
        if (!espacioService.existeEspacio(nombre, tipoEspacioDTO)) {
            EspacioDTO cubiculo = new EspacioDTO();
            cubiculo.setNombre(nombre);
            cubiculo.setTipoEspacio(tipoEspacioDTO);
            espacioService.crearEspacio(cubiculo);
        }
    }

    private void crearCancha(String nombre, TipoEspacioDTO tipoEspacioDTO) {
        if (!espacioService.existeEspacio(nombre, tipoEspacioDTO)) {
            EspacioDTO cancha = new EspacioDTO();
            cancha.setNombre(nombre);
            cancha.setTipoEspacio(tipoEspacioDTO);
            cancha.setCupos(1);
            espacioService.crearEspacio(cancha);
        }
    }

    private void crearGimansio(String nombre, TipoEspacioDTO tipoEspacioDTO) {
        if (!espacioService.existeEspacio(nombre, tipoEspacioDTO)) {
            EspacioDTO gimnasio = new EspacioDTO();
            gimnasio.setNombre(nombre);
            gimnasio.setTipoEspacio(tipoEspacioDTO);
            gimnasio.setCupos(15);
            espacioService.crearEspacio(gimnasio);
        }
    }
    private void crearComputador(String nombre, TipoEspacioDTO tipoEspacioDTO){
    	if(!espacioService.existeEspacio(nombre, tipoEspacioDTO)){
    		EspacioDTO computador=new EspacioDTO();
    		computador.setNombre(nombre);
    		computador.setTipoEspacio(tipoEspacioDTO);
    		computador.setCupos(20);
    		espacioService.crearEspacio(computador);
    	}
    }
    
    private void crearLaboratorio(String nombre, TipoEspacioDTO tipoEspacio){
    	if(!espacioService.existeEspacio(nombre, tipoEspacio)){
    		EspacioDTO laboratorio=new EspacioDTO();
    		laboratorio.setNombre(nombre);
    		laboratorio.setTipoEspacio(tipoEspacio);
    		laboratorio.setCupos(1);
    		espacioService.crearEspacio(laboratorio);
    	}
    }

    private void crearBloquesPorDefecto() {
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.CUBICULO_ESTUDIO);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.CUBICULO_VIDEO);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.CANCHA_FUTBOL);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.CANCHA_TENIS);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.CANCHA_MULTIPLE);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.GIMNASIO);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.COMPUTADOR);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.LABORATORIO_FISICA);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.LABORATORIO_QUIMICA);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.LABORATORIO_ELECTRONICA);
        crearBloquesEspacioPorDefecto(TipoEspacioDTO.LABORATORIO_TELECO);
        
    }

    private void crearBloquesEspacioPorDefecto(String tipoEspacio) {
        LocalDate hoy = new LocalDate();
        TipoEspacioDTO espacio = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        bloqueService.generarBloquesMasivamente(espacio, hoy.toDate(), hoy.plusWeeks(1).toDate());
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
        if (!usuarioService.existeUsuario(defaultAdminUsername)) {
            createDefaultAdmin();
        }
        if (!usuarioService.existeUsuario(defaultEstudianteUsername)) {
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
        usuarioService.crear(admin, RoleTypeEnum.ADMIN);
    }

    private void createDefaultEstudiante() throws UsernameIsNotUniqueException {
        UsuarioDTO estudiante = new UsuarioDTO();
        estudiante.setUsername(defaultEstudianteUsername);
        estudiante.setPassword(passwordEncoder.encode(defaultEstudiantePassword));
        estudiante.setActive(true);
        estudiante.setEmail(defaultEstudianteEmail);
        estudiante.setFullName(defaultEstudianteFullName);
        usuarioService.crear(estudiante, RoleTypeEnum.STUDENT);
    }
}
