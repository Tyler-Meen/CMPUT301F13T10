
/*
Copyright (c) 2013, Brendan Cowan, Tyler Meen, Steven Gerdes, Braeden Soetaert, Aly-khan Jamal
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
*/
package cmput301f13t10.model;

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
 * @author Aly-khan Jamal
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
	 * The interactor that this cache looks to if it does not contain the requested adventures
	 */
	private AdventureInteractor mInteractor;

	/**
	 * Constructor with dependency injection
	 */
	public AdventureCache(AdventureInteractor interactor)
	{
		adventures = new HashMap<Integer, AdventureModel>();
		mInteractor = interactor;
	}
	
	/**
	 * Constructor with dependency injection
	 */
	public AdventureCache()
	{
		adventures = new HashMap<Integer, AdventureModel>();
		mInteractor = null;
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

	@Override
	public void deleteAdventure( AdventureModel adventure )
	{
		// TODO Auto-generated method stub
		
	}
}
