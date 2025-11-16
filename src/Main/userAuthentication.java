package Main;

public class userAuthentication {
	private static final String USERNAME = "testAdmin";
	private static final String PASSWORD = "password123";
	
	public boolean authenticate(String username, String password) {
		return USERNAME.equals(username) && PASSWORD.equals(password);
	}

}
