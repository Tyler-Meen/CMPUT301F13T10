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

import java.io.IOException;
import java.io.ObjectStreamException;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import cmput301f13t10.model.IdFactory;

/**
 * Media for text that can be added and view in a section of an adventure.
 * 
 * @author Brendan Cowan
 * 
 */
public class TextMedia implements Media
{

	/**
	 * The id of the media
	 */
	private int mId;
	/**
	 * The text to be displayed
	 */
	private String mText;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The id of the media
	 */
	public TextMedia()
	{
		mText = "";
		mId = IdFactory.getIdManager( AppConstants.GENERATE_MEDIA_ID ).getNewId();
	}

	/**
	 * Set the text to be displayed for the media
	 * 
	 * @param text
	 *            The text to be displayed
	 */
	public void setText( String text )
	{
		mText = text;
	}

	public String getText()
	{
		return mText;
	}

	@Override
	public int getId()
	{
		return mId;
	}

	@Override
	public View toView( Context c )
	{
		EditText addText = new EditText( c );
		addText.setId( getId() );
		LayoutParams params = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1 );
		addText.setLayoutParams( params );
		addText.setText( getText() );
		addText.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_MULTI_LINE );
		addText.addTextChangedListener( new TextWatcher()
		{
			public void afterTextChanged( Editable s )
			{
				setText( s.toString() );
			}

			public void beforeTextChanged( CharSequence s, int start, int count, int after )
			{
			}

			public void onTextChanged( CharSequence s, int start, int before, int count )
			{
			}
		} );

		return addText;
	}

	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeInt( mId );
		out.writeObject( mText );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mId = (int) in.readInt();
		mText = (String) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}

}
