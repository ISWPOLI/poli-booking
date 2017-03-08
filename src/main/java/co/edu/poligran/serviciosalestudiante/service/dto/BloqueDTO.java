package co.edu.poligran.serviciosalestudiante.service.dto;

import java.util.Date;

public class BloqueDTO extends BaseDTO {
	private Date tiempoInicio;
	private Date tiempoFin;
	private Date dia;
	private EspacioDTO espacio;

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

	public EspacioDTO getEspacio() {
		return espacio;
	}

	public void setEspacio(EspacioDTO espacio) {
		this.espacio = espacio;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

}
