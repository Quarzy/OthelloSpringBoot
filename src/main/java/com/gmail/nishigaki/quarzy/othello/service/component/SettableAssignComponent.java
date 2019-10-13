package com.gmail.nishigaki.quarzy.othello.service.component;

import java.util.List;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public interface SettableAssignComponent {
	List<List<PieceValue>> invoke(List<List<PieceValue>> board, PieceValue pieceValue);
}
