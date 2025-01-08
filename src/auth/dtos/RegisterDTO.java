package auth.dtos;

public class RegisterDTO {
	private String username;
	private String password;

	public RegisterDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPwd() {
		return this.password;
	}
}
