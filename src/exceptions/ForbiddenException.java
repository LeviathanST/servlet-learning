package exceptions;

public class ForbiddenException extends Exception {
	private String msg;

	public ForbiddenException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}
}
