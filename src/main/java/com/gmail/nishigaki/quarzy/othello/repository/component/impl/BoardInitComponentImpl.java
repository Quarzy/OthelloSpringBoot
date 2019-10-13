package com.gmail.nishigaki.quarzy.othello.repository.component.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.gmail.nishigaki.quarzy.othello.repository.component.BoardInitComponent;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
@Component
public class BoardInitComponentImpl implements BoardInitComponent {

	@Override
	public final List<List<PieceValue>> invoke(final int size) {
		final List<List<PieceValue>> board = IntStream
				.range(0, size)
				.mapToObj(i -> generateRow(size))
				.collect(Collectors.toCollection(ArrayList::new));
		final int posA = size / 2;
		final int posB = posA - 1;
		board.get(posB).set(posB, PieceValue.BLACK);
		board.get(posB).set(posA, PieceValue.WHITE);
		board.get(posA).set(posB, PieceValue.WHITE);
		board.get(posA).set(posA, PieceValue.BLACK);

//		board.get(posB - 1).set(posA, PieceValue.SETTABLE);
//		board.get(posB).set(posA + 1, PieceValue.SETTABLE);
//		board.get(posA).set(posB - 1, PieceValue.SETTABLE);
//		board.get(posA + 1).set(posB, PieceValue.SETTABLE);

		return board;
	}

	private static List<PieceValue> generateRow(final int size) {
		return IntStream.range(0, size)
				.mapToObj(i -> PieceValue.EMPTY)
				.collect(Collectors.toCollection(ArrayList::new));
	}
}
