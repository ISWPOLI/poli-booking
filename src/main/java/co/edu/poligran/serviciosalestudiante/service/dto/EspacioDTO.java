package co.edu.poligran.serviciosalestudiante.service.dto;

import java.util.Set;

public class EspacioDTO extends BaseDTO {
	private String nombre;

	private Set<BloqueDTO> bloques;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<BloqueDTO> getBloques() {
		return bloques;
	}

	public void setBloques(Set<BloqueDTO> bloques) {
		this.bloques = bloques;
	}

}
