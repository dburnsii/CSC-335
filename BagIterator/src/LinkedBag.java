import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of Bag<E> using a singly-linked structure.
 * 
 * @author mercer/ Desone Burns II
 *
 * @param <E>
 *          The type of element store in this type-safe collection
 */
public class LinkedBag<E> implements Bag<E> {

  private class Node {
    private E data; // Reference to an element
    private Node next; // Either null, or reference to next node

    public Node(E element, Node ref) {
      data = element;
      next = ref;
    }
  }

  // An external reference to the first element in this Bag
  private Node first;
  private Node current;

  // Create an empty Bag
  public LinkedBag() {
    first = null;
    current = null;
  }

  /**
   * Add an element to this Bag, a type that is not concerned with order so it's
   * inserted at the front of this linked-structure.
   */
  public void add(E element)
  {
	  if(first == null)
		  first = new Node(element, null);
	  else
		  first = new Node(element, first);
	  current = first;
  }

  /**
   * Return true if this Bag has zero elements
   */
  public boolean isEmpty() {
    return first == null;
  }

  /**
   * Allows the client to determine how many elements in this Bag equals element
   */
  public int occurencesOf(E element) {
    if (isEmpty())
      return 0;
    else {
      int result = 0;
      Node ref = new Node(first.data, first.next);
      while (ref != null) {
        if (element.equals(ref.data))
          result++;
        ref = ref.next;
      }
      return result;
    }
  }

  /**
   * Provide a textual representation of this Bag
   */
  public String toString() {
    if (isEmpty())
      return "[]";
    String result = "[" + first.data;
    Node ref = first.next;
    while (ref != null) {
      result += ", " + ref.data;
      ref = ref.next;
    }
    return result + "]";
  }

  /**
   * Removes the first occurence of an element that equals search
   * 
   * @param search
   *          The element to be removed if found
   */
  public boolean remove(E search) {
    if (first == null)
      return false;
    if (search.equals(first.data)) {
      first = first.next;
      return true;
    } else {
      Node ref = first;
      while (ref.next != null && !search.equals(ref.next.data)) {
        ref = ref.next;
      }
      if (ref.next == null)
        return false;
      else {
        ref.next = ref.next.next;
        return true;
      }
    }
  }

  public Iterator<E> iterator() {
    return new Itr<E>();
  }

  /**
   * An iterator over a collection. {@code Iterator} takes the place of
   * {@link Enumeration} in the Java Collections Framework. Iterators differ
   * from enumerations in two ways:
   *
   * <ul>
   * <li>Iterators allow the caller to remove elements from the underlying
   * collection during the iteration with well-defined semantics.
   * <li>Method names have been improved.
   * </ul>
   *
   * <p>
   * This interface is a member of the <a href="{@docRoot}
   * /../technotes/guides/collections/index.html"> Java Collections
   * Framework</a>.
   *
   * @param <E>
   *          the type of elements returned by this iterator
   *
   * @author Josh Bloch
   * @see Collection
   * @see ListIterator
   * @see Iterable
   * @since 1.2
   */
  @SuppressWarnings("hiding")
  private class Itr<E> implements Iterator<E> {

    /**
     * Returns {@code true} if the iteration has more elements. (In other words,
     * returns {@code true} if {@link #next} would return an element rather than
     * throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    public boolean hasNext()
    {
    	if(current != null  && current.data != null)
    	{
    		return true;
    	}
      else
      {
    	  return false;
      }
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException
     *           if the iteration has no more elements
     */
    public E next() 
    {
    	if(hasNext())
    	{
    		Node temp = current;
    		current = current.next;
    		return (E) temp.data;
    	}
    	else
    		throw new NoSuchElementException();
    }

    /**
     * Removes from the underlying collection the last element returned by this
     * iterator (optional operation). This method can be called only once per
     * call to {@link #next}. The behavior of an iterator is unspecified if the
     * underlying collection is modified while the iteration is in progress in
     * any way other than by calling this method.
     *
     * @throws UnsupportedOperationException
     *           if the {@code remove} operation is not supported by this
     *           iterator
     *
     * @throws IllegalStateException
     *           if the {@code next} method has not yet been called, or the
     *           {@code remove} method has already been called after the last
     *           call to the {@code next} method
     */
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }
}