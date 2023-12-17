package controller;


import entity.Project;

/**
 * Control class to display projects
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-12
 */
public class DisplayProjectControl 
{
    private static void displayHeader() 
    {
        System.out.println();
        System.out.println("__________                      __                   __           ");
        System.out.println("\\______   \\_______   ____      |__|  ____    ____  _/  |_   ______");
        System.out.println(" |     ___/\\_  __ \\ /  _ \\     |  |_/ __ \\ _/ ___\\ \\   __\\ /  ___/");
        System.out.println(" |    |     |  | \\/(  <_> )    |  |\\  ___/ \\  \\___  |  |   \\___ \\ ");
        System.out.println(" |____|     |__|    \\____/ /\\__|  | \\___  > \\___  > |__|  /____  >");
        System.out.println("                           \\______|     \\/      \\/             \\/ ");
        System.out.println();
    }

    /** 
     * Display the project info of a specific Project instance, namely 
     * project ID, project title, supervisor name, supervisor email, and status 
     * @param project Project instance of an project 
     */
    public static void displayProjectInfo(Project project) 
    {
        int projectID = project.getProjectId();
        String header = String.valueOf(projectID) + ") " + project.getProjectTitle();

        System.out.println();
        System.out.println("+" + "-".repeat(header.length()+6) + "+");
        System.out.println("|   " + header + "   |");
        System.out.println("+" + "-".repeat(header.length()+6) + "+");
        System.out.println("[ Supervisor Name  ] " + ProjectControl.getSupervisorNameByProjectID(projectID));
        System.out.println("[ Supervisor Email ] " + ProjectControl.getSupervisorEmailByProjectID(projectID));
        System.out.println("[ Student Name     ] " + ProjectControl.getStudentNameByProjectID(projectID));
        System.out.println("[ Student Email    ] " + ProjectControl.getStudentEmailByProjectID(projectID));
        System.out.println("[ Status           ] " + project.getStatus());
        System.out.println();
        System.out.println();
    }

    /**
     * If student has not registered for a project, view all projects available to the student 
     * @param student Student instance requesting to view
     * @param projects ArrayList of Project instances to filter and display project information
     */
    public static void viewAvailableProjects(String studentID) 
    {
        boolean found = false;

        displayHeader();

        for (Project project: ProjectControl.getProjectList()) 
        {   
            if (SupervisorControl.getSupervisorById(project.getSupervisorId()).isAvailable()) { // check if cap on supervising project is reached
                if (!project.isBlacklisted(studentID) // if student is not blacklisted
                && project.getStatus().equals("available"))
                {
                    found = true;
                    displayProjectInfo(project); 
                }
            }
        }

        if (!found) 
        {
            System.out.println("No projects found");
        }
    }

    /**
     * Generate report for all projects
     */
    public static void generateReportAll() 
    {
        boolean found = false;

        displayHeader();

        for (Project project : ProjectControl.getProjectList()) 
        {
            found = true;
            displayProjectInfo(project);
        }

        if (!found) 
        {
            System.out.println("No projects found");
        }
    }

    /**
     * Filter projects by status and display projects that satisfy the filter
     * @param String Status to filter the projectlist
     */
    public static void generateReportByStatus(String status) 
    {
        boolean found = false;

        displayHeader();
        
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (project.getStatus().equals(status)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found) 
        {
            System.out.println("No projects found with status: " + status);
        }
    }

    /**
     * Filter projects by supervisor name and display projects that satisfy the filter
     * @param String supervisorName to filter the projects
     */
    public static void generateReportBySupervisorName(String supervisorName) 
    {
        boolean found = false;

        displayHeader();
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (ProjectControl.getSupervisorNameByProjectID(project.getProjectId()).equals(supervisorName)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found)
        {
            System.out.println("No projects found with supervisor name: " + supervisorName);
        }
    }

    /**
     * Filter projects by supervisor ID and display projects that satisfy the filter
     * @param String supervisorID to filter the projects
     */
    public static void generateReportBySupervisorID(String supervisorID) 
    {
        boolean found = false;

        displayHeader();
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (ProjectControl.getSupervisorIDByProjectID(project.getProjectId()).equals(supervisorID)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found)
        {
            System.out.println("No projects found with supervisor ID: " + supervisorID);
        }
    }

    /**
     * Filter projects by supervisor email and display projects that satisfy the filter
     * @param String supervisorEmail to filter the projects
     */
    public static void generateReportBySupervisorEmail(String supervisorEmail) 
    {
        boolean found = false;

        displayHeader();
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (ProjectControl.getSupervisorEmailByProjectID(project.getProjectId()).equals(supervisorEmail)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found)
        {
            System.out.println("No projects found with supervisor email: " + supervisorEmail);
        }
    }

    /**
     * Filter projects by student name and display projects that satisfy the filter
     * @param String studentName to filter the projects
     */
    public static void generateReportByStudentName(String studentName) 
    {
        boolean found = false;

        displayHeader();
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (ProjectControl.getStudentNameByProjectID(project.getProjectId()).equals(studentName)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found)
        {
            System.out.println("No projects found with student name: " + studentName);
        }
    }

     /**
     * Filter projects by student ID and display projects that satisfy the filter
     * @param String supervisorID to filter the projects
     */
    public static void generateReportByStudentID(String studentID) 
    {
        boolean found = false;

        displayHeader();
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (ProjectControl.getStudentIDByProjectID(project.getProjectId()).equals(studentID)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found)
        {
            System.out.println("No projects found with student ID: " + studentID);
        }
    }

    /**
     * Filter projects by student email and display projects that satisfy the filter
     * @param String studentEmail to filter the projects
     */
    public static void generateReportByStudentEmail(String studentEmail) 
    {
        boolean found = false;

        displayHeader();
        for (Project project : ProjectControl.getProjectList()) 
        {
            if (ProjectControl.getStudentEmailByProjectID(project.getProjectId()).equals(studentEmail)) 
            {
                found = true;
                displayProjectInfo(project);
            }
        }

        if (!found)
        {
            System.out.println("No projects found with student email: " + studentEmail);
        }
    }
    
}