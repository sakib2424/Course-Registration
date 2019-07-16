# Course Registration System User Manual

## Table of Contents

1. Introduction
2. Getting Started
3. Administrator Interface
    1. Option 1: Create a New Course
    2. Option 2: Delete A Course
    3. Option 3: Edit A Course
    4. Option 4: Display Course Info
    5. Option 5: Create A Student
    6. Option 6: View all Courses
    7. Option 7: View Courses That Are Full
    8. Option 8: Write to a file a list of full courses
    9. Option 9: View Names of Students in Specific Course
    10. Option 10: View courses a specific student is registered for
    11. Option 11: Sort courses based on number of students registered
    12. Option 12: Log Out
4. Student Interface 
    1. Option 1: View all available courses
    2. Option 2: View All Courses With Open Spots 
    3. Option 3: Register for a course
    4. Option 4: Withdraw from a course
    5. Option 5: View all courses currently registered in
    6. Option 6: Log out and exit




---
## Introduction

### Abstract
This is a basic course registration system built using Java that currently has a command line interface only. Certain features are still under development and there are currently minimal input error handling capabilities. Key features include:

1. Login as an administrator
2. Import course data into the program from external file.
3. Create student profiles.
4. Edit course info.
5. Enroll/withdraw student from a course.
6. “Log Out” feature that allows changes to be saved for next time program is run.

### Features not yet available but to be implemented:

1. Ability for admin and students to change their usernames and passwords.
2. Import student profile data from file.
3. Alter/delete a student profile once created.
4. Advanced Search - search course using its name, professor name, broad category, or location rather than only course ID.
5. Ability of admin to enroll student in a course.
6. Improved error handling to prevent program from crashing when a rouge input is entered.
7. Simultaneous login by students and administrator.
8. Categorization of courses intro fields (ex. computer science, mathematics, etc.) 
9. Ability to process external file which contains lists of student profiles enrolled in each class.
10. Functioning "Option 10" for administrator, which allows them to view which courses a specific student is registered in.

Administrator must log in and create a student profile before that student can log in. The first section documents the administrator interface, and the second is dedicated to the student.

### Goal 
Ultimately, once additional features are implemented, this program can be used for schools and universities to handle their course registration needs.

_Examples in this document follow the path of a two login sessions from beginning to end presented in chronological order. The first session is demonstrated in the "Administrator Interface" section and the second is demonstrated in the "Student Interface" section._

**Notes before starting**: The program cannot yet handle rouge entries for most inputs, so inputs must be exact to prevent crashes. The demonstrations are based on terminal commands on Mac OS; please use equivalent commands if running on a Windows OS. Option 10 on the administrator interface is currently not functioning, but selecting it will not cause the program to crash.

**Contact for questions:**  Ashfaque Sakib, as10239@stern.nyu.edu


---
## Getting Started

To begin, perform the following steps:

1. Open up terminal and navigate to root directory. 
2. Enter into folder named “src”.
3. Compile file named “Execute.java”.
4. Call the compiled program.

The example below assumes the project folder is located on the desktop:
```
> cd Desktop                   // Navigate to the desktop
> cd "Course Registration"     // Enter the folder
> cd src                       // cd to the source folder
> javac Execute.java           // Compile the program
> java Execute                 // Call compiled program
```
From here on, follow the instructions printed onto the console. Log in as an administrator using the credentials below:

Username: admin

Password: admin101

```
Enter Username
> admin          // username
Enter Password
> admin101       // password
```
---

### Data source

You will now have the option to import external course data. If this option is taken, the file must be a comma separated values (CSV) file that follows the precise format as the sample file titled “data.csv”. The sample is included in the "src" folder.

#### Datafield Schema

