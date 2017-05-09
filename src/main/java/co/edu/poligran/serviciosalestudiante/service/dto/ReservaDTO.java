package co.edu.poligran.serviciosalestudiante.service.dto;

import java.util.Date;

public class ReservaDTO extends BaseDTO {
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

}
