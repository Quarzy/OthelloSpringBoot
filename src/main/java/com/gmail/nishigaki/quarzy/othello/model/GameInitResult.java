package com.gmail.nishigaki.quarzy.othello.model;

import java.util.List;

import lombok.Data;

/**
 * @author nishigaki
 */
@Data
public final class GameInitResult {

	public GameInitResult(final String name, final PieceValue pieceValue,
			final List<List<PieceValue>> board) {
		this.name = name;
		this.pieceValue = pieceValue;
		this.board = board;
	}

	private final String name;

	private final PieceValue pieceValue;

	private final List<List<PieceValue>> board;
}
