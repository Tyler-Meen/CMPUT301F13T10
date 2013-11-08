package cmput301f13t10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A store of adventures.
 * 
 * @author Brendan Cowan
 * 
 * @author Braeden Soetaert
 * 
 */
public class AdventureCache implements AdventureInteractor
{
	/**
	 * The instance of the adventure cache.
	 */
	private static AdventureCache ac = null;

	/**
	 * The map of stored adventures.
	 */
	private Map<Integer, AdventureModel> adventures;

	/**
	 * Constructor
	 */
	public AdventureCache()
	{
		adventures = new HashMap<Integer, AdventureModel>();
	}

	/**
	 * Get an instance of the adventure cache
	 * 
	 * @return an instance of the cache.
	 */
	public static synchronized AdventureCache getAdventureCache()
	{
		if( ac == null )
			ac = new AdventureCache();
		return ac;
	}

	/**
	 * Add an adventure to the cache.
	 */
	@Override
	public void addAdventure( AdventureModel adventure )
	{
		adventures.put( adventure.getId(), adventure );
	}

	/**
	 * @return the adventure with the given id or null if the key is not in the
	 *         cache.
	 */
	@Override
	public AdventureModel getAdventureById( int id )
	{
		return adventures.get( id );
	}

	/**
	 * Returns a list of all adventures in the cache
	 * 
	 * @return a list of all adventures in the cache.
	 */
	@Override
	public ArrayList<AdventureModel> getAllAdventures()
	{
		ArrayList<AdventureModel> alladventures = new ArrayList<AdventureModel>();

		for( Integer key : adventures.keySet() )
		{
			alladventures.add( adventures.get( key ) );
		}

		return alladventures;

	}
}
