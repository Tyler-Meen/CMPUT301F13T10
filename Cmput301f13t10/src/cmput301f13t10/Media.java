package cmput301f13t10;

import java.io.Serializable;

import android.content.Context;
import android.view.View;

/**
 * This class is implemented for medias that can be represented by an android
 * view
 * 
 * @author Brendan Cowan
 * 
 */
public interface Media extends Serializable
{

	/**
	 * Get the id of the media
	 * 
	 * @return The id of the media
	 */
	public int getId();

	/**
	 * Convert the media to a view that android can represent
	 * 
	 * @param c
	 *            The context of the view that will be returned
	 * @return The media in the form of a View
	 */
	public View toView( Context c );
}
