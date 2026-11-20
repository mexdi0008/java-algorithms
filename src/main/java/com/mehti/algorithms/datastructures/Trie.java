package com.mehti.algorithms.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Prefix tree for autocomplete-style lookups. insert/contains: O(word length). */
public class Trie {

	private final Node root = new Node();

	public void insert(String word) {
		Node node = root;
		for (char c : word.toCharArray()) {
			node = node.children.computeIfAbsent(c, k -> new Node());
		}
		node.terminal = true;
	}

	public boolean contains(String word) {
		Node node = find(word);
		return node != null && node.terminal;
	}

	/** Returns all stored words that start with {@code prefix}, in alphabetical order. */
	public List<String> wordsWithPrefix(String prefix) {
		List<String> result = new ArrayList<>();
		Node node = find(prefix);
		if (node != null) {
			collect(node, new StringBuilder(prefix), result);
		}
		return result;
	}

	private Node find(String prefix) {
		Node node = root;
		for (char c : prefix.toCharArray()) {
			node = node.children.get(c);
			if (node == null) {
				return null;
			}
		}
		return node;
	}

	private void collect(Node node, StringBuilder path, List<String> out) {
		if (node.terminal) {
			out.add(path.toString());
		}
		for (Map.Entry<Character, Node> e : node.children.entrySet()) {
			path.append(e.getKey());
			collect(e.getValue(), path, out);
			path.deleteCharAt(path.length() - 1);
		}
	}

	private static final class Node {
		final Map<Character, Node> children = new TreeMap<>();
		boolean terminal;
	}
}
