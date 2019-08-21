
public interface studentInterface {
	public boolean validate(String username, String password);
	public void toStirng();
	public void setFullName(String fullName);
	public void addCourse(Course x);
	public void removeCourse(String courseId);
	public void printEnrolledClasses();
	public String getFullName();
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String toString();
}
