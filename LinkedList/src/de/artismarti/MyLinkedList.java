package de.artismarti.collection;

import java.util.NoSuchElementException;

/**
 * @author Artur
 */
public class MyLinkedList<T> {

	private int size;

	private MyElement<T> firstElement = null;
	private MyElement<T> lastElement = null;

	public T getFirst() {
		if (firstElement == null) {
			throw new NoSuchElementException();
		}
		return firstElement.object;
	}

	public T getLast() {
		if (lastElement == null) {
			throw new NoSuchElementException();
		}
		return lastElement.object;
	}

	public T get(int index) {
		MyElement<T> element = internalGet(index);
		if (element == null) {
			throw new NoSuchElementException();
		}
		return element.object;
	}

	private MyElement<T> internalGet(int index) {
		if (index < 0) {
			return null;
		}

		if (index == 0) {
			return firstElement;
		}

		int counter = 0;
		MyElement<T> nextElement = firstElement.next;

		while (nextElement != null && counter < index) {
			counter++;

			if (counter == index) {
				return nextElement;
			}

			nextElement = nextElement.next;
		}

		return null;
	}

	public void add(T element) {
		if (firstElement == null) {
			firstElement = new MyElement<>(element);
			lastElement = firstElement;
		} else {
			MyElement<T> saved = lastElement;
			lastElement = new MyElement<>(element, saved, null);
			saved.next = lastElement;
		}
		size++;
	}

	public void remove(int index) {
		if (index < 0) {
			return;
		}
		if (index == 0) {
			MyElement<T> newFirst = firstElement.next;
			if (newFirst != null) {
				newFirst.previous = null;
				firstElement = newFirst;
			}
		} else {
			MyElement<T> deleted = internalGet(index);
			if (deleted == null) {
				return;
			}
			MyElement<T> previousFromDelete = deleted.previous;
			if (deleted.next == null) {
				previousFromDelete.next = null;
			} else {
				MyElement<T> nextFromDelete = deleted.next;
				previousFromDelete.next = nextFromDelete;
				nextFromDelete.previous = previousFromDelete;
			}
		}
		size--;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void printAll() {
		MyElement<T> element = firstElement;
		while (element != null) {
			System.out.println(element);
			element = element.next;
		}
	}

	private class MyElement<E> {
		MyElement<E> next;
		MyElement<E> previous;
		E object;

		public MyElement(E element) {
			object = element;
			previous = null;
			next = null;
		}

		public MyElement(E element, MyElement<E> previous, MyElement<E> next) {
			object = element;
			this.previous = previous;
			this.next = next;
		}

		@Override public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			@SuppressWarnings("unchecked")
			MyElement<E> myElement = (MyElement<E>) o;

			return object.equals(myElement.object);

		}

		@Override public int hashCode() {
			return object.hashCode();
		}

		@Override public String toString() {
			return object.toString();
		}
	}
}
