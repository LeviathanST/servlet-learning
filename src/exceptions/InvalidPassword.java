package exceptions;

public class InvalidPassword extends Exception {
	private String msg;

	public InvalidPassword(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}
}
