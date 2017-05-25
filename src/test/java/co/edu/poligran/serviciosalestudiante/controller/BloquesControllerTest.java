package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import static co.edu.poligran.serviciosalestudiante.controller.BloquePlantillaController.BLOQUES_CONSULTAR_BLOQUES_PLANTILLA;
import static co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO.CUBICULO_ESTUDIO;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BloquesController.class)
@WebAppConfiguration
public class BloquesControllerTest {
	

    @Autowired
    private WebApplicationContext webContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiciosFacade serviciosFacade;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void pruebaConsultarBloquesVigentesPorDiaYTipoEspacio() throws Exception{
    	List<BloqueDTO>BloquesVigentes=new LinkedList<BloqueDTO>();
    	Date dia=new Date();
		when(serviciosFacade.consultarBloquesVigentesPorDiaYTipoEspacio(dia, CUBICULO_ESTUDIO)).thenReturn(BloquesVigentes);
		this.mockMvc.perform(get("/bloques/bloques-vigentes-por-dia-y-tipo-espacio").param("dia", "2017-04-26").param("tipo-espacio",CUBICULO_ESTUDIO)).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[]"));
    }
	
    @Test
    @WithMockUser(username="admin",password="admin",roles="ADMIN")
    public void pruebaConsultarDiasConBloquesDisponibles()throws Exception{
    	List<DiaCalendarioBean>consultarDiasConBloquesDisponibles=new LinkedList<DiaCalendarioBean>();
    	when(serviciosFacade.consultarDiasConBloquesDisponibles(CUBICULO_ESTUDIO)).thenReturn(consultarDiasConBloquesDisponibles);
    	this.mockMvc.perform(get("/bloques/bloques-vigentes-por-tipo-espacio").param("tipo-espacio", CUBICULO_ESTUDIO)).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[]"));
    	
    }
    
    @Test
    @WithMockUser(username="admin",password="admin",roles="ADMIN")
    public void pruebaConsultarBloque()throws Exception{
    	BloqueDTO bloque=new BloqueDTO();
    	when(serviciosFacade.consultarBloque(40L)).thenReturn(bloque);
    	this.mockMvc.perform(get("/bloques/consultar-bloque").param("idBloque","40")).andDo(print()).andExpect(status().isOk()).andExpect(content().json(""));
    }
    
    
//    @RequestMapping(value = "/bloques/consultar-bloque", method = RequestMethod.GET)
//    public BloqueDTO consultarBloque(@RequestParam("idBloque") Long idBloque) {
//        return serviciosFacade.consultarBloque(idBloque);
//    }

}
