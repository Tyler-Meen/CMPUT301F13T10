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
