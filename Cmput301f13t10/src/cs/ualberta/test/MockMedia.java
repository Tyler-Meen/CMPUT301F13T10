package cs.ualberta.test;

import android.content.Context;
import android.view.View;
import cs.ualberta.cmput301f13t10.Media;

public class MockMedia implements Media {

	private int mId;
	
	public MockMedia(int id) {
		mId = id;
	}
	
	@Override
	public void setId(int id) {
		mId = id;
	}

	@Override
	public int getId() {
		return mId;
	}

	@Override
	public View toView(Context c) {
		return null;
	}
	
}
