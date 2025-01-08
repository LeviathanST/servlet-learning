package auth.dtos;

public class LoginDTO {
	private String username;
	private String password;

	public LoginDTO(String username, String password) {
	}

	public String getUsername() {
		return this.username;
	}

	public String getPwd() {
		return this.password;
	}
}
