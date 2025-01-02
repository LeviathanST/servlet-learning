package etc;

public class Response<T> {
	private String timestamp;
	private int status;
	private String message;
	private T data;

	public Response(int status, String message, T data) {
		this.timestamp = java.time.ZonedDateTime.now().toString();
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
