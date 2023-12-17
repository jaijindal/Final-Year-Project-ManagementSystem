package controller;

import entity.ChangeTitleRequest;
import entity.Request;

/**
 * Control class to allow supervisor to approve or reject student's request to change title of project
 * 
 * @author Lin Yung Chu
 * @version 1.0
 * @since 2023-04-13
 */
public class RequestChangeTitleControl extends RequestControl 
{
    /**
     * Create a Request to change the title of a project.
     * @param requestorID   The ID of the student requesting to deregister.
     * @param requesteeID   The ID of the supervisor receiving the request.
     * @param projectId     The ID of the project which the student is requesting to change title
     * @param projectTitle  The new title of the project the student is requesting to change to             
     */
    public static void createRequest(String requestorID, String requesteeID, int projectId, String projectTitle) 
    {
        int index = getRequestListSize();
        Request r = new ChangeTitleRequest(index, requestorID, requesteeID, projectId, projectTitle);
        addRequest(r);
    }

    /**
     * Approve the specified student's request to change title
     * @param requestID The request ID of a Request
     */
    public static void approve(int requestID) 
    {
        Request request = getRequestByID(requestID);
        
        if (!(request instanceof ChangeTitleRequest))
        {
            System.out.println("[ERROR] Invalid request type");
            return;
        }

        ChangeTitleRequest changeTitleRequest = (ChangeTitleRequest) getRequestByID(requestID); // downcast

        ProjectControl.changeTitle(changeTitleRequest.getProjectId(), changeTitleRequest.getProjectTitle());
        changeTitleRequest.setStatus("approved");

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
