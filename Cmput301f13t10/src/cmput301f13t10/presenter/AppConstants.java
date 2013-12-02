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
 * A list of constants to be used by the app
 * 
 * @author Brendan Cowan
 * 
 * @author Braeden Soetaert
 * 
 * @author Aly-khan Jamal
 */
public class AppConstants
{
	/**
	 * Tag. Indicates that this is a list of possible choices in a bundle.
	 */
	public static final String CHOICES_BUNDLE = "choices_bundle";

	/**
	 * Default title of the start section.
	 */
	public static final String START = "Start";

	/**
	 * Tag. Indicates that this is an adventure_read_view.
	 */
	public static final String ADVENTURE_READ_VIEW = "adventure_read_view";

	/**
	 * Tag. Indicates that this is an adventure_edit_view.
	 */
	public static final String ADVENTURE_EDIT_VIEW = "adventure_edit_view";

	/**
	 * Tag. Indicates that this is the current adventure.
	 */
	public static final String CURRENT_ADVENTURE = "current_adventure";

	/**
	 * Tag. Indicates that this is an adventure id.
	 */
	public static final String ADVENTURE_ID = "adventure_id";

	/**
	 * Tag. Indicates that this is an section id.
	 */
	public static final String SECTION_ID = "section_id";

	/**
	 * Tag. Indicates that this is an annotation id.
	 */
	public static final String ANNOTATION_ID = "annotation_id";

	/**
	 * Tag. Indicates that this is a section title.
	 */
	public static final String TITLE = "object_title";

	/**
	 * Tag. Indicates that this is a choice description
	 */
	public static final String CHOICE_DESCRIPTION = "choice_description";
	/**
	 * Tag. Input into factory to generate an id for a choice.
	 */
	public static final String GENERATE_CHOICE_ID = "choice_id";

	/**
	 * Tag. Input into factory to generate an id for a section.
	 */
	public static final String GENERATE_SECTION_ID = "section_id";

	/**
	 * Tag. Input into factory to generate an id for an adventure.
	 */
	public static final String GENERATE_ADVENTURE_ID = "adventure_id";

	/**
	 * Tag. Input into factory to generate an id for a media.
	 */
	public static final String GENERATE_MEDIA_ID = "media_id";

	/**
	 * Tag. Input into factory to generate an id for a media.
	 */
	public static final String GENERATE_ANNOTATION_ID = "annotation_id";

	/**
	 * The URL of the elasticsearch page
	 */
	public static final String ES_URL = "http://cmput301.softwareprocess.es:8080/cmput301f13t10/";

	/**
	 * The url extention for adventures
	 */
	public static final String ES_ADVENTURE = "adventures/";

	/**
	 * The url extention for the list of all ids
	 */
	public static final String ES_IDS = "ids/1";

	/**
	 * Name of the file where adventures are saved locally
	 */
	public static final String FILE_NAME = "Adventures.sav";

}
