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
import cmput301f13t10.presenter.Media;

/**
 * An annotation of a section. Contains media to be displayed.
 * 
 * @author Aly-khan Jamal
 * 
 */
public class AnnotationModel implements Serializable
{
	/**
	 * A list of media contained within the Annotation.
	 */
	private ArrayList<Media> mMedias;
	/**
	 * The id of the section.
	 */
	private int mId;

	/**
	 * Constructor
	 */
	public AnnotationModel()
	{
		mMedias = new ArrayList<Media>();
		mId = IdFactory.getIdManager( AppConstants.GENERATE_ANNOTATION_ID ).getNewId();
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
	 * Get the list of media contained within this annotation.
	 * 
	 * @return An ordered ArrayList of media contained within this annotation.
	 */
	public ArrayList<Media> getMedia()
	{
		return mMedias;
	}

	/**
	 * Get the id of the annotation
	 * 
	 * @return The id of the annotation
	 */
	public int getId()
	{
		return mId;
	}

	/**
	 * Write the serializable object
	 * 
	 * @param out
	 *            The objectOutputStream to write the output stream
	 * @throws IOException
	 */
	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeObject( mMedias );
		out.writeInt( mId );
	}

	/**
	 * Read the serializable object
	 * 
	 * @param in
	 *            The inputStream to read the object from
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mMedias = (ArrayList<Media>) in.readObject();
		mId = in.readInt();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}
}
