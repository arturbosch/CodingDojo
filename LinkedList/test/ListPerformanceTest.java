package de.artismarti.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * @author Artur
 */
public class ListPerformanceTest {

	private MyLinkedList<Integer> myList;
	private LinkedList<Integer> javaList;
	private ArrayList<Integer> arrayList;

	@Before
	public void setUp() {
		myList = new MyLinkedList<>();
		javaList = new LinkedList<>();
		arrayList = new ArrayList<>();

		IntStream.range(0, 100).forEach(value -> {
			myList.add(value);
			javaList.add(value);
			arrayList.add(value);
		});
	}

	@Test
	public void add() {
		long start = System.currentTimeMillis();
		IntStream.range(0, 1_000_000).forEach(value -> arrayList.add(value));
		long stop = System.currentTimeMillis();
		System.out.println("ArrayList: add - " + (stop - start) + "ms");

		start = System.currentTimeMillis();
		IntStream.range(0, 1_000_000).forEach(value -> myList.add(value));
		stop = System.currentTimeMillis();
		System.out.println("MyLinkedList: add - " + (stop - start) + "ms");

		start = System.currentTimeMillis();
		IntStream.range(0, 1_000_000).forEach(value -> javaList.add(value));
		stop = System.currentTimeMillis();
		System.out.println("JavaLinkedList: add - " + (stop - start) + "ms");
	}

	@Test
	public void remove() {
		IntStream.range(0, 1_000_000).forEach(value -> arrayList.add(value));
		IntStream.range(0, 1_000_000).forEach(value -> myList.add(value));
		IntStream.range(0, 1_000_000).forEach(value -> javaList.add(value));

		long start = System.currentTimeMillis();
		IntStream.range(0, 1_000).forEach(value -> arrayList.remove(value));
		long stop = System.currentTimeMillis();
		System.out.println("ArrayList: remove - " + (stop - start) + "ms");

		start = System.currentTimeMillis();
		IntStream.range(0, 1_000).forEach(value -> myList.remove(value));
		stop = System.currentTimeMillis();
		System.out.println("MyLinkedList: remove - " + (stop - start) + "ms");

		start = System.currentTimeMillis();
		IntStream.range(0, 1_000).forEach(value -> javaList.remove(value));
		stop = System.currentTimeMillis();
		System.out.println("JavaLinkedList: remove - " + (stop - start) + "ms");
	}
}
