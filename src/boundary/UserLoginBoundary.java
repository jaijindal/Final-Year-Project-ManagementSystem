package boundary;

import utils.MenuIO;
import controller.StudentControl;
import controller.SupervisorControl;
import controller.CoordinatorControl;


/**
 * Boundary class for user login CLI
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-11
 */
public class UserLoginBoundary extends ViewBoundary 
{
    public UserLoginBoundary() 
    {
        super(
            // set menu options
            new String[] {
                "Student",
                "Supervisor",
                "FYP Coordinator",
                "Quit"
            },
            // set header
            "Login as:"
        );
    }

    /**
     * Run an iteration of login menu, including display of options and prompting user inputs
     */
    public void runMenu() 
    {
        int choice;
        String userID = "";

        System.out.println(".__                .__ ");
        System.out.println("|  |   ____   ____ |__| ____");
        System.out.println("|  |  /  _ \\ / ___\\|  |/    \\");
        System.out.println("|  |_(  <_> ) /_/  >  |   |  \\");
        System.out.println("|____/\\____/\\___  /|__|___|  /");
        System.out.println("           /_____/         \\/");
        System.out.println();

        displayMenu();

        choice = inputMenuOption();

        if (choice != 4) 
        {
            userID = MenuIO.inputString("Enter user ID", true).toUpperCase();
        }

        // attempt to login as per selection of user role, or quit
        switch (choice) 
        {
            case 1:
                caseLoginAsStudent(userID);
                break;
            case 2:
                caseLoginAsSupervisor(userID);
                break;
            case 3:
                caseLoginAsCoordinator(userID);
                break;
            case 4:
                quit();
        }
    }

    /**
     * Attempt to login as a student given a userID
     * @param userID User ID given by user
     */
    private void caseLoginAsStudent(String userID) 
    {
        if (StudentControl.authenticateStudent(userID)) 
        {
            // navigate to nested boundary and run until quit
            StudentViewBoundary studentMenu = new StudentViewBoundary(userID); 
            studentMenu.runUntilComplete();
        }
        else
        {
            System.out.println("[ERROR] Authentication failed\n");
        }
    }

    /**
     * Attempt to login as a supervisor given a userID
     * @param userID User ID given by user
     */
    private void caseLoginAsSupervisor(String userID) 
    {
        if (SupervisorControl.authenticateSupervisor(userID)) 
        {
            // navigate to nested boundary and run until quit
            SupervisorViewBoundary supervisorMenu = new SupervisorViewBoundary(userID); 
            supervisorMenu.runUntilComplete();
        }
        else
        {
            System.out.println("[ERROR] Authentication failed\n");
        }
    }

    /**
     * Attempt to login as a coordinator given a userID
     * @param userID User ID given by user
     */
    private void caseLoginAsCoordinator(String userID) 
    {
        if (CoordinatorControl.authenticateCoordinator(userID))
        {
            // navigate to nested boundary and run until quit
            CoordinatorViewBoundary coordinatorMenu = new CoordinatorViewBoundary(userID); 
            coordinatorMenu.runUntilComplete();
        }
        else
        {
            System.out.println("[ERROR] Authentication failed\n");
        }
    }
}