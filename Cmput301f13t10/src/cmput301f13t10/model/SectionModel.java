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
import java.util.Observable;

import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.presenter.Media;
import cmput301f13t10.presenter.SectionChoice;

/**
 * A section of an adventure. Contains media to be displayed, and a list of
 * choices that the reader can take to continue in the adventure.
 * 
 * @author Brendan Cowan
 * @author Aly-khan Jamal
 * 
 */
public class SectionModel extends Observable implements Serializable
{
	/**
	 * The title of the section
	 */
	private String mName;
	/**
	 * A list of sections that this section leads to.
	 */
	private ArrayList<SectionChoice> mChoices;
	/**
	 * A list of media contained within this section.
	 */
	private ArrayList<Media> mMedias;
	/**
	 * The id of the section.
	 */
	private int mId;
	/**
	 * Annotation contained within the section
	 */
	private AnnotationModel mAnnotation;


	/**
	 * Constructor
	 * 
	 * @param name
	 *            The title of the section.
	 */
	public SectionModel( String name )
	{
		mName = name;
		mMedias = new ArrayList<Media>();
		mChoices = new ArrayList<SectionChoice>();
		mId = IdFactory.getIdManager( AppConstants.GENERATE_SECTION_ID ).getNewId();
		mAnnotation = new AnnotationModel();
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
		mMedias.remove( index );
		mMedias.add( index + offset, movedMedia );
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
	public void setChoices( ArrayList<SectionChoice> choices )
	{
		mChoices = choices;
	}

	/**
	 * Set add a new choice for this section
	 * 
	 * @param choice
	 *            The new choice to add
	 */
	public void addChoice( SectionChoice choice )
	{
		mChoices.add( choice );
	}

	/**
	 * Remove a choice from the section
	 * 
	 * @param choiceToRemove
	 *            The choice to remove
	 */
	public void removeChoice( SectionChoice choiceToRemove )
	{
		mChoices.remove( choiceToRemove );
	}

	/**
	 * Get a list of choices that the section connects to.
	 * 
	 * @return The choices that the section connects to.
	 */
	public ArrayList<SectionChoice> getChoices()
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
	 * Set the name of the section
	 * 
	 * @param sectionName
	 *            The new name of the section
	 */
	public void setName( String sectionName )
	{
		mName = sectionName;
	}

	/**
	 * Get the id of the section
	 * 
	 * @return The id of the section
	 */
	public int getId()
	{
		return mId;
	}
	
	/**
	 * Get the annotation of the section
	 * 
	 * @return The annotation of the section
	 */
	public AnnotationModel getAnnotation()
	{
		return mAnnotation;
	}
	
	/**
	 * Sets the annotation of the section
	 * 
	 * @param annotation
	 */
	public void setAnnotation (AnnotationModel annotation)
	{
		mAnnotation = annotation;
	}

	/**
	 * Write the serializable object
	 * @param out The objectOutputStream to write the output stream
	 * @throws IOException
	 */
	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeObject( mName );
		out.writeObject( mChoices );
		out.writeObject( mMedias );
		out.writeInt( mId );
		out.writeObject(mAnnotation);
	}

	/**
	 * Set the id of the section
	 * @param id The id to set
	 */
	public void setId( int id )
	{
		mId = id;
	}

	/**
	 * Read the serializable object
	 * @param in The inputStream to read the object from
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mName = (String) in.readObject();
		mChoices = (ArrayList<SectionChoice>) in.readObject();
		mMedias = (ArrayList<Media>) in.readObject();
		mId = in.readInt();
		mAnnotation = (AnnotationModel) in.readObject();
		
	}
}
