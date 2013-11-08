package cmput301f13t10;

import java.util.ArrayList;

public class IdManager
{

	/**
	 * A counter to determine the next id to return
	 */
	private int mNextId;
	/**
	 * A list of ids that have been used and later deleted. They can be reused.
	 */
	private ArrayList<Integer> mReusableIds;

	/**
	 * Constructor
	 */
	public IdManager()
	{
		mReusableIds = new ArrayList<Integer>();
	}

	/**
	 * Generate a new id.
	 * 
	 * @return The new id.
	 */
	public int getNewId()
	{
		if( mReusableIds.isEmpty() )
			return mNextId++;
		return mReusableIds.remove( mReusableIds.size() - 1 );
	}

	/**
	 * Reset the ids in the factory.
	 */
	public void removeAll()
	{
		mReusableIds.clear();
		mNextId = 0;
	}

	/**
	 * Set a single Id in the factory to be reusable.
	 * 
	 * @param id
	 *            The id to set to reusable.
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the requested Id is not in use.
	 */
	public void removeId( int id ) throws ArrayIndexOutOfBoundsException
	{
		if( id > mNextId - 1 )
			throw new ArrayIndexOutOfBoundsException( "Trying to remove id that does not exist" );

		if( id == mNextId - 1 )
		{
			mNextId--;
			while( mReusableIds.contains( Integer.valueOf( mNextId - 1 ) ) )
			{
				mReusableIds.remove( Integer.valueOf( mNextId - 1 ) );
				mNextId--;
			}
		}
		else if( !mReusableIds.contains( id ) )
		{
			mReusableIds.add( id );
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException( "Trying to remove id that does not exist" );
		}
	}
}
