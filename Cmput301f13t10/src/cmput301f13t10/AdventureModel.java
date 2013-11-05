package cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains information about an adventure that the user can read, navigate
 * through, and edit.
 * 
 * @author Brendan Cowan
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

	public void setSection( SectionModel section )
	{
		int index = idexOf( section );
		if( index == -1 )
		{
			mSections.set( index, section );
		}
		else
		{
			mSections.add( section );
		}

	}

	public int idexOf( SectionModel section )
	{
		int i = 0;
		int id = section.getId();
		for( SectionModel checkSection : mSections )
		{
			if( checkSection.getId() == id )
				return i;
			i++;
		}
		return -1;
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
	 * 
	 * @return The start section of the adventure
	 */
	public SectionModel getStartSection()
	{
		return mSections.get( 0 );
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
