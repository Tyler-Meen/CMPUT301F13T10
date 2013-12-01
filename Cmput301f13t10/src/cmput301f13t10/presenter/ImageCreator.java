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
import android.support.v4.app.FragmentActivity;

/**
 * Static class for storing new images as well as resizing an image.
 * 
 * @author Braeden Soetaert
 * 
 */
public class ImageCreator
{

	private ImageCreator()
	{
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

	public static ImageMedia storeImage( FragmentActivity view, Intent data )
	{
		Bitmap tempBitmap = null;

		if( data.getData() != null )
		{
			try
			{
				// TODO Decouple the editView from the data.
				InputStream stream = view.getContentResolver().openInputStream( data.getData() );

				tempBitmap = BitmapFactory.decodeStream( stream );
				stream.close();
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		else
		{
			tempBitmap = (Bitmap) data.getExtras().get( "data" );
		}
		if( tempBitmap != null )
		{
			tempBitmap = Bitmap.createScaledBitmap( tempBitmap, 670, 670, true );

			ImageMedia newImageMedia = new ImageMedia();
			newImageMedia.setImageBitmap( tempBitmap );
			return newImageMedia;
		}
		return null;
	}

	/**
	 * This takes a new bitmap and the position of the media in the media list
	 * and sets the new bitmap
	 * 
	 * @param newBitmap
	 *            New bitmap to get set
	 * @param mediaPos
	 *            Position in media array list
	 * @param media
	 *            The media array list that the image media is in.
	 */
	public static void resizeBitmap( Bitmap newBitmap, int mediaPos, ArrayList<Media> media )
	{
		( (ImageMedia) media.get( mediaPos ) ).setImageBitmap( newBitmap );
	}
}
