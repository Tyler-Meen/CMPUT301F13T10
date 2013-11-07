package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.Media;
import cmput301f13t10.SectionModel;

public class SectionModelTest
{

	/**
	 * {@value mSection}
	 */
	private SectionModel mSection;
	/**
	 * {@value mMedia1}
	 */
	private MockMedia mMedia1;
	/**
	 * {@value mMedia1Id}
	 */
	private int mMedia1Id;
	/**
	 * {@value mMedia2}
	 */
	private MockMedia mMedia2;
	/**
	 * {@value mMedia2Id}
	 */
	private int mMedia2Id;
	/**
	 * {@value mMedia3}
	 */
	private MockMedia mMedia3;
	/**
	 * {@value mMedia3Id}
	 */
	private int mMedia3Id;

	public SectionModelTest()
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
		mSection = new SectionModel( "test" );

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
	 * Test that, when media are added to a section, they are inserted in the
	 * same order they are added in
	 */
	@Test
	public void testAddMedia()
	{
		ArrayList<Media> medias = mSection.getMedia();

		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia2Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

	/**
	 * test that when a media is removed for a section, it no longer appears in
	 * the section, and the gap it would have created is filled.
	 */
	@Test
	public void testRemoveMedia()
	{
		mSection.remove( 1 );

		ArrayList<Media> medias = mSection.getMedia();

		assertEquals( medias.size(), 2 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia3Id );
	}

	/**
	 * Test that if a media is moved in the positive direction, it appears in
	 * the position indicated.
	 */
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

	/**
	 * Test that if a media is moved in the negative direction, it appears in
	 * the position indicated.
	 */
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

	/**
	 * Test that if you try to move a media in the positive direction, and it
	 * will go out of bounds if you do, that an IndexOutOfBoundsException.
	 */
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

	/**
	 * Test that if you try to move a media in the negative direction, and it
	 * will go out of bounds if you do, that an IndexOutOfBoundsException.
	 */
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

	/**
	 * Test that if you try to move a media by zero, it does not move.
	 */
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