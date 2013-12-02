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

package cmput301f13t10.presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import cmput301f13t10.model.AdventureModel;
import cmput301f13t10.model.InvalidSearchTypeException;

/**
 * 
 * Searcher
 * 
 * Version 0.1
 * 
 * October 18, 2013
 * 
 * @author: Brendan Cowan
 */
public class Searcher
{

	/**
	 * {@value TITLE}
	 */
	public static String sTITLE = "TITLE";

	/**
	 * Searches through the list of Adventures for a given pattern in their
	 * titles, and returns any of the adventures in the list that meet the
	 * criteria.
	 * 
	 * @throws InvalidSearchTypeException
	 *             if the provided search type is not supported
	 * @param adventures
	 *            A list of Adventures to search through
	 * @param query
	 *            A string to match in the Adventures.
	 * @param searchType
	 *            A string representing the member variable type of the
	 *            adventures to query. Valid inputs are mTITLE.
	 * @return A list of Adventures that meet the search criteria.
	 */
	public static ArrayList<AdventureModel> searchBy( List<AdventureModel> adventures, String query, String searchType ) throws InvalidSearchTypeException
	{

		ArrayList<AdventureModel> returnAdventures = new ArrayList<AdventureModel>();

		if( searchType != sTITLE )
			throw new InvalidSearchTypeException( searchType + " is not a valid searchType for searchBy()" );

		for( ListIterator<AdventureModel> i = adventures.listIterator(); i.hasNext(); )
		{

			AdventureModel currentAdventure = i.next();
			String value = getSearchValue( searchType, currentAdventure );
			if( value.toLowerCase( Locale.CANADA ).contains( query.toLowerCase() ) )
				returnAdventures.add( 0, currentAdventure );
			else
				returnAdventures.add( currentAdventure );
		}

		return returnAdventures;
	}

	/**
	 * Get the value from the adventure for the search type.
	 * 
	 * @param searchType The type of search being done.
	 * @param adventure The adventure to get a value from.
	 * @return The value that is being searched on.
	 */
	private static String getSearchValue( String searchType, AdventureModel adventure )
	{
		String value = "";
		if( searchType == sTITLE )
		{
			value = adventure.getTitle();
		}
		return value;
	}
}
