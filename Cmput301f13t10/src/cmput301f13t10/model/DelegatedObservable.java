package cmput301f13t10.model;

import java.util.Observable;

public class DelegatedObservable extends Observable
{
	public void notifyObservers( Object arg )
	{
		this.setChanged();
		this.notifyObservers( arg );
	}
}
