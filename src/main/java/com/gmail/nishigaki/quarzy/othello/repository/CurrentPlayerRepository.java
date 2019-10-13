package com.gmail.nishigaki.quarzy.othello.repository;

import com.gmail.nishigaki.quarzy.othello.model.PieceValue;

/**
 * @author nishigaki
 */
public interface CurrentPlayerRepository {
	public void replace(String name, PieceValue pieceValue);

	public PieceValue read(String name);
}
