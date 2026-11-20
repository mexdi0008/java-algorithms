package com.mehti.algorithms.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicProgrammingTest {

	@Test
	void minCoins() {
		assertThat(DynamicProgramming.minCoins(new int[]{1, 5, 10, 25}, 63)).isEqualTo(6);
		assertThat(DynamicProgramming.minCoins(new int[]{2}, 3)).isEqualTo(-1);
		assertThat(DynamicProgramming.minCoins(new int[]{7}, 0)).isZero();
	}

	@Test
	void knapsack() {
		assertThat(DynamicProgramming.knapsack(new int[]{1, 3, 4, 5}, new int[]{1, 4, 5, 7}, 7)).isEqualTo(9);
	}

	@Test
	void longestCommonSubsequence() {
		assertThat(DynamicProgramming.longestCommonSubsequence("ABCBDAB", "BDCABA")).isEqualTo(4);
		assertThat(DynamicProgramming.longestCommonSubsequence("", "abc")).isZero();
	}

	@Test
	void editDistance() {
		assertThat(DynamicProgramming.editDistance("kitten", "sitting")).isEqualTo(3);
		assertThat(DynamicProgramming.editDistance("same", "same")).isZero();
	}
}
