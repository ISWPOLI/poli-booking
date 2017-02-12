package co.edu.poligran.serviciosalestudiante.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseException() {
		super("Error General");
	}

	public BaseException(String message) {
		super(message);
	}
}
