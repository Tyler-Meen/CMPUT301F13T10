package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.IdFactory;

public class IdFactoryTest
{

	/**
	 * {@value mThrown}
	 */
	Exception mThrown;

	public IdFactoryTest()
	{
	}

	/**
	 * Set up the tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		IdFactory.getIdFactory().removeAll();
		mThrown = null;
	}

	/**
	 * Tear down the tests
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test that each time the id factory is called, it returns a new id.
	 */
	@Test
	public void testUniqueId()
	{
		IdFactory idFactory = IdFactory.getIdFactory();
		assertEquals( idFactory.getNewId(), 0 );
		assertEquals( idFactory.getNewId(), 1 );
		assertEquals( idFactory.getNewId(), 2 );
	}

	/**
	 * Test that different calls to getIdFactory returns the same factory
	 */
	@Test
	public void testSingleton()
	{
		assertEquals( IdFactory.getIdFactory().getNewId(), 0 );
		assertEquals( IdFactory.getIdFactory().getNewId(), 1 );
	}

	/**
	 * Test that when you remove an id from the factory, it gets reused
	 */
	@Test
	public void testRemoveId()
	{
		IdFactory idFactory = IdFactory.getIdFactory();
		for( int i = 0; i < 3; i++ )
			idFactory.getNewId();
		try
		{
			idFactory.removeId( 1 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNull( mThrown );
		assertEquals( idFactory.getNewId(), 1 );
		assertEquals( idFactory.getNewId(), 3 );
	}

	/**
	 * Test that when an Id is removed from the end, that new ids are added
	 * starting from the earliest usable id
	 */
	@Test
	public void testRemoveIdFromEnd()
	{
		IdFactory idFactory = IdFactory.getIdFactory();
		for( int i = 0; i < 5; i++ )
			idFactory.getNewId();
		try
		{
			idFactory.removeId( 2 );
			idFactory.removeId( 3 );
			idFactory.removeId( 4 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNull( mThrown );
		assertEquals( idFactory.getNewId(), 2 );
		assertEquals( idFactory.getNewId(), 3 );
		assertEquals( idFactory.getNewId(), 4 );
		assertEquals( idFactory.getNewId(), 5 );
	}

	/**
	 * Test that an ArrayindexOutOfBoundsException is thrown when you try to
	 * remove an id that has already been removed
	 */
	@Test
	public void testRemoveNonExistant()
	{
		IdFactory idFactory = IdFactory.getIdFactory();
		for( int i = 0; i < 5; i++ )
			idFactory.getNewId();
		try
		{
			idFactory.removeId( 3 );
			idFactory.removeId( 3 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNotNull( mThrown );
		assertEquals( idFactory.getNewId(), 3 );
		assertEquals( idFactory.getNewId(), 5 );
	}

	/**
	 * Test that an ArrayindexOutOfBoundsException is thrown when you try to
	 * remove an id that is not in use
	 */
	@Test
	public void testRemoveOutOfBounds()
	{
		IdFactory idFactory = IdFactory.getIdFactory();
		idFactory.getNewId();
		try
		{
			idFactory.removeId( 1 );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			mThrown = e;
		}
		assertNotNull( mThrown );
		assertEquals( idFactory.getNewId(), 1 );
	}

}
