package co.edu.poligran.serviciosalestudiante.controller;


import co.edu.poligran.serviciosalestudiante.exception.UserNotFoundException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@WebMvcTest(UsuarioController.class)
@WebAppConfiguration

public class UsuarioControllerTest {
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
	    public void pruebaUser()throws Exception{
		        this.mockMvc.perform(get("/user"))
		                .andDo(print())
		                .andExpect(status().isOk())
		                .andExpect(content().json("{\"authorities\":[{\"authority\":\"ROLE_ADMIN\"}],\"details\":{\"remoteAddress\":\"127.0.0.1\",\"sessionId\":\"F9BBF82DE4CFDE42F34E19FE9C2889AC\"},\"authenticated\":true,\"principal\":{\"password\":null,\"username\":\"admin\",\"authorities\":[{\"authority\":\"ROLE_ADMIN\"}],\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true},\"credentials\":null,\"name\":\"admin\"}"));
	    }	
	    
	    @RequestMapping(value = "/user/reset-password", method = RequestMethod.POST)
	    public String restablecerPassword(HttpServletRequest request, @RequestParam("email") String email)
	            throws UserNotFoundException {
	        return serviciosFacade.resetPassword(email, getFullAppUrl(request));
	    }
	    
	    
}
