import controller.InitializationControl;
import boundary.UserLoginBoundary;
import java.io.*;

public class App 
{
    /**
     * Application class
     * @param args
     */
    public static void main(String[] args) 
    {
        InitializationControl.initStudents("../data/student list.txt");
        InitializationControl.initSupervisors("../data/faculty_list.txt");
        InitializationControl.initCoordinators("../data/FYP coordinator.txt");
        InitializationControl.initProjects("../data/rollover project.txt");

        UserLoginBoundary loginMenu = new UserLoginBoundary();

        System.out.println("._______________________________________________________________________________________.");
        System.out.println("|                                                                                       |");
        System.out.println("|   ________________.___.__________                            __                       |");
        System.out.println("|   \\_   _____/\\__  |   |\\______   \\   ______ ___.__.  _______/  |_   ____    _____     |");
        System.out.println("|    |    __)   /   |   | |     ___/  /  ___/<   |  | /  ___/\\   __\\_/ __ \\  /     \\    |");
        System.out.println("|    |     \\    \\____   | |    |      \\___ \\  \\___  | \\___ \\  |  |  \\  ___/ |  Y Y  \\   |");
        System.out.println("|    \\___  /    / ______| |____|     /____  > / ____|/____  > |__|   \\___  >|__|_|  /   |");
        System.out.println("|        \\/     \\/                        \\/  \\/          \\/             \\/       \\/    |");
        System.out.println("|                                                                                       |");
        System.out.println("._______________________________________________________________________________________.");
        System.out.println();
        System.out.println();

        loginMenu.runUntilComplete();

        System.out.println("Thank you for using the FYP System!");
        System.out.println("Have a nice day.");
    }   
}