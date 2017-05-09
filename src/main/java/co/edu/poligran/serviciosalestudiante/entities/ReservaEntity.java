package co.edu.poligran.serviciosalestudiante.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESERVAS")
public class ReservaEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReserva;

    @ManyToOne
    private UsuarioEntity usuario;

    @ManyToOne
    private BloqueEntity bloque;

    public Date getFechaReserva() {
        return (this.fechaReserva != null ? (Date) this.fechaReserva.clone() : null);
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = (fechaReserva != null ? (Date) fechaReserva.clone() : null);
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public BloqueEntity getBloque() {
        return bloque;
    }

    public void setBloque(BloqueEntity bloque) {
        this.bloque = bloque;
    }

}
