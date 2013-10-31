package cs.ualberta.cmput301f13t10;

public class SoundMedia implements Media {

	private int mId;
	
	public SoundMedia(int id) {
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
