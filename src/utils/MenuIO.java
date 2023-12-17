package utils;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;


/**
 * Standard menu I/O with dynamic formatting and error checking
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-11
 */
public class MenuIO 
{
    /**
     * Console object that can read passwords with without displaying on CLI
     */
    private static Console console = System.console();

    /**
     * Scanner object to read standard inputs
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Print menu in a fixed template format
     * @param choices List of menu choices
     */
    public static void multipleChoiceMenu(String choices[]) 
    {
        int maxNumberingWidth = String.valueOf(choices.length).length();
        int maxContentWidth = 0;
        int i;

        for (i=0; i<choices.length; i++) 
        {
            maxContentWidth = Math.max(maxContentWidth, choices[i].length());
        }

        System.out.println();
        System.out.println("+---" + "-".repeat(maxNumberingWidth + 1) + "+" + "-".repeat(maxContentWidth+1) + "---+");

        for (i=0; i<choices.length; i++)
        {
            System.out.println(String.format("|   %" + maxNumberingWidth + "s | %-" + maxContentWidth + "s   |", i+1, choices[i]));
        }
        System.out.println("+---" + "-".repeat(maxNumberingWidth + 1) + "+" + "-".repeat(maxContentWidth+1) + "---+");
    }

    /**
     * Method overloading with extra header parameter
     * @param choices List of menu choices
     * @param header Menu header
     */
    public static void multipleChoiceMenu(String choices[], String header) 
    {
        int maxNumberingWidth = String.valueOf(choices.length).length();
        int maxContentWidth = header.length() + 3 + maxNumberingWidth;
        int i;

        for (i=0; i<choices.length; i++) 
        {
            maxContentWidth = Math.max(maxContentWidth, choices[i].length());
        }

        System.out.println();
        System.out.println("+---" + "-".repeat(maxNumberingWidth + 3) + "-".repeat(maxContentWidth) + "---+");
        System.out.println("|   " + header + " ".repeat(maxContentWidth - header.length() + 3 + maxNumberingWidth) + "   |");
        System.out.println("+---" + "-".repeat(maxNumberingWidth + 1) + "+" + "-".repeat(maxContentWidth+1) + "---+");

        for (i=0; i<choices.length; i++)
        {
            System.out.println(String.format("|   %" + maxNumberingWidth + "s | %-" + maxContentWidth + "s   |", i+1, choices[i]));
        }
        System.out.println("+---" + "-".repeat(maxNumberingWidth + 1) + "+" + "-".repeat(maxContentWidth+1) + "---+");
    }
    
    /**
     * Repeatedly prompt for integer input until valid value given
     * @param min Minimum acceptable value
     * @param max Maximum acceptable value
     * @return Integer value entered by user
     */
    public static int inputInt(int min, int max) 
    {
        int value;
        while (true) 
        {
            System.out.println();
            try
            {
                System.out.print("Enter a value (" + String.valueOf(min) + "," + String.valueOf(max) + "): ");
                value = sc.nextInt();
                sc.nextLine(); // consume \n char

                if (value >= min && value <= max) 
                {
                    return value;
                }
                System.out.println("[ERROR] Valid range exceeded");
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("[ERROR] Non-integer entered");
                sc.nextLine(); // consume rubbish input
            }
        }   
    }
    
    /**
     * Overloads inputInt. Use default input prompt if not specified
     * @param min Minimum acceptable value
     * @param max Maximum acceptable value
     * @param prompt Text prompt for user input
     * @return Integer value entered by user
     */
    public static int inputInt(int min, int max, String prompt) 
    {
        int value;
        while (true) 
        {
            System.out.println();
            try
            {
                System.out.print(prompt + ": ");
                value = sc.nextInt();
                sc.nextLine(); // consume \n char

                if (value >= min && value <= max) 
                {
                    return value;
                }
                System.out.println("[ERROR] Valid range exceeded");
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("[ERROR] Non-integer entered");
                sc.nextLine(); // consume rubbish input
            }
        }   
    }

    /**
     * Repeatedly prompt for string input until valid value given
     * @param prompt Text prompt for user input
     * @param required True if empty string allowed as input
     * @return String value entered by user
     */
    public static String inputString(String prompt, boolean required) 
    {
        String value;
        
        while (true) 
        {
            System.out.println();
            System.out.print(prompt + ": ");
            value = sc.nextLine();

            if (!required || value.length() > 0) 
            {
                return value;
            }
            else 
            {
                System.out.println("[ERROR] Enter a value.");
            }
        }
    }

    /**
     * Get input for password
     * @return Password
     */
    public static String inputPassword() 
    {
        return String.valueOf(console.readPassword("Password: "));
    }

    /**
     * Overloading of inputPassword() with additional inputPrompt parameter
     * @param inputPrompt Text prompt for user to input password
     * @return Password
     */
    public static String inputPassword(String inputPrompt) 
    {
        return String.valueOf(console.readPassword(inputPrompt + ": "));
    }
}

