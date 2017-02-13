package co.edu.poligran.serviciosalestudiante.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetTokenEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String token;

	@OneToOne
	private UserEntity user;

	private Date expirationDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
