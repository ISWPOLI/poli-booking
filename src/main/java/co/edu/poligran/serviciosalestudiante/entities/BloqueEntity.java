package co.edu.poligran.serviciosalestudiante.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BLOQUES")
public class BloqueEntity extends BaseEntity {

    private static final long serialVersionUID = -4569912661223921729L;

    private Date tiempoInicio;
    private Date tiempoFin;
    private Date dia;

    @ManyToOne
    private EspacioEntity espacio;

    @OneToMany(mappedBy = "bloque")
    @JsonIgnore
    private List<ReservaEntity> reservas;

    public Date getTiempoInicio() {
        return (this.tiempoInicio != null ? (Date) this.tiempoInicio.clone() : null);
    }

    public void setTiempoInicio(Date tiempoInicio) {
        this.tiempoInicio = (tiempoInicio != null ? (Date) tiempoInicio.clone() : null);
    }

    public Date getTiempoFin() {
        return (this.tiempoFin != null ? (Date) tiempoFin.clone() : null);
    }

    public void setTiempoFin(Date tiempoFin) {
        this.tiempoFin = (tiempoFin != null ? (Date) tiempoFin.clone() : null);
    }

    public EspacioEntity getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioEntity espacio) {
        this.espacio = espacio;
    }

    public Date getDia() {
        return (this.dia != null ? (Date) this.dia.clone() : null);
    }

    public void setDia(Date dia) {
        this.dia = (dia != null ? (Date) dia.clone() : null);
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
