
/*
Copyright (c) 2013, Brendan Cowan, Tyler Meen, Steven Gerdes, Braeden Soetaert, Aly-khan Jamal
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
*/
package cmput301f13t10.model;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

import cmput301f13t10.presenter.AppConstants;

/**
 * Contains information about an adventure that the user can read, navigate
 * through, and edit.
 * 
 * @author Brendan Cowan
 * 
 * @author Steven Gerdes
 * 
 * @author Braeden Soetaert
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
	 */
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
		mId = IdFactory.getIdManager( AppConstants.GENERATE_ADVENTURE_ID ).getNewId();
		mTitle = title;
		SectionModel startSection = new SectionModel( AppConstants.START );
		mSections = new ArrayList<SectionModel>();
		mSections.add( startSection );
	}

	/**
	 * Deletes the section corresponding to the given section id. If the section
	 * id is not in the list of sections, it is not deleted.
	 * 
	 * @param sectionId
	 */
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

	/**
	 * Get the specified section by id.
	 * 
	 * @param sectionId
	 *            The id of the section to return
	 * @return The specified section, or null if it does not exist.
	 */
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

	/**
	 * Get the index of a given section in the adventures list of sections
	 * 
	 * @param id
	 *            The id of the section to find
	 * @return The index of the section
	 * @throws SectionNotFoundException
	 *             if the section does not exist.
	 */
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

	/**
	 * Get the current section the adventure is on. Currently just returns the
	 * start section.
	 * 
	 * @return The current section the adventure is on.
	 */
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
