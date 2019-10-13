package com.gmail.nishigaki.quarzy.othello.model;

import java.util.List;

import lombok.Data;

/**
 * @author nishigaki
 */
@Data
public class GameReadResult {

	public GameReadResult(final boolean player, final PieceValue pieceValue,
			final List<List<PieceValue>> board) {
		this.player = player;
		this.pieceValue = pieceValue;
		this.board = board;
	}

	private final boolean player;

	private final PieceValue pieceValue;

	private final List<List<PieceValue>> board;
}
