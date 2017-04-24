package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.entities.BloquePlantillaEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import co.edu.poligran.serviciosalestudiante.repository.BloquePlantillaRepository;
import co.edu.poligran.serviciosalestudiante.service.BloquePlantillaService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BloquePlantillaServiceImpl extends BaseService implements BloquePlantillaService {

    @Autowired
    private BloquePlantillaRepository bloquePlantillaRepository;

    @Override
    public BloquePlantillaDTO crearBloquePlantilla(BloquePlantillaDTO bloquePlantillaDTO) {
        BloquePlantillaEntity bloquePlantillaEntity = mapper.map(bloquePlantillaDTO, BloquePlantillaEntity.class);

        bloquePlantillaEntity = bloquePlantillaRepository.saveAndFlush(bloquePlantillaEntity);

        return mapper.map(bloquePlantillaEntity, BloquePlantillaDTO.class);
    }

    @Override
    public List<BloquePlantillaDTO> consultarBloquesPlantillaPorTipoEspacio(TipoEspacioDTO tipoEspacio) {
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacio, TipoEspacioEntity.class);
        return DozerUtils.mapCollection(bloquePlantillaRepository.consultarBloquesPlantillaPorTipoEspacio
                        (tipoEspacioEntity),
                BloquePlantillaDTO.class, mapper);
    }

    @Override
    public boolean existePlantillaParaTipoEspacioYDia(TipoEspacioDTO tipoEspacio, DayOfWeek dia, Date horaInicio) {
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacio, TipoEspacioEntity.class);
        long conteo = bloquePlantillaRepository.contarBloquesPlantillaPorTipoEspacioYDia(tipoEspacioEntity,
                dia, horaInicio);
        return conteo > 0;
    }
}
