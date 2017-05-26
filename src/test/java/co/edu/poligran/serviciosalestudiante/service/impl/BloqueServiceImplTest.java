package co.edu.poligran.serviciosalestudiante.service.impl;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.edu.poligran.serviciosalestudiante.beans.DiaCalendarioBean;
import co.edu.poligran.serviciosalestudiante.configuration.SpringBeansConfiguration;
import co.edu.poligran.serviciosalestudiante.entities.BloqueEntity;
import co.edu.poligran.serviciosalestudiante.entities.TipoEspacioEntity;
import co.edu.poligran.serviciosalestudiante.repository.BloqueRepository;
import co.edu.poligran.serviciosalestudiante.service.BloquePlantillaService;
import co.edu.poligran.serviciosalestudiante.service.EspacioService;
import co.edu.poligran.serviciosalestudiante.service.ReservaService;
import co.edu.poligran.serviciosalestudiante.service.TipoEspacioService;
import co.edu.poligran.serviciosalestudiante.service.dto.BloqueDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.EspacioDTO;
import co.edu.poligran.serviciosalestudiante.service.dto.TipoEspacioDTO;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.reset;

import java.util.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBeansConfiguration.class, BloqueServiceImpl.class })

public class BloqueServiceImplTest {
	@MockBean
	private BloqueRepository bloqueRepository;

	@MockBean
	private TipoEspacioService tipoEspacioService;

	@MockBean
	private BloquePlantillaService bloquePlantillaService;

	@MockBean
	private EspacioService espacioService;

	@MockBean
	private ReservaService reservaService;

	@Autowired
    private Mapper mapper;
	
	@Autowired
    private BloqueServiceImpl bloqueServiceImpl;
	
	@Test
	public void generarBloquesTest(){
		BloqueEntity bloque=new BloqueEntity();
		when(bloqueRepository.saveAndFlush(bloque)).thenReturn(bloque);
		bloqueServiceImpl.generarBloques(new EspacioDTO(), 20L);		
	}
	
	@Test
	public void consultarBloquesVigentesPorDiaYTipoEspacioTest(){
		TipoEspacioDTO tipoEspacio=new TipoEspacioDTO();
		tipoEspacio.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
		when(tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_FUTBOL)).thenReturn(tipoEspacio);
		
		TipoEspacioEntity tipoEspacioEntity=new TipoEspacioEntity();
		tipoEspacioEntity.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
		
		List<BloqueEntity> lista=new ArrayList<>();
		lista.add(new BloqueEntity());
		when(bloqueRepository.consultarBloquesVigentesPorDiaYTipoEspacio(new Date(),tipoEspacioEntity)).thenReturn(lista);
		
		List<BloqueDTO> listaDTO= bloqueServiceImpl.consultarBloquesVigentesPorDiaYTipoEspacio(new Date(), tipoEspacio);
		assertThat(listaDTO.size(),is(lista.size()));
	}
	
	@Test
	public void consultarBloquesVigentesPorDiaYTipoEspacioTestString(){
		TipoEspacioDTO tipoEspacio=new TipoEspacioDTO();
		tipoEspacio.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
		when(tipoEspacioService.buscarTipoEspacioPorNombre(TipoEspacioDTO.CANCHA_FUTBOL)).thenReturn(tipoEspacio);
		
		TipoEspacioEntity tipoEspacioEntity=new TipoEspacioEntity();
		tipoEspacioEntity.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
		
		List<BloqueEntity> lista=new ArrayList<>();
		lista.add(new BloqueEntity());
		when(bloqueRepository.consultarBloquesVigentesPorDiaYTipoEspacio(new Date(),tipoEspacioEntity)).thenReturn(lista);
		
		List<BloqueDTO> listaDTO= bloqueServiceImpl.consultarBloquesVigentesPorDiaYTipoEspacio(new Date(), tipoEspacio.getNombre());
//		assertThat(listaDTO.size(),is(lista.size()));
	}
	
	@Test
	public void consultarBloquesVigentesPorTipoEspacioTestString(){
		TipoEspacioDTO tipoEspacioDTO=new TipoEspacioDTO();
		tipoEspacioDTO.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
		when(tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacioDTO.getNombre())).thenReturn(tipoEspacioDTO);
		
		List<BloqueEntity>lista=new ArrayList<>();
		lista.add(new BloqueEntity());
		when(bloqueRepository.consultarBloquesVigentesPorTipoEspacio(new TipoEspacioEntity())).thenReturn(lista);
		
		List <BloqueDTO> listaDTO= bloqueServiceImpl.consultarBloquesVigentesPorTipoEspacio(tipoEspacioDTO.getNombre());
		assertThat(listaDTO.size(),is(lista.size()));
	}
	
	@Test
	public void consultarBloquesVigentesPorTipoEspacioTest(){
		TipoEspacioDTO tipoEspacioDTO=new TipoEspacioDTO();
		tipoEspacioDTO.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
		when(tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacioDTO.getNombre())).thenReturn(tipoEspacioDTO);
		
		List<BloqueEntity>lista=new ArrayList<>();
		lista.add(new BloqueEntity());
		when(bloqueRepository.consultarBloquesVigentesPorTipoEspacio(new TipoEspacioEntity())).thenReturn(lista);
		
		List <BloqueDTO> listaDTO= bloqueServiceImpl.consultarBloquesVigentesPorTipoEspacio(tipoEspacioDTO);
		assertThat(listaDTO.size(),is(lista.size()));
	}
	
	@Test
    public void consultarDiasConBloquesDisponiblesTest() {
        TipoEspacioDTO tipoEspacioDTO = new TipoEspacioDTO();
        tipoEspacioDTO.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
        when(tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacioDTO.getNombre())).thenReturn(tipoEspacioDTO);

        List<Date> lista = new ArrayList<>();
        lista.add(new Date());

        when(bloqueRepository.consultarDiasConBloquesVigentesPorTipoEspacio(new TipoEspacioEntity())).thenReturn(lista);

        List<DiaCalendarioBean> listaCalendario = bloqueServiceImpl.consultarDiasConBloquesDisponibles(tipoEspacioDTO
                .getNombre());
        assertThat(listaCalendario.size(), is(lista.size()));
    }
	
	@Test
    public void generarBloquesMasivamenteTest() {
        TipoEspacioDTO tipoEspacioDTO = new TipoEspacioDTO();
        tipoEspacioDTO.setNombre(TipoEspacioDTO.CANCHA_FUTBOL);
        when(tipoEspacioService.buscarTipoEspacioPorNombre(tipoEspacioDTO.getNombre())).thenReturn(tipoEspacioDTO);

        List<EspacioDTO> lista = new ArrayList<>();
        lista.add(new EspacioDTO());
        when(espacioService.getEspaciosPorTipoEspacio(tipoEspacioDTO)).thenReturn(lista);

    }
	
	@Test
	public void consultarBloqueTest(){
		BloqueEntity bloqueEntity=new BloqueEntity();
		bloqueEntity.setId(40L);
		when(bloqueRepository.findOne(40L)).thenReturn(bloqueEntity);
		BloqueDTO bloqueDTO=new BloqueDTO();
		bloqueDTO.setId(40L);
		BloqueDTO bdto=bloqueServiceImpl.consultarBloque(40L);
		assertThat(bloqueDTO.getId(),is(bdto.getId()));
	}
	
	
}
