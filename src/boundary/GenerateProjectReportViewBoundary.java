package boundary;

import utils.MenuIO;
import controller.DisplayProjectControl;

/**
 * Boundary class to generate project report.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-14
 */
public class GenerateProjectReportViewBoundary extends ViewBoundary 
{
    public GenerateProjectReportViewBoundary() 
    {
        super(new String[] {
            "Project status",
            "Supervisor name",
            "Back"
            },
            "Search filters"
        );
    }

    public void runMenu()
    {
        String filter = "";
        int choice;
        
        displayMenu();

        choice = inputMenuOption();
        if (choice != 3) 
        {
            filter = MenuIO.inputString("Filter value", true);
        }
        
        switch (choice) 
        {
            // filter by project status
            case 1:
                DisplayProjectControl.generateReportByStatus(filter);
                break;

            // filter by supervisor name
            case 2:
                DisplayProjectControl.generateReportBySupervisorName(filter.toUpperCase());
                break;

            // quit
            case 3:
                quit();
        }
    }    
}