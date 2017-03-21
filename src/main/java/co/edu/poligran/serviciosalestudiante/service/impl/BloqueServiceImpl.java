package co.edu.poligran.serviciosalestudiante.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;
import co.edu.poligran.serviciosalestudiante.entities.EspacioEntity;
import co.edu.poligran.serviciosalestudiante.repository.BloqueRepository;
import co.edu.poligran.serviciosalestudiante.service.BloqueService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;

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
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYEspacio(Date dia, Long idEspacio) {
        List<BloqueEntity> bloques = bloqueRepository.consultarBloquesVigentesPorDiaYEspacio(dia, idEspacio);

        return DozerUtils.mapCollection(bloques, BloqueDTO.class, mapper);
    }

    @Override
    public List<BloqueDTO> consultarBloquesVigentes() {
        List<BloqueEntity> bloques = bloqueRepository.consultarBloquesVigentes();

        return DozerUtils.mapCollection(bloques, BloqueDTO.class, mapper);
    }

    @Override
    public BloqueDTO consultarBloque(Long idBloque) {
        return mapper.map(bloqueRepository.findOne(idBloque), BloqueDTO.class);
    }
}
