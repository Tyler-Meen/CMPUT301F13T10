package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.AdventureModel;
import cmput301f13t10.SectionModel;

public class AdventureModelTest
{

	AdventureModel mAdventure;
	SectionModel mSection1;
	SectionModel mSection2;
	SectionModel mSection3;

	@Before
	public void SetUp()
	{
		mAdventure = new AdventureModel();
		mSection1 = new SectionModel( "1" );
		mSection2 = new SectionModel( "2" );
		mSection3 = new SectionModel( "3" );
		mAdventure.addSection( mSection1 );
		mAdventure.addSection( mSection2 );
		mAdventure.addSection( mSection3 );
	}

	@Test
	public void testAddSection()
	{
		assertEquals( mAdventure.getSections().size(), 4 );
		assertTrue( mAdventure.getSections().contains( mSection1 ) );
		assertTrue( mAdventure.getSections().contains( mSection2 ) );
		assertTrue( mAdventure.getSections().contains( mSection3 ) );
	}

	@Test
	public void testDeleteSection()
	{
		mAdventure.deleteSection( mSection2.getId() );
		assertEquals(mAdventure.getSections().size(), 3);
		assertFalse(mAdventure.getSections().contains( mSection2 ));
	}

}
