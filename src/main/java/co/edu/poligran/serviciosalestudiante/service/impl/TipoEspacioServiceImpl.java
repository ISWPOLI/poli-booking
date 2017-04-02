package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.repository.TipoEspacioRepository;
import co.edu.poligran.serviciosalestudiante.service.TipoEspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoEspacioServiceImpl extends BaseService implements TipoEspacioService {

    @Autowired
    private TipoEspacioRepository tipoEspacioRepository;

    @Override
    public TipoEspacioDTO buscarTipoEspacioPorNombre(String tipoEspacio) {
        return mapper.map(tipoEspacioRepository.findByNombre(tipoEspacio), TipoEspacioDTO.class);
    }
}
