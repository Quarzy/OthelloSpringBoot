package com.gmail.nishigaki.quarzy.othello.model;

import java.util.List;

import lombok.Data;

/**
 * @author nishigaki
 */
@Data
public final class GameResult {

	public GameResult(final boolean assigned, final List<List<PieceValue>> board) {
		this.assigned = assigned;
		this.board = board;
	}

	private final boolean assigned;

	private final List<List<PieceValue>> board;
}
