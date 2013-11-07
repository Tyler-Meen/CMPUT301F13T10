package cmput301f13t10;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SectionEditView extends Activity implements SectionView
{
	private SectionPresenter mPresenter;
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );

		setContentView( R.layout.section_edit_view );

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

	@Override
	public void updateSectionView()
	{
		//TODO: implement
	}

	@Override
	public Context getContext()
	{
		return this;
	}
	
	private void launchModifyChoices()
	{
		Intent intent = new Intent( this, SectionModifyChoicesView.class );
		startActivity( intent );
	}
}





