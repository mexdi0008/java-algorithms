package com.mehti.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortsTest {

	static Stream<Arguments> sorts() {
		return Stream.of(
				Arguments.of("merge", (Consumer<int[]>) Sorts::mergeSort),
				Arguments.of("quick", (Consumer<int[]>) Sorts::quickSort),
				Arguments.of("heap", (Consumer<int[]>) Sorts::heapSort));
	}

	@ParameterizedTest(name = "{0} sort")
	@MethodSource("sorts")
	void sortsLikeArraysSort(String name, Consumer<int[]> sort) {
		Random random = new Random(7);
		for (int[] input : new int[][]{
				{}, {1}, {2, 1}, {5, 5, 5, 5},
				random.ints(1_000, -50, 50).toArray(),     // many duplicates
				random.ints(10_000).toArray(),
				java.util.stream.IntStream.range(0, 5_000).toArray(),           // already sorted
				java.util.stream.IntStream.range(0, 5_000).map(i -> -i).toArray() // reversed
		}) {
			int[] expected = input.clone();
			Arrays.sort(expected);
			sort.accept(input);
			assertThat(input).as(name).containsExactly(expected);
		}
	}
}
