package boundary;

import utils.MenuIO;
import controller.RequestControl;
import controller.CoordinatorControl;
import controller.DisplayProjectControl;
import controller.DisplayRequestControl;

/**
 * Boundary class for coordinator.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-14
 */
public class CoordinatorViewBoundary extends SupervisorViewBoundary 
{   
    /**
	 * Create a view menu for Coordinator
	 */
    public CoordinatorViewBoundary(String userID) 
    {
        super(userID);

        // prepend to SupervisorViewBoundary's menu options since coordinator menu superset of supervisor menu 
        prependMenuOptions(new String[] {
            "View all projects",
            "Generate project details report",
            
        });

        setHeader("Welcome to the Coodinator Menu!");
    }

    /**
	 * View the entire project list
	 */
    private void caseViewAllProjects()
    {
        DisplayProjectControl.generateReportAll();
    }

    /**
	 * Filter project list based on status and supervisor name
	 */
    private void caseGenerateReport()
    {
        GenerateProjectReportViewBoundary submenu = new GenerateProjectReportViewBoundary();
        submenu.runUntilComplete();
    }

    /**
	 * View all pending requests
	 */
    private void caseViewAllPendingRequests()
    {
        DisplayRequestControl.viewRequests(true, this.userID, false);
    }

    /**
	 * View all request history
	 */
    private void caseViewAllRequestHistory()
    {
        DisplayRequestControl.viewRequests(false);
    }

    /**
	 * Approve request for Coordinator
	 */
    private void caseApproveCoordinatorRequest()
    {
        int numRequests = RequestControl.getRequestListSize();
        int requestID;

        if (numRequests == 0) 
        {
            System.out.println("No requests to approve");
            return;
        }

        requestID = MenuIO.inputInt(-1, numRequests-1, "Enter the Request ID to approve. (-1 to CANCEL)");
        if (requestID == -1)
        {
            return;
        }

        CoordinatorControl.approveAny(requestID, this.userID);
    }

    /**
	 * Reject request for Coordinator
	 */
    private void caseRejectCoordinatorRequest()
    {
        int numRequests = RequestControl.getRequestListSize();
        int requestID;

        if (numRequests == 0) 
        {
            System.out.println("No requests to approve");
            return;
        }
        
        requestID = MenuIO.inputInt(-1, numRequests-1, "Enter the Request ID to reject. (-1 to CANCEL)");
        if (requestID == -1)
        {
            return;
        }

        CoordinatorControl.rejectAny(requestID, this.userID);
    }

    /**
     * Run an iteration of coordinator menu, including display of options and prompting user inputs
     */
    public void runMenu() 
    {
        int choice;
        
        displayMenu();

        choice = inputMenuOption();
        
        switch (choice) 
        {
            // View all projects
            case 1:
                caseViewAllProjects();
                break;

            // Generate project details report
            case 2:
                caseGenerateReport();
                break;

            // Create Project
            case 3: 
                caseCreateProject();
                break;

            // View projects submitted by coordinator
            case 4:
                caseViewProjects();
                break;

            // Modify title of project
            case 5:
                caseModifyTitle();
                break;

            // View pending requests
            case 6:
                caseViewAllPendingRequests();
                break;

            // View request history
            case 7:
                caseViewAllRequestHistory();
                break;

            // Approve request
            case 8: 
                caseApproveCoordinatorRequest();
                break;

            // Reject request
            case 9:
                caseRejectCoordinatorRequest();
                break;

            // Transfer current student under me to another Supervisor
            case 10:
                caseTransferRequest();
                break;
            // Change password
            case 11:
                caseChangePassword();
                break;
            
            // Quit
            case 12:
                quit();
        }
    }
}
