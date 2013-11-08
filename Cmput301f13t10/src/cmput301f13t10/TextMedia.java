package cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import cs.ualberta.cmput301f13t10.R;

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
