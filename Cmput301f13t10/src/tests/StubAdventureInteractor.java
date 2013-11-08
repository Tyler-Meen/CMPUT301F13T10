package tests;

import java.util.ArrayList;

import cmput301f13t10.AdventureInteractor;
import cmput301f13t10.AdventureModel;

/**
 * Simple stubbed Adventure Interactor. Functions just well enough that you can
 * tell what methods were called.
 * 
 * @author Brendan Cowan
 * 
 */
public class StubAdventureInteractor implements AdventureInteractor
{
	/**
	 * All adventures that have been added
	 */
	private ArrayList<AdventureModel> mAddedAdventures;

	/**
	 * Constructor
	 */
	public StubAdventureInteractor()
	{
		mAddedAdventures = new ArrayList<AdventureModel>();
	}

	@Override
	public void addAdventure( AdventureModel adventure )
	{
		mAddedAdventures.add( adventure );
	}

	/**
	 * get all adventures that were added with addAdventure
	 * @return The adventures
	 */
	public ArrayList<AdventureModel> getAddedAdventures()
	{
		return mAddedAdventures;
	}

	@Override
	public ArrayList<AdventureModel> getAllAdventures()
	{
		ArrayList<AdventureModel> onlineAdventures = new ArrayList<AdventureModel>();
		onlineAdventures.add( new AdventureModel() );
		return onlineAdventures;
	}

	@Override
	public AdventureModel getAdventureById( int id )
	{
		AdventureModel adventure = new AdventureModel();
		adventure.setTitle( "Test" );
		return adventure;
	}

	@Override
	public void deleteAdventure( AdventureModel adventure )
	{
		mAddedAdventures.remove( adventure );
	}

}
