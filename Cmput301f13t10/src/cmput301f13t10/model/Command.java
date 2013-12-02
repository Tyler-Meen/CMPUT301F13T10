package cmput301f13t10.model;


/**
 * Command class for the command pattern
 * 
 * @author Brendan Cowan
 * 
 */
public interface Command
{
	/**
	 * Execute the command
	 * 
	 * @return An arbitrary object, depending on the command
	 */
	public Object execute();
}
