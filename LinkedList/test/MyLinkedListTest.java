package de.artismarti.collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Artur
 */
public class MyLinkedListTest {

	@Test
	public void test() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(1);
		assertThat(list.size(), is(1));
		list.add(2);
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(2));
		list.add(3);
		list.add(4);
		list.printAll();
		assertThat(list.getFirst(), is(1));
		assertThat(list.getLast(), is(4));
		list.remove(1);
		assertThat(list.size(), is(3));
		assertThat(list.get(0), is(1));
		assertThat(list.get(1), is(3));
		assertThat(list.get(2), is(4));
		list.printAll();
		list.add(5);
		list.add(6);
		list.remove(0);
		list.remove(3);
		list.printAll();
	}

	@Test
	public void removeFirst() {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		list.add(1);
		list.add(2);
		list.remove(0);
		assertThat(list.size(), is(1));
		assertThat(list.get(0), is(2));
		list.printAll();
		list.remove(0);
		assertThat(list.isEmpty(), is(true));
	}
}
