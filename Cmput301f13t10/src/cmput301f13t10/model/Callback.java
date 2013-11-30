package cmput301f13t10.model;

/**
 * Abstract class for callbacks
 * 
 * @author Brendan Cowan
 * 
 */
public abstract class Callback
{
	/**
	 * Argument that is passed into the callback
	 */
	protected Object mCallbackArg = null;

	/**
	 * Constructor
	 * 
	 * @param arg
	 *            Arbitrary argument that can be used when the callback is
	 *            executed
	 */
	public Callback( Object arg )
	{
		mCallbackArg = arg;
	}

	/**
	 * Parameterless constructor
	 */
	public Callback()
	{

	}

	/**
	 * The function to be called when the callback is executed.
	 * 
	 * @param arg
	 *            Arbitrary argument that can be passed to the callback when it
	 *            is executed
	 */
	public abstract void callBack( Object arg );
}
