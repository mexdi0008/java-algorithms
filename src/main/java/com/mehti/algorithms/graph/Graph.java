package com.mehti.algorithms.graph;

import java.util.*;

/** Weighted directed graph (adjacency list) with BFS, DFS, Dijkstra and topological sort. */
public class Graph {

	public record Edge(int to, int weight) {
	}

	private final List<List<Edge>> adjacency;

	public Graph(int vertices) {
		adjacency = new ArrayList<>(vertices);
		for (int i = 0; i < vertices; i++) {
			adjacency.add(new ArrayList<>());
		}
	}

	public void addEdge(int from, int to, int weight) {
		if (weight < 0) {
			throw new IllegalArgumentException("negative weights are not supported");
		}
		adjacency.get(from).add(new Edge(to, weight));
	}

	public void addUndirectedEdge(int a, int b, int weight) {
		addEdge(a, b, weight);
		addEdge(b, a, weight);
	}

	public int size() {
		return adjacency.size();
	}

	/** Vertices in breadth-first order from {@code start}. */
	public List<Integer> bfs(int start) {
		List<Integer> order = new ArrayList<>();
		boolean[] seen = new boolean[size()];
		Deque<Integer> queue = new ArrayDeque<>(List.of(start));
		seen[start] = true;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			order.add(v);
			for (Edge e : adjacency.get(v)) {
				if (!seen[e.to()]) {
					seen[e.to()] = true;
					queue.add(e.to());
				}
			}
		}
		return order;
	}

	/** Vertices in depth-first order from {@code start} (iterative, no recursion limit). */
	public List<Integer> dfs(int start) {
		List<Integer> order = new ArrayList<>();
		boolean[] seen = new boolean[size()];
		Deque<Integer> stack = new ArrayDeque<>(List.of(start));
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (seen[v]) continue;
			seen[v] = true;
			order.add(v);
			List<Edge> edges = adjacency.get(v);
			for (int i = edges.size() - 1; i >= 0; i--) {
				if (!seen[edges.get(i).to()]) stack.push(edges.get(i).to());
			}
		}
		return order;
	}

	/** Shortest distances from {@code source}; unreachable vertices get {@link Long#MAX_VALUE}. O((V + E) log V). */
	public long[] dijkstra(int source) {
		long[] dist = new long[size()];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[source] = 0;
		PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
		pq.add(new long[]{source, 0});
		while (!pq.isEmpty()) {
			long[] cur = pq.poll();
			int v = (int) cur[0];
			if (cur[1] > dist[v]) continue; // stale entry
			for (Edge e : adjacency.get(v)) {
				long candidate = dist[v] + e.weight();
				if (candidate < dist[e.to()]) {
					dist[e.to()] = candidate;
					pq.add(new long[]{e.to(), candidate});
				}
			}
		}
		return dist;
	}

	/** Kahn's algorithm. Throws if the graph contains a cycle. */
	public List<Integer> topologicalOrder() {
		int[] inDegree = new int[size()];
		adjacency.forEach(edges -> edges.forEach(e -> inDegree[e.to()]++));
		Deque<Integer> ready = new ArrayDeque<>();
		for (int v = 0; v < size(); v++) {
			if (inDegree[v] == 0) ready.add(v);
		}
		List<Integer> order = new ArrayList<>();
		while (!ready.isEmpty()) {
			int v = ready.poll();
			order.add(v);
			for (Edge e : adjacency.get(v)) {
				if (--inDegree[e.to()] == 0) ready.add(e.to());
			}
		}
		if (order.size() != size()) {
			throw new IllegalStateException("graph contains a cycle");
		}
		return order;
	}
}
