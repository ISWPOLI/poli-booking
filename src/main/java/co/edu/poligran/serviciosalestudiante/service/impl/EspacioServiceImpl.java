package co.edu.poligran.serviciosalestudiante.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poligran.serviciosalestudiante.entities.CubiculoEntity;
import co.edu.poligran.serviciosalestudiante.repository.CubiculoRepository;
import co.edu.poligran.serviciosalestudiante.service.EspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.CubiculoDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;

@Service
@Transactional
public class EspacioServiceImpl extends BaseService implements EspacioService {

	@Autowired
	private CubiculoRepository cubiculoRepository;

	@Override
	public List<CubiculoDTO> getCubiculos() {
		List<CubiculoEntity> cubiculos = cubiculoRepository.findAll();
		return DozerUtils.mapCollection(cubiculos, CubiculoDTO.class, mapper);
	}

	@Override
	public CubiculoDTO crearCubiculo(CubiculoDTO cubiculo) {
		CubiculoEntity cubiculoEntity = mapper.map(cubiculo, CubiculoEntity.class);
		cubiculoEntity = cubiculoRepository.saveAndFlush(cubiculoEntity);
		return mapper.map(cubiculoEntity, CubiculoDTO.class);
	}

}
