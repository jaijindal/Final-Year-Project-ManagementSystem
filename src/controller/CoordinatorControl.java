package controller;

import entity.Supervisor;
import entity.Request;
import entity.ChangeSupervisorRequest;
import entity.ChangeTitleRequest;
import entity.DeregisterProjectRequest;
import entity.RegisterProjectRequest;

/**
 * Control class to allow FYP coordinator to carry out his/her various functions
 * @author Arjun Kumar Nair
 * @version 1.0
 * @since 2023-04-12
 */
public class CoordinatorControl extends SupervisorControl
{
    /**
	 * The hashmap will store the Coordinators's user id as the key and the Coordinator object as the value
	 */
	private static Supervisor coordinator;

    /** 
	 * Return Coordinator
	 * @return Coordinator instance
	 */
  	public static Supervisor getCoordinator() 
    {
        return coordinator;
    }

    /** 
	 * Return Coordinator user ID
	 * @return Coordinator ID
	 */
    public static String getCoordinatorId() 
    {
        return coordinator.getUserId();
    }

    /** 
	 * Return Coordinator name
	 * @return Coordinator name
	 */
    public static String getCoordinatorName() 
    {
        return coordinator.getName();
    }

    /** 
	 * Set one of the supervisors as the coordinator (Assume only one coordinator at a time)
	 * @param supervisor supervisor object
	 */
	public static void setCoordinator(Supervisor supervisor) 
    {
		coordinator = supervisor;
	}

    /** 
	 * Verify the username and password for coordinator
	 * @param userID The userID of coordinator
     * @return whether the verification is successful or not
	 */
    public static boolean authenticateCoordinator(String userID)
    {   
        return UserAccountControl.authenticate(getCoordinator()) && getCoordinatorId().equals(userID);
    }

    /** 
	 * Approve a pending request
	 * @param requestID The id of request
     * @param userID The userID of coordinator
	 */
    public static void approveAny(int requestID, String userID)
    {
        Request request = RequestControl.getRequestByID(requestID);

        if (request instanceof ChangeTitleRequest)
        {
            RequestChangeTitleControl.attemptApprove(requestID, userID);
        }
        else if (request instanceof ChangeSupervisorRequest)
        {
            RequestChangeSupervisorControl.attemptApprove(requestID, userID);
        }
        else if (request instanceof RegisterProjectRequest)
        {
            RequestRegisterControl.attemptApprove(requestID, userID);
        }
        else if (request instanceof DeregisterProjectRequest)    
        {
            RequestDeregisterControl.attemptApprove(requestID, userID);
        }
    }

    /** 
	 * Reject a pending request
	 * @param requestID The id of request
     * @param userID The userID of coordinator
	 */
    public static void rejectAny(int requestID, String userID)
    {
        Request request = RequestControl.getRequestByID(requestID);

        if (request instanceof ChangeTitleRequest)
        {
            RequestChangeTitleControl.attemptReject(requestID, userID);
        }
        else if (request instanceof ChangeSupervisorRequest)
        {
            RequestChangeSupervisorControl.attemptReject(requestID, userID);
        }
        else if (request instanceof RegisterProjectRequest)
        {
            RequestRegisterControl.attemptReject(requestID, userID);
        }
        else if (request instanceof DeregisterProjectRequest)    
        {
            RequestDeregisterControl.attemptReject(requestID, userID);
        }
    }
}
