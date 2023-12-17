package controller;

import entity.ChangeSupervisorRequest;
import entity.ChangeTitleRequest;
import entity.DeregisterProjectRequest;
import entity.RegisterProjectRequest;
import entity.Request;
import entity.Supervisor;
import entity.Project;
import entity.User;

/**
 * Control class to display requests
 * @author Lin Yung Chu
 * @version 1.0
 * @since 2023-04-14
 */
public class DisplayRequestControl 
{
    /** 
     * Get the user object of requestor/requestee
     * @param userId The id of the user
     * @param role The role of user (e.g. student/supervisor)
     * @return The user object
     */
    private static User getUser(String userId, String role) {
        User user;
        if (role.equals("Student")) {
            user = StudentControl.getStudentById(userId);
        } else {
            user = SupervisorControl.getSupervisorById(userId);
        }
        return user;
    }

    /** 
     * Print out the details of a particular request
     * @param request The request object
     */
    public static void viewRequest(Request request) 
    {
        User requestor = getUser(request.getRequestorID(), request.getRequestorRole());
        User requestee = getUser(request.getRequesteeID(), request.getRequesteeRole());
        String pending = request.getStatus() == "pending" ? "(NEW)" : "";

        System.out.println();

        // Handle output for different request types
        if (request instanceof ChangeTitleRequest) 
        {
            ChangeTitleRequest r = (ChangeTitleRequest) request;

            System.out.println(".__________________________.");
            System.out.println("|                          |");
            System.out.println("|   Change title request   |");
            System.out.println(".__________________________.");
            System.out.println();
    
            System.out.println(String.format("[ ID %-5s          ] ", pending) + request.getRequestID());
            System.out.println("[ Old Project Title ] " + r.getOldProjectTitle());
            System.out.println("[ New Project Title ] " + r.getProjectTitle());
        } 
        else if (request instanceof ChangeSupervisorRequest) 
        {
            ChangeSupervisorRequest r = (ChangeSupervisorRequest) request;
            Supervisor replacementSupervisor = (Supervisor) getUser(r.getNewSupervisorId(), "Supervisor");
            int projectCount = replacementSupervisor.getNoOfProjects();

            System.out.println("._______________________________.");
            System.out.println("|                               |");
            System.out.println("|   Change supervisor request   |");
            System.out.println("._______________________________.");
            System.out.println();
                                
            System.out.println(String.format("[ ID %-5s          ] ", pending) + request.getRequestID());
            System.out.println("[ Replacement Name  ] " + replacementSupervisor.getName());
            System.out.println("[ Replacement Email ] " + replacementSupervisor.getEmailAddress());
            System.out.println();
            System.out.println("Current Supervising Project Count: " + projectCount);
            
            if (projectCount >= 2) System.out.println("TAKE NOTE: Replacement Supervisor has already hit the project supervising cap!");
            
            System.out.println();
        } 
        else if (request instanceof RegisterProjectRequest) 
        {
            RegisterProjectRequest r = (RegisterProjectRequest) request;
            Project p = ProjectControl.getProjectByID(r.getProjectId());
            Supervisor s = SupervisorControl.getSupervisorById(p.getSupervisorId());
            int projectCount = s.getNoOfProjects();

            System.out.println(".______________________________.");
            System.out.println("|                              |");
            System.out.println("|   Register project request   |");
            System.out.println(".______________________________.");
            System.out.println();

            System.out.println(String.format("[ ID %-5s          ] ", pending) + request.getRequestID());
            System.out.println("[ Project Title     ] " + p.getProjectTitle());
            System.out.println("Current Supervising Project Count: " + s.getNoOfProjects());
            if (projectCount >= 2) System.out.println("TAKE NOTE: Supervisor has already hit the project supervising cap!");
        } 
        else if (request instanceof DeregisterProjectRequest) 
        {
            DeregisterProjectRequest r = (DeregisterProjectRequest) request;

            System.out.println(".________________________________.");
            System.out.println("|                                |");
            System.out.println("|   Deregister project request   |");
            System.out.println(".________________________________.");
            System.out.println();

            System.out.println(String.format("[ ID %-5s          ] ", pending) + request.getRequestID());
            System.out.println("[ Project Title     ] " + ProjectControl.getProjectByID(r.getProjectId()).getProjectTitle());
        }

        // Print out basic request details
        System.out.println("[ Requestor Name    ] " + requestor.getName());
        System.out.println("[ Requestor Email   ] " + requestor.getEmailAddress());
        System.out.println("[ Requestee Name    ] " + requestee.getName());
        System.out.println("[ Requestee Email   ] " + requestee.getEmailAddress());
        System.out.println("[ Status            ] " + request.getStatus());
        System.out.println();
    }

    public static boolean viewRequests(boolean pending) 
    {
        boolean found = false;

        for (Request request: RequestControl.getRequestList()) 
        {
            // show only pending requests if pending, and vice versa
            if ((request.getStatus().equals("pending")) == pending) 
            {
                found = true;
                viewRequest(request);
            }
        }

        return found;
    }

    /**
     * @param pending View all pending requests that satisfy requestor / requestee condition
     * @param userID userID of either requestor or requestee
     * @param requestor true if userID is for requestor, otherwise for requestee
     */
    public static boolean viewRequests(boolean pending, String userID, boolean requestor) 
    {
        boolean found = false;

        for (Request request: RequestControl.getRequestList()) 
        {
            // show only pending requests if pending, and vice versa
            if (
                (request.getStatus().equals("pending")) == pending
                && (requestor ? request.getRequestorID().equals(userID) : request.getRequesteeID().equals(userID))
            )
            {
                found = true;
                viewRequest(request);
            }
        }

        return found;
    }
}