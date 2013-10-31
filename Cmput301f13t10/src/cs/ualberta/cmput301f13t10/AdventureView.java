package cs.ualberta.cmput301f13t10;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public abstract class AdventureView extends Activity implements MVPView {
	AdventurePresenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mPresenter = new AdventurePresenter(this);

		setContentView(R.layout.activity_main);
		
		updateAdventureSection();
	}
	
	public void updateAdventureSection() {
		//View newReadItems = mPresenter.getCurrentSectionView();
		//ScrollView scrollBox = findViewById(R.id.read_items);
		//scrollBox.addView(newReadItems);
	}
}
