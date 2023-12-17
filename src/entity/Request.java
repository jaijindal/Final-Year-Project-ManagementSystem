package entity;

/**
 * Represents a request in the system.
 * Class is abstract because actual request will be created using subclasses.
 * @author Twu Pin Yang
 * @version 1.0
 * @since 2023-04-12
 */
public abstract class Request 
{
	/**
	 * The ID of this request.
	 */
    private int requestId;

	/**
	 * The requestor ID of this request.
	 */
	private String requestorId;

	/**
	 * The requestee ID of this request.
	 */
	private String requesteeId;

	/**
	* The id of project involved in this request.
	*/
    private int projectId;

	/**
	 * The status of this request.
	 */
	private String status;

	private String requestorRole;
	private String requesteeRole;

	/**
	 * Creates a new request.
	 * @param requestId This request's ID.
	 * @param requestorId This request's requestor ID.
	 * @param requesteeId This request's requestee ID.
	 */
	public Request(int requestId, String requestorId, String requesteeId, String requestorRole, String requesteeRole) 
	{
		this.requestId = requestId;
        this.requestorId = requestorId;
		this.requesteeId = requesteeId;
		this.requestorRole = requestorRole;
		this.requesteeRole = requesteeRole;
		this.status = "pending";	
	}
	
	/**
	 * Gets the ID of this request.
	 * @return this Request's ID.
	 */
	public int getRequestID() 
	{
		return this.requestId;
	}

	/**
	 * Gets the Requestor's ID of this request.
	 * @return this Request's Requestor's ID.
	 */
	public String getRequestorID() 
	{
		return this.requestorId;
	}

	/**
	 * Gets the Requestee's ID of this request.
	 * @return this Request's Requestee's ID.
	 */
	public String getRequesteeID() 
	{
		return this.requesteeId;
	}

	/**
	 * Gets the role of requestor of this request.
	 * @return this Requestor's role
	 */
	public String getRequestorRole() 
	{
		return this.requestorRole;
	}

	/**
	 * Gets the role of requestee of this request.
	 * @return this Requestee's role
	 */
	public String getRequesteeRole() 
	{
		return this.requesteeRole;
	}

	/**
	* Get the id of the project in the request
	* @return This project ID
	*/
    public int getProjectId() 
	{
        return this.projectId;
    }

	/**
	* Set the id of the project in the request
	* @return The project ID
	*/
	public void setProjectId(int projectID)
	{
		this.projectId = projectID;
	}

	/**
	 * Gets the status of this request.
	 * @return this Request's status.
	 */
	public String getStatus() 
	{
		return this.status;
	}

	/**
	 * Changes the status of this Request.
	 * @param status This Request's new status.
	 */
	public void setStatus(String status) 
	{
		this.status = status;
	}
}

