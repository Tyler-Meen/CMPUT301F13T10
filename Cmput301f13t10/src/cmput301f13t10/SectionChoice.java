package cmput301f13t10;

public class SectionChoice
{
	private SectionTitle mSectionTitle;
	private String mUserChoice;

	public SectionChoice( SectionTitle sectionTitle, String userChoice )
	{
		mSectionTitle = sectionTitle;
		mUserChoice = userChoice;
	}

	public SectionTitle getSectionModel()
	{
		return mSectionTitle;
	}

	public String getUserChoice()
	{
		return mUserChoice;
	}
}
