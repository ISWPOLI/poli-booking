package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;
import co.edu.poligran.serviciosalestudiante.entities.EspacioEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import co.edu.poligran.serviciosalestudiante.repository.BloqueRepository;
import co.edu.poligran.serviciosalestudiante.service.*;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.*;

@Service
@Transactional
public class BloqueServiceImpl extends BaseService implements BloqueService {

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

    @Autowired
    private BloqueRepository bloqueRepository;

    @Autowired
    private BloquePlantillaService bloquePlantillaService;

    @Autowired
    private EspacioService espacioService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private TipoEspacioService tipoEspacioService;

    @Override
    public void generarBloques(EspacioDTO espacio, long numeroDeDias) {
        LocalDate hoy = new LocalDate(new Date());

        for (int i = 0; i < numeroDeDias; i++) {
            LocalDate dia = hoy.plusDays(i);
            Date diaDate = dia.toDate();

            if (dia.getDayOfWeek() != DateTimeConstants.SUNDAY) {
                crearBloque(diaDate, BLOQUE1_INICIO, BLOQUE1_FIN, espacio);
                crearBloque(diaDate, BLOQUE2_INICIO, BLOQUE2_FIN, espacio);
                crearBloque(diaDate, BLOQUE3_INICIO, BLOQUE3_FIN, espacio);
                crearBloque(diaDate, BLOQUE4_INICIO, BLOQUE4_FIN, espacio);
                crearBloque(diaDate, BLOQUE5_INICIO, BLOQUE5_FIN, espacio);
                crearBloque(diaDate, BLOQUE6_INICIO, BLOQUE6_FIN, espacio);

                if (dia.getDayOfWeek() != (DateTimeConstants.SATURDAY)) {
                    crearBloque(diaDate, BLOQUE7_INICIO, BLOQUE7_FIN, espacio);
                    crearBloque(diaDate, BLOQUE8_INICIO, BLOQUE8_FIN, espacio);
                    crearBloque(diaDate, BLOQUE9_INICIO, BLOQUE9_FIN, espacio);
                }
            }
        }
    }

    private void crearBloque(Date diaDate, LocalTime inicio, LocalTime fin, EspacioDTO espacio) {
        BloqueEntity bloque = new BloqueEntity();
        bloque.setTiempoInicio(new Date(diaDate.getTime() + inicio.getMillisOfDay()));
        bloque.setTiempoFin(new Date(diaDate.getTime() + fin.getMillisOfDay()));
        bloque.setEspacio(mapper.map(espacio, EspacioEntity.class));
        bloque.setDia(DateUtils.truncate(bloque.getTiempoInicio(), Calendar.DAY_OF_MONTH));
        bloqueRepository.saveAndFlush(bloque);
    }

    @Override
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(Date dia, String tipoEspacio) {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<BloqueEntity> bloques = bloqueRepository.consultarBloquesVigentesPorDiaYTipoEspacio(dia,
                tipoEspacioEntity);

        return DozerUtils.mapCollection(bloques, BloqueDTO.class, mapper);
    }

    @Override
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(Date dia, TipoEspacioDTO tipoEspacio) {
        return consultarBloquesVigentesPorDiaYTipoEspacio(dia, tipoEspacio.getNombre());
    }

    @Override
    public List<BloqueDTO> consultarBloquesVigentesPorTipoEspacio(String tipoEspacio) {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<BloqueEntity> bloques = bloqueRepository.consultarBloquesVigentesPorTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(bloques, BloqueDTO.class, mapper);
    }

    @Override
    public List<BloqueDTO> consultarBloquesVigentesPorTipoEspacio(TipoEspacioDTO tipoEspacio) {
        return consultarBloquesVigentesPorTipoEspacio(tipoEspacio.getNombre());
    }

    @Override
    public List<DiaCalendarioBean> consultarDiasConBloquesDisponibles(String tipoEspacio) {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);

        List<Date> dias = bloqueRepository.consultarDiasConBloquesVigentesPorTipoEspacio(tipoEspacioEntity);