| Name | id | Description | Input Type | Example |
| --- | --- | --- | --- | --- |
| Course Name | `Course_Name` | The name of the class | String | Data Structures |
| Course ID | `Course_Id` | A unique identification code associated with the course | String | CS-104
| Maximum Permissable Students | `Maximum_Students` | The maximum capacity of the the class | Integer | 25
| Currently Registered | `Current_Students` | The number of students currently registered in the course | Integer | 0 
| Name List | `List_Of_Names` | An array of student names registered for course | String[] | [ "John Smith", "Mary Watson", "Jake Segal"]
| Professor Name | `Course_Instructor` | Name of the professor who teaches the course | String | Mohammad Zaran
| Course Section | `Course_Section_Number` | Number identifying course section | Integer | 004
| Course Location | `Course_Location` | Address and room number of the class | String | 60 5th Avenue 110

**NOTE: System cannot yet handle enteries in datafields for "Currently Registered" and "Name List". Current version of the program assumes all imported classes are empty, so all values for these columns must be set to 0 and NULL respectively.**

| Course_Name | Course_Id | Maximum_Students | Current_Students | List_Of_Names | Course_Instructor | Course_Section_Number | Course_Location
| --- | --- | --- | --- | --- | --- | --- | --- |
| PAC II | CSCI-GA.1144 | 7 | 0 | NULL | Mohamed Zahran | 1 | CIWW 312
| Fundamental Algorithms | CSCI-GA.1170 | 4 | 0 | NULL | Joel Spencer | 1 | CIWW 109

#### Demonstration of a sucessful import:

```
There is no data from previous log in, system will treat as initial log in
Would you like to retrieve class data from external file?
Enter 1 for yes or 2 for no
> 1
Please enter the name of the file
> data.csv                                                             
The data was sucessfully imported from the csv file
```

---

## Administrator Interface

Once past the promt to retrieve class data (whether imported or not), a series of 12 options as shown below will be presented in an infinite loop until user logs out (option 12).  **User MUST log out** for changes to be saved. 

```
Here are your options, please enter the number of option to select
1. Create a new Course
2. Delete a course
3. Edit a course
4. Display course info
5. Create a student
6. View all courses
7. View courses that are full
8. Write to a file a list of full courses
9. View names of student in specific course
10. View courses a specifc student is registered for
11. Sort courses based on number of stuents registered
12. Exit and log out
```

All these options are documented as follows: 

### Option 1: Create a New Course

A course object consists of the following attributes, which will require user input to set: 

|Field | Type
| --- | ---
| Course Name | String
| Course ID | String
| Maximum number of students permissible | Integer
| Name of instructor | String
| Location | String

And this attribute which user  **CANNOT directly set/alter**:

| Field | Type |
| --- | --- |
| Number of students enrolled | Integer |

Sample CLI is as follows:

```
> 1
Enter a course name
> Data Structures
Enter a course ID
> cs104
Enter the max number of students allowed in class
> 2
Enter the name of instructor
> John Smith
Enter the course section number
> 2
Enter location of class
> 60 5th Avenue
Class successfully added
```
### Option 2: Delete A Course

A course is deleted using the course ID number as the key identifier. All students enrolled in the class will be withdrawn and course will be removed. Below is an example of a successful deletion of the “Data Structures” course created above:

```
> 2
Enter the course ID of the class you want to delete
> cs104
Sucessfully deleted course
```
### Option 3: Edit A Course
The course is first identified using the course ID entered by user, then a menu of options are presented for which attribute user desires to change. Example below changes instructor’s name from John Smith to Jill Green (the “Data Structures” class was recreated exactly as it was after deletion in the previous step for demonstration):

```
> 3
Please enter the course ID of the class you want to edit
> cs104
You've selected the following course: Data Structures
What part of this course do you want to change? 
Enter number for option
1. Change the max number of allowed students
2. Change the instructor name
3. Change the location of the class
4. Change the course section number
> 2
Please enter the updated name of the instructor
> Jill Green
Instructor name sucessfully updated
```

### Option 4: Display Course Info
This option prints the all attributes of a course onto the console. Notice: the updated instructor name for the course "Data Structures" is displayed from the previous step.

