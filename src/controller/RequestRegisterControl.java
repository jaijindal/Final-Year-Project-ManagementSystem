package controller;

import entity.RegisterProjectRequest;
import entity.Request;
import entity.Project;
import entity.Supervisor;

/**
 * Control class to allow sudent to register for a project.
 * 
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-13
 */
public class RequestRegisterControl extends RequestControl 
{
    /**
     * Create a Request to register a project for the student
     * @param requestorID   The ID of the student requesting to deregister.
     * @param requesteeID   The ID of the supervisor receiving the request.
     * @param projectId     The ID of the project which the student is requesting to change title            
     */
    public static void createRequest(String requestorID, String requesteeID, int projectID) 
    {
        Request newRequest;
        Project project = ProjectControl.getProjectByID(projectID);

        if (project.getStatus() != "available") 
        {
            System.out.println("Project is: " + project.getStatus());

            return;
        }

        ProjectControl.reserveProject(projectID);

        newRequest = new RegisterProjectRequest(getRequestListSize(), requestorID, requesteeID, projectID);
        addRequest(newRequest);
    }

    /**
     * Approve the student's request to register for a project
     * @param requestID The request ID of a Request
     */
    public static void approve(int requestID) 
    {
        
        Request request = getRequestByID(requestID);
        // verify before downcast
        if (!(request instanceof RegisterProjectRequest))
        {
            System.out.println("[ERROR] Invalid request type");
            return;
        }

        RegisterProjectRequest r = (RegisterProjectRequest) getRequestByID(requestID);
        Project project = ProjectControl.getProjectByID(r.getProjectId());
        Supervisor supervisor = SupervisorControl.getSupervisorById(project.getSupervisorId());
        
        ProjectControl.registerProject(r.getRequestorID(), r.getProjectId());
        System.out.println(supervisor.isAvailable() + " | " + supervisor.getNoOfProjects());

        r.setStatus("approved");
        System.out.println("Request approved\n");
    }

    /**
     * Reject the student's request to register for a project
     * @param requestID The request ID of a Request
     */
    public static void reject(int requestID)
    {
        RegisterProjectRequest r = (RegisterProjectRequest) getRequestByID(requestID);
        int projectID = r.getProjectId();
        ProjectControl.unreserveProject(projectID);
        r.setStatus("rejected");
    }

    /**
     * Accept a request if it is pending and valid
     * @param requestID The request ID of a Request
     * @param approverID ID of approver
     */
    public static void attemptApprove(int requestID, String approverID) 
    {
        if (isValid(requestID, approverID)) approve(requestID);
    }

    /**
     * Reject a request if it is pending and valid
     * @param requestID The request ID of a Request
     * @param approverID ID of approver
     */
    public static void attemptReject(int requestID, String approverID) 
    {
        if (isValid(requestID, approverID)) reject(requestID);
    }
}