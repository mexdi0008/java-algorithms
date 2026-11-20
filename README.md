# Java Algorithms & Data Structures

Core data structures and algorithms implemented from scratch in modern Java, each with complexity notes and unit tests.

![CI](../../actions/workflows/ci.yml/badge.svg) ![Java](https://img.shields.io/badge/Java-17-orange) ![JUnit](https://img.shields.io/badge/JUnit-5-25A162)

## Contents

### Data structures — `datastructures`
| Structure | Operations | Complexity |
|---|---|---|
| `LruCache` | get / put with eviction | O(1) — hash map + doubly linked list |
| `MinHeap` | push / poll / peek | O(log n) / O(log n) / O(1) |
| `Trie` | insert / contains / prefix search | O(L) per word |
| `UnionFind` | union / find / connected | ~O(1) amortized (path compression + union by rank) |

### Sorting — `sorting.Sorts`
| Algorithm | Time | Space | Stable |
|---|---|---|---|
| Merge sort | O(n log n) | O(n) | ✅ |
| Quick sort (random pivot, 3-way partition) | O(n log n) expected | O(log n) | ❌ |
| Heap sort | O(n log n) | O(1) | ❌ |

### Searching — `search.BinarySearch`
Exact match, lower bound (insertion point) and rotation point of a rotated sorted array — all O(log n).

### Graphs — `graph.Graph`
BFS, iterative DFS, Dijkstra's shortest paths with a priority queue — O((V + E) log V), and Kahn's topological sort with cycle detection.

### Dynamic programming — `dp.DynamicProgramming`
Coin change (min coins), 0/1 knapsack, longest common subsequence and Levenshtein edit distance — all with space-optimized tables.

## Testing approach

- Sorting algorithms are verified against `Arrays.sort` on empty, single, duplicate-heavy, random, sorted and reversed inputs (parameterized tests).
- Edge cases: empty inputs, unreachable vertices, cycles, impossible coin amounts.

```bash
./mvnw test
```

## Tech
Java 17 · Maven · JUnit 5 · AssertJ · GitHub Actions
