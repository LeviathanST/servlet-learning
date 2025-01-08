package account;

public class AccountEntity {
	private Integer id;
	private String username;
	private String password;
	private Integer role;

	public AccountEntity() {
	}

	public AccountEntity(Integer id, String username, String password, Integer role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return this.id;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPwd() {
		return this.password;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
