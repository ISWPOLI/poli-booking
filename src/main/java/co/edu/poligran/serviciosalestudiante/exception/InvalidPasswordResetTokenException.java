package co.edu.poligran.serviciosalestudiante.exception;

public class InvalidPasswordResetTokenException extends BaseException {

	private static final long serialVersionUID = 1L;

	public InvalidPasswordResetTokenException() {
		super("El token es inválido");
	}

}
