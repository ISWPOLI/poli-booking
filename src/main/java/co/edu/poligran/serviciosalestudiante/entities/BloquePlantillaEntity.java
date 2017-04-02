package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Date;

@Entity
@Table(name = "BLOQUES_PLANTILLA")
public class BloquePlantillaEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private DayOfWeek dia;

    @ManyToOne
    private TipoEspacioEntity tipoEspacio;

    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    private Date horaFin;

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public TipoEspacioEntity getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacioEntity tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
}
