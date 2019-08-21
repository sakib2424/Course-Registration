
public class Admin extends User implements AdminInterface {

	private static final long serialVersionUID = -5233573603363538321L;
	static String username = "admin";
	static String password = "admin101";
	private String firstName;
	private String lastName;
	private String fullName;
	
	// Validates admin log in credentials
	public static Boolean validate (String u, String p) {
		u = u.toLowerCase();
		p = p.toLowerCase();
		
		if (u.equals(username) && p.equals(password)) {
			return true;
		}
		
		else {
			return false;
		}
			
	}

	
	
	// GETTERS AND SETTERS
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
		
}
