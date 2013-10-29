/*
 * Searcher
 * 
 * Version 0.1
 * 
 * October 18, 2013
 * 
 * Copyright: Brendan Cowan
 */

package cs.ualberta.cmput301f13t10;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class Searcher {

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
	public static ArrayList<Adventure> searchBy(List<Adventure> adventures,
			String query, String searchType) throws InvalidSearchTypeException {

		ArrayList<Adventure> returnAdventures = new ArrayList<Adventure>();

		if (searchType != sTITLE)
			throw new InvalidSearchTypeException(searchType
					+ " is not a valid searchType for searchBy()");

		for (ListIterator<Adventure> i = adventures.listIterator(); i.hasNext();) {

			String value = "";
			Adventure currentAdventure = i.next();
			if (searchType == sTITLE)
				value = currentAdventure.getTitle();
			if (value.toLowerCase(Locale.CANADA).contains(query.toLowerCase()))
				returnAdventures.add(currentAdventure);
		}

		return returnAdventures;
	}
}
