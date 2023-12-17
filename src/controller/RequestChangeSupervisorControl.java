package controller;

import entity.ChangeSupervisorRequest;
import entity.Request;

/**
 * Control class to allow FYP Coordinator to approve or reject supervisor's request to transfer student to another replacement supervisor
 * 
 * @author Lin Yung Chu
 * @version 1.0
 * @since 2023-04-13
 */
public class RequestChangeSupervisorControl extends RequestControl 
{
    /**
     * Create a Request to transfer student to another replacement supervisor
     * @param requestorID   The ID of the original supervisor
     * @param requesteeID   The ID of the FYP coordinator
     * @param projectId     The ID of the project which the original supervisor is currently supervisor
     * @param supervisorId  The ID of the replacement supervisor        
     */  
    public static void createRequest(String requestorID, String requesteeID, int projectId, String newSupervisorId) 
    {
        if (!SupervisorControl.getSupervisorById(requestorID).getProjectList().contains(projectId)) {
            System.out.println("[ERROR] Project is not currently supervised by Supervisor (YOU).");
            return;
        }
        int index = getRequestListSize();
        Request r = new ChangeSupervisorRequest(index, requestorID, requesteeID, projectId, newSupervisorId);
        addRequest(r);
    }

    /**
     * Approve the supervisor's request to change supervisor
     * @param requestID The request ID of a Request
     */
    public static void approve(int requestID) 
    {
        Request request = getRequestByID(requestID);
        
        if (!(request instanceof ChangeSupervisorRequest))
        {
            System.out.println("[ERROR] Invalid request type");
            return;
        }

        ChangeSupervisorRequest r = (ChangeSupervisorRequest) request;
        ProjectControl.transferProject(r.getProjectId(), r.getRequestorID(), r.getNewSupervisorId());

        r.setStatus("approved");
        System.out.println("Request approved\n");
    }

    /**
     * Accept a request if it is pending and valid
     * @param requestID The request ID of a Request
     * @param approverID ID of approver
     */
    public static void attemptApprove(int requestID, String approverID) 
    {
        if (isValid(requestID, approverID))
        {
            approve(requestID);
        }
    }

    /**
     * Reject a request if it is pending and valid
     * @param requestID The request ID of a Request
     * @param approverID ID of approver
     */
    public static void attemptReject(int requestID, String approverID) 
    {
        if (isValid(requestID, approverID))
        {
            reject(requestID);
        }
    }
}