```
> 4
Enter course ID of class you wish to view
> cs104
Here is the info for your selected course
Course name: Data Structures
Course ID: cs104
Max number of students permissable: 2
Name of instructor: Jill Green
Name of location: 60 5th Avenue
Current number of stuents enrolled: 0
Course section number: 2
```

### Option 5: Create A Student
This option creates a student object. A student object contains the following attributes, all to be input by the admin:

|Field|Type | Note
| --- | --- | ---
| Username | String | Unique identifier for the student, also used for student log in
| Password | String | Credential used by student to log in
| First Name | String | The first name of the student
| Last Name | String | The last name of the student

Example below creates a profile for a student Andy Saban.

```
> 5
Please enter a username of the student
> as10239
Please enter a password of the student
> abc123
Please enter the first name of the student
> Andy
Please enter the last name of the student
> Saban
Student profile sucessfuly created and saved to records
```

### Option 6: View all Courses

This option prints all course objects onto the console. As displayed below, it includes imported courses from external file from initial set up (entire output of this is not included in example below).

```
> 6
Course name: PAC II
Course ID: CSCI-GA.1144
Max number of students permissable: 7
Name of instructor: Mohamed Zahran
Name of location: CIWW 312
Current number of stuents enrolled: 0
Course section number: 1

Course name: Fundamental Algorithms
Course ID: CSCI-GA.1170
Max number of students permissable: 4
Name of instructor: Joel Spencer
Name of location: CIWW 109
Current number of stuents enrolled: 0
Course section number: 1

Course name: Programming Languages
Course ID: CSCI-GA.2110
Max number of students permissable: 7
Name of instructor: Benjamin Goldberg
Name of location: CIWW 109
Current number of stuents enrolled: 0
Course section number: 1
```

### Option 7: View Courses That Are Full

This will print a list of courses that have reached their capacity of students. In the first output there are no full classes because no students have been registered in previous steps. The second output displays “Data Structures” as being full (students Andy and Steve were registered here for demonstration purposes).

```
> 7
There are no courses that are full
```
Using "Option 7" after two students were entrolled in "Data Structures" (capacity of the class was 2):
```
> 7
Course 'Data Structures' with course ID cs104 is currently full
```
**Enrollment is demonstrated under option 3 of the student interface section**

### Option 8: Write to a file a list of full courses

This option uses the information displayed in step 7 and writes it to a text file, but also includes a list of students who registered.

Sucessful execution: 
```
> 8
What would you like to name the file?
> fullClasses.txt
File scuesfully created
```
What text file displays: 
```
Data Structures
Course ID: cs104
Max Number of Students: 2
Instructor: Jill Green
Location: 60 5th Avenue
Number of students currently enrolled: 2
Class Section: 2
List of student names: 
Steve Rubin
Andy Saban
```

**Notice: Students Andy and Steve are displayed as registered for the course in the text file output above**

### Option 9: View Names of Students in Specific Course

This option prints a list of students enrolled in the selected course to the console

```
> 9
Please enter the course ID of the course you wish to view the sudents of
> cs104
Here is the list of students enrolled in course: Data Structures
Steve Rubin
Andy Saban
```

### Option 10: View courses a specific student is registered for **(This is currently not functioning)**

Purpose: to list out for the admin the courses a selected student has registered for.

_**Under Development**_

### Option 11: Sort courses based on number of students registered

Sorts the courses based on how many students are enrolled from most to least. Courses with tied registration numbers are displayed in the same order as they were created (whether by import or by admin).

