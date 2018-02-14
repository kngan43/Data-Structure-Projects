package structures;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;

	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
	@Test (expected = NullPointerException.class)
	public void testNullPointerExceptionInsertFirst() throws Exception{
		list.insertFirst(null);
		list.insertAt(0, null);
	}
	@Test (expected = NullPointerException.class)
	public void testNullPointerExceptionInsertLast() throws Exception{
		list.insertLast(null);
	}
	
	@Test (timeout = 500)
	public void testInsertLast(){
		list = new RecursiveList<String>();
		assertEquals("Should have eat when adding empty list","eat",list.insertLast("eat").getLast());
		assertEquals("size should be 1",1,list.size());
		list.insertLast("apple");
		assertEquals("Should have eat when adding empty list","eat",list.getFirst());
		assertEquals("Should have apple when adding empty list","apple",list.getLast());
		assertEquals("size should be 2",2,list.size());
	}
	
	@Test (expected = NullPointerException.class)
	public void testNullPointerExceptionInsertAt() throws Exception{
		list.insertAt(0, null);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testOutOfBoundsExceptionInsertAt()throws Exception{
		list = new RecursiveList<String>();
		list.insertFirst("eat");
		list.insertFirst("eat");
		list.insertAt(3, "eat");
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testOutOfBoundsExceptionGet()throws Exception{
		list = new RecursiveList<String>();
		list.insertFirst("eat");
		list.insertFirst("eat");
		list.get(2);
	}
	
	@Test (timeout = 500)
	public void testInsertAtandGet(){
		list = new RecursiveList<String>();
		assertEquals(0,list.size());
		list.insertAt(0, "eat");
		assertEquals(1,list.size());
		assertEquals("should return eat","eat",list.get(0));
		list.insertAt(0, "apple");
		assertEquals("should return apple","apple",list.get(0));
		assertEquals(2,list.size());
		assertEquals("eat",list.getLast());
		assertEquals("should return eat","eat",list.get(1));
		list.insertAt(1,"pie");
		assertEquals(3,list.size());
		assertEquals("should return pie","pie",list.get(1));
		assertEquals("should return eat","eat",list.get(2));
		list.insertAt(2, "done");
		assertEquals("should return done","done",list.get(2));
		list.insertAt(2, "more?");
		assertEquals("should return done","more?",list.get(2));
		list.insertAt(4, "further");
		assertEquals("should return done","further",list.get(4));
		assertEquals(6,list.size());
		assertEquals("should return done","further",list.removeAt(4));
		assertEquals("should return done","more?",list.removeAt(2));
		assertEquals(4,list.size());
	}
	@Test (timeout = 500)
	public void testRemoveFirst(){
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("pie");
		assertEquals(3,list.size());
		assertEquals("should be pie","pie",list.removeFirst());
		assertEquals(2,list.size());
		assertEquals("eat",list.removeFirst());
		assertEquals(1,list.size());
		assertEquals("apple",list.removeFirst());
		assertEquals(0,list.size());
	}
	@Test (expected = IllegalStateException.class)
	public void testRemoveFirstException() throws IllegalStateException{
		list.removeFirst();
	}
	@Test (timeout = 500)
	public void testRemoveLast(){
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("pie");
		assertEquals("apple", list.removeLast());
		assertEquals(2,list.size());
		assertEquals("eat",list.removeLast());
		assertEquals(1,list.size());
		assertEquals("pie",list.removeLast());
		assertEquals(0,list.size());
	}
	@Test (expected = IllegalStateException.class)
	public void testRemoveLastException() throws IllegalStateException{
		list = new RecursiveList<String>();
		list.removeLast();
	}
	@Test (timeout = 500)
	public void testRemoveAt(){
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("done");
		list.insertFirst("pie");
		assertEquals(4,list.size());
		assertEquals("apple",list.removeAt(3));
		assertEquals(3,list.size());
		assertEquals("pie",list.removeAt(0));
		assertEquals(2,list.size());
		assertEquals("eat",list.removeAt(1));
		assertEquals(1,list.size());
		assertEquals("done", list.removeAt(0));
		assertEquals(0,list.size());
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("done");
		list.insertFirst("pie");
		assertEquals("apple",list.removeAt(3));
		assertEquals("eat",list.getLast());
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveAtException() throws Exception{
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.removeAt(2);
		list.removeAt(0);
	}
	@Test (timeout = 500)
	public void testRemoveElement(){
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("done");
		list.insertFirst("pie");
		assertFalse(list.remove("peas"));
		assertTrue(list.remove("apple"));
		assertEquals("eat",list.getLast());
		assertEquals(3,list.size());
		assertTrue(list.remove("done"));;
		assertTrue(list.remove("eat"));
		assertFalse(list.remove("eat"));
		assertEquals(1,list.size());
		assertTrue(list.remove("pie"));
		assertEquals(0,list.size());
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("done");
		list.insertFirst("pie");
		assertTrue(list.remove("apple"));
		assertEquals("eat", list.getLast());
	}
	@Test (timeout = 500)
	public void testIndex(){
		list = new RecursiveList<String>();
		list.insertFirst("apple");
		list.insertFirst("eat");
		list.insertFirst("done");
		list.insertFirst("pie");
		assertEquals(0,list.indexOf("pie"));
		assertEquals(1,list.indexOf("done"));
		assertEquals(2,list.indexOf("eat"));
		assertEquals(3,list.indexOf("apple"));
		assertEquals(-1,list.indexOf("sdfsdf"));
	}
	@Test (timeout = 500)
	public void testIterations(){
		list = new RecursiveList<String>();
		list.insertAt(0,"apple");
		list.insertAt(0,"eat");
		list.insertAt(0,"done");
		list.insertAt(0,"pie");
		list.insertAt(3,"peas");
		list.insertAt(3,"peas");
		list.insertAt(2, "apple");
		for(String a:list)
		System.out.println(list.indexOf(a)+" | " +a);
		System.out.println(list.size());
		list.removeAt(6);
		//list.remove("peas");
		//list.remove("pie");
		//list.remove("apple");
		//list.remove("peas");
		//list.remove("eat");
		//list.remove("done");
		
		for(String a:list)
			System.out.println(list.indexOf(a)+" | " +a);
		System.out.println(list.size());
	}
	
}
