/*This file contains the SolutionNotFound class. The class extends Exception and is used to catch exceptions that occur in the polynomial class. It has one method, 
 * the constructor, SolutionNotFound.
 */
public class SolutionNotFound extends Exception
{
	//constructor method, calls the Exception parent class constructor with the message. 
	public SolutionNotFound(String message) //@param exception message to pass to the parent class constructor method. 
	{
		super(message); 
	}
}

//End of class. 