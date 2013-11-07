package cmput301f13t10;

import cs.ualberta.cmput301f13t10.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class SectionEditView extends Activity implements SectionView
{
	private SectionPresenter mPresenter;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );

		setContentView( R.layout.section_edit_view );
		setUpActionBar();

		Intent intent = getIntent();
		int defaultVal = -1;
		if( intent.hasExtra( AppConstants.ADVENTURE_ID ) )			
			mPresenter.setCurrentAdventure( intent.getIntExtra( AppConstants.ADVENTURE_ID, defaultVal ) );
		if( intent.hasExtra( AppConstants.SECTION_ID ) )
			mPresenter.setCurrentSectionById( intent.getIntExtra( AppConstants.SECTION_ID, defaultVal ) );
		else
			mPresenter.setCurrentSectionById( null );
	}

	@Override
	public void updateSectionView()
	{
		String sectionTitle = mPresenter.getSectionTitle();
		ActionBar actionBar = getActionBar();
		EditText title = (EditText) actionBar.getCustomView().findViewById( R.id.adventure_edit_title );
		title.setText( sectionTitle );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu )
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate( R.menu.adventure_edit_view, menu );

		return super.onCreateOptionsMenu( menu );
	}
	
	@Override
	public Context getContext()
	{
		return this;
	}
	
	public void launchModifyChoicesAction( View view )
	{
		Intent intent = new Intent( this, SectionModifyChoicesView.class );
		intent.putExtra( AppConstants.ADVENTURE_ID, mPresenter.getAdventureId() );
		intent.putExtra( AppConstants.SECTION_ID, mPresenter.getSectionId() );
		startActivity( intent );
	}
	
	public void setUpActionBar()
	{
		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
		{
			ActionBar actionBar = getActionBar();
			// add the custom view to the action bar
			actionBar.setCustomView( R.layout.adventure_edit_action_bar );
			EditText title = (EditText) actionBar.getCustomView().findViewById( R.id.adventure_edit_title );
			title.setOnEditorActionListener( new OnEditorActionListener()
			{
				@Override
				public boolean onEditorAction( TextView v, int actionId, KeyEvent event )
				{
					mPresenter.UpdateSectionTitle(v.getText().toString());
					return false;
				}
			} );
			actionBar.setDisplayShowCustomEnabled( true );
			actionBar.setDisplayHomeAsUpEnabled( true );
		}
	}
}





