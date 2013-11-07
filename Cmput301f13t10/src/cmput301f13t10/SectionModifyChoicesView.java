package cmput301f13t10;

import java.util.ArrayList;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SectionModifyChoicesView extends Activity implements SectionView
{
	private SectionPresenter mPresenter;

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );
		
		Intent intent = getIntent();
		int defaultVal = -1;
		if( intent.hasExtra( AppConstants.ADVENTURE_ID ) )
			mPresenter.setCurrentAdventure( intent.getIntExtra( AppConstants.ADVENTURE_ID, defaultVal ) );
		if( intent.hasExtra( AppConstants.SECTION_ID ) )
			mPresenter.setCurrentSectionById( intent.getIntExtra( AppConstants.SECTION_ID, defaultVal ) );
			
		setContentView( R.layout.section_modify_choice_view );
		
		updateSectionView();
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
		ArrayList<String> choices = mPresenter.getChoices();
	}

	private void updateCurrentChoices()
	{
		
	}
	
	public void newSectionChoice( View view )
	{
		NewSectionChoiceDialogFragment dialog = new NewSectionChoiceDialogFragment();
		dialog.show( getFragmentManager(), "" );
	}

}
