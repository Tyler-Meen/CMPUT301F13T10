package cs.ualberta.cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;

import android.content.Context;
import android.graphics.Bitmap;
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
	private Bitmap mBitmap;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The image's id.
	 */
	public ImageMedia()
	{
		mId = IdFactory.getIdFactory().getNewId();
	}

	/**
	 * Set the image associated with the media object
	 * 
	 * @param bm
	 *            The bitmap file to use
	 */
	public void setImageBitmap( Bitmap bm )
	{
		mBitmap = bm;
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
		iv.setImageBitmap( mBitmap );
		return iv;
	}

	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeInt( mId );
		out.writeObject( mBitmap );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mId = (int) in.readInt();
		mBitmap = (Bitmap) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}

}