_"Data Structures" is displayed at the top because 2 students are enrolled in it while rest have 0 students enrolled._
```
> 11
Here is the order of classes from having the least students to the most
Data Structures
PAC II
Fundamental Algorithms
Programming Languages
Compiler Construction
Open Source Tools
Operating Systems
Database Systems
Software Engineering
Web Search Engines
Special Topic: Big Data Science
Special Topic: Social Networks
Special Topic: Cloud Computing
Special Topic: DevOps
Intro To Computer Programming
Intro To Computer Programming
Intro to Web Design & Computer Principles
Intro to Web Design & Computer Principles
Database Design And Web Implementation
Web Development And Programming
Intro To Computer Science
Intro To Computer Science
Data Structures
Computer Systems Organization
Basic Algorithms
Numerical Computing
Theory Of Computation
Object Oriented Programming
Intro To Machine Learning
Introduction to Robotics
```

### Option 12: Log Out

This option is to be selected once edits are completed.  **It is very important to exit program using this in order to save changes made.**  Once logged off,  **do not delete SER files generated**.  Doing so will destroy data from previous use.

```
> 12
Logged out, program ended
```
---

## Student Interface

After admin has logged in and made changes the first time, subsequent logins from student or admin will import data from serialized file. Run the complied program "Execute" again to log in again. Example below demonstrates log in by student Andy, whose profile was created in "Option 5" in the previous section.

```
> java Execute
Enter Username
> as10239
Enter Password
> abc123
Course data sucesfully imported from previous log in...

Student data sucesfully imported from previous log in...

Sucesfully logged in
```

Now, the following options will be displayed in an infinite loop until student logs out.  **It is important for student to log out for his/her edits to be saved.**

```
Here are your options, please enter the number of option
1. View all available courses
2. View all courses with open spots
3. Register in a course
4. Withdraw from a course
5. View all courses currently registered in
6. Log out and exit
```
All six of these options are demonstrated below

### Option 1: View all available courses

This lists course names and course ID for the student to view, as shown below (full terminal output of 31 items not included)

```
> 1
Here is the list of all courses: 
Course Name: PAC II
Course ID: CSCI-GA.1144

Course Name: Fundamental Algorithms
Course ID: CSCI-GA.1170

Course Name: Programming Languages
Course ID: CSCI-GA.2110

Course Name: Compiler Construction
Course ID: CSCI-GA.2130
```

### Option 2: View All Courses With Open Spots 

This option will display all courses which are not at their max capacity yet. “Data Structures” is not listed anywhere in the output because it is filled with 2 students (full terminal output  of 30 courses not included).

```
> 2
Here is the list of all courses: 
Course Name: PAC II
Course ID: CSCI-GA.1144

Course Name: Fundamental Algorithms
Course ID: CSCI-GA.1170

Course Name: Programming Languages
Course ID: CSCI-GA.2110

Course Name: Compiler Construction
Course ID: CSCI-GA.2130
```

### Option 3: Register for a course

This allows the student to register for a course using its course ID. If a class that has reached maximum capacity has been selected or the input course ID does not exist then this will now allow registration. In the sample below, student Andy is registered for the “Introduction to Robotics” class. (Success of this function can be tested using option 5 of the student interface)

```
> 3
Please enter the course ID of the course you want to register for: 
> CSCI-UA.0465
Course successfully registered in
```

### Option 4: Withdraw from a course

This allows student to withdraw from a course that he/she is currently enrolled in. Example below shows withdrawal by Andy from the “Introduction to Robotics” course that was just enrolled in from the previous step. (Success of this function can be tested using option 5 of the student interace)

```
> 4
Please enter the course ID of the course you want to withdraw from: 
> CSCI-UA.0465
Course succesfully withdrawn from
```

### Option 5: View all courses currently registered in

This will print a list of all courses student is enrolled in onto the console. As seen in the example below, only “Data Structures” remains after “Introduction to Robotics” was withdrawn from in the previous step.

```
> 5
Here are the list of courses currently registered for: 
Data Structures 
```

### Option 6: Log out and exit

Use this option once there are no futher changes to be made. Successful logout demonstrated below:
```
> 6
Logged out, program ended
```
Once logged off,  **do not delete SER files generated**. Doing so will destroy data from previous use.

---
