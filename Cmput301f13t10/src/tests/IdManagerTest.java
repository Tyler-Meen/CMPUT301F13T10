package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.IdFactory;
import cmput301f13t10.IdManager;

public class IdManagerTest
{

	Exception mThrown;
	IdManager mManager;

	public IdManagerTest()
	{
	}

	@Before
	public void setUp() throws Exception
	{
		mManager = new IdManager();
		mThrown = null;
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testUniqueId()
	{
		assertEquals( mManager.getNewId(), 0 );
		assertEquals( mManager.getNewId(), 1 );
		assertEquals( mManager.getNewId(), 2 );
	}

	@Test
	public void testRemoveId()
	{
		for( int i = 0; i < 3; i++ )
			mManager.getNewId();
		try
		{
			mManager.removeId( 1 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNull( mThrown );
		assertEquals( mManager.getNewId(), 1 );
		assertEquals( mManager.getNewId(), 3 );
	}

	@Test
	public void testRemoveIdFromEnd()
	{
		for( int i = 0; i < 5; i++ )
			mManager.getNewId();
		try
		{
			mManager.removeId( 2 );
			mManager.removeId( 3 );
			mManager.removeId( 4 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNull( mThrown );
		assertEquals( mManager.getNewId(), 2 );
		assertEquals( mManager.getNewId(), 3 );
		assertEquals( mManager.getNewId(), 4 );
		assertEquals( mManager.getNewId(), 5 );
	}

	@Test
	public void testRemoveNonExistant()
	{
		for( int i = 0; i < 5; i++ )
			mManager.getNewId();
		try
		{
			mManager.removeId( 3 );
			mManager.removeId( 3 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNotNull( mThrown );
		assertEquals( mManager.getNewId(), 3 );
		assertEquals( mManager.getNewId(), 5 );
	}

	@Test
	public void testRemoveOutOfBounds()
	{
		mManager.getNewId();
		try
		{
			mManager.removeId( 1 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNotNull( mThrown );
		assertEquals( mManager.getNewId(), 1 );
	}
}
