package co.edu.poligran.serviciosalestudiante.controller;



import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import org.springframework.web.context.WebApplicationContext;

import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import co.edu.poligran.serviciosalestudiante.service.dto.ReservaDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservaController.class)
@WebAppConfiguration
public class ReservaControllerTest {
	

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
    public static final String RESERVAS_ROOT_URL = "/reservas/mis-reservas";
    
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void pruebaConsultarMisReservas() throws Exception {
        List<ReservaDTO> bloques = new ArrayList<>();

        when(serviciosFacade.consultarReservasVigentesPorUsuario("admin")).thenReturn(bloques);

        this.mockMvc.perform(get(RESERVAS_ROOT_URL).param("usuario", "admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void pruebaConsultarReservasGrafica()throws Exception{
    	List<ReservaDTO>reservas=new LinkedList<ReservaDTO>();
    	when(serviciosFacade.consultarReservasVigentesGrafica()).thenReturn(reservas);
    	this.mockMvc.perform(get(RESERVAS_ROOT_URL+ "/reservas-grafica")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[]"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void pruebaConsultarHistorico()throws Exception{
    	List<ReservaDTO>reservas=new LinkedList<ReservaDTO>();
    	when(serviciosFacade.consultarHistorico()).thenReturn(reservas);
    	this.mockMvc.perform(get(RESERVAS_ROOT_URL + "/historico-grafica")).andDo(print()).andExpect(status().isOk()).andExpect(content().json("[]"));
    }
    
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void pruebaConfirmarReserva()throws Exception{
    	serviciosFacade.confirmarReserva(40L, "admin");
    	this.mockMvc.perform(post(RESERVAS_ROOT_URL).param("idBloque","40").param("usuario", "admin")).andDo(print()).andExpect(status().isOk());
    }

}
