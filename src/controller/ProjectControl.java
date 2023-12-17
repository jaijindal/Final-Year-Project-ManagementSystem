package controller;

import entity.Student;
import entity.Project;
import entity.Supervisor;
import java.util.ArrayList;


/**
 * Control class for various functions/methods related with projects
 * 
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-13
 */
public class ProjectControl 
{
    /**
     * The list of ALL projects
     */
    private static ArrayList<Project> projectList = new ArrayList<Project>();

    /**
     * Get the entire list of projects
     * @return this list of Projects
     */
    public static ArrayList<Project> getProjectList() 
    {
        return projectList;
    }

    /**
     * Get the specific project object
     * @param projectID The project ID of an project
     * @return a specific project
     */
    public static Project getProjectByID(int projectID) 
    {
        return projectList.get(projectID);
    }

    /**
     * Get the name of supervisor of a project
     * @param projectID The project ID of an project
     * @return supervisor's name
     */
    public static String getSupervisorNameByProjectID(int projectID) 
    {
        String supervisorID = getProjectByID(projectID).getSupervisorId();

        return SupervisorControl.getSupervisorById(supervisorID).getName();
    }

    /**
     * Get the email of supervisor of a project
     * @param projectID The project ID of an project
     * @return supervisor's email
     */
    public static String getSupervisorEmailByProjectID(int projectID) 
    {
        String supervisorID = getProjectByID(projectID).getSupervisorId();

        return SupervisorControl.getSupervisorById(supervisorID).getEmailAddress();
    }

     /**
     * Get the ID of supervisor of a project
     * @param projectID The project ID of an project
     * @return supervisor's ID
     */
    public static String getSupervisorIDByProjectID(int projectID) 
    {
        String supervisorID = getProjectByID(projectID).getSupervisorId();

        return supervisorID;
    }

    /**
     * Get the name of student of a project
     * @param projectID The project ID of an project
     * @return student's name
     */
    public static String getStudentNameByProjectID(int projectID) 
    {
        String studentID = getProjectByID(projectID).getStudentId();

        return studentID.isEmpty() ? "<Unassigned>" : StudentControl.getStudentById(studentID).getName();
    }

    /**
     * Get the ID of student of a project
     * @param projectID The project ID of an project
     * @return student's ID
     */
    public static String getStudentIDByProjectID(int projectID) 
    {
        String studentID = getProjectByID(projectID).getStudentId();

        return studentID;
    }

    /**
     * Get the name of student of a project
     * @param projectID The project ID of an project
     * @return student's email
     */
    public static String getStudentEmailByProjectID(int projectID) 
    {
        String studentID = getProjectByID(projectID).getStudentId();

        return studentID.isEmpty() ? "<Unassigned>" : StudentControl.getStudentById(studentID).getEmailAddress();
    }

    /**
     * Create a new Project with supervisor name and email, and project title,
     * Then add to projectList.
     * The projectID will be auto generated sequential index.
     * @param supervisorID The supervisor ID of supervisor
     * @param projectTitle The title of project
     */
    public static void createProject(String supervisorID, String projectTitle) 
    {
        int index = projectList.size();
        Supervisor supervisor = SupervisorControl.getSupervisorById(supervisorID);
        Project project = new Project(index, projectTitle, supervisorID);

        projectList.add(project);
        supervisor.addToCreated(index);
    }

    /**
     * Update the title of a Project
     * @param projectID     The project ID of a Project
     * @param projectTitle  The new title of a Project
     */
    public static void changeTitle(int projectID, String projectTitle) 
    {
        Project p = projectList.get(projectID);
        p.setProjectTitle(projectTitle);
    }
    
    /**
     * The project is reserved to a student after request.
     * Status of project is converted to "reserved"
     * @param projectID The project ID of a Project
     */
    public static void reserveProject(int projectID) 
    {
        Project p = projectList.get(projectID);
        p.setStatus("reserved");
    } 

    /**
     * Register project request is rejected by coordinated
     * Status of project is reverted back to "available"
     * @param projectID The project ID of a Project
     */
    public static void unreserveProject(int projectID) 
    {
        Project p = projectList.get(projectID);
        p.setStatus("available");
    }  

    /**
     * Register a student for a project
     * Update the studentId and status of Project
     * Add registered project to Student object
     * Add supervised project to Supervisor object
     * @param studentID User ID of student requesting for project registration
     * @param projectID Project ID of requested project
     */
    public static void registerProject(String studentID, int projectID) 
    {
        Project project = projectList.get(projectID);
        Supervisor supervisor = SupervisorControl.getSupervisorById(project.getSupervisorId());
        Student student = StudentControl.getStudentById(studentID);
        project.setStudentId(studentID);
        project.setStatus("allocated");
        student.setRegisteredProject(projectID);
        supervisor.superviseProject(projectID);
    }

    /**
     * The project is deallocated from the student.
     * The studentID is removed from the project and added to blacklist
     * The status of the project is changed from "allocated" to "available".
     * Remove registered project from Student object
     * Remove supervised project from Supervisor object
     * @param projectID The project ID of a Project
     */
    public static void deregisterProject(int projectID) 
    {
        Project p = projectList.get(projectID);
        Student student = StudentControl.getStudentById(p.getStudentId());
        Supervisor supervisor = SupervisorControl.getSupervisorById(p.getSupervisorId());

        // Student add to blacklist and removed from supervisor's project list
        if (p.getStatus() == "allocated") 
        {
            p.addNewBlacklist(p.getStudentId());
            p.setStudentId("");
            p.setStatus("available");
            
            student.setRegisteredProject(-1);
            supervisor.unsuperviseProject(projectID);
        }
    }

    /**
     * Transfer student to from one supervisor to another replacement supervisor
     * Remove supervised project from old supervisor
     * Add supervised project to new supervisor
     * Update the supervisor ID of project
     * @param projectID     The project ID of a Project
     * @param oldSupervisorID  The ID of original supervisor
     * @param newSupervisorID  The ID of replacement supervisor
     */
    public static void transferProject(int projectID, String oldSupervisorID, String newSupervisorID) 
    {
        Supervisor oldSup = SupervisorControl.getSupervisorById(oldSupervisorID);
        Supervisor newSup = SupervisorControl.getSupervisorById(newSupervisorID);
        Project p = ProjectControl.getProjectByID(projectID);

        oldSup.unsuperviseProject(projectID);
        newSup.superviseProject(projectID);
        p.setSupervisorId(newSupervisorID);
        p.setSubmitterId(newSupervisorID);
    }

    /**
     * Return number of projects in project list
     * @return number of projects in project list
     */
    public static int numProjects()
    {
        return projectList.size();
    }

    /**
     * Return the submitterID of specified project
     * @param projectID The project ID of a Project
     * @return the submitterID of specified project
     */
    public static String getProjectSubmitterID(int projectID)
    {  
        return projectList.get(projectID).getSubmitterId();
    }
}
