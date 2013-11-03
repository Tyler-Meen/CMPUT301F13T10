package cs.ualberta.cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A section of an adventure. Contains media to be displayed, and a list of
 * choices that the reader can take to continue in the adventure.
 * 
 * @author Brendan Cowan
 * 
 */
public class SectionModel implements Serializable
{

	/**
	 * If the section is the first section in a story
	 */
	private boolean mIsStart;
	/**
	 * The title of the section
	 */
	private String mName;
	/**
	 * A list of sections that this section leads to.
	 */
	private ArrayList<SectionModel> mChoices;
	/**
	 * A list of media contained within this section.
	 */
	private ArrayList<Media> mMedias;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The title of the section.
	 */
	public SectionModel( String name )
	{
		mName = name;
		setStart( false );
		mMedias = new ArrayList<Media>();
		mChoices = new ArrayList<SectionModel>();
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            The title of the section
	 * @param isStart
	 *            If the section is the first section in a story.
	 */
	public SectionModel( String name, boolean isStart )
	{
		mName = name;
		setStart( isStart );
		mMedias = new ArrayList<Media>();
		mChoices = new ArrayList<SectionModel>();
	}

	/**
	 * Insert a media at the end of the list of media.
	 * 
	 * @param m
	 *            The media to insert.
	 */
	public void add( Media m )
	{
		mMedias.add( m );
	}

	/**
	 * Remove a media from the list of media.
	 * 
	 * @param index
	 *            The index of the media to remove.
	 */
	public void remove( int index )
	{
		mMedias.remove( index );
	}

	/**
	 * Move a media in the list of media by a given offset
	 * 
	 * @param index
	 *            The index of the media to move
	 * @param offset
	 *            The offset to move the media by. A positive number moves it
	 *            foreward; a negative number moves it backwards.
	 * @throws IndexOutOfBoundsException
	 *             If the offset provided would result in the media being moved
	 *             out of the array.
	 */
	public void moveMedia( int index, int offset ) throws IndexOutOfBoundsException
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

	/**
	 * Get the list of media contained within this section.
	 * 
	 * @return An ordered ArrayList of media contained within this section.
	 */
	public ArrayList<Media> getMedia()
	{
		return mMedias;
	}

	/**
	 * Set the sections that this section connects to.
	 * 
	 * @param choices
	 *            The choices that this section will connect to.
	 */
	public void setChoices( ArrayList<SectionModel> choices )
	{
		mChoices = choices;
	}
	
	/**
	 * Set add a new choice for this section
	 * @param choice The new choice to add
	 */
	public void addChoice(SectionModel choice) {
		mChoices.add(choice);
	}

	/**
	 * Get a list of choices that the section connects to.
	 * 
	 * @return The choices that the section connects to.
	 */
	public ArrayList<SectionModel> getChoices()
	{
		return mChoices;
	}

	/**
	 * Get the name of the section
	 * 
	 * @return The name of the section
	 */
	public String getName()
	{
		return mName;
	}

	/**
	 * Set whether or not the section is meant to be at the start of an
	 * adventure.
	 * 
	 * @param isStart
	 *            True if the section is meant to be at the start of an
	 *            adventure, False otherwise
	 */
	private void setStart( boolean isStart )
	{
		mIsStart = isStart;
	}

	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeBoolean( mIsStart );
		out.writeObject( mName );
		out.writeObject( mChoices );
		out.writeObject( mMedias );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mIsStart = (Boolean) in.readBoolean();
		mName = (String) in.readObject();
		mChoices = (ArrayList<SectionModel>) in.readObject();
		mMedias = (ArrayList<Media>) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}
}
