import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{

	private static final long serialVersionUID = 631303682915624435L;
	private String courseName;
	private String courseId;
	private int maxNumberOfStudents;
	private String instructor;
	private String location;
	private int currentStudents;
	private int courseSectionNumber;
	
	// List of all students registered in this course
	ArrayList<Student> listOfStudents;
	
	// Constructor
	public Course(String courseName, String courseId, int maxNumberOfStudents, int currentStudents, String instructor, int courseSectionNumber, String location) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.maxNumberOfStudents = maxNumberOfStudents;
		this.instructor = instructor;
		this.location = location;
		this.currentStudents = currentStudents;
		this.courseSectionNumber = courseSectionNumber;
		listOfStudents = new ArrayList<>();
	}
	// returns student name at given index
	public String returnStudentNameAtIndex(int index) {
		return (listOfStudents.get(index).fullName);
	}
	// Adds student object to list of students
	public void addStudent(Student x) {
		listOfStudents.add(x);
		this.currentStudents++;
	}
	// Deletes student from list
	public void removeStudent(String name) {
		for (int i = 0; i < listOfStudents.size(); i++) {
			if (name.equals((listOfStudents.get(i).getFirstName() + " " + listOfStudents.get(i).getLastName()).toLowerCase())) {
				listOfStudents.remove(i);
				currentStudents--;
			}
		}
	}
	// Print method
	public void noReturnToString() {
		System.out.printf("Course name: %s%n \nCourse ID: %s%n \nMax number of students permissable: %s%n \nName of instructor: %s%n \nName of location: %s%n \nCurrent number of stuents enrolled: %s%n \nCourse section number: %s%n", courseName, courseId, Integer.toString(maxNumberOfStudents), instructor, location, Integer.toString(currentStudents), Integer.toString(courseSectionNumber));
	}
	// Prints list of all studnets taking course
	public void printListOfStudents() {
		for (int i = 0; i < listOfStudents.size(); i++) {
			System.out.println(listOfStudents.get(i).getFirstName() + " " + listOfStudents.get(i).getLastName());
		}
	}
	// toString method
	public String toString() {
		System.out.println(this.courseName);
		return this.courseName;
	}
	
	// GETTS AND SETTERS
	public int getCurrentStudents() {
		return currentStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}

	public int getCourseSectionNumber() {
		return courseSectionNumber;
	}

	public void setCourseSectionNumber(int courseSectionNumber) {
		this.courseSectionNumber = courseSectionNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public int getMaxNumberOfStudents() {
		return maxNumberOfStudents;
	}

	public void setMaxNumberOfStudents(int maxNumberOfStudents) {
		this.maxNumberOfStudents = maxNumberOfStudents;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
