package co.edu.poligran.serviciosalestudiante.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
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
