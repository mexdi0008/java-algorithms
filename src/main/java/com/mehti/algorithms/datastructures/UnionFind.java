package com.mehti.algorithms.datastructures;

/** Disjoint-set union with path compression and union by rank: near O(1) amortized operations. */
public class UnionFind {

	private final int[] parent;
	private final int[] rank;
	private int components;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		components = n;
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	/** Returns {@code true} if the two elements were in different sets. */
	public boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra == rb) {
			return false;
		}
		if (rank[ra] < rank[rb]) {
			int tmp = ra;
			ra = rb;
			rb = tmp;
		}
		parent[rb] = ra;
		if (rank[ra] == rank[rb]) {
			rank[ra]++;
		}
		components--;
		return true;
	}

	public boolean connected(int a, int b) {
		return find(a) == find(b);
	}

	public int components() {
		return components;
	}
}
