package cs.ualberta.cmput301f13t10;

import java.io.IOException;
import java.io.ObjectStreamException;

import android.content.Context;
import android.view.View;
import android.widget.VideoView;

/**
 * Media for sound that can be added and listened to in a section of an
 * adventure.
 * 
 * @author Brendan Cowan
 * 
 */
public class SoundMedia implements Media
{

	/**
	 * The id of the media
	 */
	private int mId;
	/**
	 * The file path of the sound.
	 */
	private String mPath;

	/**
	 * Constructor
	 * 
	 * @param id
	 *            The id of the media
	 */
	public SoundMedia()
	{
		mId = IdFactory.getIdFactory().getNewId();
	}

	/**
	 * Set the file path of the sound
	 * 
	 * @param path
	 *            The file path of the sound
	 */
	public void setSound( String path )
	{
		mPath = path;
	}

	@Override
	public int getId()
	{
		return mId;
	}

	@Override
	public View toView( Context c )
	{
		VideoView sound = new VideoView( c );
		sound.setVideoPath( mPath );
		return sound;
	}

	private void writeObject( java.io.ObjectOutputStream out ) throws IOException
	{
		out.writeInt( mId );
		out.writeObject( mPath );
	}

	private void readObject( java.io.ObjectInputStream in ) throws IOException, ClassNotFoundException
	{
		mId = (int) in.readInt();
		mPath = (String) in.readObject();
	}

	private void readObjectNoData() throws ObjectStreamException
	{
	}

}
