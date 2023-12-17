package entity;

/**
 * Represents a student's request to supervisor to change project title 
 * @author Lin Yung CHu
 * @version 1.0
 * @since 2023-04-12
 */
public class ChangeSupervisorRequest extends Request 
{
    /**
	* The id of the replacement supervisor.
	*/
    private String newSupervisorId;

    /**
	* Creates a new request.
	* @param requestId This request's ID
	* @param requestorId This request's requestor ID
	* @param requesteeId This request's requestee ID
	* @param projectId This project ID
	* @param supervisorId This id of the replacement supervisor
	*/
	public ChangeSupervisorRequest(int requestId, String requestorId, String requesteeId, int projectId, String newSupervisorId) 
	{
		super(requestId, requestorId, requesteeId, "Supervisor", "FypCoordinator");
        this.newSupervisorId = newSupervisorId;
		setProjectId(projectId);
    }

    /**
	* Get the id of the replacement supervisor in the request
	* @return This replacement supervisor's ID
	*/
    public String getNewSupervisorId() 
	{
        return newSupervisorId;
    }
}