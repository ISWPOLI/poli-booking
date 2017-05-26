package co.edu.poligran.serviciosalestudiante.controller;

import static org.mockito.Mockito.when;

import co.edu.poligran.serviciosalestudiante.exception.InvalidPasswordResetTokenException;
import co.edu.poligran.serviciosalestudiante.service.ServiciosFacade;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).apply(springSecurity()).build();
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void pruebaUser() throws Exception {
		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void pruebaMostrarPaginaCambioPassword() throws Exception {
		serviciosFacade.autorizarCambioPassword(40L, "ABC");
		this.mockMvc.perform(get("/user/change-password").param("id", "40").param("token", "ABC")).andDo(print())
				.andExpect(status().is3xxRedirection());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void pruebaCambiarPassword() throws Exception {
		serviciosFacade.cambiarPassword("abc");
		this.mockMvc.perform(post("/user/save-password").param("new-password", "abc")).andDo(print())
				.andExpect(status().isOk());
	}

}
