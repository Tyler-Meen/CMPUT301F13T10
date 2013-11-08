package cmput301f13t10;

import java.util.ArrayList;

import cmput301f13t10.SectionChoiceDialogFragment.AddChoiceDialogListener;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cs.ualberta.cmput301f13t10.R;

/**
 * This is the activity that allows a user to change which choices the current section is linked to 
 * @author Steven Gerdes
 *
 */
public class SectionModifyChoicesView extends Activity implements SectionView, AddChoiceDialogListener
{
	/**
	 * The section presenter that gives this view its correctly formatted information
	 */
	private SectionPresenter mPresenter;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.section_modify_choice_view );
		mPresenter = new SectionPresenter( this );

		Intent intent = getIntent();
		int defaultVal = -1;
		if( intent.hasExtra( AppConstants.ADVENTURE_ID ) )
			mPresenter.setCurrentAdventure( intent.getIntExtra( AppConstants.ADVENTURE_ID, defaultVal ) );
		if( intent.hasExtra( AppConstants.SECTION_ID ) )
			mPresenter.setCurrentSectionById( intent.getIntExtra( AppConstants.SECTION_ID, defaultVal ) );
	}

	@Override
	public Context getContext()
	{
		return this;
	}

	@Override
	public void updateSectionView()
	{
		updateCurrentChoices();
		updatePossibleChoices();
	}

	/**
	 * Updates the list of possible section to use as choices
	 */
	private void updatePossibleChoices()
	{
		ArrayList<SectionTitle> choices = mPresenter.getSectionTitles();

		ListView sectionList = (ListView) findViewById( R.id.complete_section_list );
		sectionList.setAdapter( new SimpleSectionArrayAdapter( this, choices ) );

		sectionList.setOnItemClickListener( onAddSectionChoice() );
	}

	/**
	 * When some one wants to add a section it will pop up a dialog asking for more information
	 * @return this as a click listener
	 */
	private OnItemClickListener onAddSectionChoice()
	{
		return new OnItemClickListener()
		{
			@Override
			public void onItemClick( AdapterView parent, View v, int position, long id )
			{
				SectionTitle selectedSection = (SectionTitle) parent.getItemAtPosition( position );

				Bundle choicesBundle = new Bundle();
				choicesBundle.putString( AppConstants.SECTION_TITLE, selectedSection.getTitle() );
				choicesBundle.putInt( AppConstants.SECTION_ID, selectedSection.getId() );

				SectionChoiceDialogFragment dialog = new SectionChoiceDialogFragment();
				dialog.setArguments( choicesBundle );
				dialog.show( getFragmentManager(), "" );

			}
		};
	}

	/**
	 * update the currently selected choices list
	 */
	private void updateCurrentChoices()
	{
		ArrayList<SectionChoice> choices = mPresenter.getChoices();

		ListView sectionList = (ListView) findViewById( R.id.selected_section_list );
		sectionList.setAdapter( new SectionChoiceArrayAdapter( this, choices ) );

		sectionList.setOnItemClickListener( onRemoveSectionChoice() );
	}

	/**
	 * What to do when some one want to remove a section
	 * @return this function so it can be used 
	 */
	private OnItemClickListener onRemoveSectionChoice()
	{
		return new OnItemClickListener()
		{
			@Override
			public void onItemClick( AdapterView parent, View v, int position, long id )
			{
				SectionChoice selectedChoice = (SectionChoice) parent.getItemAtPosition( position );
				mPresenter.removeSectionChoice( selectedChoice );
			}
		};
	}

	/**
	 * Adds a new section choice dialog
	 * @param view the view that was clicked
	 */
	public void newSectionChoice( View view )
	{
		SectionChoiceDialogFragment dialog = new SectionChoiceDialogFragment();
		Bundle choicesBundle = new Bundle();
		choicesBundle.putInt( AppConstants.SECTION_ID, -1 );
		dialog.setArguments( choicesBundle );
		dialog.show( getFragmentManager(), "" );
	}

	@Override
	public void onAddChoice( DialogFragment dialog )
	{
		SectionChoiceDialogFragment choiceDialog = (SectionChoiceDialogFragment) dialog;
		mPresenter.addSectionChoice( choiceDialog.getChoiceId(), choiceDialog.getChoiceDescription(), choiceDialog.getSectionTitle() );
	}

	@Override
	public void onCancel( DialogFragment dialog )
	{
		// do nothing
	}

}
