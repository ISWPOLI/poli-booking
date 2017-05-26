package co.edu.poligran.serviciosalestudiante.initializer;

import co.edu.poligran.serviciosalestudiante.configuration.SpringBeansConfiguration;
import co.edu.poligran.serviciosalestudiante.repository.RoleRepository;
import co.edu.poligran.serviciosalestudiante.repository.TipoEspacioRepository;
import co.edu.poligran.serviciosalestudiante.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBeansConfiguration.class, InformacionPorDefecto.class})
public class InformacionPorDefectoTest {

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private BloqueService bloqueService;

    @MockBean
    private EspacioService espacioService;

    @MockBean
    private TipoEspacioRepository tipoEspacioRepository;

    @MockBean
    private TipoEspacioService tipoEspacioService;

    @MockBean
    private BloquePlantillaService bloquePlantillaService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InformacionPorDefecto informacionPorDefecto;

    @Test
    public void test() {
        informacionPorDefecto.onApplicationEvent(null);
    }

}
