package entity;

/**
 * Represents a student's request to FYP Coordinator to deregister for a project
 * @author Lin Yung CHu
 * @version 1.0
 * @since 2023-04-12
 */
public class DeregisterProjectRequest extends Request 
{
    /**
	* Creates a new request.
	* @param requestId This request's ID
	* @param requestorId This request's requestor ID
	* @param requesteeId This request's requestee ID
	* @param projectId This project ID
	*/
	public DeregisterProjectRequest(int requestId, String requestorId, String requesteeId, int projectId) 
	{
		super(requestId, requestorId, requesteeId, "Student", "FypCoordinator");
        setProjectId(projectId);
	}
}