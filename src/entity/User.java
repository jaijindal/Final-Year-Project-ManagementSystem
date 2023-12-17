package entity;


/**
Represents a user for the system.
@author Twu Pin Yang
@version 1.0
@since 2023-04-12
*/

public class User 
{
	/**
	* The User ID of this user.
	*/
	private String userId;

	/**
	* The password of this user.
	*/
	private String password; 
	
	/**
	* The name of this user.
	*/
	private String name;
	
	/**
	* The email address of this user.
	*/
	private String emailAddress;
	
	public User(String name, String emailAddress) 
	{
		int index = emailAddress.indexOf('@');
		String userId = emailAddress.substring(0, index);
				
		this.userId = userId;
		this.password = "password";
		this.name = name;
		this.emailAddress = emailAddress;
	}
	
	/**
	* Retrieve the User ID of this user.
	* @return the User's ID.
	*/
	public String getUserId() 
	{
		return this.userId;
	}
	
	/**
	* Retrieve the password of this user.
	* @return the User's password.
	*/
	public String getPassword() 
	{
		return this.password;
	}
	
	/**
	* Retrieve the name of this user.
	* @return the User's name.
	*/
	public String getName() 
	{
		return this.name;
	}
	
	/**
	* Retrieve the email address of this user.
	* @return the User's email address.
	*/
	public String getEmailAddress() 
	{
		return this.emailAddress;
	}

	/**
	* Change the password of this user.
	* @param newPassword This user's new password.
	*/
	public void setPassword(String newPassword) 
	{
		this.password = newPassword;
	}

}
	