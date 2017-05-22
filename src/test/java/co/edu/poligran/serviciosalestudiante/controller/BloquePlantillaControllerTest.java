package co.edu.poligran.serviciosalestudiante.controller;

import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
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

import java.util.ArrayList;
import java.util.List;

import static co.edu.poligran.serviciosalestudiante.controller.BloquePlantillaController
        .BLOQUES_CONSULTAR_BLOQUES_PLANTILLA;
import static co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO.CUBICULO_ESTUDIO;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BloquePlantillaController.class)
@WebAppConfiguration
public class BloquePlantillaControllerTest {

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
    public void contextLoads() throws Exception {
        List<BloquePlantillaDTO> bloquesPlantilla = new ArrayList<>();

        when(serviciosFacade.consultarBloquesPlantillaPorTipoEspacio(CUBICULO_ESTUDIO)).thenReturn(bloquesPlantilla);

        this.mockMvc.perform(get(BLOQUES_CONSULTAR_BLOQUES_PLANTILLA).param("tipo-espacio", CUBICULO_ESTUDIO))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
