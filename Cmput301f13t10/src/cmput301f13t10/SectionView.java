package cmput301f13t10;

import android.content.Context;

/**
 * Interface for section views. Can update the view, and get the context of the view.
 * @author Steven Gerdes
 *
 */
public interface SectionView
{
	/**
	 * Update the section view with new data.
	 */
	public void updateSectionView();
	/**
	 * Get the context of the section view
	 * @return The context of the section view.
	 */
	public Context getContext();
}
