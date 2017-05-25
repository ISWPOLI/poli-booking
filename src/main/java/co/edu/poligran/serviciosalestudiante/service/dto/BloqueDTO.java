package co.edu.poligran.serviciosalestudiante.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class BloqueDTO extends BaseDTO {
    private static final long serialVersionUID = -2217115755648786183L;

    private Date tiempoInicio;
    private Date tiempoFin;
    private Date dia;
    private EspacioDTO espacio;
    @JsonIgnore
    private List<ReservaDTO> reservas;

    public Date getTiempoInicio() {
        return (this.tiempoInicio != null ? (Date) this.tiempoInicio.clone() : null);
    }

    public void setTiempoInicio(Date tiempoInicio) {
        this.tiempoInicio = (tiempoInicio != null ? (Date) tiempoInicio.clone() : null);
    }

    public Date getTiempoFin() {
        return (this.tiempoFin != null ? (Date) this.tiempoFin.clone() : null);
    }

    public void setTiempoFin(Date tiempoFin) {
        this.tiempoFin = (tiempoFin != null ? (Date) tiempoFin.clone() : null);
    }

    public EspacioDTO getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDTO espacio) {
        this.espacio = espacio;
    }

    public Date getDia() {
        return (this.dia != null ? (Date) this.dia.clone() : null);
    }

    public void setDia(Date dia) {
        this.dia = (dia != null ? (Date) dia.clone() : null);
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
