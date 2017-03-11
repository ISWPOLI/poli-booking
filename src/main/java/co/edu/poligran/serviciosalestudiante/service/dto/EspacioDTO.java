package co.edu.poligran.serviciosalestudiante.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.poligran.serviciosalestudiante.entities.TipoEspacio;

public class EspacioDTO extends BaseDTO {
	private TipoEspacio tipoEspacio;
	private String nombre;

	@JsonIgnore
	private List<BloqueDTO> bloques;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<BloqueDTO> getBloques() {
		return bloques;
	}

	public void setBloques(List<BloqueDTO> bloques) {
		this.bloques = bloques;
	}

	public TipoEspacio getTipoEspacio() {
		return tipoEspacio;
	}

	public void setTipoEspacio(TipoEspacio tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}

}
