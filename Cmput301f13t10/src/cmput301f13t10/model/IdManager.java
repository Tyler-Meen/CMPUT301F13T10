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
import java.util.HashSet;
import java.util.Set;

/**
 * Manages the creation and deletion of ids
 * 
 * @author Brendan Cowan
 * 
 */
public class IdManager
{

	/**
	 * A counter to determine the next id to return
	 */
	private int mNextId;
	/**
	 * A list of ids that have been used and later deleted. They can be reused.
	 */
	private Set<Integer> mReusableIds;

	/**
	 * Constructor
	 */
	public IdManager()
	{
		mReusableIds = new HashSet<Integer>();
	}

	/**
	 * Set the ids in the id manager to be considered "used" (so that they won't
	 * be used again until they are removed)
	 * 
	 * @param ids
	 *            The ids to set as used
	 */
	public void setUsedIds( ArrayList<Integer> ids )
	{
		Integer max = mNextId;
		for( Integer id : ids )
		{
			if( id > max )
				max = id;
		}

		for( Integer i = 0; i < ids.size(); i++ )
		{
			mReusableIds.add( i );
		}
		mNextId = max + 1;
	}

	/**
	 * Generate a new id.
	 * 
	 * @return The new id.
	 */
	public int getNewId()
	{
		if( mReusableIds.isEmpty() )
			return mNextId++;

		Integer[] asArray = (Integer[]) mReusableIds.toArray();
		Integer ret = asArray[asArray.length - 1];
		mReusableIds.remove( ret );
		return ret;
	}

	/**
	 * Reset the ids in the factory.
	 */
	public void removeAll()
	{
		mReusableIds.clear();
		mNextId = 0;
	}

	/**
	 * Set a single Id in the factory to be reusable.
	 * 
	 * @param id
	 *            The id to set to reusable.
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the requested Id is not in use.
	 */
	public void removeId( int id ) throws ArrayIndexOutOfBoundsException
	{
		if( id > mNextId - 1 )
			throw new ArrayIndexOutOfBoundsException( "Trying to remove id that does not exist" );

		if( id == mNextId - 1 )
		{
			mNextId--;
			while( mReusableIds.contains( Integer.valueOf( mNextId - 1 ) ) )
			{
				mReusableIds.remove( Integer.valueOf( mNextId - 1 ) );
				mNextId--;
			}
		}
		else if( !mReusableIds.contains( id ) )
		{
			mReusableIds.add( id );
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException( "Trying to remove id that does not exist" );
		}
	}
}
