package com.mehti.algorithms.sorting;

import java.util.concurrent.ThreadLocalRandom;

/** Classic comparison sorts on int arrays (in place). */
public final class Sorts {

	private Sorts() {
	}

	/** Merge sort: O(n log n) time in all cases, O(n) extra space, stable. */
	public static void mergeSort(int[] a) {
		if (a.length < 2) {
			return;
		}
		mergeSort(a, new int[a.length], 0, a.length - 1);
	}

	private static void mergeSort(int[] a, int[] buf, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mid = (lo + hi) >>> 1;
		mergeSort(a, buf, lo, mid);
		mergeSort(a, buf, mid + 1, hi);
		if (a[mid] <= a[mid + 1]) {
			return; // already ordered
		}
		System.arraycopy(a, lo, buf, lo, hi - lo + 1);
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = buf[j++];
			else if (j > hi) a[k] = buf[i++];
			else if (buf[j] < buf[i]) a[k] = buf[j++];
			else a[k] = buf[i++];
		}
	}

	/** Quick sort with random pivot and 3-way partitioning: O(n log n) expected, handles duplicates well. */
	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}

	private static void quickSort(int[] a, int lo, int hi) {
		while (lo < hi) {
			int pivot = a[ThreadLocalRandom.current().nextInt(lo, hi + 1)];
			int lt = lo, i = lo, gt = hi;
			while (i <= gt) {
				if (a[i] < pivot) swap(a, lt++, i++);
				else if (a[i] > pivot) swap(a, i, gt--);
				else i++;
			}
			// Recurse into the smaller side to bound stack depth at O(log n).
			if (lt - lo < hi - gt) {
				quickSort(a, lo, lt - 1);
				lo = gt + 1;
			} else {
				quickSort(a, gt + 1, hi);
				hi = lt - 1;
			}
		}
	}

	/** Heap sort: O(n log n) time, O(1) extra space, not stable. */
	public static void heapSort(int[] a) {
		int n = a.length;
		for (int i = n / 2 - 1; i >= 0; i--) {
			sink(a, i, n);
		}
		for (int end = n - 1; end > 0; end--) {
			swap(a, 0, end);
			sink(a, 0, end);
		}
	}

	private static void sink(int[] a, int i, int n) {
		while (2 * i + 1 < n) {
			int child = 2 * i + 1;
			if (child + 1 < n && a[child + 1] > a[child]) child++;
			if (a[i] >= a[child]) return;
			swap(a, i, child);
			i = child;
		}
	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
