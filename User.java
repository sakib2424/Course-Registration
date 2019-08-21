import java.io.Serializable;

public abstract class User implements Serializable {
	private static final long serialVersionUID = -5222775447138437999L;
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String fullName;
	
}
