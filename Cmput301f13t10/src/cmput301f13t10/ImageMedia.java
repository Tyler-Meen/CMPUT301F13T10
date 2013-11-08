
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
package cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

/**
 * Media for pictures that can be added and viewed in a section of an adventure.
 * 
 * @author Brendan Cowan
 * 
 */
public class ImageMedia implements Media
{

	/**
	 * The id of the media object.
	 */
	private int mId;

	/**
	 * The bitmap object associated with the media object
	 */
	private Uri mImagePath;
	
	private Bitmap mImageBitmap;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The image's id.
	 */
	public ImageMedia()
	{
		mId = IdFactory.getIdManager( AppConstants.GENERATE_MEDIA_ID ).getNewId();
	}

	/**
	 * Set the image associated with the media object
	 * 
	 * @param bm
	 *            The bitmap file to use
	 */
	public void setImagePath( Uri ip )
	{
		mImagePath = ip;
	}
	
	public void setImageBitmap( Bitmap bm )
	{
		mImageBitmap = bm;
	}

	@Override
	public int getId()
	{
		return mId;
	}

	@Override
	public View toView( Context c )
	{
		ImageView iv = new ImageView( c );
		iv.setImageBitmap( mImageBitmap );
		//iv.setImageDrawable( Drawable.createFromPath( mImagePath.getPath() ) );
		return iv;
	}

	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeInt( mId );
		out.writeObject( mImagePath );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mId = (int) in.readInt();
		mImagePath = (Uri) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}

}
