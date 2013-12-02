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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.model.AdventureModel;
import cmput301f13t10.model.SectionModel;

/**
 * Test the AdventureModel
 * 
 * @author Brendan Cowan
 * 
 */
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
		assertEquals( mAdventure.getSections().size(), 3 );
		assertFalse( mAdventure.getSections().contains( mSection2 ) );
	}

}
