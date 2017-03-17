package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;
import co.edu.poligran.serviciosalestudiante.entities.ReservaEntity;
import co.edu.poligran.serviciosalestudiante.entities.UsuarioEntity;
import co.edu.poligran.serviciosalestudiante.repository.ReservaRepository;
import co.edu.poligran.serviciosalestudiante.service.ReservaService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReservaServiceImpl extends BaseService implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public ReservaDTO consultarReserva(Long idReserva) {
        ReservaEntity reservaEntity = reservaRepository.findOne(idReserva);
        return mapper.map(reservaEntity, ReservaDTO.class);
    }

    @Override
    public List<ReservaDTO> consultarReservasVigentesPorUsuario(UsuarioDTO usuario) {
        List<ReservaEntity> reservas = reservaRepository
                .consultarReservasVigentesPorUsuario(mapper.map(usuario, UsuarioEntity.class));
        return DozerUtils.mapCollection(reservas, ReservaDTO.class, mapper);
    }

    @Override
    public ReservaDTO crearReserva(UsuarioDTO usuario, BloqueDTO bloque) {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setBloque(mapper.map(bloque, BloqueEntity.class));
        reserva.setUsuario(mapper.map(usuario, UsuarioEntity.class));
        reserva.setFechaReserva(new Date());

        reserva = reservaRepository.saveAndFlush(reserva);

        return mapper.map(reserva, ReservaDTO.class);
    }

    @Override
    public void cancelarReserva(Long idReserva) {
        reservaRepository.delete(idReserva);
    }
}
