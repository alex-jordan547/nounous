package nounous.commun.exception;

@SuppressWarnings("serial")
public class ExceptionValidation extends Exception {

	public ExceptionValidation() {
		super();
	}

	public ExceptionValidation(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionValidation(String message) {
		super(message);
	}

	public ExceptionValidation(Throwable cause) {
		super(cause);
	}

}
