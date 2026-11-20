package com.mehti.algorithms.dp;

import java.util.Arrays;

/** Classic dynamic programming problems with space-optimized tables. */
public final class DynamicProgramming {

	private DynamicProgramming() {
	}

	/** Minimum number of coins to make {@code amount}, or -1 if impossible. O(amount * coins). */
	public static int minCoins(int[] coins, int amount) {
		int[] best = new int[amount + 1];
		Arrays.fill(best, Integer.MAX_VALUE);
		best[0] = 0;
		for (int value = 1; value <= amount; value++) {
			for (int coin : coins) {
				if (coin <= value && best[value - coin] != Integer.MAX_VALUE) {
					best[value] = Math.min(best[value], best[value - coin] + 1);
				}
			}
		}
		return best[amount] == Integer.MAX_VALUE ? -1 : best[amount];
	}

	/** 0/1 knapsack: max total value within {@code capacity}. O(n * capacity) time, O(capacity) space. */
	public static int knapsack(int[] weights, int[] values, int capacity) {
		int[] best = new int[capacity + 1];
		for (int i = 0; i < weights.length; i++) {
			for (int c = capacity; c >= weights[i]; c--) { // backwards: each item used at most once
				best[c] = Math.max(best[c], best[c - weights[i]] + values[i]);
			}
		}
		return best[capacity];
	}

	/** Length of the longest common subsequence. O(n * m) time, O(m) space. */
	public static int longestCommonSubsequence(String a, String b) {
		int[] prev = new int[b.length() + 1];
		int[] cur = new int[b.length() + 1];
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				cur[j] = a.charAt(i - 1) == b.charAt(j - 1) ? prev[j - 1] + 1 : Math.max(prev[j], cur[j - 1]);
			}
			int[] tmp = prev;
			prev = cur;
			cur = tmp;
		}
		return prev[b.length()];
	}

	/** Levenshtein edit distance (insert, delete, replace). */
	public static int editDistance(String a, String b) {
		int[] prev = new int[b.length() + 1];
		int[] cur = new int[b.length() + 1];
		for (int j = 0; j <= b.length(); j++) prev[j] = j;
		for (int i = 1; i <= a.length(); i++) {
			cur[0] = i;
			for (int j = 1; j <= b.length(); j++) {
				int replace = prev[j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1);
				cur[j] = Math.min(replace, Math.min(prev[j], cur[j - 1]) + 1);
			}
			int[] tmp = prev;
			prev = cur;
			cur = tmp;
		}
		return prev[b.length()];
	}
}
