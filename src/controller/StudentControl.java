package controller;

import entity.Student;
import java.util.HashMap;
import java.util.ArrayList;


/**
 * Control class for controller to create, store and get Student instances
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-12
 */
public class StudentControl 
{
    /**
     * Mapping of Student ID to Student instance
     */
    private static HashMap<String, Student> students = new HashMap<String, Student>();

    /**
     * Get list of students
     * @return List of Student instances
     */
    public static ArrayList<Student> getStudentList() 
    {
        return new ArrayList<Student>(students.values()); // return ArrayList of values
    }

    /**
     * Create new student instance
     * @param studentName Name of student
     * @param studentEmail Email of student
     */
    public static Student createStudent(String studentName, String studentEmail) 
    {
        Student newStudent = new Student(studentName, studentEmail);
        students.put(newStudent.getUserId(), newStudent);

        return newStudent;
    }

    /**
     * Check if studentID exists in the current list of students 
     * @param studentID UserID of student
     * @return whether student list contains the specified studentID
     */
    public static boolean exists(String studentID)
	{
		return students.containsKey(studentID);
	}

    /**
	 * Return the Student instance based on the studentID
	 * @param studentID UserID of student
	 * @return Student instance
	 */
	public static Student getStudentById(String studentID)
	{
		return students.get(studentID);
	}

    /**
	 * Return the id of registered project of student
	 * @param studentID UserID of student
	 * @return project id of registered project
	 */
    public static int getStudentRegisteredProject(String studentID)
    {
        return getStudentById(studentID).getRegisteredProject();
    }

    /**
	 * Update the password of Student
     * @param studentID UserID of student
	 */
    public static void changeStudentPassword(String studentID)
    {
        UserAccountControl.changePassword(getStudentById(studentID));
    }

    /** 
	 * Verify the username and password for student
	 * @param userID The userID of student
     * @return whether the verification is successful or not
	 */
    public static boolean authenticateStudent(String studentID)
    {   
        return exists(studentID) && UserAccountControl.authenticate(getStudentById(studentID));
    }
}