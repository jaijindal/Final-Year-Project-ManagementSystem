package controller;

import entity.DeregisterProjectRequest;
import entity.RegisterProjectRequest;
import entity.Request;
import entity.Project;
import entity.Supervisor;
import entity.Student;

/**
 * Control class to allow FYP Coordinator to approve request and deregisterproject
 * 
 * @author Jindal Jai
 * @version 1.0
 * @since 2023-04-13
 */
public class RequestDeregisterControl extends RequestControl 
{
    /**
     * Create a Request to deregister a project.
     * @param requestorID The ID of the student requesting to deregister.
     * @param requesteeID The ID of the FYP coordinator receiving the request.
     * @param projectId   The ID of the project the student is requesting to deregister from.               
     */
    public static void createRequest(String requestorID, String requesteeID, int projectId) 
    {
        int index = getRequestListSize();
        Request r = new DeregisterProjectRequest(index, requestorID, requesteeID, projectId);
        addRequest(r);
    }

    /**
     * Approve the specified request to deregister a project.
     * @param requestID The ID of the request to be approved.
     */
    public static void approve(int requestID) 
    {
        Request request = getRequestByID(requestID);
        int projectID = request.getProjectId();
        String studentID = request.getRequestorID();
        Project project = ProjectControl.getProjectByID(projectID);
        Supervisor supervisor;
        boolean wasAvailable;
        Student student;

        if (!(request instanceof DeregisterProjectRequest))
        {
            System.out.println("[ERROR] Invalid request type");

            return;
        }
        
        supervisor = SupervisorControl.getSupervisorById(project.getSupervisorId());
        wasAvailable = supervisor.isAvailable();
        student = StudentControl.getStudentById(project.getStudentId());

        // Student add to blacklist and removed from supervisor's project list
        if (project.getStatus().equals("allocated")) 
        {
            project.addNewBlacklist(project.getStudentId());
            project.setStudentId("");
            supervisor.unsuperviseProject(projectID);
            project.setStatus("available");

            student.setRegisteredProject(-1);
        }

        // crossed back down the threshold
        if (supervisor.isAvailable() && !wasAvailable) 
        {
            for (int pid: supervisor.getCreatedList())
            {
                project = ProjectControl.getProjectByID(pid);

                // was originally available, but made unavailable when supervisor exceeded max project cap
                if (project.getStatus().equals("unavailable") && project.getStudentId().equals("")) 
                {
                    project.setStatus("available");
                }
            }
        }

        // for all requests pertaining to this project associated with this student, automatically reject them
        for (Request otherRequest: RequestControl.getRequestList())
        {
            if (
                otherRequest.getStatus().equals("pending")
                && otherRequest.getProjectId() == projectID
                && (
                    otherRequest.getRequestorID().equals(studentID) 
                    || otherRequest.getRequesteeID().equals(studentID)
                )
            ) 
            {
                requestID = otherRequest.getRequestID();

                if (otherRequest instanceof RegisterProjectRequest) 
                {
                    // only RequestRegisterControl uses method-hiding for reject()
                    RequestRegisterControl.reject(requestID);
                }
                else
                {
                    RequestControl.reject(requestID);
                }
            }
        }
        
        request.setStatus("approved");

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
