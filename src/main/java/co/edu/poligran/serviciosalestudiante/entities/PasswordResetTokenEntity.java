package co.edu.poligran.serviciosalestudiante.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PASSWORD_RESET_TOKENS")
public class PasswordResetTokenEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String token;

	@OneToOne
	private UsuarioEntity user;

	private Date expirationDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioEntity getUser() {
		return user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
