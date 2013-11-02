package cs.ualberta.cmput301f13t10;

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
	 * The adventure's title
	 */
	private String mTitle;
	/**
	 * Sections contained within the adventure
	 */
	private ArrayList<SectionModel> mSections;
	/**
	 * The first section in the adventure
	 */
	private SectionModel mStartSection;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            The adventure's title
	 */
	public AdventureModel( String title )
	{
		mTitle = title;
		mStartSection = new SectionModel( AppConstants.START, true );
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
	 * Set the starting section of the adventure
	 * 
	 * @param startSection
	 *            The section to start at
	 */
	public void setStartSection( SectionModel startSection )
	{
		mStartSection = startSection;
	}

	/**
	 * Get the starting section of the adventure
	 * 
	 * @return The section the adventure starts at
	 */
	public SectionModel getStartSection()
	{
		return mStartSection;
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
		out.writeObject( mStartSection );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mTitle = (String) in.readObject();
		mSections = (ArrayList<SectionModel>) in.readObject();
		mStartSection = (SectionModel) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}

}
