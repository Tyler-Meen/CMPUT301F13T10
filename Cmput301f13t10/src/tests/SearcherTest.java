/*
 * SearcherTest
 * 
 * Version 0.1
 * 
 * October 18, 2013
 * 
 * Copyright: Brendan Cowan
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.AdventureModel;
import cmput301f13t10.InvalidSearchTypeException;
import cmput301f13t10.Searcher;

public class SearcherTest
{

	/**
	 * {@value mAdventures}
	 */
	private ArrayList<AdventureModel> mAdventures;
	/**
	 * {@value mFullMatch}
	 */
	private AdventureModel mFullMatch;
	/**
	 * {@value mPartMatch}
	 */
	private AdventureModel mPartMatch;
	/**
	 * {@value mNoMatch}
	 */
	private AdventureModel mNoMatch;
	/**
	 * {@value mResults}
	 */
	private List<AdventureModel> mResults;
	/**
	 * {@value mThrown}
	 */
	private Exception mThrown;

	public SearcherTest()
	{
	}

	/**
	 * Set up the tests
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		mAdventures = new ArrayList<AdventureModel>();

		mFullMatch = new AdventureModel( "FullMatch" );
		mPartMatch = new AdventureModel( "PartMatch" );
		mNoMatch = new AdventureModel( "NoMatch" );

		mFullMatch.setTitle( "bcd" );
		mPartMatch.setTitle( "abcdef" );
		mNoMatch.setTitle( "cd" );

		mAdventures.add( mFullMatch );
		mAdventures.add( mPartMatch );
		mAdventures.add( mNoMatch );
	}

	/**
	 * Tear down the tests
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * testSearchByTitle Can the searcher return a list of adventures matching a
	 * title pattern?
	 */
	@Test
	public void testSearchByTitle()
	{

		try
		{
			mResults = Searcher.searchBy( mAdventures, "bcd", Searcher.sTITLE );
		}
		catch( InvalidSearchTypeException e )
		{
			mThrown = e;
		}

		org.junit.Assert.assertNull( mThrown );
		assertEquals( mResults.size(), 2 );
		assertTrue( mResults.contains( mFullMatch ) );
		assertTrue( mResults.contains( mPartMatch ) );
		assertFalse( mResults.contains( mNoMatch ) );
	}

	/**
	 * testBadSearchType Can the searcher give a valid error if the search type
	 * does not exist?
	 */
	@Test
	public void testBadSearchType()
	{
		try
		{
			mResults = Searcher.searchBy( mAdventures, "bcd", "invalid" );
		}
		catch( InvalidSearchTypeException e )
		{
			mThrown = e;
		}
		org.junit.Assert.assertNotNull( mThrown );
	}

}
