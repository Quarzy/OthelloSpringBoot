package com.gmail.nishigaki.quarzy.othello.service.component.impl;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nishigaki.quarzy.othello.service.component.BoardComponent;
import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.model.GameResult;
import com.gmail.nishigaki.quarzy.othello.service.component.IsAssignableComponent;
import com.gmail.nishigaki.quarzy.othello.service.util.Util;

/**
 * @author nishigaki
 */
@Component
public class BoardComponentImpl implements BoardComponent {

	@Autowired
	private IsAssignableComponent isAssignableComponent;

	@Override
	public final GameResult invoke(final List<List<PieceValue>> board, final Piece piece) {
		final boolean assigned = assign(board, piece);
		return new GameResult(assigned, board);
	}

	private boolean assign(final List<List<PieceValue>> board, final Piece piece) {
		if (piece.getPiece() == PieceValue.EMPTY || !Util.judge(piece.getX(), piece.getY(), board)) {
			throw new RuntimeException();
		}
		final BiPredicate<IntUnaryOperator, IntUnaryOperator> tryAssign = tryAssign(board, piece);
		final boolean result0 = tryAssign.test(IntUnaryOperator.identity(), y -> y - 1);
		final boolean result1 = tryAssign.test(x -> x + 1, y -> y - 1);
		final boolean result2 = tryAssign.test(x -> x + 1, IntUnaryOperator.identity());
		final boolean result3 = tryAssign.test(x -> x + 1, y -> y + 1);
		final boolean result4 = tryAssign.test(IntUnaryOperator.identity(), y -> y + 1);
		final boolean result5 = tryAssign.test(x -> x - 1, y -> y + 1);
		final boolean result6 = tryAssign.test(x -> x - 1, IntUnaryOperator.identity());
		final boolean result7 = tryAssign.test(x -> x - 1, y -> y - 1);
		if (result0 || result1 || result2 || result3 || result4 || result5 || result6 || result7) {
			board.get(piece.getY()).set(piece.getX(), piece.getPiece());
			return true;
		} else {
			return false;
		}
	}

	private BiPredicate<IntUnaryOperator, IntUnaryOperator> tryAssign(final List<List<PieceValue>> board,
			final Piece piece) {
		return (changeX, changeY) -> {
			if (isAssignableComponent.invoke(board, piece, changeX, changeY)) {
				assign(board, piece, changeX, changeY);
				return true;
			}
			return false;
		};
	}


	private static void assign(final List<List<PieceValue>> board, final Piece piece,
			final IntUnaryOperator changeX, final IntUnaryOperator changeY) {
		int tmpX = changeX.applyAsInt(piece.getX());
		int tmpY = changeY.applyAsInt(piece.getY());
		while (Util.judge(tmpX, tmpY, board) && board.get(tmpY).get(tmpX) != piece.getPiece()) {
			board.get(tmpY).set(tmpX, piece.getPiece());
			tmpX = changeX.applyAsInt(tmpX);
			tmpY = changeY.applyAsInt(tmpY);
		}
	}
}
