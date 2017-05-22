package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BloquePlantillaController extends BaseController {

    public static final String BLOQUES_CONSULTAR_BLOQUES_PLANTILLA = "/bloques/consultar-bloques-plantilla";

    @Autowired
    private ServiciosFacade serviciosFacade;

    @RequestMapping(value = BLOQUES_CONSULTAR_BLOQUES_PLANTILLA, method = RequestMethod.GET)
    public List<BloquePlantillaDTO> consultarBloquesPlantilla(@RequestParam("tipo-espacio") String tipoEspacio) {
        return serviciosFacade.consultarBloquesPlantillaPorTipoEspacio(tipoEspacio);
    }
}
