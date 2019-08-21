import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable, studentInterface {

	private static final long serialVersionUID = 6812926703132386342L;
	// List of all courses objects the student is registered
	private ArrayList<Course> enrolledCourses;
	// Constructor	
	public Student(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = this.firstName + " " + this.lastName;
		enrolledCourses = new ArrayList<Course>();
	}
	// Performs edits on the max students and section numbers of course objects in student list when admin makes changes
	public void edits(int option, int number, String editId) {
		if (option == 1) {
			for (int i = 0; i < enrolledCourses.size(); i++) {
				if (editId.toLowerCase().equals(enrolledCourses.get(i).getCourseId().toLowerCase())) {
					enrolledCourses.get(i).setMaxNumberOfStudents(number);
				}
			}
		}
		else if (option == 4) {
			for (int i = 0; i < enrolledCourses.size(); i++) {
				if (editId.toLowerCase().equals(enrolledCourses.get(i).getCourseId().toLowerCase())) {
					enrolledCourses.get(i).setCourseSectionNumber(number);
				}
			}
		}
	}
	// Performs edits of the instructor and location on course objects in student list when admin makes changes
	public void edits(int option, String phrase, String editId) {
		if (option == 2) {
			for (int i = 0; i < enrolledCourses.size(); i++) {
				if (editId.toLowerCase().equals(enrolledCourses.get(i).getCourseId().toLowerCase())) {
					enrolledCourses.get(i).setInstructor(phrase);
				}
			}
		}
		
		else if (option == 3) {
			for (int i = 0; i < enrolledCourses.size(); i++) {
				if (editId.toLowerCase().equals(enrolledCourses.get(i).getCourseId().toLowerCase())) {
					enrolledCourses.get(i).setLocation(phrase);
				}
			}
		}
	}
	// Checks if student log in is valid
	public boolean validate(String username, String password) {
		if (username.equals(this.username) && password.equals(this.password)) {
			return true;
		}           
		return false;
	}
	// Check if student is in a given course
	public boolean isStudentInCourse(String courseId) {
		boolean courseInStudentList;
		courseInStudentList = false;
		for (int i = 0; i < enrolledCourses.size(); i++) {
			if (enrolledCourses.get(i).getCourseId().toLowerCase().equals(courseId.toLowerCase())) {
				courseInStudentList = true;
			}
		}
		return (courseInStudentList);
	}
	// toString method for student
	public void toStirng() {
		System.out.println("The username of this student is " + username);
		System.out.println("The password of this student is " + password);
		System.out.println("The first name of this student is " + firstName);
		System.out.println("The last name of this student is " + lastName);
		System.out.println("These are the classes the sudent is currently enrolled in");
		printEnrolledClasses();
	}
	// modified toString
	public String toString() {
		return this.username + " " + this.password;
	}
	// adds new course to stdent course list
	public void addCourse(Course x) {
		enrolledCourses.add(x);
	}
	// removes course from student list
	public void removeCourse(String courseId) {
		boolean courseInStudentList;
		courseInStudentList = false;
		for (int i = 0; i < enrolledCourses.size(); i++) {
			if (courseId.toLowerCase().equals(enrolledCourses.get(i).getCourseId().toLowerCase())) {
				courseInStudentList = true;
				enrolledCourses.remove(i);
			}
		}
		if (!courseInStudentList) {
			System.out.println("The student is not registered in this course");
		}
	}
	// Prints all courses the given student is enrolled in
	public void printEnrolledClasses() {
		if (enrolledCourses.size() == 0) {
			System.out.println("The student is not currently registered in any classes");
		}
		for (int i = 0; i < enrolledCourses.size(); i++) {
			System.out.println(enrolledCourses.get(i).getCourseName() + " ");
		}
	}
	
	
	// GETTERS AND SETTERS
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
