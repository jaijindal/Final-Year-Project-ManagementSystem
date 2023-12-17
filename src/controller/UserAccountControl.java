package controller;

import utils.MenuIO;
import entity.User;

/**
 * Control class to handle user authentication and changing of password
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-12
 */
public class UserAccountControl {    
    /*
     * Change password of user
     * @param user User instance
     */
    public static void changePassword(User user) 
    {
        String newPassword;

        // verify user identity by checking if entered password is correct
        if (MenuIO.inputPassword("Enter old password").equals(user.getPassword())) 
        {
            newPassword = MenuIO.inputPassword("Enter new password");
            if (newPassword.equals(MenuIO.inputPassword("Confirm new password"))) 
            {
            	user.setPassword(newPassword);
            	System.out.println("Password has been changed");
            }
            else 
            {
            	System.out.println("[ERROR] Passwords do not match");
            }
        } 
        else 
        {
        	 System.out.println("[ERROR] Wrong password");
        }
    }

    /**
     * Authenticate user
     * @param user User instance
     * @return Boolean stating whether user is authenticated or not
     */
    public static boolean authenticate(User user) 
    {
        String password = MenuIO.inputPassword();
        
        // user does not exist or password does not match
        if (user == null || !user.getPassword().equals(password))
        { 
            return false;
        }
        return true;
    }
}