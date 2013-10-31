package cs.ualberta.cmput301f13t10;

public class TextMedia implements Media {

	private int mId;
	
	public TextMedia(int id) {
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
	
}
