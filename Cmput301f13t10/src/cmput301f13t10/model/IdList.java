package cmput301f13t10.model;

import java.util.ArrayList;

/**
 * A list of ids.
 * @author Brendan Cowan
 *
 */
public class IdList
{
	/**
	 * The ids in the list
	 */
	private int[] mIds;

	/**
	 * Constructor
	 * @param ids the ids to put into the list
	 */
	public IdList( String[] ids )
	{
		for( int i = 0; i < ids.length; i++ )
		{
			mIds[i] = Integer.valueOf( ids[i] );
		}
	}

	/**
	 * Return the ids in the list
	 * @return The ids in the list
	 */
	public int[] getIds()
	{
		return mIds;
	}
}
