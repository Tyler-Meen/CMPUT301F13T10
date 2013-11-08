package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.AppConstants;
import cmput301f13t10.IdFactory;

public class IdFactoryTest
{

	Exception mThrown;

	public IdFactoryTest()
	{
	}

	@Before
	public void setUp() throws Exception
	{
		IdFactory.getIdManager( AppConstants.GENERATE_ADVENTURE_ID ).removeAll();
		mThrown = null;
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testSingleton()
	{
		assertEquals( IdFactory.getIdManager( AppConstants.GENERATE_ADVENTURE_ID ).getNewId(), 0 );
		assertEquals( IdFactory.getIdManager( AppConstants.GENERATE_ADVENTURE_ID ).getNewId(), 1 );
	}

}
