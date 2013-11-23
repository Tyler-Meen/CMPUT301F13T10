package cmput301f13t10.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.model.AdventureCache;
import cmput301f13t10.model.AdventureModel;


/**
 * Test the AdventureCache
 * @author Brendan Cowan
 *
 */
public class CacheTest
{

	/**
	 * The adventureCache
	 */
	AdventureCache mCache;

	/**
	 * The interactor which the cache will look to if the requested adventure
	 * does not exist in the cache
	 */
	StubAdventureInteractor mInteractor;

	@Before
	public void SetUp()
	{
		mInteractor = new StubAdventureInteractor();
		mCache = new AdventureCache( mInteractor );
	}

	/**
	 * Test that when you add a new adventure, it gets added to both the cache
	 * and the interactor its connected to.
	 */
	@Test
	public void testAddAdventure()
	{
		AdventureModel adventure = new AdventureModel();
		mCache.addAdventure( adventure );
		assertTrue( mCache.getAllAdventures().contains( adventure ) );
		assertTrue( mInteractor.getAddedAdventures().contains( adventure ) );
	}

	/**
	 * Test that when you delete an adventure it gets removed from both the
	 * cache and the interactor its connected to.
	 */
	@Test
	public void testDeleteAdventure()
	{
		AdventureModel adventure = new AdventureModel();
		mCache.addAdventure( adventure );
		mCache.deleteAdventure( adventure );
		assertFalse( mCache.getAllAdventures().contains( adventure ) );
		assertFalse( mInteractor.getAddedAdventures().contains( adventure ) );
	}

	/**
	 * test that when you try to get an adventure that is in the cache, it
	 * retrieves the one in the cache
	 */
	@Test
	public void testGetAdventureInCache()
	{
		AdventureModel adventure = new AdventureModel();
		adventure.setTitle( "yeah" );
		mCache.addAdventure( adventure );
		AdventureModel loadedAdventure = mCache.getAdventureById( adventure.getId() );
		assertEquals( loadedAdventure.getTitle(), "yeah" );
	}

	/**
	 * test that when you try to get an adventure that is not in the cache, it
	 * retrieves the one in the interacter it is connected to
	 */
	@Test
	public void testGetAdventureNotInCache()
	{
		AdventureModel adventure = mCache.getAdventureById( 9999999 );
		assertEquals( adventure.getTitle(), "Test" );
	}

}
