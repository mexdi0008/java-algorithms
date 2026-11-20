package com.mehti.algorithms.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Least-recently-used cache with O(1) get and put.
 * A hash map gives O(1) lookup; a doubly linked list keeps entries in usage order (head = most recent).
 */
public class LruCache<K, V> {

	private final int capacity;
	private final Map<K, Node<K, V>> index = new HashMap<>();
	private final Node<K, V> head = new Node<>(null, null);
	private final Node<K, V> tail = new Node<>(null, null);

	public LruCache(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity must be positive");
		}
		this.capacity = capacity;
		head.next = tail;
		tail.prev = head;
	}

	public Optional<V> get(K key) {
		Node<K, V> node = index.get(key);
		if (node == null) {
			return Optional.empty();
		}
		moveToFront(node);
		return Optional.ofNullable(node.value);
	}

	public void put(K key, V value) {
		Node<K, V> node = index.get(key);
		if (node != null) {
			node.value = value;
			moveToFront(node);
			return;
		}
		if (index.size() == capacity) {
			Node<K, V> lru = tail.prev;
			unlink(lru);
			index.remove(lru.key);
		}
		node = new Node<>(key, value);
		index.put(key, node);
		linkFirst(node);
	}

	public int size() {
		return index.size();
	}

	private void moveToFront(Node<K, V> node) {
		unlink(node);
		linkFirst(node);
	}

	private void linkFirst(Node<K, V> node) {
		node.next = head.next;
		node.prev = head;
		head.next.prev = node;
		head.next = node;
	}

	private void unlink(Node<K, V> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	private static final class Node<K, V> {
		final K key;
		V value;
		Node<K, V> prev;
		Node<K, V> next;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
