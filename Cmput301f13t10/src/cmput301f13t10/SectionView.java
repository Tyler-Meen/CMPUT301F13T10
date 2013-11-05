package cmput301f13t10;

import java.io.Serializable;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * View that displays adventures to the user.
 * 
 * @author Brendan Cowan
 * 
 */
public abstract class SectionView extends Activity implements Serializable
{
	/**
	 * The presenter for the view (as per MVP)
	 */
	SectionPresenter mPresenter;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );

		setContentView( R.layout.section_read_view );
		

		try
		{
			Intent intent = getIntent();
			Bundle intentBundle = intent.getBundleExtra( AppConstants.CURRENT_ADVENTURE );
			int adventure = intentBundle.getInt( AppConstants.CURRENT_ADVENTURE );
			mPresenter.setCurrentAdventure( adventure );
		}
		catch( Exception e )
		{
			Logger.log( "Invalid AdventureReadView instantiation bundle", e );
			return;
		}
		
		//updateAdventureSection();

	}

	/**
	 * Reset the media displayed in the scrollable view by replacing it with
	 * that stored in the current section of the presenter. Also change the
	 * button at the bottom of the screen to a "return to main menu button" if
	 * there are no more choices.
	 */
	public void updateAdventureSection()
	{
		Button continueButton = (Button) findViewById(R.id.continue_button);
		
		LinearLayout scrollBox = (LinearLayout) findViewById( R.id.read_items_linear );
		mPresenter.setCurrentSectionView( scrollBox );
		//TODO: change to mPpresenter.IsMainMenu or something
		if( mPresenter.getChoices().isEmpty() )
		{
			continueButton.setText( "Main Menu" );
		}
		else
		{
			continueButton.setText( "Continue" );
		}
	}

	/**
	 * Change the section that is being currently viewed by the user.
	 * 
	 * @param sectionId
	 *            The id of the new section to view
	 */
	public void changeSection( int sectionId )
	{
		mPresenter.setCurrentSectionId( sectionId );
	}
}