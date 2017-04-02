package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.service.BloqueService;
import co.edu.poligran.serviciosalestudiante.service.TipoEspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BloquesController extends BaseController {

    @Autowired
    private BloqueService bloquesService;

    @Autowired
    private TipoEspacioService tipoEspacioService;

    @RequestMapping(value = "/bloques/bloques-vigentes-por-dia-y-tipo-espacio", method = RequestMethod.GET)
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYTipoEspacio(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dia,
                                                                      @RequestParam("tipo-espacio") String tipoEspacio) {

        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        return bloquesService.consultarBloquesVigentesPorDiaYTipoEspacio(dia, tipoEspacioDTO);
    }

    @RequestMapping(value = "/bloques/bloques-vigentes-por-tipo-espacio", method = RequestMethod.GET)
    public List<DiaCalendarioBean> consultarBloquesVigentesPorTipoEspacio(@RequestParam("tipo-espacio") String tipoEspacio) {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        List<BloqueDTO> bloquesDTOs = bloquesService.consultarBloquesVigentesPorTipoEspacio(tipoEspacioDTO);

        return transformarBloquesADias(bloquesDTOs);
    }

    @RequestMapping(value = "/bloques/consultar-bloque", method = RequestMethod.GET)
    public BloqueDTO consultarBloque(@RequestParam("idBloque") Long idBloque) {
        return bloquesService.consultarBloque(idBloque);
    }

    private List<DiaCalendarioBean> transformarBloquesADias(List<BloqueDTO> bloquesDTOs) {
        Map<Long, DiaCalendarioBean> dias = new HashMap();
        for (BloqueDTO bloqueDTO : bloquesDTOs) {
            if (!dias.containsKey(bloqueDTO.getDia().getTime())) {
                DiaCalendarioBean dia = new DiaCalendarioBean();
                dia.setDia(bloqueDTO.getDia());
                dias.put(dia.getDia().getTime(), dia);
            }
        }
        return new ArrayList<>(dias.values());
    }
}
