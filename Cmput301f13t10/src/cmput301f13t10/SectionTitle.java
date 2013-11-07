package cmput301f13t10;

public class SectionTitle
{
	private String mTitle;
	private Integer mId;
	
	public SectionTitle(String title, Integer id) {
		setTitle( title );
		setId( id );
	}

	/**
	 * @return the mId
	 */
	public Integer getId()
	{
		return mId;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setId( Integer mId )
	{
		this.mId = mId;
	}

	/**
	 * @return the mTitle
	 */
	public String getTitle()
	{
		return mTitle;
	}

	/**
	 * @param mTitle the mTitle to set
	 */
	public void setTitle( String mTitle )
	{
		this.mTitle = mTitle;
	}
}
