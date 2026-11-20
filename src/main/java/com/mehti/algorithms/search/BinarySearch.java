package com.mehti.algorithms.search;

/** Binary search variants on sorted int arrays. All run in O(log n). */
public final class BinarySearch {

	private BinarySearch() {
	}

	/** Index of {@code target} or -1. */
	public static int indexOf(int[] sorted, int target) {
		int lo = 0, hi = sorted.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) >>> 1; // unsigned shift avoids int overflow
			if (sorted[mid] == target) return mid;
			if (sorted[mid] < target) lo = mid + 1;
			else hi = mid - 1;
		}
		return -1;
	}

	/** First index whose value is {@code >= target} (insertion point); {@code length} if none. */
	public static int lowerBound(int[] sorted, int target) {
		int lo = 0, hi = sorted.length;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (sorted[mid] < target) lo = mid + 1;
			else hi = mid;
		}
		return lo;
	}

	/** Index of the minimum element in a rotated sorted array without duplicates, e.g. [4,5,6,1,2,3] -> 3. */
	public static int rotationPoint(int[] rotated) {
		int lo = 0, hi = rotated.length - 1;
		while (lo < hi) {
			int mid = (lo + hi) >>> 1;
			if (rotated[mid] > rotated[hi]) lo = mid + 1;
			else hi = mid;
		}
		return lo;
	}
}
