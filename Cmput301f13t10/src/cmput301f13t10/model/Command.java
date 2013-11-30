package cmput301f13t10.model;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

/**
 * Command class for the command pattern
 * @author Brendan Cowan
 *
 */
public interface Command
{
	/**
	 * Execute the command
	 * @return An arbitrary object, depending on the command
	 */
	public Object execute();
}
