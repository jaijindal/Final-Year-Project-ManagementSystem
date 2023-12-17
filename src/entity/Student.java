package entity;

/**
 * Represents a student in the system.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-12
 */
public class Student extends User
{
	/**
	 * The project ID of this student's registered project.
	 */
	private int registeredProject;
		
	/**
	 * Creates a new Student with the given name.
	 * @param name This Student's name.
	 * @param emailAddress This Student's email address.
	 */
	public Student(String name, String emailAddress) 
	{
		super(name, emailAddress);
		this.registeredProject = -1;
	} 

	/**
	 * Get the project ID of this student's registered project.
	 * @return Student's project ID
	 */
	public int getRegisteredProject() 
	{
		return this.registeredProject;
	}

	/**
	 * Set the project ID of this student's registered project.
	 * @param this Student's project ID
	 */
	public void setRegisteredProject(int projectId) 
	{
		this.registeredProject = projectId;
	}
}
