package co.edu.poligran.serviciosalestudiante.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poligran.serviciosalestudiante.service.BloqueService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;

@RestController
public class BloquesController extends BaseController {

    @Autowired
    private BloqueService bloquesService;

    @RequestMapping(value = "/bloques/consultar-bloques-vigentes", method = RequestMethod.GET)
    public List<BloqueDTO> consultarBloquesVigentesPorDiaYEspacio(@DateTimeFormat(pattern = "yyyy-MM-dd") Date dia,
                                                                  @RequestParam("id-espacio") Long idEspacio) {
        return bloquesService.consultarBloquesVigentesPorDiaYEspacio(dia, idEspacio);
    }

    @RequestMapping(value = "/bloques/consultar-bloque", method = RequestMethod.GET)
    public BloqueDTO consultarBloque(@RequestParam("idBloque")Long idBloque) {
        return bloquesService.consultarBloque(idBloque);
    }
}
