package co.edu.poligran.serviciosalestudiante.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BLOQUES")
public class BloqueEntity extends BaseEntity {

	private static final long serialVersionUID = -4569912661223921729L;

	private Date tiempoInicio;
	private Date tiempoFin;
	private Date dia;
	
	@ManyToOne
	private EspacioEntity espacio;

	public Date getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(Date tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public Date getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(Date tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public EspacioEntity getEspacio() {
		return espacio;
	}

	public void setEspacio(EspacioEntity espacio) {
		this.espacio = espacio;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

}
