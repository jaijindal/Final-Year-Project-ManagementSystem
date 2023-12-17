package entity;

import controller.ProjectControl;

/**
 * Represents a student's request to supervisor to change project title 
 * @author Lin Yung CHu
 * @version 1.0
 * @since 2023-04-12
 */
public class ChangeTitleRequest extends Request 
{
    /**
	* The new project title of this request.
	*/
    private String projectTitle;

	/**
	* The old project title of this request.
	*/
    private String oldProjectTitle;

    /**
	* Creates a new request.
	* @param requestId This request's ID
	* @param requestorId This request's requestor ID
	* @param requesteeId This request's requestee ID
	* @param projectId This project ID
	* @param projectTitle This new project title
	*/
	public ChangeTitleRequest(int requestId, String requestorId, String requesteeId, int projectId, String projectTitle) 
	{
		super(requestId, requestorId, requesteeId, "Student", "Supervisor");
        this.projectTitle = projectTitle;
		this.oldProjectTitle = ProjectControl.getProjectByID(projectId).getProjectTitle();
		setProjectId(projectId);
    }

    /**
	* Get the new title of the project in the request
	* @return This project title
	*/
    public String getProjectTitle() 
	{
        return this.projectTitle;
    }

	/**
	* Get the old title of the project in the request
	* @return Old project title
	*/
    public String getOldProjectTitle() 
	{
        return this.oldProjectTitle;
    }
}
