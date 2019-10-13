package com.gmail.nishigaki.quarzy.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;

/**
 * @author nishigaki
 */
public final class Board {

	public Board(final int size) {
		if (size % 2 == 1) {
			throw new RuntimeException();
		}
		final List<List<PieceValue>> pieces = IntStream
				.range(0, size)
				.mapToObj(i -> generateRow(size))
				.collect(Collectors.toCollection(ArrayList::new));
		final int posA = size / 2;
		final int posB = posA - 1;
		pieces.get(posB).set(posB, PieceValue.BLACK);
		pieces.get(posB).set(posA, PieceValue.WHITE);
		pieces.get(posA).set(posB, PieceValue.WHITE);
		pieces.get(posA).set(posA, PieceValue.BLACK);
		this.pieces = pieces;
	}

	private static List<PieceValue> generateRow(final int size) {
		return IntStream.range(0, size)
				.mapToObj(i -> PieceValue.EMPTY)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Getter
	private final List<List<PieceValue>> pieces;
}
