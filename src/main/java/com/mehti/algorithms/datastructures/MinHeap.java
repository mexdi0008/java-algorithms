package com.mehti.algorithms.datastructures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/** Binary min-heap backed by an array. push/poll: O(log n), peek: O(1). */
public class MinHeap<T> {

	private final Comparator<? super T> comparator;
	private Object[] items = new Object[16];
	private int size;

	public MinHeap(Comparator<? super T> comparator) {
		this.comparator = comparator;
	}

	public static <T extends Comparable<? super T>> MinHeap<T> natural() {
		return new MinHeap<>(Comparator.naturalOrder());
	}

	public void push(T value) {
		if (size == items.length) {
			items = Arrays.copyOf(items, size * 2);
		}
		items[size] = value;
		siftUp(size++);
	}

	public T peek() {
		if (size == 0) {
			throw new NoSuchElementException("heap is empty");
		}
		return at(0);
	}

	public T poll() {
		T top = peek();
		items[0] = items[--size];
		items[size] = null;
		if (size > 0) {
			siftDown(0);
		}
		return top;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void siftUp(int i) {
		while (i > 0) {
			int parent = (i - 1) / 2;
			if (less(parent, i) || equal(parent, i)) {
				return;
			}
			swap(i, parent);
			i = parent;
		}
	}

	private void siftDown(int i) {
		while (true) {
			int left = 2 * i + 1;
			int right = left + 1;
			int smallest = i;
			if (left < size && less(left, smallest)) smallest = left;
			if (right < size && less(right, smallest)) smallest = right;
			if (smallest == i) {
				return;
			}
			swap(i, smallest);
			i = smallest;
		}
	}

	private boolean less(int a, int b) {
		return comparator.compare(at(a), at(b)) < 0;
	}

	private boolean equal(int a, int b) {
		return comparator.compare(at(a), at(b)) == 0;
	}

	private void swap(int a, int b) {
		Object tmp = items[a];
		items[a] = items[b];
		items[b] = tmp;
	}

	@SuppressWarnings("unchecked")
	private T at(int i) {
		return (T) items[i];
	}
}
