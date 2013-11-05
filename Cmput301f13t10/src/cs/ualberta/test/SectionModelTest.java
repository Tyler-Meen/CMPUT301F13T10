package cs.ualberta.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs.ualberta.cmput301f13t10.Media;
import cs.ualberta.cmput301f13t10.SectionModel;

public class SectionModelTest
{

	private SectionModel mSection;
	private MockMedia mMedia1;
	private int mMedia1Id;
	private MockMedia mMedia2;
	private int mMedia2Id;
	private MockMedia mMedia3;
	private int mMedia3Id;

	public SectionModelTest()
	{
	}

	@Before
	public void setUp() throws Exception
	{
		mSection = new SectionModel( "test");

		mMedia1 = new MockMedia();
		mMedia2 = new MockMedia();
		mMedia3 = new MockMedia();

		mSection.add( mMedia1 );
		mSection.add( mMedia2 );
		mSection.add( mMedia3 );
		
		mMedia1Id = mMedia1.getId();
		mMedia2Id = mMedia2.getId();
		mMedia3Id = mMedia3.getId();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testAddMedia()
	{
		ArrayList<Media> medias = mSection.getMedia();

		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia2Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

	@Test
	public void testRemoveMedia()
	{
		mSection.remove( 1 );

		ArrayList<Media> medias = mSection.getMedia();

		assertEquals( medias.size(), 2 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia3Id );
	}

	@Test
	public void testMoveMediaPositive()
	{

		Exception ex = null;

		try
		{
			mSection.moveMedia( 1, 1 );
		}
		catch( IndexOutOfBoundsException e )
		{
			ex = e;
		}

		ArrayList<Media> medias = mSection.getMedia();

		assertNull( ex );
		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia3Id );
		assertEquals( medias.get( 2 ).getId(), mMedia2Id );
	}

	@Test
	public void testMoveMediaNegative()
	{

		Exception ex = null;

		try
		{
			mSection.moveMedia( 1, -1 );
		}
		catch( IndexOutOfBoundsException e )
		{
			ex = e;
		}

		ArrayList<Media> medias = mSection.getMedia();

		assertNull( ex );
		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia2Id );
		assertEquals( medias.get( 1 ).getId(), mMedia1Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

	@Test
	public void testMoveInvalidOffsetPositive()
	{

		Exception ex = null;

		try
		{
			mSection.moveMedia( 1, 2 );
		}
		catch( IndexOutOfBoundsException e )
		{
			ex = e;
		}

		ArrayList<Media> medias = mSection.getMedia();

		assertNotNull( ex );
		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia2Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

	@Test
	public void testMoveInvalidOffsetNegative()
	{

		Exception ex = null;

		try
		{
			mSection.moveMedia( 1, -2 );
		}
		catch( IndexOutOfBoundsException e )
		{
			ex = e;
		}

		ArrayList<Media> medias = mSection.getMedia();

		assertNotNull( ex );
		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia2Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

	@Test
	public void testMoveZero()
	{

		Exception ex = null;

		try
		{
			mSection.moveMedia( 1, 0 );
		}
		catch( IndexOutOfBoundsException e )
		{
			ex = e;
		}

		ArrayList<Media> medias = mSection.getMedia();

		assertNull( ex );
		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia2Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

}