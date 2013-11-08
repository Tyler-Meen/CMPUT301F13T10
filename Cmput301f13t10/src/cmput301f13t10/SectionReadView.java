package cmput301f13t10;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import cs.ualberta.cmput301f13t10.R;

/**
 * View that allows the user to read and navigate an adventure.
 * 
 * @author Brendan Cowan
 * 
 */
public class SectionReadView extends Activity implements SectionView
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

	}

	/**
	 * Reset the media displayed in the scrollable view by replacing it with
	 * that stored in the current section of the presenter. Also change the
	 * button at the bottom of the screen to a "return to main menu button" if
	 * there are no more choices.
	 */
	public void updateSectionView()
	{
		Button continueButton = (Button) findViewById( R.id.continue_button );

		LinearLayout scrollBox = (LinearLayout) findViewById( R.id.read_items_linear );
		mPresenter.setCurrentSectionView( scrollBox );

		if( mPresenter.atLastSection() )
		{
			MainMenuButtonListener mainMenuListener = new MainMenuButtonListener();
			continueButton.setOnClickListener( mainMenuListener );
			continueButton.setText( "Main Menu" );
		}
		else
		{
			ContinueButtonListener continueListener = new ContinueButtonListener();
			continueButton.setOnClickListener( continueListener );
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
		mPresenter.setCurrentSectionById( sectionId );
	}
	

	@Override
	public Context getContext()
	{
		return this;
	}

	/**
	 * Handler for the continue button of the view. Displays a dialog box which
	 * gives the user options as to how to continue with the story.
	 * 
	 * @author Brendan Cowan
	 * 
	 */
	private class ContinueButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick( View v )
		{
			Bundle choicesBundle = new Bundle();
			ArrayList<String> choices = mPresenter.getChoices();
			choicesBundle.putStringArray( AppConstants.CHOICES_BUNDLE, (String[]) choices.toArray( new String[choices.size()] ) );

			ContinueDialogFragment dialog = new ContinueDialogFragment();
			dialog.setArguments( choicesBundle );
			dialog.show( getFragmentManager(), "" );
		}

	}
	
	/**
	 * Handler for the main menu button of the view. Sends the user back to the main menu.
	 * 
	 * @author Brendan Cowan
	 * 
	 */
	private class MainMenuButtonListener implements View.OnClickListener
	{

		@Override
		public void onClick( View v )
		{
			Intent intent = new Intent(getContext(), MainActivity.class);
			intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
			startActivity(intent);
		}

	}

}