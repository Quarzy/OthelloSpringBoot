package com.gmail.nishigaki.quarzy.othello.service.component;

import java.util.List;
import java.util.function.IntUnaryOperator;

import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public interface IsAssignableComponent {

	public boolean invoke(List<List<PieceValue>> board, Piece piece,
			IntUnaryOperator changeX, IntUnaryOperator changeY);
}
