package cs.ualberta.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cs.ualberta.cmput301f13t10.Media;
import cs.ualberta.cmput301f13t10.SectionModel;

public class SectionModelTest {

	private SectionModel mSection;
	MockMedia mMedia1;
	MockMedia mMedia2;
	MockMedia mMedia3;

	public SectionModelTest() {
	}

	@Before
	public void setUp() throws Exception {
		mSection = new SectionModel("test", false);
		
		mMedia1 = new MockMedia(1);
		mMedia2 = new MockMedia(2);
		mMedia3 = new MockMedia(3);
		
		mSection.add(mMedia1);
		mSection.add(mMedia2);
		mSection.add(mMedia3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddMedia() {
		ArrayList<Media> medias = mSection.getMedia();
		
		assertEquals(medias.size(), 3);
		assertEquals(medias.get(0).getId(), 1 );
		assertEquals(medias.get(1).getId(), 2 );
		assertEquals(medias.get(2).getId(), 3 );
	}

	@Test
	public void testRemoveMedia() {
		mSection.remove(1);
		
		ArrayList<Media> medias = mSection.getMedia();
		
		assertEquals(medias.size(), 2);
		assertEquals(medias.get(0).getId(), 1);
		assertEquals(medias.get(1).getId(), 3);
	}

	@Test
	public void testMoveMediaPositive() {
		
		Exception ex = null;
		
		try{
			mSection.move(1, 1);
		} catch (IndexOutOfBoundsException e) {
			ex = e;
		}
		
		ArrayList<Media> medias = mSection.getMedia();
		
		assertNull(ex);
		assertEquals(medias.size(), 3);
		assertEquals(medias.get(0).getId(), 1);
		assertEquals(medias.get(1).getId(), 3);
		assertEquals(medias.get(2).getId(), 2);
	}
	
	@Test
	public void testMoveMediaNegative() {
		
		Exception ex = null;
		
		try{
			mSection.move(1, -1);
		} catch (IndexOutOfBoundsException e) {
			ex = e;
		}
		
		ArrayList<Media> medias = mSection.getMedia();
		
		assertNull(ex);
		assertEquals(medias.size(), 3);
		assertEquals(medias.get(0).getId(), 2);
		assertEquals(medias.get(1).getId(), 1);
		assertEquals(medias.get(2).getId(), 3);
	}
	
	@Test
	public void testMoveInvalidOffsetPositive() {
		
		Exception ex = null;
		
		try{
			mSection.move(1, 2);
		} catch (IndexOutOfBoundsException e) {
			ex = e;
		}
		
		ArrayList<Media> medias = mSection.getMedia();
		
		assertNotNull(ex);
		assertEquals(medias.size(), 3);
		assertEquals(medias.get(0).getId(), 1);
		assertEquals(medias.get(1).getId(), 2);
		assertEquals(medias.get(2).getId(), 3);
	}
	
	@Test
	public void testMoveInvalidOffsetNegative() {
		
		Exception ex = null;
		
		try{
			mSection.move(1, -2);
		} catch (IndexOutOfBoundsException e) {
			ex = e;
		}
		
		ArrayList<Media> medias = mSection.getMedia();
		
		assertNotNull(ex);
		assertEquals(medias.size(), 3);
		assertEquals(medias.get(0).getId(), 1);
		assertEquals(medias.get(1).getId(), 2);
		assertEquals(medias.get(2).getId(), 3);
	}
	
	@Test
	public void testMoveZero() {
		
		Exception ex = null;
		
		try{
			mSection.move(1, 0);
		} catch (IndexOutOfBoundsException e) {
			ex = e;
		}
		
		ArrayList<Media> medias = mSection.getMedia();
		
		assertNull(ex);
		assertEquals(medias.size(), 3);
		assertEquals(medias.get(0).getId(), 1);
		assertEquals(medias.get(1).getId(), 2);
		assertEquals(medias.get(2).getId(), 3);
	}

}