package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.entities.EspacioEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import co.edu.poligran.serviciosalestudiante.repository.EspacioRepository;
import co.edu.poligran.serviciosalestudiante.service.EspacioService;
import co.edu.poligran.serviciosalestudiante.service.TipoEspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EspacioServiceImpl extends BaseService implements EspacioService {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private TipoEspacioService tipoEspacioService;

    @Override
    public List<EspacioDTO> getCubiculosEstudio() {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CUBICULO_ESTUDIO);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<EspacioEntity> cubiculos = espacioRepository.findByTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(cubiculos, EspacioDTO.class, mapper);
    }

    @Override
    public EspacioDTO crearEspacio(EspacioDTO espacio) {
        EspacioEntity cubiculoEntity = mapper.map(espacio, EspacioEntity.class);
        cubiculoEntity = espacioRepository.saveAndFlush(cubiculoEntity);
        return mapper.map(cubiculoEntity, EspacioDTO.class);
    }

}
