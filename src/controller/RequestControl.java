package controller;

import entity.Request;
import java.util.ArrayList;

/**
 * Control class to handle approval and rejection of request
 * 
 * @author Lin Yung Chu
 * @version 1.0
 * @since 2023-04-13
 */
public abstract class RequestControl 
{
    /**
     * The list of ALL requests
     */
    private static ArrayList<Request> requestList = new ArrayList<Request>();

    /**
     * Get the entire list of Requests
     * @return this list of Requests
     */
    public static ArrayList<Request> getRequestList() 
    {
        return requestList;
    }

    /**
     * Get the size of entire list of Requests
     * @return this size of list of Requests
     */
    public static int getRequestListSize() 
    {
        return requestList.size();
    }

    /**
     * Get the specific Request object
     * @param requestID The request ID of a request
     * @return a specific request object
     */
    public static Request getRequestByID(int requestID) 
    {
        return requestList.get(requestID);
    }

    /**
     * Add a Request to the request list
     * @param request The new request to be added
     */
    public static void addRequest(Request request) 
    {
        requestList.add(request);
    }

    /**
     * Reject the specified request
     * Set its status to "rejected"
     * @param requestID The request ID of a Request
     */
    public static void reject(int requestID)
    {
        Request r = requestList.get(requestID);
        r.setStatus("rejected");
    }    

    /**
     * Check if the userID matches the ID of requestee
     * @return if the userID matches the ID of requestee
     */
    public static boolean isRequestee(String userID, int requestID)
    {
        return getRequestByID(requestID).getRequesteeID().equals(userID);
    }

    /**
     * Check if a request is valid (i.e. pending and userID matches the ID of requestee)
     * @param requestID id of request
     * @param approverID id of approver
     * @return if a request is valid
     */
    public static boolean isValid(int requestID, String approverID) {
        Request request = getRequestByID(requestID);

        // check request status
        if (!request.getStatus().equals("pending")) 
        {
            System.out.println("[ERROR] Invalid request status. Request may have already been processed");

            return false;
        }

        // check if approverID is also requesteeID
        if (!isRequestee(approverID, requestID))
        {
            System.out.println("[ERROR] The request is not directed to you");
            
            return false;
        }
        
        return true;
    }
}