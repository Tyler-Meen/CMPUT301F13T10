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

public class SectionModifyChoicesView extends Activity implements SectionView, AddChoiceDialogListener
{
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

	private void updatePossibleChoices()
	{
		ArrayList<SectionTitle> choices = mPresenter.getSectionTitles();

		ListView sectionList = (ListView) findViewById( R.id.complete_section_list );
		sectionList.setAdapter( new SectionArrayAdapter( this, choices ) );

		sectionList.setOnItemClickListener( onAddSectionChoice() );
	}

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

	private void updateCurrentChoices()
	{
		ArrayList<SectionTitle> choices = mPresenter.getChoiceSectionTitles();

		ListView sectionList = (ListView) findViewById( R.id.selected_section_list );
		sectionList.setAdapter( new SectionArrayAdapter( this, choices ) );

		sectionList.setOnItemClickListener( onRemoveSectionChoice() );
	}
	
	private OnItemClickListener onRemoveSectionChoice()
	{
		return new OnItemClickListener()
		{
			@Override
			public void onItemClick( AdapterView parent, View v, int position, long id )
			{

				SectionTitle selectedSection = (SectionTitle) parent.getItemAtPosition( position );
				mPresenter.removeSectionChoice( selectedSection.getId() );
			}
		};
	}

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
		// TODO Auto-generated method stub

	}

}
