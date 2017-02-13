package co.edu.poligran.serviciosalestudiante.service.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PasswordResetTokenDTO {

	public static final int EXPIRATION_TIME_IN_MINUTES = 60 * 24;

	private String token;

	@JsonIgnore
	private UserDTO user;

	private Date expirationDate;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}
