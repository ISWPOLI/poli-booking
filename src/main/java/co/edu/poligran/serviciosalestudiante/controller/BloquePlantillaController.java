package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.service.BloquePlantillaService;
import co.edu.poligran.serviciosalestudiante.service.TipoEspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BloquePlantillaController extends BaseController {

    @Autowired
    private TipoEspacioService tipoEspacioService;

    @Autowired
    private BloquePlantillaService bloquePlantillaService;

    @RequestMapping(value = "/bloques/consultar-bloques-plantilla", method = RequestMethod.GET)
    public List<BloquePlantillaDTO> consultarBloquesPlantilla(@RequestParam("tipo-espacio") String tipoEspacio) {
        TipoEspacioDTO tipoEspacioDTO = tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio);
        return bloquePlantillaService.consultarBloquesPlantillaPorTipoEspacio(tipoEspacioDTO);
    }
}
