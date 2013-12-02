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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import cmput301f13t10.model.IdFactory;

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
	private transient Bitmap mImageBitmap = null;

	/**
	 * The base 64 representation of the bitmap object
	 */
	private String mBase64String;

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
	public void setImageBitmap( Bitmap bm )
	{
		mImageBitmap = bm;
		mBase64String = toBase64( bm );
		Log.d( "debug", mBase64String );
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
		iv.setClickable( true );
		iv.setImageBitmap( getImageBitmap() );
		return iv;
	}

	/**
	 * Returns the image associated with the media object
	 */

	public Bitmap getImageBitmap()
	{
		if( mImageBitmap == null )
			mImageBitmap = toBitmap( mBase64String );
		return mImageBitmap;
	}

	/**
	 * Write the serializable object
	 * 
	 * @param out
	 *            The output stream to write the object to
	 * @throws IOException
	 */
	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeInt( mId );
		out.writeObject( mBase64String );
	}

	/**
	 * Read the serializable object
	 * 
	 * @param in
	 *            The input stream to read the object from
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mId = (int) in.readInt();
		mBase64String = (String) in.readObject();
		mImageBitmap = toBitmap( mBase64String );
	}

	/**
	 * Convert the bitmap image into a base 64 string
	 * 
	 * @param image
	 *            The image to convert
	 * @return The base 64 string representation of the string
	 */
	private String toBase64( Bitmap image )
	{
		Bitmap imageCopy = Bitmap.createBitmap( image );
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		imageCopy.compress( Bitmap.CompressFormat.JPEG, 100, outputStream );
		byte[] bytes = outputStream.toByteArray();

		return Base64.encodeToString( bytes, Base64.DEFAULT );
	}

	/**
	 * Convert the Base 64 string into a bitmap image
	 * 
	 * @param base64String
	 *            The string to convert
	 * @return The converted Bitmap image
	 */
	private Bitmap toBitmap( String base64String )
	{
		byte[] decodedByte = Base64.decode( base64String, Base64.DEFAULT );
		return BitmapFactory.decodeByteArray( decodedByte, 0, decodedByte.length );
	}
}
