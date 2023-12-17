package controller;

import java.util.ArrayList;
import java.util.HashMap;
import entity.Supervisor;

/**
 * Control class for Supervisor
 * @author Arjun Kumar Nair
 * @version 1.0
 * @since 2023-04-12
 */
public class SupervisorControl 
{    
	/**
	 * The hashmap will store the supervisor's user id as the key and the supervisor object as the value
	 */
	private static HashMap<String, Supervisor> supervisors = new HashMap <String, Supervisor>();
      
	/** 
	 * Return all supervisors
	 * @return ArrayList of supervisors
	 */
  	public static ArrayList<Supervisor> getSupervisorList() 
	{
        return new ArrayList<Supervisor>(supervisors.values());
    }

	/**
	 * Create a supervisor and put in the hashmap
	 * @param supervisorName Name of supervisor
	 * @param supervisorEmail Email of supervisor
	 */
	public static Supervisor createSupervisor(String supervisorName, String supervisorEmail) 
	{
		Supervisor newSupervisor = new Supervisor(supervisorName, supervisorEmail);
		supervisors.put(newSupervisor.getUserId(), newSupervisor);

		return newSupervisor;
	}

	/**
     * Check if supervisorID exists in the current list of supervisors 
     * @param supervisorID UserID of supervisor
     * @return whether supervisor list contains the specified supervisorID
     */
	public static boolean exists(String supervisorID)
	{
		return supervisors.containsKey(supervisorID);
	}

	/**
	 * Check if the project is currently being supervisored by the supervisor
	 * @param supervisorID UserID of supervisor
	 * @param projectID ID of project
	 * @return if the project is currently being supervisored by the supervisor
	 */
	public static boolean isSupervising(String supervisorID, int projectID)
	{
		return getSupervisorById(supervisorID).getProjectList().contains(projectID);
	}
	
	/**
	 * To return the Supervisor instance based on the supervisorID
	 * @param supervisorID UserID of supervisor
	 * @return Supervisor instance
	 */
	public static Supervisor getSupervisorById(String supervisorID)
	{
		return supervisors.get(supervisorID);
	}

	/**
	 * Update the password of supervisor
     * @param supervisorID UserID of supervisor
	 */
	public static void changeSupervisorPassword(String supervisorID)
	{
		UserAccountControl.changePassword(getSupervisorById(supervisorID));
	}

	/** 
	 * Verify the username and password for supervisor
	 * @param userID The userID of supervisor
     * @return whether the verification is successful or not
	 */
	public static boolean authenticateSupervisor(String supervisorID)
    {
        return exists(supervisorID) && UserAccountControl.authenticate(getSupervisorById(supervisorID));
    }
}