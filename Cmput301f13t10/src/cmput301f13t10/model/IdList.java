package cmput301f13t10.model;

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
	public IdList( int[] ids )
	{
		mIds = ids;
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
