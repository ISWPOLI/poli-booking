package co.edu.poligran.serviciosalestudiante.service.dto;

import java.util.Date;

public class ReservaDTO extends BaseDTO {
    private static final long serialVersionUID = -9213589675850834258L;
    private Date fechaReserva;

    private UsuarioDTO usuario;

    private BloqueDTO bloque;

    public Date getFechaReserva() {
        return (this.fechaReserva != null ? (Date) this.fechaReserva.clone() : null);
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = (fechaReserva != null ? (Date) fechaReserva.clone() : null);
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public BloqueDTO getBloque() {
        return bloque;
    }

    public void setBloque(BloqueDTO bloque) {
        this.bloque = bloque;
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
