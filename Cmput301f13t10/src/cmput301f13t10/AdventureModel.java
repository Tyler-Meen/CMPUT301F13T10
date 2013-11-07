package cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains information about an adventure that the user can read, navigate
 * through, and edit.
 * 
 * @author Brendan Cowan, Steven Gerdes
 * 
 */
public class AdventureModel implements Serializable
{

	/**
	 * The id of the adventure
	 */
	private int mId;
	/**
	 * The adventure's title
	 */
	private String mTitle;
	/**
	 * Sections contained within the adventure
	 */
	private ArrayList<SectionModel> mSections;

	public AdventureModel()
	{
		this( "" );
	}

	/**
	 * Constructor
	 * 
	 * @param title
	 *            The adventure's title
	 */
	public AdventureModel( String title )
	{
		mId = IdFactory.getIdFactory().getNewId();
		mTitle = title;
		SectionModel startSection = new SectionModel( AppConstants.START );
		mSections = new ArrayList<SectionModel>();
		mSections.add( startSection );
	}

	public void deleteSection( Integer sectionId )
	{
		for( int i = 0; i < mSections.size(); i++ )
		{
			if( mSections.get( i ).getId() == sectionId )
			{
				mSections.remove( i );
			}
		}
	}

	/**
	 * Set the title of the adventure
	 * 
	 * @param title
	 *            The adventure's new title
	 */
	public void setTitle( String title )
	{
		mTitle = title;
	}

	/**
	 * Get the title of the adventure
	 * 
	 * @return The adventure's title
	 */
	public String getTitle()
	{
		return mTitle;
	}

	/**
	 * Get the id of the adventure
	 * 
	 * @return The id of the adventure
	 */
	public int getId()
	{
		return mId;
	}

	/**
	 * Set all of the sections contained within the adventure
	 * 
	 * @param sections
	 *            The sections in the adventure
	 */
	public void setSections( ArrayList<SectionModel> sections )
	{
		mSections = sections;
	}

	/**
	 * Set one section in the adventure. If the section does not exist, it is
	 * added to the end. Otherwise, the section is overwritten.
	 * 
	 * @param section
	 *            The section to write
	 */
	public void setSection( SectionModel section )
	{
		try
		{
			mSections.set( indexOf( section ), section );
		}
		catch( SectionNotFoundException e )
		{
			mSections.add( section );
		}

	}

	public SectionModel getSection( Integer sectionId )
	{
		try
		{
			return mSections.get( indexOf( sectionId ) );
		}
		catch( SectionNotFoundException e )
		{
			return null;
		}
	}
	
	/**
	 * Find the index of a section in the adventure, as determined by the
	 * section's id.
	 * 
	 * @param section
	 *            The section to find.
	 * @return The index of the section, if it exists in the adventure. -1 if it
	 *         does not.
	 */
	public int indexOf( SectionModel section ) throws SectionNotFoundException
	{
		int id = section.getId();
		return indexOf( id );
	}

	private int indexOf( int id ) throws SectionNotFoundException
	{
		int i = 0;
		for( SectionModel checkSection : mSections )
		{
			if( checkSection.getId() == id )
				return i;
			i++;
		}
		throw new SectionNotFoundException();
	}

	/**
	 * Add a section to the current list of sections
	 * 
	 * @param section
	 *            The section to add
	 */
	public void addSection( SectionModel section )
	{
		mSections.add( section );
	}

	/**
	 * Get the section that the adventure will start on
	 * 
	 * @return The start section of the adventure
	 */
	public SectionModel getStartSection()
	{
		return mSections.get( 0 );
	}
	
	public SectionModel getCurrentSection()
	{
		// TODO make it so it keeps track of current section and returns it
		// currently it returns start section
		return getStartSection();
	}

	/**
	 * Get all of the sections contained within the adventure
	 * 
	 * @return The sections contained within the adventure
	 */
	public ArrayList<SectionModel> getSections()
	{
		return mSections;
	}

	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeObject( mTitle );
		out.writeObject( mSections );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mTitle = (String) in.readObject();
		mSections = (ArrayList<SectionModel>) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}




}
