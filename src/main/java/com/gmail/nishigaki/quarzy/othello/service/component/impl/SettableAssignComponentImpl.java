package com.gmail.nishigaki.quarzy.othello.service.component.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.service.component.IsAssignableComponent;
import com.gmail.nishigaki.quarzy.othello.service.component.SettableAssignComponent;

/**
 * @author nishigaki
 */
@Component
public class SettableAssignComponentImpl implements SettableAssignComponent {

	@Autowired
	private IsAssignableComponent isAssignableComponent;

	@Override
	public final List<List<PieceValue>> invoke(final List<List<PieceValue>> board,
			final PieceValue pieceValue) {
		if (pieceValue == PieceValue.EMPTY || pieceValue == PieceValue.SETTABLE) {
			throw new RuntimeException();
		}
		final List<List<PieceValue>> newBoard = new ArrayList<>();
		for (int y = 0; y < board.size(); y++) {
			final List<PieceValue> row = board.get(y);
			final List<PieceValue> newRow = new ArrayList<>();
			for (int x = 0; x < row.size(); x++) {
				if (isAssignable(board, pieceValue, x, y)) {
					newRow.add(PieceValue.SETTABLE);
				} else {
					newRow.add(row.get(x));
				}
			}
			newBoard.add(newRow);
		}
		return newBoard;
	}

	private boolean isAssignable(final List<List<PieceValue>> board, final PieceValue pieceValue,
			final int xValue, final int yValue) {
		if (board.get(yValue).get(xValue) != PieceValue.EMPTY) {
			return false;
		}
		final Piece piece = new Piece(pieceValue, xValue, yValue);
		final BiPredicate<IntUnaryOperator, IntUnaryOperator> isAssignable = (changeX, changeY) ->
				isAssignableComponent.invoke(board, piece, changeX, changeY);
		return Stream.of(
				isAssignable.test(IntUnaryOperator.identity(), y -> y - 1),
				isAssignable.test(x -> x + 1, y -> y - 1),
				isAssignable.test(x -> x + 1, IntUnaryOperator.identity()),
				isAssignable.test(x -> x + 1, y -> y + 1),
				isAssignable.test(IntUnaryOperator.identity(), y -> y + 1),
				isAssignable.test(x -> x - 1, y -> y + 1),
				isAssignable.test(x -> x - 1, IntUnaryOperator.identity()),
				isAssignable.test(x -> x - 1, y -> y - 1)
		).anyMatch(b -> b);
	}
}
