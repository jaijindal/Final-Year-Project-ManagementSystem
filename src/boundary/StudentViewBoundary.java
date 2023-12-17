package boundary;

import utils.MenuIO;
import controller.DisplayProjectControl;
import controller.DisplayRequestControl;
import controller.RequestChangeTitleControl;
import controller.RequestRegisterControl;
import controller.RequestDeregisterControl;
import controller.StudentControl;
import controller.ProjectControl;
import controller.CoordinatorControl;


/**
 * Display the menu after a student logs in.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-12
 */
public class StudentViewBoundary extends UserViewBoundary 
{
    /**
	 * Create a view menu for Student
	 */
    public StudentViewBoundary(String userID) 
    {
        super(
            // set user ID
            userID,
            // set menu options
            new String[] {
                "View all available projects",
                "Register for a project",
                "View registered project",
                "Request to change title",
                "Deregister project",
                "View requests",
            },
            // set header
            "Welcome to the FYP System!"
        );
    }

    /**
     * Verify that student has already registered for a project
     */
    private boolean verifyRegistered()
    {
        if (StudentControl.getStudentRegisteredProject(this.userID) == -1) 
        {
            System.out.println("[WARNING] You have not registered for a project.");

            return false;
        } 
        return true;
    }

    /**
     * Verify that student has not registered for a project
     */
    private boolean verifyUnregistered()
    {
        if (StudentControl.getStudentRegisteredProject(this.userID) == -1) 
        {
            return true;
        }
        System.out.println("[WARNING] You have already registered for a project.");

        return false; 
    }

    /**
	 * View all projects that are available
	 */
    private void caseViewProjects() 
    {
        if (verifyUnregistered()) 
        { 
            DisplayProjectControl.viewAvailableProjects(this.userID);
        }
    }

    /**
	 * Send request to Coordinator to register for a project
	 */
    private void caseRegisterProject() 
    {
        int projectID;
        int numProjects = ProjectControl.numProjects();

        if (numProjects == 0)
        {
            System.out.println("No existing projects");
        }
        else if (verifyUnregistered())
        {
            projectID = MenuIO.inputInt(-1, numProjects-1, "Select a project (-1 to CANCEL)");
            if (projectID == -1) 
            {
                return;
            }
            RequestRegisterControl.createRequest(this.userID, CoordinatorControl.getCoordinatorId(), projectID);
            System.out.println("[SUCCESS] Register request sent\n");
        }
    }

    /**
	 * Display registered project
	 */
    private void caseViewRegistered()
    {
        if (verifyRegistered()) 
        {
            int projectID = StudentControl.getStudentRegisteredProject(this.userID);

            DisplayProjectControl.displayProjectInfo(ProjectControl.getProjectByID(projectID));
        }
    }

    /**
	 * Send request to Supervisor to change title of project
	 */
    private void caseRequestChangeTitle()
    {
        int projectID;
        String supervisorID;
        String newTitle;

        if (verifyRegistered()) 
        {
            projectID = StudentControl.getStudentRegisteredProject(this.userID);
            supervisorID = ProjectControl.getProjectByID(projectID).getSupervisorId();
            newTitle = MenuIO.inputString("Proposed title", true);

            RequestChangeTitleControl.createRequest(
                this.userID,
                supervisorID, 
                projectID, 
                newTitle
            );
        }
    }

    /**
	 * Send request to Coordinator to deregister FYP
	 */
    private void caseDeregisterProject()
    {
        int projectID;

        if (verifyRegistered())
        {
            projectID = StudentControl.getStudentRegisteredProject(this.userID);
    
            // verify identity before deregister
            if (StudentControl.authenticateStudent(this.userID)) 
            {
                RequestDeregisterControl.createRequest(
                    this.userID, 
                    CoordinatorControl.getCoordinatorId(), 
                    projectID
                );
            }
        }
    }

    /**
	 * View all requests of student
	 */
    private void caseViewRequests()
    {
        boolean found = false;

        if (DisplayRequestControl.viewRequests(true, this.userID, true)) // view pending
        {
            found = true;
        }

        if (DisplayRequestControl.viewRequests(false, this.userID, true)) // view history
        {
            found = true;
        }

        if (!found)
        {
            System.out.println("No requests found\n");
        }
    }

    /**
	 * Change user password
	 */
    protected void caseChangePassword() 
    {
        StudentControl.changeStudentPassword(this.userID);
    }
    
    /**
     * Run an iteration of student menu, including display of options and prompting user inputs
     */
    public void runMenu() 
    {
        int choice;
        
        displayMenu();

        choice = inputMenuOption();

        // Switch statement to call relevant controllers
        switch (choice) 
        {
            // View all available projects
            case 1: 
                caseViewProjects();
                break; 

            // Register for a project
            case 2: 
                caseRegisterProject();
                break;
            
            // View registered project
            case 3: 
                caseViewRegistered();
                break;

            // Request to change title
            case 4: 
                caseRequestChangeTitle();
                break;

            // Deregister project
            case 5:
                caseDeregisterProject();
                break;

            // View requests
            case 6:
                caseViewRequests();
                break;

            // Change password
            case 7:
                caseChangePassword();
                break;

            // Log out
            case 8:
                quit();
        }
    }
}
