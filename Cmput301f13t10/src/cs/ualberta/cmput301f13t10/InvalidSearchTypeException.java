package cs.ualberta.cmput301f13t10;

/**
 * Thrown when the searchBy method of the searcher is called with an
 * unrecognized search type
 * 
 * @author Brendan Cowan
 * 
 */
public class InvalidSearchTypeException extends Exception
{
	/**
	 * Constructor
	 * 
	 * @param message
	 *            Message associated with the exception
	 */
	public InvalidSearchTypeException( String message )
	{
		super( message );
	}
}
