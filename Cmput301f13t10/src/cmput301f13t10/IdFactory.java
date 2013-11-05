package cmput301f13t10;

import java.util.ArrayList;

public class IdFactory
{
	private static IdFactory mIdFactory = null;
	private int mNextId;
	private ArrayList<Integer> mReusableIds;

	protected IdFactory()
	{
		mReusableIds = new ArrayList<Integer>();
	}

	public static IdFactory getIdFactory()
	{
		if( mIdFactory == null )
			mIdFactory = new IdFactory();
		return mIdFactory;
	}

	public int getNewId()
	{
		if( mReusableIds.isEmpty() )
			return mNextId++;
		return mReusableIds.remove( mReusableIds.size() - 1 );
	}

	public void removeAll()
	{
		mReusableIds.clear();
		mNextId = 0;
	}

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
