package co.edu.poligran.serviciosalestudiante.entities;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name = "TIPO_ESPACIO")
@Table(name = "ESPACIOS")
public abstract class EspacioEntity extends BaseEntity {

	private static final long serialVersionUID = -3685813705664830546L;

	private String nombre;

	@OneToMany(mappedBy = "espacio")
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

}