        return transformarDiasACalendario(dias);
    }

    private List<DiaCalendarioBean> transformarDiasACalendario(List<Date> dias) {
        List<DiaCalendarioBean> diasCalendario = new ArrayList<>();
        for (Date dia : dias) {
            DiaCalendarioBean diaCalendario = new DiaCalendarioBean();
            diaCalendario.setDia(dia);
            diasCalendario.add(diaCalendario);
        }

        return diasCalendario;
    }

    @Override
    public BloqueDTO consultarBloque(Long idBloque) {
        return mapper.map(bloqueRepository.findOne(idBloque), BloqueDTO.class);
    }

    @Override
    public List<BloqueDTO> generarBloquesMasivamente(String tipoEspacio, Date fechaInicio, Date fechaFin) {

        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        Map<DayOfWeek, List<BloquePlantillaDTO>> mapaBloquesPlantillaPorDia = armarMapaDeBloquesPlantillaPorDia
                (tipoEspacioDTO);
        List<EspacioDTO> espacios = espacioService.getEspaciosPorTipoEspacio(tipoEspacioDTO);
        LocalDate fechaInicioJoda = new LocalDate(fechaInicio);
        LocalDate fechaFinJoda = new LocalDate(fechaFin);

        List<BloqueEntity> bloquesCreados = crearBloquesHorarios(mapaBloquesPlantillaPorDia, espacios, fechaInicioJoda,
                fechaFinJoda);

        return DozerUtils.mapCollection(bloquesCreados, BloqueDTO.class, mapper);
    }

    @Override
    public List<BloqueDTO> generarBloquesMasivamente(TipoEspacioDTO tipoEspacio, Date fechaInicio, Date fechaFin) {
        return generarBloquesMasivamente(tipoEspacio.getNombre(), fechaInicio, fechaFin);
    }

    @Override
    public void eliminarBloquesMasivamente(String tipoEspacio, Date diaInicio, Date diaFin) {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<BloqueEntity> bloquesParaEliminar = bloqueRepository.findByTipoEspacioAndDateInterval(tipoEspacioEntity,
                diaInicio, diaFin);

        for (BloqueEntity bloque : bloquesParaEliminar) {
            BloqueDTO bloqueDTO = mapper.map(bloque, BloqueDTO.class);
            reservaService.eliminarReservasDelBloque(bloqueDTO);
        }

        bloqueRepository.delete(bloquesParaEliminar);
    }

    @Override
    public void eliminarBloquesMasivamente(TipoEspacioDTO tipoEspacioDTO, Date diaInicio, Date diaFin) {
        eliminarBloquesMasivamente(tipoEspacioDTO.getNombre(), diaInicio, diaFin);
    }

    private List<BloqueEntity> crearBloquesHorarios(Map<DayOfWeek, List<BloquePlantillaDTO>> mapaBloquesPorDia,
                                                    List<EspacioDTO> espacios, LocalDate fechaInicioJoda, LocalDate
                                                            fechaFinJoda) {
        LocalDate fechaActual = fechaInicioJoda;
        List<BloqueDTO> bloquesParaCrearDTOs = new ArrayList<>();

        while (fechaActual.compareTo(fechaFinJoda) <= 0) {

            DayOfWeek diaDeLaSemana = DayOfWeek.of(fechaActual.getDayOfWeek());
            List<BloquePlantillaDTO> bloquesDelDia = mapaBloquesPorDia.get(diaDeLaSemana);

            if (bloquesDelDia != null && !bloquesDelDia.isEmpty()) {

                for (BloquePlantillaDTO bloque : bloquesDelDia) {

                    for (EspacioDTO espacio : espacios) {
                        BloqueDTO bloqueCandidato = bloque.obtenerBloqueDTO(espacio, fechaActual.toDate());
                        EspacioEntity espacioEntity = mapper.map(espacio, EspacioEntity.class);
                        if (bloqueRepository.countByEspacioAndTiempoInicio(espacioEntity, bloqueCandidato
                                .getTiempoInicio()) == 0) {
                            bloquesParaCrearDTOs.add(bloqueCandidato);
                        }
                    }
                }
            }

            fechaActual = fechaActual.plusDays(1);
        }

        List<BloqueEntity> bloquesParaCrearEntities = DozerUtils.mapCollection(bloquesParaCrearDTOs, BloqueEntity.class,
                mapper);
        bloquesParaCrearEntities = bloqueRepository.save(bloquesParaCrearEntities);

        return bloquesParaCrearEntities;
    }

    private Map<DayOfWeek, List<BloquePlantillaDTO>> armarMapaDeBloquesPlantillaPorDia(TipoEspacioDTO tipoEspacioDTO) {

        List<BloquePlantillaDTO> bloquesPlantilla = bloquePlantillaService.consultarBloquesPlantillaPorTipoEspacio
                (tipoEspacioDTO);
        Map<DayOfWeek, List<BloquePlantillaDTO>> mapaBloquesPorDia = new HashMap<>();

        for (BloquePlantillaDTO bloquePlantilla : bloquesPlantilla) {
            DayOfWeek dia = bloquePlantilla.getDia();
            if (!mapaBloquesPorDia.containsKey(dia)) {
                mapaBloquesPorDia.put(dia, new ArrayList<>());
            }
            List<BloquePlantillaDTO> bloquesDelDia = mapaBloquesPorDia.get(dia);
            if (!bloquesDelDia.contains(bloquePlantilla)) {
                bloquesDelDia.add(bloquePlantilla);
            }
        }

        return mapaBloquesPorDia;
    }
}
