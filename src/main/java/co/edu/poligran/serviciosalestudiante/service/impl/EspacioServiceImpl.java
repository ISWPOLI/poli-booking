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
    public List<EspacioDTO> getCubiculosVideo() {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CUBICULO_VIDEO);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<EspacioEntity> cubiculosV = espacioRepository.findByTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(cubiculosV, EspacioDTO.class, mapper);
    }

    @Override
    public List<EspacioDTO> getCanchaFutbol() {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_FUTBOL);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<EspacioEntity> canchaFutbol = espacioRepository.findByTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(canchaFutbol, EspacioDTO.class, mapper);
    }

    @Override
    public List<EspacioDTO> getCanchaTenis() {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_TENIS);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<EspacioEntity> canchaTenis = espacioRepository.findByTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(canchaTenis, EspacioDTO.class, mapper);
    }

    @Override
    public List<EspacioDTO> getCanchaMultiple() {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_MULTIPLE);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<EspacioEntity> canchaMultiple = espacioRepository.findByTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(canchaMultiple, EspacioDTO.class, mapper);
    }

    @Override
    public List<EspacioDTO> getGimnasio() {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.GIMNASIO);
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        List<EspacioEntity> gimnasio = espacioRepository.findByTipoEspacio(tipoEspacioEntity);
        return DozerUtils.mapCollection(gimnasio, EspacioDTO.class, mapper);
    }

    @Override
    public EspacioDTO crearEspacio(EspacioDTO espacio) {
        EspacioEntity cubiculoEntity = mapper.map(espacio, EspacioEntity.class);
        cubiculoEntity = espacioRepository.saveAndFlush(cubiculoEntity);
        return mapper.map(cubiculoEntity, EspacioDTO.class);
    }


    @Override
    public List<EspacioDTO> getEspaciosPorTipoEspacio(TipoEspacioDTO tipoEspacioDTO) {
        TipoEspacioEntity tipoEspacio = mapper.map(tipoEspacioDTO, TipoEspacioEntity.class);
        return DozerUtils.mapCollection(espacioRepository.findByTipoEspacio(tipoEspacio), EspacioDTO.class, mapper);
    }

    @Override
    public boolean existeEspacio(String nombre, TipoEspacioDTO tipoEspacio) {
        TipoEspacioEntity tipoEspacioEntity = mapper.map(tipoEspacio, TipoEspacioEntity.class);
        long conteo = espacioRepository.countByNombreAndTipoEspacio(nombre, tipoEspacioEntity);
        return conteo > 0;
    }

}
