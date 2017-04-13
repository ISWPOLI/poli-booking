package co.edu.poligran.serviciosalestudiante.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ESPACIOS")
public class EspacioEntity extends BaseEntity {

	private static final long serialVersionUID = -3685813705664830546L;

	@ManyToOne
	private TipoEspacioEntity tipoEspacio;
	private String nombre;
	private int cupos;

	@OneToMany(mappedBy = "espacio")
	@JsonIgnore
	private List<BloqueEntity> bloques;

	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

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

	public TipoEspacioEntity getTipoEspacio() {
		return tipoEspacio;
	}

	public void setTipoEspacio(TipoEspacioEntity tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}

}
