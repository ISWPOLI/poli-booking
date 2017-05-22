package co.edu.poligran.serviciosalestudiante.service.impl;

import co.edu.poligran.serviciosalestudiante.configuration.SpringBeansConfiguration;
import co.edu.poligran.serviciosalestudiante.entities.BloquePlantillaEntity;
import co.edu.poligran.serviciosalestudiante.repository.BloquePlantillaRepository;
import co.edu.poligran.serviciosalestudiante.service.TipoEspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloquePlantillaDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBeansConfiguration.class, BloquePlantillaServiceImpl.class})
public class BloquePlantillaServiceImplTest {
    @MockBean
    private BloquePlantillaRepository bloquePlantillaRepository;

    @MockBean
    private TipoEspacioService tipoEspacioService;

    @Autowired
    private BloquePlantillaServiceImpl bloquePlantillaServiceImpl;

    @Autowired
    private Mapper mapper;

    @Test
    public void escenarioExitoso() {
        TipoEspacioDTO tipoEspacio = new TipoEspacioDTO();
        tipoEspacio.setNombre(TipoEspacioDTO.CUBICULO_ESTUDIO);
        tipoEspacio.setId(30L);

        when(tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacio.getNombre())).thenReturn(tipoEspacio);

        BloquePlantillaDTO bloquePlantillaPorCrear = new BloquePlantillaDTO();
        bloquePlantillaPorCrear.setDia(DayOfWeek.MONDAY);
        bloquePlantillaPorCrear.setTipoEspacio(tipoEspacio);
        bloquePlantillaPorCrear.setHoraInicio(new Date());
        bloquePlantillaPorCrear.setHoraFin(new Date());

        when(bloquePlantillaRepository.saveAndFlush(anyObject())).thenReturn(mapper.map(bloquePlantillaPorCrear,
                BloquePlantillaEntity.class));

        BloquePlantillaDTO bloquePlantillaCreado = bloquePlantillaServiceImpl.crearBloquePlantilla
                (bloquePlantillaPorCrear);

        assertThat(bloquePlantillaCreado.getDia(), is(bloquePlantillaPorCrear.getDia()));
        assertThat(bloquePlantillaCreado.getTipoEspacio().getNombre(), is(bloquePlantillaPorCrear.getTipoEspacio()
                .getNombre()));
        assertThat(bloquePlantillaCreado.getHoraInicio(), is(bloquePlantillaPorCrear.getHoraInicio()));
        assertThat(bloquePlantillaPorCrear.getHoraFin(), is(bloquePlantillaPorCrear.getHoraFin()));
    }

}
