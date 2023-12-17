package entity;

import java.util.ArrayList;

/**
Represents a supervisor in the system.
@author Twu Pin Yang
@version 1.0
@since 2023-04-12
*/ 
public class Supervisor extends User 
{
	/**
	* The number of projects this supervisor is supervising.
	*/
	private int noOfProjects;

	/**
	* The list of projects IDs that this supervisor is supervising.
	*/
	private ArrayList<Integer> listOfProjects;

	/**
	 * The list of project IDs that this supervisor has created
	 */
	private ArrayList<Integer> listOfCreated;

	/**
	* Creates a new Supervisor with the given name.
	* @param name This Supervisor's name.
	* @param emailAddress This Supervisor's email address.
	*/
	public Supervisor(String name, String emailAddress) 
	{		
		super(name, emailAddress);
		this.noOfProjects = 0;
		this.listOfProjects = new ArrayList<Integer>();
		this.listOfCreated = new ArrayList<Integer>();
	}

	public void addToCreated(int projectId)
	{
		this.listOfCreated.add(projectId);
	}

	public ArrayList<Integer> getCreatedList()
	{
		return this.listOfCreated;
	}
	
	/**
	* Gets the number of projects this supervisor is supervising.
	* @return the number of projects this supervisor is supervising
	*/
	public int getNoOfProjects() 
	{
		return this.noOfProjects;
	}
	
	/**
	* Gets the list of projects this supervisor is supervising.
	* @return the list of projects.
	*/
	public ArrayList<Integer> getProjectList() 
	{
		return this.listOfProjects;
	}

	public void createProject(int projectId) 
	{
		this.listOfCreated.add(projectId);
	}

	/**
	* Adds a project to this supervisor.
	* @param projectId the project ID to be added.
	*/
	public void superviseProject(int projectId) 
	{
		listOfProjects.add(projectId);
		this.noOfProjects++;
	}

	/**
	* Removes a project from this supervisor.
	* @param projectId the project ID to be added.
	*/
	public void unsuperviseProject(int projectId) 
	{
		for (int i=0; i<this.listOfProjects.size(); i++) 
		{
			if (this.listOfProjects.get(i) == projectId) 
			{
				this.listOfProjects.remove(i);
				break;
			}
		}
		this.noOfProjects--;
	}

	/**
	* Check if the cap on number of supervising project is reached
	* @param projectId the project ID to be added.
	*/
	public boolean isAvailable() 
	{
		return getNoOfProjects() < 2;
	}
}
