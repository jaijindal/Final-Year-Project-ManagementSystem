package boundary;

/**
 * Boundary class for user view.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-14
 */
public abstract class UserViewBoundary extends ViewBoundary 
{
    protected String userID;

    public UserViewBoundary(String userID) {
        super();

        this.userID = userID;
        setMenuOptions(new String[] {"Change password", "Log out"});
    }

    public UserViewBoundary(String userID, String menuOptions[]) 
    {
        super(menuOptions);

        this.userID = userID;
        appendMenuOptions(new String[] {"Change password", "Log out"});
    }

    public UserViewBoundary(String userID, String menuOptions[], String header) 
    {
        super(menuOptions, header);

        this.userID = userID;
        appendMenuOptions(new String[] {"Change password", "Log out"});
    }

    protected abstract void caseChangePassword();
}