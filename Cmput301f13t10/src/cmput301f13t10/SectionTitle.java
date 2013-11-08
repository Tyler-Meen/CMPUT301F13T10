package cmput301f13t10;

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
