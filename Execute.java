import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

//Username: admin password: admin101
// Students should first be created by the admin to be registered.
// Both admin and student users must click option 12 to exit for program to function correctly
// Both students and admin must properly log out for program to function correctly
// There is no need for program to ask whether the user is admin or student, it will know after info is entered.
// The program can also be run without initially importing any course data, courses can be made by the admin in the program.
// do not edit any course information as admin once student is registered in that course, program is not eq
public class Execute {
	
	static ArrayList<Course> listOfCourses = new ArrayList<Course>();
	static ArrayList<Student> listOfStudents = new ArrayList<Student>();
	private static Scanner input;
	private static int loggedInStudentIndex;
	private static BufferedReader reader;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		input = new Scanner(System.in);
		String user;
		String pass;
		System.out.println("Enter Username");
		user = input.nextLine();
		System.out.println("Enter Password");
		pass = input.nextLine();
		boolean adminValid = Admin.validate(user, pass);
		boolean isStudent = true;
		boolean isAdmin = false;
		
		if (adminValid) {
			dataRetrival(isAdmin);
			adminLoop();
		}
		
		else {
			dataRetrival(isStudent);
			boolean studentFound; 
			studentFound = false;
			for (int i = 0; i < listOfStudents.size(); i++) {
				if (listOfStudents.get(i).validate(user, pass)) {
					studentFound = true;
					loggedInStudentIndex = i;
					System.out.println("Sucesfully logged in");
					System.out.println();
					studentLoop();
				}
			}
			if (!studentFound) {
				System.out.println("Error in username or password");
			}
		}
	}
	
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	static void dataRetrival(boolean student) throws IOException, ClassNotFoundException {
		File classesData = new File ("savedClasses");
		File studentData = new File ("savedStudents");
		boolean exists = classesData.exists();
		boolean exists2 = studentData.exists();
		
		if (exists && exists2) {
			try {
				ObjectInputStream classes = new ObjectInputStream(new FileInputStream("SavedClasses"));
				listOfCourses = (ArrayList<Course>) classes.readObject();
				classes.close();
				System.out.println("Course data sucesfully imported from previous log in...");
				System.out.println();
				ObjectInputStream students= new ObjectInputStream(new FileInputStream(studentData));
				listOfStudents = (ArrayList<Student>) students.readObject();
				
				students.close();
				System.out.println("Student data sucesfully imported from previous log in...");
				System.out.println();
			}
			catch(IOException hi) {
				System.out.println("There was an error while retriving student data from previous log in");
			}
		}
		
		else if (!student){
			System.out.println("There is no data from previous log in, system will treat as initial log in");
			System.out.println("Would you like to retrieve class data from external file?");
			System.out.println("Enter 1 for yes or 2 for no");
			int answer = Integer.parseInt(input.nextLine());
			if (answer == 1) {
				System.out.println("Please enter the name of the file");
				String fileName = input.nextLine();
				reader = new BufferedReader(new FileReader(new File(fileName)));
				String line = reader.readLine();
				line = reader.readLine();
				while(line != null) {
					String[] arr = line.split(",");
					listOfCourses.add(new Course(arr[0], arr[1], Integer.valueOf(arr[2]), Integer.valueOf(arr[3]), arr[5], Integer.valueOf(arr[6]), arr[7]));
					line = reader.readLine();
				}
				System.out.println("The data was sucessfully imported from the csv file");
				reader.close();
			}
		}
	}
	
	// Processes student option input until log out
	// During log off, serializes student and course lists
	static void studentLoop() {
		boolean bool = true;
		
		while (bool) {
			printStudentOptions();
			int selectedOption = Integer.parseInt(input.nextLine());
			
			if (selectedOption == 1) {
				studentOptionOne();
			}
			
			else if (selectedOption == 2) {
				studentOptionTwo();
			}
			
			else if (selectedOption == 3) {
				studentOptionThree();
			}
			
			else if (selectedOption == 4) {
				studentOptionFour();
			}
			
			else if (selectedOption == 5) {
				studentOptionFive();
			}
			
			else if (selectedOption == 6) {
				try {
					ObjectOutputStream classes = new ObjectOutputStream(new FileOutputStream("savedClasses"));
					ObjectOutputStream students = new ObjectOutputStream(new FileOutputStream("savedStudents"));
					classes.writeObject(listOfCourses);
					students.writeObject(listOfStudents);
					classes.close();
					students.close();				
				}
				
				catch (IOException ioe) {
					System.out.println("The program was unable to export the student and course data");
					ioe.printStackTrace();
				}
				
				System.out.println("Logged out, program ended");
				bool = false;
			
			}
			
			else {
				System.out.println("Invalid input");
			}
			
		}
	}
	
	// Method for student to view all available courses
	static void studentOptionOne() {
		if (listOfCourses.size() == 0) {
			System.out.println("There is no courses currently in the system");
		}
		
		else {
			System.out.println("Here is the list of all courses: ");
			for (int i = 0; i < listOfCourses.size(); i++) {
				System.out.println("Course Name: " + listOfCourses.get(i).getCourseName());
				System.out.println("Course ID: " + listOfCourses.get(i).getCourseId());
				System.out.println();
			}
		}
	}
	
	// Method for student to view all courses with open positions
	static void studentOptionTwo() {
		if (listOfCourses.size() == 0) {
			System.out.println("There is no courses currently in the system");
		}
		
		else {
			System.out.println("Here is the list of classes with open spots:");
			System.out.println();
			for (int i = 0; i < listOfCourses.size(); i++) {
				if (listOfCourses.get(i).getCurrentStudents() < listOfCourses.get(i).getMaxNumberOfStudents()) {
					System.out.printf("Class name: %s%n \nCourse ID: %s%n", listOfCourses.get(i).getCourseName(), listOfCourses.get(i).getCourseId());
					System.out.println();
					System.out.println();
					System.out.println();
				}
			}
			
		}
		
	}
	
	// Method for student to register in a course
	static void studentOptionThree() {
		System.out.println("Please enter the course ID of the course you want to register for: ");
		String enteredID;
		enteredID = input.nextLine();
		boolean foundCourse;
		foundCourse = false;
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (listOfCourses.get(i).getCourseId().toLowerCase().equals(enteredID.toLowerCase())) {
				if (listOfCourses.get(i).getCurrentStudents() < listOfCourses.get(i).getMaxNumberOfStudents()) {
					foundCourse = true;
					listOfStudents.get(loggedInStudentIndex).addCourse(listOfCourses.get(i));
					listOfCourses.get(i).addStudent(listOfStudents.get(loggedInStudentIndex));
					System.out.println("Course successfully registered in");
				}
				else {
					System.out.println("This class is full, cannot register");
					return;
				}
			}
		}
		if (!foundCourse) {
			System.out.println("The course ID you entered was not found in the system");
		}
	}
	
	// Method for student to withdraw from a course
	static void studentOptionFour() {
		System.out.println("Please enter the course ID of the course you want to withdraw from: ");
		String selectedCourseId = input.nextLine();
		boolean foundClass = false;
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (selectedCourseId.equals(listOfCourses.get(i).getCourseId())) {
				foundClass = true;
				listOfStudents.get(loggedInStudentIndex).removeCourse(selectedCourseId);
				listOfCourses.get(i).removeStudent(listOfStudents.get(loggedInStudentIndex).getFullName());
				System.out.println("Course succesfully withdrawn from");
			}				
		}
		if (!foundClass) {
			System.out.println("Course not found");
		}
	}
	
	// Allows student to view all courses he/she is currently in
	static void studentOptionFive() {
		System.out.println();
		System.out.println("Here are the list of courses currently registered for: ");
		listOfStudents.get(loggedInStudentIndex).printEnrolledClasses();
	}
	
	// Processes admin option inputs until admin logs off 
	// During log off, serializes student and course lists
	static void adminLoop() throws FileNotFoundException {
		boolean bool = true;
		while (bool) {
			printOptions();
			int selection = Integer.parseInt(input.nextLine());
			System.out.println();
			
			if (selection == 1) {
				optionOne();
			}
			
			else if (selection == 2) {
				optionTwo();
			}
			
			else if(selection == 3) {
				optionThree();
			}
			
			else if (selection == 4) {
				optionFour();
			}
			
			else if (selection == 5) {
				optionFive();
			}
			
			else if (selection == 6) {
				optionSix();
			}
			
			else if (selection == 7) {
				optionSeven();
			}
			
			else if (selection == 8) {
				optionEight();
			}
			
			else if (selection == 9) {
				optionNine();
			}
			
			else if (selection == 10) {
				optionTen();
			}
			
			else if (selection == 11) {
				optionEleven();
			}
			
			else if (selection == 12) {
				try {
					ObjectOutputStream classes = new ObjectOutputStream(new FileOutputStream("savedClasses"));
					ObjectOutputStream students = new ObjectOutputStream(new FileOutputStream("savedStudents"));
					classes.writeObject(listOfCourses);
					students.writeObject(listOfStudents);
					classes.close();
					students.close();				
				}
				
				catch (IOException ioe) {
					System.out.println("The program was unable to export the student and course data");
					ioe.printStackTrace();
				}
				
				System.out.println("Logged out, program ended");
				bool = false;
			}
			
			else {
				System.out.println("This is an invalid input");
			}
			
						
		}
	}
	
	// Method with print statement to display student's options
	static void printStudentOptions() {
		System.out.println("Here are your options, please enter the number of option");
		System.out.println("1. View all available courses");
		System.out.println("2. View all courses with open spots");
		System.out.println("3. Register in a course");
		System.out.println("4. Withdraw from a course");
		System.out.println("5. View all courses currently registered in");
		System.out.println("6. Log out and exit");
	}
	
	// Contains print statements for the options for admin user 
	static void printOptions() {
		System.out.println("Here are your options, please enter the number of option to select");
		System.out.println("1. Create a new Course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. Display course info");
		System.out.println("5. Create a student");
		System.out.println("6. View all courses");
		System.out.println("7. View courses that are full");
		System.out.println("8. Write to a file a list of full courses");
		System.out.println("9. View names of student in specific course");
		System.out.println("10. View courses a specifc student is registered for");
		System.out.println("11. Sort courses based on number of stuents registered");
		System.out.println("12. Exit and log out");
	}
	
	// Creates a new course for admin
	static void optionOne() {
		System.out.println("Enter a course name");
		String courseName = input.nextLine();
		System.out.println("Enter a course ID");
		String courseID = input.nextLine();
		System.out.println("Enter the max number of students allowed in class");
		int maxNumberOfStudents = Integer.parseInt(input.nextLine());
		System.out.println();
		System.out.println("Enter the name of instructor");
		String instructor = input.nextLine();
		System.out.println("Enter the course section number");
		int courseSectionNumber = Integer.parseInt(input.nextLine());
		System.out.println("Enter location of class");
		String location = input.nextLine();
		listOfCourses.add(new Course(courseName, courseID, maxNumberOfStudents, 0, instructor, courseSectionNumber, location));
		System.out.println("Class sucesfully added");
	}
	
	// Allows admin to delete a course
	static void optionTwo() {
		System.out.println("Enter the course ID of the class you want to delete");
		String delID;
		delID = input.nextLine();
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (delID.toLowerCase().equals(listOfCourses.get(i).getCourseId().toLowerCase())) {
				listOfCourses.remove(i);
				for (int m = 0; m < listOfStudents.size(); m++) {
					if (listOfStudents.get(m).isStudentInCourse(delID)) {
						listOfStudents.get(m).removeCourse(delID);
					}
				}
				System.out.println("Sucessfully deleted course");
				return;
			}
		}
		System.out.println("Course with this ID not found in system");
		return;
	}
	
	// Allows admin to make edits to a registered course.
	// All changes are also made to course objects inside the course lists of the student objects
	static void optionThree() {
		System.out.println("Please enter the course ID of the class you want to edit");
		String editId;
		boolean courseFound;
		editId = input.nextLine();
		courseFound = false;
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (editId.toLowerCase().equals(listOfCourses.get(i).getCourseId().toLowerCase())) {
				courseFound = true;
				System.out.println("You've selected the following course: " + listOfCourses.get(i).getCourseName());
				System.out.println("What part of this course do you want to change? \nEnter number for option");
				System.out.println("1. Change the max number of allowed students");
				System.out.println("2. Change the instructor name");
				System.out.println("3. Change the location of the class");
				System.out.println("4. Change the course section number");
				int editChoice;
				editChoice = Integer.parseInt(input.nextLine());
				
				if (editChoice == 1) {
					System.out.println("What is the new number of max students that should be allowed?");
					int newMax = Integer.parseInt(input.nextLine());
					for (int m = 0; m < listOfStudents.size(); m++) {
						if (listOfStudents.get(m).isStudentInCourse(editId)) {
							listOfStudents.get(m).edits(1, newMax, editId);
						}
					}
					listOfCourses.get(i).setMaxNumberOfStudents(newMax);
					System.out.println("Max student limit succesffully changed");
				}
				
				else if (editChoice == 2) {
					System.out.println("Please enter the updated name of the instructor");
					String newInstructorName = input.nextLine();
					for (int m = 0; m < listOfStudents.size(); m++) {
						if (listOfStudents.get(m).isStudentInCourse(editId)) {
							listOfStudents.get(m).edits(2, newInstructorName, editId);
						}
					}
					listOfCourses.get(i).setInstructor(newInstructorName);
					System.out.println("Instructor name sucessfully updated");
				}

				else if (editChoice == 3) {
					System.out.println("Please enter the new location of the class");
					String newLocation = input.nextLine();
					for (int m = 0; m < listOfStudents.size(); m++) {
						if (listOfStudents.get(m).isStudentInCourse(editId)) {
							listOfStudents.get(m).edits(3, newLocation, editId);
						}
					}
					listOfCourses.get(i).setLocation(newLocation);
					System.out.println("Location of course succesfully updated");
				}

				else if (editChoice == 4) {
					System.out.println("Please enter the new section number for the course");
					int newSectionNumber = Integer.parseInt(input.nextLine());
					for (int m = 0; m < listOfStudents.size(); m++) {
						if (listOfStudents.get(m).isStudentInCourse(editId)) {
							listOfStudents.get(m).edits(4, newSectionNumber, editId);
						}
					}
					listOfCourses.get(i).setCourseSectionNumber(newSectionNumber);
					System.out.println("Course section number sucesfully updated");
				}
				
				else {
					System.out.println("Invalid input");
				}
				
			}
			
		}
		
		if (!courseFound) {
			System.out.println("This course could not be found");
		}
	}
	
	// Displays the course information of a selected course
	static void optionFour() {
		System.out.println("Enter course ID of class you wish to view");
		String selectedCourseId;
		boolean courseFound;
		courseFound = false;
		selectedCourseId = input.nextLine();
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (selectedCourseId.equals(listOfCourses.get(i).getCourseId())) {
				courseFound = true;
				System.out.println("Here is the info for your selected course");
				listOfCourses.get(i).noReturnToString();
			}
		}
		
		if (!courseFound) {
			System.out.println("There is no course registered with that course ID");
		}
	}
	
	// Allows admin to create a student profile
	static void optionFive() {
		System.out.println("Please enter a username of the student");
		String username = input.nextLine();
		System.out.println("Please enter a password of the student");
		String password = input.nextLine();
		System.out.println("Please enter the first name of the student");
		String firstName = input.nextLine();
		System.out.println("Please enter the last name of the student");
		String lastName = input.nextLine();
		listOfStudents.add(new Student(username, password, firstName, lastName));
		System.out.println("Student profile sucessfuly created and saved to records");
	}
	
	// Prints data of all the registered courses
	static void optionSix() {
		if (listOfCourses.size() == 0) {
			System.out.println("There are no courses currently registered");
			return;
		}
		for (int i = 0; i < listOfCourses.size(); i++) {
			listOfCourses.get(i).noReturnToString();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
	
	// Prints info of all courses that are at max capacity
	static void optionSeven() {
		boolean atLeastOneClassFull;
		atLeastOneClassFull = false;
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (listOfCourses.get(i).getMaxNumberOfStudents() == listOfCourses.get(i).getCurrentStudents()) {
				atLeastOneClassFull = true;
				System.out.printf("Course '%s' with course ID %s is currenty full", listOfCourses.get(i).getCourseName(), listOfCourses.get(i).getCourseId());
				System.out.println();
			}
		}
		
		System.out.println();
		
		if (!atLeastOneClassFull) {
			System.out.println("There are no courses that are full");
		}
	}
	
	// Allows admin to export list of all filled classes into a text file
	static void optionEight() throws FileNotFoundException {
		System.out.println("What would you like to name the file?");
		String fileName = input.nextLine();
		File logFile = new File(fileName);
		PrintWriter logFileWriter = new PrintWriter(logFile);
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (listOfCourses.get(i).getCurrentStudents() == listOfCourses.get(i).getMaxNumberOfStudents()) {
				logFileWriter.printf("%s%nCourse ID: %s%nMax Number of Students: %s%nInstructor: %s%nLocation: %s%nNumber of students currently enrolled: %s%nClass Section: %s%n", listOfCourses.get(i).getCourseName(), listOfCourses.get(i).getCourseId(), Integer.toString(listOfCourses.get(i).getMaxNumberOfStudents()), listOfCourses.get(i).getInstructor(), listOfCourses.get(i).getLocation(), Integer.toString(listOfCourses.get(i).getCurrentStudents()), Integer.toString(listOfCourses.get(i).getCourseSectionNumber()));
				logFileWriter.println("List of student names: ");
				for (int x = 0; x < listOfCourses.get(i).getCurrentStudents(); x++) {
					logFileWriter.printf("%s%n", listOfCourses.get(i).returnStudentNameAtIndex(x));
				}
				logFileWriter.println();
				logFileWriter.println();
			}
		}
		logFileWriter.println("");
		System.out.println("File scuesfully created");
		logFileWriter.close();
		
	}
	
	// Shows student registered in selected course
	static void optionNine() {
		System.out.println("Please enter the course ID of the course you wish to view the sudents of");
		String selectedCourseId;
		boolean courseFound;
		courseFound = false;
		selectedCourseId = input.nextLine();
		for (int i = 0; i < listOfCourses.size(); i++) {
			if (selectedCourseId.equals(listOfCourses.get(i).getCourseId())) {
				courseFound = true;
				if (listOfCourses.get(i).getCurrentStudents() > 0) {
					System.out.println("Here is the list of students enrolled in course: " + listOfCourses.get(i).getCourseName());
					listOfCourses.get(i).printListOfStudents();
				}
				else {
					System.out.println("There are no students currently enrolled in this course");
				}
				
			}
		}
		if (!courseFound) {
			System.out.println("There is no class registered with that ID");
		}
	}
	
	// Shows courses a specific student is in
	static void optionTen() {
		String firstName;
		String lastName;
		boolean studentFound;
		studentFound = false;
		System.out.println("Please enter the first name of the student");
		firstName = input.nextLine().toLowerCase();
		System.out.println("Please enter the last name of the student");
		lastName = input.nextLine().toLowerCase();
		for (int i = 0; i < listOfStudents.size(); i++) {
			if ((firstName + " " + lastName).equals((listOfStudents.get(i).getFullName()))) {
				studentFound = true;
				System.out.println("This student is taking the following classes");
				listOfStudents.get(i).printEnrolledClasses();
			}
		}	
		
		if (!studentFound) {
			System.out.println("There is no such student in the existing records");
		}
	}
	
	// Sorts the courses based on number of students registered
	// All ties are placed in their original order
	static void optionEleven() {
		ArrayList<Course> dummyList = new ArrayList<Course>();
		ArrayList<Course> finalList = new ArrayList<Course>();
		for (int i = 0; i < listOfCourses.size(); i++) {
			dummyList.add(listOfCourses.get(i));
		}
		
		while (dummyList.size() > 0) {
			int start;
			start = dummyList.get(0).getCurrentStudents();
			int index;
			index = 0;
			for (int i = 0; i < dummyList.size(); i++) {
				if (dummyList.get(i).getCurrentStudents() > start) {
					start = dummyList.get(i).getCurrentStudents();
					index = i;
				}
			}
			finalList.add(dummyList.get(index));
			dummyList.remove(index);
		}
		
		System.out.println("Here is the order of classes from having the least students to the most");
		
		for (int i = 0; i < finalList.size(); i++) {
			System.out.println(finalList.get(i).getCourseName());
		}
		
		System.out.println("");
	}
	
	
}















