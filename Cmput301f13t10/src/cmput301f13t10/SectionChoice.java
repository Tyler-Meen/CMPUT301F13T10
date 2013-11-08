package cmput301f13t10;

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
	public String getUserChoice()
	{
		return mUserChoice;
	}
}
