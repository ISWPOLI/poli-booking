package co.edu.poligran.serviciosalestudiante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.entities.EspacioEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacio;
import co.edu.poligran.serviciosalestudiante.repository.EspacioRepository;
import co.edu.poligran.serviciosalestudiante.service.EspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;

@Service
@Transactional
public class EspacioServiceImpl extends BaseService implements EspacioService {

	@Autowired
	private EspacioRepository espacioRepository;

	@Override
	public List<EspacioDTO> getCubiculos() {
		List<EspacioEntity> cubiculos = espacioRepository.findByTipoEspacio(TipoEspacio.CUBICULO);
		return DozerUtils.mapCollection(cubiculos, EspacioDTO.class, mapper);
	}

	@Override
	public EspacioDTO crearEspacio(EspacioDTO espacio) {
		EspacioEntity cubiculoEntity = mapper.map(espacio, EspacioEntity.class);
		cubiculoEntity = espacioRepository.saveAndFlush(cubiculoEntity);
		return mapper.map(cubiculoEntity, EspacioDTO.class);
	}

}
