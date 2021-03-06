import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

/**
 * This unit test shows a further specification of the Bag ADT.
 */
public class LinkedBagTest {

  @Test
  public void testIsEmptyWithOneAdd() {
    Bag<String> names = new LinkedBag<String>();
    assertTrue(names.isEmpty());
    names.add("Kim");
    assertFalse(names.isEmpty());
  }

  @Test
  public void testOccurencesOfWithOneElement() {
    Bag<Integer> ints = new LinkedBag<Integer>();
    
    assertEquals(0, ints.occurencesOf(99));
    ints.add(99);
    assertEquals(1, ints.occurencesOf(99));

    Bag<String> names = new LinkedBag<String>();
    names.add("Sam");
    names.add("Devon");
    names.add("Sam");
    names.add("Sam");
    assertEquals(3, names.occurencesOf("Sam"));
    assertEquals(1, names.occurencesOf("Devon"));
    assertEquals(0, names.occurencesOf("Not here"));
  }

  @Test
  public void testToString() {
    LinkedBag<String> bag = new LinkedBag<String>();
    assertEquals("[]", bag.toString());

    bag.add("Baker");
    assertEquals("[Baker]", bag.toString());

    bag.add("Miller");
    bag.add("Cartwright");
    assertEquals("[Cartwright, Miller, Baker]", bag.toString());
  }

  @Test
  public void testRemove1() {
    Bag<Integer> ints = new LinkedBag<Integer>();
    ints.add(8);
    ints.add(7);
    ints.add(6);
    ints.add(5);
    assertEquals("[5, 6, 7, 8]", ints.toString());
    assertTrue(ints.remove(7));
    assertEquals("[5, 6, 8]", ints.toString());
    assertFalse(ints.remove(99));
    assertEquals("[5, 6, 8]", ints.toString());
    ints.remove(5);
    assertEquals("[6, 8]", ints.toString());
    assertTrue(ints.remove(8));
    assertEquals("[6]", ints.toString());
    ints.remove(6);
    assertEquals("[]", ints.toString());
    assertFalse(ints.remove(6));
    assertEquals("[]", ints.toString());
  }

  @Test
  public void testRemove2() {
    Bag<String> names = new LinkedBag<String>();
    names.add("Sam");
    names.add("Chris");
    names.add("Devon");

    // Return false if the element does not occur
    assertFalse(names.remove("Not here"));

    // Remove Sam successfully, then try again.
    assertTrue(names.remove("Sam"));
    assertEquals(0, names.occurencesOf("Sam"));
    assertFalse(names.remove("Sam"));
    assertEquals(0, names.occurencesOf("Sam"));

    // Remove all other elements
    assertEquals(1, names.occurencesOf("Chris"));
    assertTrue(names.remove("Chris"));
    assertEquals(0, names.occurencesOf("Chris"));

    // Only one element left
    assertFalse(names.isEmpty());
    assertEquals(1, names.occurencesOf("Devon"));
    assertTrue(names.remove("Devon"));
    assertEquals(0, names.occurencesOf("Devon"));

    // Assert the bag is empty
    assertTrue(names.isEmpty());
  }
 
   
  @Test
  public void testIterator() {
    LinkedBag<String> names = new LinkedBag<String>();
    names.add("Sam");
    names.add("Chris");
    names.add("Devon");
    
    Iterator<String> itr = names.iterator();
    
    // We still have 3 elements to iterate over
    assertTrue(itr.hasNext());
    // Note: this version of Bag adds elements at the
    // front, which is the reason why the first element
    // in the iteration is the most recently added element
    assertEquals("Devon", itr.next());
    assertTrue(itr.hasNext());
    assertEquals("Chris", itr.next());
    assertTrue(itr.hasNext());
    assertEquals("Sam", itr.next());
    assertFalse(itr.hasNext());
   }
}