package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BloquesController extends BaseController {

    @Autowired
    private ServiciosFacade serviciosFacade;

    @RequestMapping(value = "/bloques/bloques-vigentes-por-dia-y-tipo-espacio", method = RequestMethod.GET)
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(@DateTimeFormat(pattern = BaseController
            .FORMATO_FECHA_POR_DEFECTO) Date dia, @RequestParam("tipo-espacio") String tipoEspacio) {

        return serviciosFacade.consultarBloquesVigentesPorDiaYTipoEspacio(dia, tipoEspacio);
    }

    @RequestMapping(value = "/bloques/bloques-vigentes-por-tipo-espacio", method = RequestMethod.GET)
    public List<DiaCalendarioBean> consultarDiasConBloquesDisponibles(@RequestParam("tipo-espacio") String
                                                                                  tipoEspacio) {
        return serviciosFacade.consultarDiasConBloquesDisponibles(tipoEspacio);
    }

    @RequestMapping(value = "/bloques/consultar-bloque", method = RequestMethod.GET)
    public BloqueDTO consultarBloque(@RequestParam("idBloque") Long idBloque) {
        return serviciosFacade.consultarBloque(idBloque);
    }

    @RequestMapping(value = "/bloques/generar-bloques-masivamente", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public List<BloqueDTO> generarBloquesMasivamente(@RequestParam("tipo-espacio") String tipoEspacio,
                                                     @RequestParam("fecha-inicio") @DateTimeFormat(pattern =
                                                             BaseController.FORMATO_FECHA_POR_DEFECTO) Date
                                                             fechaInicio,
                                                     @RequestParam("fecha-fin") @DateTimeFormat(pattern =
                                                             BaseController.FORMATO_FECHA_POR_DEFECTO) Date fechaFin) {
        return serviciosFacade.generarBloquesMasivamente(tipoEspacio, fechaInicio, fechaFin);
    }


    @RequestMapping(value = "/bloques/eliminar-bloques-masivamente", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminarBloquesMasivamente(@RequestParam("tipo-espacio") String tipoEspacio, @RequestParam
            ("fecha-inicio") @DateTimeFormat(pattern = BaseController.FORMATO_FECHA_POR_DEFECTO) Date fechaInicio,
                                           @RequestParam("fecha-fin") @DateTimeFormat(pattern = BaseController
                                                   .FORMATO_FECHA_POR_DEFECTO) Date fechaFin) {
        serviciosFacade.eliminarBloquesMasivamente(tipoEspacio, fechaInicio, fechaFin);
    }
}
