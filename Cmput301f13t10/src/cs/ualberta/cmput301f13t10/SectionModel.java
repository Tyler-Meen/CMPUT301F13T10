package cs.ualberta.cmput301f13t10;

import java.util.ArrayList;

public class SectionModel
{

	private boolean mIsStart;
	private String mName;
	private ArrayList<Media> mMedias;

	public SectionModel( String name )
	{
		mName = name;
		setStart( false );
		mMedias = new ArrayList<Media>();
	}

	public SectionModel( String name, boolean isStart )
	{
		mName = name;
		setStart( isStart );
		mMedias = new ArrayList<Media>();
	}

	public void add( Media m )
	{
		mMedias.add( m );
	}

	public void remove( int index )
	{
		mMedias.remove( index );
	}

	public void move( int index, int offset ) throws IndexOutOfBoundsException
	{
		if( index + offset > mMedias.size() - 1 || index + offset < 0 )
			throw new IndexOutOfBoundsException( "Trying to move media out of bounds." );

		if( offset == 0 )
			return;

		Media movedMedia = mMedias.get( index );
		int increment = Math.abs( offset ) / offset; // are we moving forwards
														// or
														// backwards?
		for( int i = index; i != offset + index; i += increment )
		{
			mMedias.set( i, mMedias.get( i + increment ) );
		}

		mMedias.set( index + offset, movedMedia );
	}

	public ArrayList<Media> getMedia()
	{
		return mMedias;
	}

	public String getName()
	{
		return mName;
	}

	private void setStart( boolean isStart )
	{
		mIsStart = isStart;
	}
}
