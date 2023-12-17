package boundary;

import utils.MenuIO;
import controller.RequestControl;
import controller.ProjectControl;
import controller.SupervisorControl;
import controller.CoordinatorControl;
import controller.DisplayProjectControl;
import controller.DisplayRequestControl;
import controller.RequestChangeSupervisorControl;
import controller.RequestChangeTitleControl;

/**
 * Represents the menu after the Supervisor logs in.
 * @author Arjun Kumar Nair
 * @version 1.0
 * @since 23-04-13
 */
public class SupervisorViewBoundary extends UserViewBoundary {

    /**
	 * Create a view menu for Supervisor
	 */
    public SupervisorViewBoundary(String userID){
        super(
            // set user ID
            userID,
            new String[] {
                "Create Project",
                "View projects submitted by me",
                "Modify the title of a project submitted by me", 
                "View pending requests",
                "View request history",
                "Approve request",
                "Reject request",
                "Request to transfer student"
            },
            "Welcome to the FYP System!"
        );
    }

    /**
	 * Create a project and add to project list
	 */
    protected void caseCreateProject() 
    {
        String title = MenuIO.inputString("Enter project title (-1 to CANCEL)", true);
        
        if (title.equals("-1")) 
        {
            return;
        }
            ProjectControl.createProject(this.userID, title);
    }

    /**
	 * View projects submitted by supervisor
	 */
    protected void caseViewProjects()
    {
        DisplayProjectControl.generateReportBySupervisorID(this.userID);
    }

    /**
	 * Modify title of projects submitted by supervisor
	 */
    protected void caseModifyTitle()
    {
        int projectID;
        String title;
        int numProjects = ProjectControl.numProjects();

        if (numProjects == 0)
        {
            System.out.println("No existing projects");

            return;
        }

        projectID = MenuIO.inputInt(0, ProjectControl.numProjects()-1, "Select a project");

        // selected project is not submitted by this supervisor
        if (!ProjectControl.getProjectSubmitterID(projectID).equals(this.userID)) 
        {
            System.out.println("[ERROR] Selected project is not submitted by you.");

            return;
        }

        title = MenuIO.inputString("Enter new project title", true);
        ProjectControl.changeTitle(projectID, title);
    }

    /**
	 * View pending requests of supervisor
	 */
    protected void caseViewPendingRequests()
    {
        // display all pending requests where requesteeID matches current supervisor's user ID
        if (!(DisplayRequestControl.viewRequests(true, this.userID, false) && !DisplayRequestControl.viewRequests(true, this.userID, true)))
        {
            System.out.println("No requests found\n");
        };
    }

    /**
	 * View request history and status for supervisor
	 */
    protected void caseViewRequestHistory() 
    {
        // display all non-pending requests where requestorID matches supervisorID
        System.out.println();
        System.out.println("[ Outgoing request history ]");

        if (!DisplayRequestControl.viewRequests(false, this.userID, true))
        {
            System.out.println("No outgoing requests found\n");
        }

        // display all non-pending requests where requesteeID matches supervisorID
        System.out.println();
        System.out.println("[ Incoming request history ]");

        if (!DisplayRequestControl.viewRequests(false, this.userID, false))
        {
            System.out.println("No incoming requests found\n");
        }
    }

    /**
	 * Approve a pending request
	*/
    private void caseApproveRequest()
    {
        int numRequests = RequestControl.getRequestListSize();
        int requestID;

        if (numRequests == 0)
        {
            System.out.println("No existing requests");
            
            return;
        }

        requestID = MenuIO.inputInt(-1, numRequests-1, "Enter the Request ID to approve. (-1 to CANCEL)");
       
        if (requestID == -1) 
        {
            return;
        }

        RequestChangeTitleControl.attemptApprove(requestID, this.userID);
    }

    /**
	 * Reject a pending request
	*/
    private void caseRejectRequest()
    {
        int numRequests = RequestControl.getRequestListSize();
        int requestID;

        if (numRequests == 0)
        {
            System.out.println("No existing requests");
            
            return;
        }
        
        requestID = MenuIO.inputInt(-1, numRequests-1, "Enter the Request ID to reject. (-1 to CANCEL)");
       
        if (requestID == -1) 
        {
            return;
        }

        RequestChangeTitleControl.attemptReject(requestID, this.userID);
    }

    /**
	 * Send request to Coordinator to transfer a student in his supervising project to another replacement supervisor
	*/
    protected void caseTransferRequest() 
    {
        int projectID;
        String supervisorID;
        int numProjects = ProjectControl.numProjects();

        if (numProjects == 0)
        {
            System.out.println("No existing projects");
            
            return;
        }

        projectID = MenuIO.inputInt(0, numProjects-1, "Select a project");

        // check if current supervisor is supervising the project
        if (!SupervisorControl.isSupervising(this.userID, projectID))
        {
            System.out.println("[ERROR] You are not supervising this project");
            
            return;
        }

        supervisorID = MenuIO.inputString("Enter ID of supervisor to transfer student to", true).toUpperCase();

        // check if supervisor exists
        if (!SupervisorControl.exists(supervisorID)) 
        {
            System.out.println("[ERROR] Supervisor with this ID does not exist");

            return;
        }

        RequestChangeSupervisorControl.createRequest(
            this.userID, 
            CoordinatorControl.getCoordinatorId(), 
            projectID, 
            supervisorID
        );
    }
    
    /**
	 * Change user password
	 */
    protected void caseChangePassword() 
    {
        SupervisorControl.changeSupervisorPassword(this.userID);
    }

    /**
     * Run an iteration of supervisor menu, including display of options and prompting user inputs
     */
    public void runMenu() 
    {
        int choice;
        
        displayMenu();

        choice = inputMenuOption();

        switch (choice) 
        {
            // Create project one at a time
            case 1:
                caseCreateProject();
                break;

            // View information of all projects submitted by this supervisor 
            case 2:
                caseViewProjects();
                break;

            // Modify the title of a project submitted by this supervisor
            case 3:
                caseModifyTitle();
                break;
            
            // View pending requests from students
            case 4:
                caseViewPendingRequests();
                break;
            
            // View request history
            case 5:
                caseViewRequestHistory();
                break;

            // Approve request
            case 6: 
                caseApproveRequest();
                break;

            // Reject request
            case 7:
                caseRejectRequest();
                break;

            // Request to transfer student
            case 8:
                caseTransferRequest();
                break;

            // Change password
            case 9:
                caseChangePassword();
                break;
            
            // Quit
            case 10:
                quit();
        }
    }
}
