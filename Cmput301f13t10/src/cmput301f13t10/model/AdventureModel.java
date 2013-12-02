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
import java.util.Observer;

import android.util.Log;
import cmput301f13t10.presenter.AppConstants;

import com.google.gson.Gson;

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

	private SectionArray mSectionArray = new SectionArray();

	/**
	 * The local id of the adventure
	 */
	private int mLocalId;

	/**
	 * The remote id of the adventure
	 */
	private int mRemoteId;

	/**
	 * The adventure's title
	 */
	private String mTitle;

	/**
	 * A flag to indicate if the adventure is wanted to save
	 */
	private Boolean mToSave;

	private boolean mIsRandomAvailable;
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
		mLocalId = IdFactory.getIdManager( AppConstants.GENERATE_ADVENTURE_ID ).getNewId();
		mRemoteId = -1;
		mTitle = title;
		SectionModel startSection = new SectionModel( AppConstants.START );
		mSectionArray.setSections( new ArrayList<SectionModel>() );
		mSectionArray.getSections().add( startSection );
		mIsRandomAvailable= false; 
		mToSave = false;
	}

	/**
	 * Deletes the section corresponding to the given section id. If the section
	 * id is not in the list of sections, it is not deleted.
	 * 
	 * @param sectionId
	 */
	public void deleteSection( Integer sectionId )
	{
		mSectionArray.deleteSection( sectionId );
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
	 * Get the local id of the adventure
	 * 
	 * @return The local id of the adventure
	 */
	public int getLocalId()
	{
		return mLocalId;
	}

	/**
	 * Set the local id of the adventure
	 * 
	 * @param id
	 *            The id to set
	 */
	public void setLocalId( int id )
	{
		mLocalId = id;
	}

	/**
	 * Get the remote id of the adventure
	 * 
	 * @return the remote id
	 */
	public int getRemoteId()
	{
		return mRemoteId;
	}

	/**
	 * Set the remote id of the adventure
	 * 
	 * @param id
	 *            the id to set.
	 */
	public void setRemoteId( int id )
	{
		mRemoteId = id;
	}

	/**
	 * Set all of the sections contained within the adventure
	 * 
	 * @param sections
	 *            The sections in the adventure
	 */
	public void setSections( ArrayList<SectionModel> sections )
	{
		mSectionArray.setSections( sections );
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
		mSectionArray.setSection( section );

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
		return mSectionArray.getSection( sectionId );
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
		return mSectionArray.indexOf( section );
	}

	/**
	 * Add a section to the current list of sections
	 * 
	 * @param section
	 *            The section to add
	 */
	public void addSection( SectionModel section )
	{
		mSectionArray.addSection( section, this );
	}

	/**
	 * Get the section that the adventure will start on
	 * 
	 * @return The start section of the adventure
	 */
	public SectionModel getStartSection()
	{
		return mSectionArray.getStartSection();
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
		return mSectionArray.getStartSection();
	}

	/**
	 * Get all of the sections contained within the adventure
	 * 
	 * @return The sections contained within the adventure
	 */
	public ArrayList<SectionModel> getSections()
	{
		return mSectionArray.getSections();
	}

	/**
	 * Write the serializable object
	 * 
	 * @param out
	 *            The output stream to write to
	 * @throws IOException
	 *             If the write failed
	 */
	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeObject( mTitle );
		out.writeObject( mSectionArray.getSections() );
		out.writeInt( mRemoteId );
		out.writeInt( mLocalId );
		out.writeBoolean( mToSave );
	}

	/**
	 * read the serializable object
	 * 
	 * @param in
	 *            The output stream to write to
	 * @throws IOException
	 *             If the read fails
	 * @throws ClassNotFoundException
	 */
	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mTitle = (String) in.readObject();
		mSectionArray.setSections( (ArrayList<SectionModel>) in.readObject() );
		mRemoteId = in.readInt();
		mLocalId = in.readInt();
		mToSave = in.readBoolean();
	}

	/**
	 * If the adventure is to be saved
	 * 
	 * @return True if the adventure is to be saved, false if it isn't
	 */
	public Boolean toSave()
	{
		return mToSave;
	}

	/**
	 * Set a flag to indicate that the adventure is to be saved or not
	 * 
	 * @param save
	 *            True if the adventure is to be saved, false if it isn't
	 */
	public void setSave( Boolean save )
	{
		mToSave = save;
	}
	
	@Override
	public String toString()
	{
		return mTitle;
	}

	public boolean getRandomSet()
	{
		return mIsRandomAvailable;
	}

	public void setRandom( boolean randomEnabled )
	{
		mIsRandomAvailable = randomEnabled;
	}

}
