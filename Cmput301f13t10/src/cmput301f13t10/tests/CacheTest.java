package cmput301f13t10.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.model.AdventureCache;
import cmput301f13t10.model.AdventureModel;

/**
 * Test the AdventureCache
 * 
 * @author Brendan Cowan
 * 
 */
public class CacheTest
{

	/**
	 * The adventureCache
	 */
	AdventureCache mCache;

	@Before
	public void SetUp()
	{
		mCache = new AdventureCache();
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
		AdventureModel loadedAdventure = mCache.getAdventureById( adventure.getLocalId() );
		assertEquals( loadedAdventure.getTitle(), "yeah" );
	}

}
