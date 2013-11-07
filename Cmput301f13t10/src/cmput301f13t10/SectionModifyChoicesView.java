package cmput301f13t10;

import cs.ualberta.cmput301f13t10.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SectionModifyChoicesView extends Activity implements SectionView
{
	private SectionPresenter mPresenter;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		mPresenter = new SectionPresenter( this );

		setContentView( R.layout.section_edit_view );
	}

	@Override
	public void updateSectionView()
	{
		
	}

	@Override
	public Context getContext()
	{
		return this;
	}
}
