package cmput301f13t10.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cmput301f13t10.model.AnnotationModel;
import cmput301f13t10.presenter.Media;

public class AnnotationModelTest
{
	private AnnotationModel mAnnotation;
	private Media mMedia1;
	private Media mMedia2;
	private Media mMedia3;
	private int mMedia1Id;
	private int mMedia2Id;
	private int mMedia3Id;

	@Before
	public void SetUp()
	{
		mAnnotation = new AnnotationModel();

		mMedia1 = new MockMedia();
		mMedia2 = new MockMedia();
		mMedia3 = new MockMedia();

		mAnnotation.add( mMedia1 );
		mAnnotation.add( mMedia2 );
		mAnnotation.add( mMedia3 );

		mMedia1Id = mMedia1.getId();
		mMedia2Id = mMedia2.getId();
		mMedia3Id = mMedia3.getId();
	}

	@Test
	public void testAddMedia()
	{
		ArrayList<Media> medias = mAnnotation.getMedia();

		assertEquals( medias.size(), 3 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia2Id );
		assertEquals( medias.get( 2 ).getId(), mMedia3Id );
	}

	@Test
	public void testRemoveMedia()
	{
		mAnnotation.remove( 1 );

		ArrayList<Media> medias = mAnnotation.getMedia();

		assertEquals( medias.size(), 2 );
		assertEquals( medias.get( 0 ).getId(), mMedia1Id );
		assertEquals( medias.get( 1 ).getId(), mMedia3Id );
	}
}
