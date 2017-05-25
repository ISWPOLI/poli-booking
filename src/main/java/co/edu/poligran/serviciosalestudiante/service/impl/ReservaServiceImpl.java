package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;
import co.edu.poligran.serviciosalestudiante.entities.ReservaEntity;
import co.edu.poligran.serviciosalestudiante.entities.UsuarioEntity;
import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
import co.edu.poligran.serviciosalestudiante.repository.ReservaRepository;
import co.edu.poligran.serviciosalestudiante.service.NotificadorCorreosService;
import co.edu.poligran.serviciosalestudiante.service.ReservaService;
import co.edu.poligran.serviciosalestudiante.service.UsuarioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.UsuarioDTO;
import co.edu.poligran.serviciosalestudiante.utils.DozerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@CacheDefaults(cacheName = "reservas")
public class ReservaServiceImpl extends BaseService implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private NotificadorCorreosService notificadorCorreosService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @CacheResult
    public ReservaDTO consultarReserva(Long idReserva) {
        ReservaEntity reservaEntity = reservaRepository.findOne(idReserva);
        return mapper.map(reservaEntity, ReservaDTO.class);
    }

    @Override
    @CacheResult
    public List<ReservaDTO> consultarReservasVigentesPorUsuario(String usuario) throws UserNotFoundException {
        UsuarioDTO usuarioDTO = usuarioService.buscarPorUsername(usuario);
        List<ReservaEntity> reservas = reservaRepository
                .consultarReservasVigentesPorUsuario(mapper.map(usuarioDTO, UsuarioEntity.class));
        return DozerUtils.mapCollection(reservas, ReservaDTO.class, mapper);
    }

    @Override
    @CacheResult
    public List<ReservaDTO> consultarReservasVigentesPorUsuario(UsuarioDTO usuario) throws UserNotFoundException {
        return consultarReservasVigentesPorUsuario(usuario.getUsername());
    }

    @Override
    @CacheResult(cacheName = "reservas-vigentes-grafica")
    public List<ReservaDTO> consultarReservasVigentesGrafica() {
        List<ReservaEntity> reservas = reservaRepository.consultarReservasVigentesGrafica();
        return DozerUtils.mapCollection(reservas, ReservaDTO.class, mapper);
    }


    @Override
    @CacheResult(cacheName = "reservas-historico-grafica")
    public List<ReservaDTO> consultarHistorico() {
        List<ReservaEntity> reservas = reservaRepository.consultarHistorico();
        return DozerUtils.mapCollection(reservas, ReservaDTO.class, mapper);
    }

    @Override
    @CacheEvict(value = {"reservas", "reservas-vigentes-grafica", "reservas-historico-grafica"}, allEntries = true)
    public ReservaDTO crearReserva(UsuarioDTO usuario, BloqueDTO bloque) {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setBloque(mapper.map(bloque, BloqueEntity.class));
        reserva.setUsuario(mapper.map(usuario, UsuarioEntity.class));
        reserva.setFechaReserva(new Date());

        reserva = reservaRepository.saveAndFlush(reserva);

        return mapper.map(reserva, ReservaDTO.class);
    }

    @Override
    @CacheEvict(value = {"reservas", "reservas-vigentes-grafica", "reservas-historico-grafica"}, allEntries = true)
    public void eliminarReserva(Long idReserva) {
        reservaRepository.delete(idReserva);
    }

    @Override
    @CacheEvict(value = {"reservas", "reservas-vigentes-grafica", "reservas-historico-grafica"}, allEntries = true)
    public void eliminarReservasDelBloque(BloqueDTO bloque) {
        BloqueEntity bloqueEntity = mapper.map(bloque, BloqueEntity.class);

        List<ReservaEntity> reservasParaCancelar = reservaRepository.findByBloque(bloqueEntity);
        List<ReservaDTO> reservasParaNotificar = DozerUtils.mapCollection(reservasParaCancelar, ReservaDTO.class,
                mapper);

        reservaRepository.deleteByBloque(bloqueEntity);

        for (ReservaDTO reserva : reservasParaNotificar) {
            notificadorCorreosService.enviarNotificacionReservaCanceladaPorAdmin(reserva);
        }
    }
}
