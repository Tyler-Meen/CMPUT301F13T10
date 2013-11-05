package cs.ualberta.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs.ualberta.cmput301f13t10.IdFactory;

public class IdFactoryTest
{

	Exception mThrown;

	public IdFactoryTest()
	{
	}

	@Before
	public void setUp() throws Exception
	{
		IdFactory.getIdFactory().removeAll();
		mThrown = null;
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testUniqueId()
	{
		IdFactory idFactory = IdFactory.getIdFactory();
		assertEquals( idFactory.getNewId(), 0 );
		assertEquals( idFactory.getNewId(), 1 );
		assertEquals( idFactory.getNewId(), 2 );
	}

	@Test
	public void testSingleton()
	{
		assertEquals( IdFactory.getIdFactory().getNewId(), 0 );
		assertEquals( IdFactory.getIdFactory().getNewId(), 1 );
	}

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
