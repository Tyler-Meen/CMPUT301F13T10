/*
Copyright (c) 2013, Brendan Cowan, Tyler Meen, Steven Gerdes, Braeden Soetaert, Aly-khan Jamal
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
*/
package cmput301f13t10.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.model.SectionModel;
import cmput301f13t10.presenter.Media;


/**
 * Test the SectionModel
 * 
 * @author Brendan Cowan
 *
 */
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