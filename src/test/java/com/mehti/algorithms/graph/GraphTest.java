package com.mehti.algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GraphTest {

	@Test
	void traversesInBreadthAndDepthOrder() {
		Graph g = new Graph(5);
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 1);
		g.addEdge(1, 3, 1);
		g.addEdge(2, 4, 1);

		assertThat(g.bfs(0)).containsExactly(0, 1, 2, 3, 4);
		assertThat(g.dfs(0)).containsExactly(0, 1, 3, 2, 4);
	}

	@Test
	void dijkstraFindsShortestPaths() {
		Graph g = new Graph(5);
		g.addUndirectedEdge(0, 1, 4);
		g.addUndirectedEdge(0, 2, 1);
		g.addUndirectedEdge(2, 1, 2);
		g.addUndirectedEdge(1, 3, 5);

		long[] dist = g.dijkstra(0);
		assertThat(dist[1]).isEqualTo(3);   // 0 -> 2 -> 1
		assertThat(dist[3]).isEqualTo(8);
		assertThat(dist[4]).isEqualTo(Long.MAX_VALUE); // unreachable
	}

	@Test
	void topologicalOrderRespectsDependencies() {
		Graph g = new Graph(4);
		g.addEdge(0, 1, 1);
		g.addEdge(1, 2, 1);
		g.addEdge(0, 3, 1);
		g.addEdge(3, 2, 1);

		var order = g.topologicalOrder();
		assertThat(order.indexOf(0)).isLessThan(order.indexOf(1));
		assertThat(order.indexOf(1)).isLessThan(order.indexOf(2));
		assertThat(order.indexOf(3)).isLessThan(order.indexOf(2));

		g.addEdge(2, 0, 1);
		assertThatThrownBy(g::topologicalOrder).isInstanceOf(IllegalStateException.class);
	}
}
