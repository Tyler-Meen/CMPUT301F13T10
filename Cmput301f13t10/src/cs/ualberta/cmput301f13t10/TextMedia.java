package cs.ualberta.cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

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
	 * @param id The id of the media
	 */
	public TextMedia( int id )
	{
		mId = id;
	}

	/**
	 * Set the text to be displayed for the media
	 * @param text The text to be displayed
	 */
	public void setText( String text )
	{
		mText = text;
	}

	@Override
	public void setId( int id )
	{
		mId = id;
	}

	@Override
	public int getId()
	{
		return mId;
	}

	@Override
	public View toView( Context c )
	{
		TextView mc = new TextView( c );
		mc.setText( mText );
		return mc;
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
