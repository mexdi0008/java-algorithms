package com.mehti.algorithms.datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DataStructuresTest {

	@Test
	void lruCacheEvictsLeastRecentlyUsed() {
		LruCache<String, Integer> cache = new LruCache<>(2);
		cache.put("a", 1);
		cache.put("b", 2);
		cache.get("a");          // "a" becomes most recent
		cache.put("c", 3);       // evicts "b"

		assertThat(cache.get("b")).isEmpty();
		assertThat(cache.get("a")).contains(1);
		assertThat(cache.get("c")).contains(3);
		assertThat(cache.size()).isEqualTo(2);
	}

	@Test
	void lruCacheUpdatesExistingKey() {
		LruCache<String, Integer> cache = new LruCache<>(1);
		cache.put("a", 1);
		cache.put("a", 10);
		assertThat(cache.get("a")).contains(10);
		assertThat(cache.size()).isEqualTo(1);
	}

	@Test
	void minHeapPollsInSortedOrder() {
		MinHeap<Integer> heap = MinHeap.natural();
		List<Integer> values = new Random(42).ints(500, -1000, 1000).boxed().toList();
		values.forEach(heap::push);

		List<Integer> polled = new ArrayList<>();
		while (!heap.isEmpty()) polled.add(heap.poll());

		assertThat(polled).isSorted().hasSize(500);
		assertThatThrownBy(heap::peek).isInstanceOf(NoSuchElementException.class);
	}

	@Test
	void trieFindsWordsByPrefix() {
		Trie trie = new Trie();
		List.of("car", "card", "care", "cat", "dog").forEach(trie::insert);

		assertThat(trie.contains("car")).isTrue();
		assertThat(trie.contains("ca")).isFalse();
		assertThat(trie.wordsWithPrefix("car")).containsExactly("car", "card", "care");
		assertThat(trie.wordsWithPrefix("x")).isEmpty();
	}

	@Test
	void unionFindTracksComponents() {
		UnionFind uf = new UnionFind(5);
		assertThat(uf.union(0, 1)).isTrue();
		assertThat(uf.union(3, 4)).isTrue();
		assertThat(uf.union(1, 0)).isFalse();

		assertThat(uf.connected(0, 1)).isTrue();
		assertThat(uf.connected(1, 3)).isFalse();
		assertThat(uf.components()).isEqualTo(3);
	}
}
