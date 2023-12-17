package controller;

import entity.Supervisor;
import utils.FileIO;
import java.util.ArrayList;


/**
 * Control class for initialization of entities using text files
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-11
 */
public class InitializationControl 
{
    /**
     * Initialise students from a text file
     * @param filename Student data filepath
     */
    public static void initStudents(String filename) 
    {
        ArrayList<String> lines = FileIO.readLines(filename);
        String parts[];

        // start from i=1 to skip header
        for (int i=1; i<lines.size(); i++) 
        {
            parts = lines.get(i).toUpperCase().split(";"); // Name;Email
            StudentControl.createStudent(parts[0], parts[1]);
        }
    }

    /**
     * Initialise supervisors from a text file
     * @param filename Supervisor data filepath
     */
    public static void initSupervisors(String filename) 
    {
        ArrayList<String> lines = FileIO.readLines(filename);
        String parts[];

        // start from i=1 to skip header
        for (int i=1; i<lines.size(); i++) 
        {
            parts = lines.get(i).toUpperCase().split(";"); // Name;Email
            SupervisorControl.createSupervisor(parts[0], parts[1]);
        }
    }

    /**
     * Initialise FYP coordinators from a text file
     * @param filename Coordinator data filepath
     */
    public static void initCoordinators(String filename) 
    {
        ArrayList<String> lines = FileIO.readLines(filename);
        Supervisor supervisor;
        String parts[];

        // start from i=1 to skip header
        for (int i=1; i<lines.size(); i++) 
        {

            parts = lines.get(i).toUpperCase().split(";"); // Name;Email
            supervisor = SupervisorControl.createSupervisor(parts[0], parts[1]);

            CoordinatorControl.setCoordinator(supervisor);

            // there should only be 1 coordinator
            break; 
        }
    }

    /**
     * Initialise projects from a text file
     * @param filename Project data filepath
     */
    public static void initProjects(String filename) 
    {
        ArrayList<String> lines = FileIO.readLines(filename);
        String parts[];
        ArrayList<Supervisor> supervisors = SupervisorControl.getSupervisorList();

        // start from i=1 to skip header
        for (int i=1; i<lines.size(); i++) 
        {
            parts = lines.get(i).split(";"); // Supervisor;Title
            parts[0] = parts[0].toUpperCase(); // uppercase supervisor name

            // find user ID corresponding to supervisor name from supervisors
            for (Supervisor supervisor: supervisors) 
            {
                if (parts[0].equals(supervisor.getName())) 
                {
                    ProjectControl.createProject(supervisor.getUserId(), parts[1]);
                }
            }
        }
    }
}
