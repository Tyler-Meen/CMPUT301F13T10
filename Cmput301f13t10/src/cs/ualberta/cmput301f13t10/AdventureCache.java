package cs.ualberta.cmput301f13t10;

import java.util.HashMap;
import java.util.Map;

public class AdventureCache
{

	private static AdventureCache ac = null;
	private Map<Integer, AdventureModel> adventures;
	
	protected AdventureCache() {
		adventures = new HashMap<Integer, AdventureModel>();
	}
	
	public static AdventureCache getAdventureCache() {
		if (ac == null)
			ac = new AdventureCache();
		return ac;
	}
	
	public void addAdventure(AdventureModel adventure) {
		adventures.put( adventure.getId() , adventure );
	}
	
	public AdventureModel getAdventureById(int id) {
		return adventures.get( id );
	}
}
