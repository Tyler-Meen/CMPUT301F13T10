/*
 * SearcherTest
 * 
 * Version 0.1
 * 
 * October 18, 2013
 * 
 * Copyright: Brendan Cowan
 */

package cs.ualberta.cmput301f13t10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearcherTest
{

	private ArrayList<Adventure> mAdventures;
	private Adventure mFullMatch;
	private Adventure mPartMatch;
	private Adventure mNoMatch;
	private List<Adventure> mResults;
	private Exception mThrown;

	public SearcherTest()
	{
	}

	@Before
	public void setUp() throws Exception
	{
		mAdventures = new ArrayList<Adventure>();

		mFullMatch = new Adventure();
		mPartMatch = new Adventure();
		mNoMatch = new Adventure();

		mFullMatch.setTitle( "bcd" );
		mPartMatch.setTitle( "abcdef" );
		mNoMatch.setTitle( "cd" );

		mAdventures.add( mFullMatch );
		mAdventures.add( mPartMatch );
		mAdventures.add( mNoMatch );
	}

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
