package com.mehti.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTest {

	private final int[] sorted = {1, 3, 3, 5, 8, 13};

	@Test
	void findsExistingAndMissingValues() {
		assertThat(BinarySearch.indexOf(sorted, 8)).isEqualTo(4);
		assertThat(BinarySearch.indexOf(sorted, 4)).isEqualTo(-1);
		assertThat(BinarySearch.indexOf(new int[0], 1)).isEqualTo(-1);
	}

	@Test
	void lowerBoundReturnsInsertionPoint() {
		assertThat(BinarySearch.lowerBound(sorted, 3)).isEqualTo(1);
		assertThat(BinarySearch.lowerBound(sorted, 4)).isEqualTo(3);
		assertThat(BinarySearch.lowerBound(sorted, 100)).isEqualTo(sorted.length);
	}

	@Test
	void findsRotationPoint() {
		assertThat(BinarySearch.rotationPoint(new int[]{4, 5, 6, 1, 2, 3})).isEqualTo(3);
		assertThat(BinarySearch.rotationPoint(new int[]{1, 2, 3})).isZero();
	}
}
