package model;

public class User {
	private String userName;
	private String email;
	private String passwords;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.passwords = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return passwords;
	}
	public void setPassword(String password) {
		this.passwords = password;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", email=" + email + ", password=" + passwords + "]";
	}
	
	
	
}
