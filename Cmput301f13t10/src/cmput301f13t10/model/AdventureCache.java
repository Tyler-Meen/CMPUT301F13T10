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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cmput301f13t10.presenter.AppConstants;
import cmput301f13t10.view.MainActivity;

/**
 * A store of adventures.
 * 
 * @author Brendan Cowan
 * 
 * @author Braeden Soetaert
 * 
 * @author Aly-khan Jamal
 */
public class AdventureCache
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
	 * Constructor without dependency injection
	 */
	public AdventureCache()
	{
		adventures = new HashMap<Integer, AdventureModel>();
		FileInputStream fileInputStream = null;
		try
		{
			fileInputStream = MainActivity.getContext().openFileInput( AppConstants.FILE_NAME );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		ArrayList<AdventureModel> advList = FileInteractor.loadAdventures( fileInputStream );
		for( AdventureModel adv : advList )
			adventures.put( adv.getLocalId(), adv );
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
	public void addAdventure( AdventureModel adventure )
	{
		adventures.put( adventure.getLocalId(), adventure );
	}

	/**
	 * @return the adventure with the given id or null if the key is not in the
	 *         cache.
	 */
	public AdventureModel getAdventureById( int id )
	{
		return adventures.get( id );
	}

	/**
	 * Return all adventures in the cache
	 * @return An arraylist of all adventures in the cache
	 */
	public ArrayList<AdventureModel> getAllAdventures()
	{
		ArrayList<AdventureModel> alladventures = new ArrayList<AdventureModel>();

		for( Integer key : adventures.keySet() )
		{
			alladventures.add( adventures.get( key ) );
		}

		return alladventures;

	}

	/**
	 * If the cache contains the adventure with the given local id
	 * @param adventure The adventure to compare against
	 * @return True if the cache contains the adventure. False otherwise.
	 */
	public boolean containsLocal( AdventureModel adventure )
	{
		return adventures.containsKey( adventure.getLocalId() );
	}

	/**
	 * If the cache contains the adventure with the given remote id
	 * @param adventure The adventure to compare against
	 * @return True if the cache contains the adventure. False otherwise.
	 */
	public boolean containsRemote( AdventureModel adventure )
	{
		if( adventure == null )
			return false;
		for( Integer id : adventures.keySet() )
		{
			if( adventures.get( id ).getRemoteId() == adventure.getRemoteId() )
				return true;
		}

		return false;
	}

	/**
	 * Delete an adventure from the cache
	 * @param adventure The adventure to remove
	 */
	public void deleteAdventure( AdventureModel adventure )
	{
		adventures.remove( adventure.getLocalId() );

	}
}
