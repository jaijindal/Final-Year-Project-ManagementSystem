package entity;

import java.util.ArrayList;


/**
Represents a project in the system.
@author Twu Pin Yang
@version 1.0
@since 2023-04-12
*/
public class Project 
{
	/**
	* The ID of this project.
	*/
	private int projectId;

	/**
	* The title of this project.
	*/
	private String projectTitle;
	
	/**
	* The supervisor ID of this project.
	*/
	private String supervisorId;

	/**
	* The student ID of this project.
	*/
	private String studentId;

	/**
	* The status of this project.
	*/
	private String status;

	/**
	 * ID of supervisor who submitted this project
	 */
	private String submitter;

	/**
	* The list of students blacklisted from this project.
	*/
	private ArrayList<String> blacklist;
	
	/**
	* Creates a new Project.
	* @param projectId This Project's ID.
	* @param projectTitle This Project's title.
	* @param supervisorId This Project's supervisor's ID.
	*/
	public Project(int projectId, String projectTitle, String supervisorId) 
	{
		this.projectId = projectId;
		this.projectTitle = projectTitle;
		this.supervisorId = supervisorId;
		this.studentId = "";
		this.status = "available";
		this.blacklist = new ArrayList<String>();
		this.submitter = supervisorId;
	}
	
	/**
	* Gets the submitter ID of this project.
	* @return this Project's submitter ID.
	*/
	public String getSubmitterId() 
	{
		return this.submitter;
	} 
	
	/**
	* Gets the ID of this project.
	* @return this Project's ID.
	*/
	public int getProjectId() 
	{
		return this.projectId;
	} 
	
	/**
	* Gets the title of this project.
	* @return this Project's title.
	*/
	public String getProjectTitle() 
	{
		return this.projectTitle;
	} 
	
	/**
	* Gets the supervisor's ID of this project.
	* @return this Project's supervisor's ID.
	*/
	public String getSupervisorId() 
	{
		return this.supervisorId;
	}  
	
	/**
	* Gets the student's ID of this project.
	* @return this Project's student's ID.
	*/
	public String getStudentId() 
	{
		return this.studentId;
	} 
	
	/**
	* Gets the status of this project.
	* @return this Project's status.
	*/
	public String getStatus() 
	{
		return this.status;
	}
	
	/**
	* Changes the title of this Project.
	* @param newProjectTitle This Project's new title.
	*/
	public void setProjectTitle(String newProjectTitle) 
	{
		this.projectTitle = newProjectTitle;
	}
	
	/**
	* Changes the supervisor's ID of this Project.
	* @param supervisorId This Project's new supervisor's ID.
	*/
	public void setSupervisorId(String newSupervisorId) 
	{
		this.supervisorId = newSupervisorId;
	}

	/**
	* Changes the submitter's ID of this Project.
	* @param supervisorId This Project's new supervisor's ID.
	*/
	public void setSubmitterId(String newSubmitterID) 
	{
		this.submitter = newSubmitterID;
	}
	
	/**
	* Changes the student's ID of this Project.
	* @param studentId This Project's new student's ID.
	*/
	public void setStudentId(String studentId) 
	{
		this.studentId = studentId;
	}
	
	/**
	* Changes the status of this Project.
	* @param status This Project's new status.
	*/
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	/**
	* Gets the blacklist of this project.
	* @return this Project's blacklist.
	*/
	public void getBlacklist() {
		for (int i = 0; i < this.blacklist.size(); i++) 
		{
			System.out.println(this.blacklist.get(i));
		}
	}

	/**
	* Adds a student ID to the blacklist of this Project.
	* @param studentID This Project's new blacklist's student's ID.
	*/
	public void addNewBlacklist(String studentID) 
	{
		this.blacklist.add(studentID);
	}

	/**
	* Checks if a student is in the blacklist of this project.
	* @return whether the student is in the blacklist.
	*/
	public boolean isBlacklisted(String studentID) 
	{
		return this.blacklist.contains(studentID); 
	}
}

