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
 * Maps a Choice Description to a section title
 * @author Steven Gerdes
 *
 */
public class SectionChoice
{
	/**
	 * The section title.
	 */
	private SectionTitle mSectionTitle;
	
	/**
	 * The title of the choice
	 */
	private String mUserChoice;

	/**
	 * Constructor
	 * @param sectionTitle The section title
	 * @param userChoice The title of the choice
	 */
	public SectionChoice( SectionTitle sectionTitle, String userChoice )
	{
		mSectionTitle = sectionTitle;
		mUserChoice = userChoice;
	}

	/**
	 * Get the section title
	 * @return The section title
	 */
	public SectionTitle getSectionTitle()
	{
		return mSectionTitle;
	}

	/**
	 * Get the user choice
	 * @return the user choice
	 */
	public String getChoiceDescription()
	{
		return mUserChoice;
	}
}
