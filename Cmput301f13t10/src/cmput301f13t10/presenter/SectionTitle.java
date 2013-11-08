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

/**
 * A data container that holds the id, and title of a section. As well as an
 * identifier of whether it is that start section for an adventure.
 * 
 * @author Braeden Soetaert
 * 
 */
public class SectionTitle
{
	/**
	 * The title of the section.
	 */
	private String mTitle;

	/**
	 * The id of the section.
	 */
	private Integer mId;

	/**
	 * Whether this section is that start section or not.
	 */
	private Boolean mIsStartSection;

	/**
	 * Constructor. Sets the start section indicator to false.
	 * 
	 * @param title
	 *            The title of the section.
	 * @param id
	 *            The id of the section.
	 */
	public SectionTitle( String title, Integer id )
	{
		mTitle = title;
		mId = id;
		mIsStartSection = false;
	}

	/**
	 * Constructor.
	 * 
	 * @param title
	 *            The title of the section.
	 * @param id
	 *            The id of the section.
	 * @param isStartSection
	 *            Whether this section is the start section.
	 */
	public SectionTitle( String title, Integer id, Boolean isStartSection )
	{
		this( title, id );
		mIsStartSection = true;
	}

	/**
	 * @return the section id
	 */
	public Integer getId()
	{
		return mId;
	}

	/**
	 * @return true if this section is a start section, false otherwise.
	 */
	public Boolean isStartSection()
	{
		return mIsStartSection;
	}

	/**
	 * @param id
	 *            the new id of the section
	 */
	public void setId( Integer id )
	{
		mId = id;
	}

	/**
	 * @return the section title
	 */
	public String getTitle()
	{
		return mTitle;
	}

	/**
	 * @param title
	 *            the new title of the section
	 */
	public void setTitle( String title )
	{
		mTitle = title;
	}
}
