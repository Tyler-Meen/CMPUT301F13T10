package cmput301f13t10.model;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface Command
{
	public Object execute() throws ClientProtocolException, IOException;
}
