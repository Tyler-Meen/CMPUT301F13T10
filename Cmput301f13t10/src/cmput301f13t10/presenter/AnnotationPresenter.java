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
package cmput301f13t10.presenter;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import cmput301f13t10.model.AdventureCache;
import cmput301f13t10.model.AdventureModel;
import cmput301f13t10.model.AnnotationModel;
import cmput301f13t10.model.DatabaseInteractor;
import cmput301f13t10.model.SectionModel;
import cmput301f13t10.view.AnnotationEditView;

/**
 * Presenter that relays data between the annotation model and the annotation
 * view. This class also allows you to store images in the annotation media.
 * 
 * @author Aly-khan Jamal
 * 
 */
public class AnnotationPresenter
{
	/**
	 * The view that created the presenter
	 */
	private AnnotationEditView mView;
	/**
	 * The current annotation that the reader is viewing
	 */
	private AnnotationModel mCurrentAnnotation;
	/**
	 * The current section that the reader is viewing
	 */
	private SectionModel mCurrentSection;
	/**
	 * The current adventure that the reader is viewing
	 */
	private AdventureModel mCurrentAdventure;
	
	/**
	 * Constructor
	 * 
	 * @param view
	 *            The view that created this presenter
	 */
	public AnnotationPresenter( AnnotationEditView view )
	{
		mView = view;
	}
	
	/**
	 * Set the current annotation that the user is viewing
	 * 
	 * @param annotation
	 *            The current annotation to set
	 */
	public void setCurrentAnnotation( AnnotationModel annotation )
	{
		mCurrentAnnotation = annotation;
		mCurrentSection.setAnnotation( mCurrentAnnotation );
		mView.updateAnnotationView();
	}
	
	/**
	 * Get the id of the current annotation
	 * 
	 * @return The id of the current annotation
	 */
	public int getAnnotationId()
	{
		return mCurrentAnnotation.getId();
	}
	
	/**
	 * Set the current Adventure that the user is viewing
	 * 
	 * @param adventure
	 *            The current adventure to set
	 */
	public void setCurrentAdventure( int adventure, int sectionId )
	{
		mCurrentAdventure = AdventureCache.getAdventureCache().getAdventureById( adventure );
		setCurrentSection( mCurrentAdventure.getSection( sectionId ) );
		
	}
	
	/**
	 * Set the current Section that the user is viewing
	 * 
	 * @param section
	 *            The current section to set
	 */
	public void setCurrentSection( SectionModel section )
	{
		mCurrentSection = section;
		setCurrentAnnotation( mCurrentSection.getAnnotation() );
	}
	
	/**
	 * Takes a bitmap (which is store in the intent) and adds it as a Media to
	 * the current section.
	 * 
	 * @param view
	 *            The current AnnotationView
	 * @param data
	 *            The data taken from the camera
	 */

	public void storeImage( AnnotationEditView view, Intent data )
	{
		ImageMedia newImageMedia = ImageCreator.storeImage( view, data );
		if( newImageMedia != null)
		{
			mCurrentAnnotation.add( newImageMedia );
		}
	}
	
	/**
	 * This takes a new bitmap and the position of the media in the current
	 * annotations media list and sets the new bitmap
	 * 
	 * @param newBitmap
	 *            New bitmap to get set
	 * @param mediaPos
	 *            Position in media array list
	 */
	public void resizeBitmap( Bitmap newBitmap, int mediaPos )
	{
		ImageCreator.resizeBitmap( newBitmap, mediaPos, mCurrentAnnotation.getMedia() );
	}
	
	/**
	 * Get the media that's in the annotation
	 *
	 */
	public ArrayList<Media> getMedia()
	{
		return mCurrentAnnotation.getMedia();
	}
	
	public void uploadAnnotation()
	{
		DatabaseInteractor.getDatabaseInteractor().addAdventure( mCurrentAdventure );
	}
	
}
