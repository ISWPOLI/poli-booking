package co.edu.poligran.serviciosalestudiante.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ESPACIOS")
public class EspacioEntity extends BaseEntity {

	private static final long serialVersionUID = -3685813705664830546L;

	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_ESPACIO")
	private TipoEspacio tipoEspacio;
	private String nombre;

	@OneToMany(mappedBy = "espacio")
	@JsonIgnore
	private List<BloqueEntity> bloques;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<BloqueEntity> getBloques() {
		return bloques;
	}

	public void setBloques(List<BloqueEntity> bloques) {
		this.bloques = bloques;
	}

	public TipoEspacio getTipoEspacio() {
		return tipoEspacio;
	}

	public void setTipoEspacio(TipoEspacio tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}

}
