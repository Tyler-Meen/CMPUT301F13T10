package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.AdventureModel;
import cmput301f13t10.SectionModel;

public class AdventureModelTest
{

	/**
	 * {@value mAdventure}
	 */
	AdventureModel mAdventure;

	/**
	 * Set up the test
	 */
	@Before
	public void SetUp()
	{
		mAdventure = new AdventureModel( "Test" );
	}

	/**
	 * Tear down the test
	 */
	@After
	public void TearDown()
	{

	}

	/**
	 * Test that the idexOf function returns the sections in the order they were
	 * created, and -1 if it does not exit
	 */
	@Test
	public void testIndexOf()
	{
		SectionModel SM1 = new SectionModel( "ONE" );
		SectionModel SM2 = new SectionModel( "TWO" );
		SectionModel SM3 = new SectionModel( "THREE" );
		SectionModel SM4 = new SectionModel( "FOUR" );
		mAdventure.addSection( SM1 );
		mAdventure.addSection( SM2 );
		mAdventure.addSection( SM3 );

		assertEquals( mAdventure.indexOf( SM1 ), 1 );
		assertEquals( mAdventure.indexOf( SM2 ), 2 );
		assertEquals( mAdventure.indexOf( SM3 ), 3 );
		assertEquals( mAdventure.indexOf( SM4 ), -1 );
	}

	/**
	 * Test that when you try to set a section, it overwrites the old one, or it
	 * adds the section if it does not exist
	 */
	@Test
	public void testSetSection()
	{
		SectionModel SM1 = new SectionModel( "ONE" );
		mAdventure.setSection( SM1 );

		ArrayList<SectionModel> returnedSections = mAdventure.getSections();
		assertEquals( returnedSections.size(), 2 );
		assertEquals( returnedSections.get( 1 ).getId(), SM1.getId() );
		assertEquals( returnedSections.get( 1 ).getName(), "ONE" );

		SM1.setName( "TWO" );
		mAdventure.setSection( SM1 );
		assertEquals( returnedSections.size(), 2 );
		assertEquals( returnedSections.get( 1 ).getId(), SM1.getId() );
		assertEquals( returnedSections.get( 1 ).getName(), "TWO" );

	}

}
