package com.gmail.nishigaki.quarzy.othello.service.component.impl;

import java.util.List;
import java.util.function.IntUnaryOperator;

import org.springframework.stereotype.Component;

import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.service.component.IsAssignableComponent;
import com.gmail.nishigaki.quarzy.othello.service.util.Util;

/**
 * @author nishigaki
 */
@Component
public class IsAssignableComponentImpl implements IsAssignableComponent {

	@Override
	public final boolean invoke(final List<List<PieceValue>> board, final Piece piece,
			final IntUnaryOperator changeX, final IntUnaryOperator changeY) {
		int tmpX = changeX.applyAsInt(piece.getX());
		int tmpY = changeY.applyAsInt(piece.getY());
		boolean settable = false;
		while (Util.judge(tmpX, tmpY, board)) {
			final PieceValue tmpPiece = board.get(tmpY).get(tmpX);
			if (tmpPiece == PieceValue.EMPTY || tmpPiece == PieceValue.SETTABLE) {
				return false;
			} else if (tmpPiece == piece.getPiece()) {
				return settable;
			} else {
				settable = true;
				tmpX = changeX.applyAsInt(tmpX);
				tmpY = changeY.applyAsInt(tmpY);
			}
		}
		return false;
	}
}
