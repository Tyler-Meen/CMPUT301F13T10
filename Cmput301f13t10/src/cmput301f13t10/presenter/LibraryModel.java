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
package cmput301f13t10.presenter;

import cmput301f13t10.model.AdventureCache;

import java.util.ArrayList;
import java.util.Random;

import cmput301f13t10.model.AdventureModel;
import cmput301f13t10.model.DatabaseInteractor;
import cmput301f13t10.model.InvalidSearchTypeException;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.Serializable;

import cmput301f13t10.model.FileInteractor;

/**
 * Data associated with the library's in the app.
 * 
 * @author Braeden Soetaert
 * 
 */
public class LibraryModel
{
	private AdventureCache mCache;
	private ArrayList<AdventureModel> mAdventureList;
	private int mCurrentAdventureId;
	
	public LibraryModel()
	{
		mAdventureList = new ArrayList<AdventureModel>();
		mCache = AdventureCache.getAdventureCache();
	}

	public AdventureCache getCache()
	{
		return mCache;
	}

	public void setCache( AdventureCache cache )
	{
		this.mCache = cache;
	}

	public ArrayList<AdventureModel> getAdventureList()
	{
		return mAdventureList;
	}

	public void setAdventureList( ArrayList<AdventureModel> adventureList )
	{
		this.mAdventureList = adventureList;
	}

	public void loadData()
	{
		mAdventureList = mCache.getAllAdventures();
	}

	public void updateAdventures()
	{
		for( AdventureModel adv : AdventureCache.getAdventureCache().getAllAdventures() )
		{
			if( !libContains( adv ) )
				mAdventureList.add( adv );
		}
	}

	public boolean libContains( AdventureModel adv )
	{
		for( AdventureModel thisAdv : mAdventureList )
		{
			if( thisAdv.getLocalId() == adv.getLocalId() )
				return true;
		}
		return false;
	}

	public void deleteAdventure( int localId )
	{
		for( int i = 0; i < mAdventureList.size(); i++ )
		{
			if( mAdventureList.get( i ).getLocalId() == localId )
			{
				mAdventureList.get( i ).setSave( false );
				AdventureCache.getAdventureCache().deleteAdventure( mAdventureList.get( i ) );
				DatabaseInteractor.getDatabaseInteractor().deleteAdventure( mAdventureList.get( i ) );
				mAdventureList.remove( i );
				break;
			}
		}
	}

	public void sortLibraryUsing( String searchText )
	{
		try
		{
			mAdventureList = Searcher.searchBy( mAdventureList, searchText, Searcher.sTITLE );
		}
		catch( InvalidSearchTypeException e )
		{
			Log.v( "Library Search Error", Searcher.sTITLE + " not a valid search type" );
			mAdventureList = mCache.getAllAdventures();
		}
	}

	public void saveData( FileOutputStream fileOutputStream )
	{
		FileInteractor.saveAdventures( AdventureCache.getAdventureCache().getAllAdventures(), fileOutputStream );
	}

	public void setRandomCurrentAdventure()
	{
		Random rand = new Random();
		setCurrentAdventure(rand.nextInt( mAdventureList.size() ));
	}

	public void setCurrentAdventure( int localId )
	{
		mCurrentAdventureId = mAdventureList.get( localId ).getLocalId();;
	}

	public AdventureModel getCurrentAdventure()
	{
		return mAdventureList.get( mCurrentAdventureId );
	}

	public int getCurrentAdventureId()
	{
		return mCurrentAdventureId;
	}
}