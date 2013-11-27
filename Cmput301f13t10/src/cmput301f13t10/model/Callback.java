package cmput301f13t10.model;

public abstract class Callback
{
	protected Object mCallbackArg = null;
	public Callback( Object arg )
	{
		mCallbackArg = arg;
	}
	public Callback() {
		
	}

	public abstract void callBack( Object arg );
}
