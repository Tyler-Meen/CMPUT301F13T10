
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
package cmput301f13t10.model;

import java.util.ArrayList;

import cmput301f13t10.presenter.AppConstants;

/**
 * Factory class that generates Id numbers to be used as identifiers for objects
 * 
 * @author Brendan Cowan
 * 
 */
public class IdFactory
{
	/**
	 * The singleton IdFactory instance
	 */
	private static IdFactory mIdFactory = null;

	/**
	 * manager used to generate ids for sections
	 */
	private static IdManager mSectionIdManager = null;

	/**
	 * manager used to generate ids for sections
	 */
	private static IdManager mAdventureIdManager = null;

	/**
	 * manager used to generate ids for sections
	 */
	private static IdManager mChoiceIdManager = null;

	/**
	 * manager used to generate ids for medias
	 */
	private static IdManager mMediaIdManager = null;

	/**
	 * Singleton constructor.
	 */
	protected IdFactory()
	{
		mSectionIdManager = new IdManager();
		mAdventureIdManager = new IdManager();
		mChoiceIdManager = new IdManager();
		mMediaIdManager = new IdManager();
	}

	/**
	 * Singleton getter.
	 * 
	 * @return The static instance of the IdFactory.
	 */
	public static IdFactory getIdFactory()
	{
		if( mIdFactory == null )
			mIdFactory = new IdFactory();
		return mIdFactory;
	}

	public static IdManager getIdManager( String type )
	{
		getIdFactory();
		if( type == AppConstants.GENERATE_ADVENTURE_ID )
		{
			return mAdventureIdManager;
		}
		else if( type == AppConstants.GENERATE_CHOICE_ID )
		{
			return mChoiceIdManager;
		}
		else if( type == AppConstants.GENERATE_SECTION_ID )
		{
			return mSectionIdManager;
		}
		else if( type == AppConstants.GENERATE_MEDIA_ID )
		{
			return mMediaIdManager;
		}

		return null;

	}

	public void assignLocalId( AdventureModel adventure )
	{
		for( SectionModel section : adventure.getSections()) {
			section.setId( mSectionIdManager.getNewId() );
		}
		adventure.setLocalId( mAdventureIdManager.getNewId() );
		
	}
}
