package cmput301f13t10;

import java.util.ArrayList;

/**
 * An interface for accessing stored adventures.
 * 
 * @author Braeden Soetaert
 * 
 */
public interface AdventureInteractor
{
	/**
	 * Add an adventure
	 * 
	 * @param adventure
	 *            The adventure to add.
	 */
	public void addAdventure( AdventureModel adventure );

	/**
	 * Returns a list of all adventures in the cache
	 * 
	 * @return a list of all adventures in the cache.
	 */
	public ArrayList<AdventureModel> getAllAdventures();

	/**
	 * @return the adventure with the given id or null if the key is not in the
	 *         cache.
	 */
	public AdventureModel getAdventureById( int id );
}
