package cmput301f13t10;

public class SectionChoice
{
	private SectionModel mSectionModel;
	private String mUserChoice;

	public SectionChoice( SectionModel sectionModel, String userChoice )
	{
		mSectionModel = sectionModel;
		mUserChoice = userChoice;
	}

	public SectionModel getSectionModel()
	{
		return mSectionModel;
	}

	public String getUserChoice()
	{
		return mUserChoice;
	}
}
