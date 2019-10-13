package com.gmail.nishigaki.quarzy.othello.service.component;

import java.util.List;

import com.gmail.nishigaki.quarzy.othello.model.Piece;
import com.gmail.nishigaki.quarzy.othello.model.PieceValue;
import com.gmail.nishigaki.quarzy.othello.model.GameResult;

/**
 * @author nishigaki
 */
public interface BoardComponent {

	public GameResult invoke(List<List<PieceValue>> board, Piece piece);
}
